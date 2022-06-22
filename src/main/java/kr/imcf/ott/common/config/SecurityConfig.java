package kr.imcf.ott.common.config;

import kr.imcf.ott.account.CustomOAuth2AuthorizedClientService;
import kr.imcf.ott.account.OAuth2Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    AccountService accountService;

    @Autowired
    CustomOAuth2AuthorizedClientService customOAuth2AuthorizedClientService;

//    @Autowired
//    OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new CustomOAuth2AuthorizedClientService();
    }

    OAuth2ClientProperties oAuth2ClientProperties = new OAuth2ClientProperties();

    // Voter's expression handler custom
    public AccessDecisionManager accessDecisionManager() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);

        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(handler);

        List<AccessDecisionVoter<? extends Object>> voters = Collections.singletonList(webExpressionVoter);
        return new AffirmativeBased(voters);
    }

    @Override
    public void configure(WebSecurity web) {
        String[] prevLogin = {
        };

        // 임시적으로 인증절차 제거
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .mvcMatchers("**")
                .mvcMatchers(prevLogin);
        //web.ignoring().requestMatchers(PathRequest.toH2Console());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(new LoggerFilter(), WebAsyncManagerIntegrationFilter.class);
//
//        http.authorizeRequests()
//                .mvcMatchers("/admin/**", "/manage/**").hasRole("ADMIN")
//                .anyRequest().permitAll()
//                .accessDecisionManager(accessDecisionManager())
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
//                .and()
//                .oauth2Login()
//                .successHandler(oAuth2LoginSuccessHandler);
//
//
//        http.formLogin()
//                .loginPage("/login")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .loginProcessingUrl("/login")
//                .successHandler(new LoginSuccessHandler())
//                .failureHandler(new LoginFailHandler())
//                .permitAll();
//
//        http.csrf().disable();
//
//        http.logout()
//                .logoutUrl("/logout")
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/index");

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(accountService);
//    }

    // method security에서 authenticationManager를 bean으로 접근 가능하도록
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*
        Oauth2 로그인
        현재 카카오 로그인까지만 개발완료
     */
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository(
//            @Value("${spring.security.oauth2.kakao.client-id}") String kakaoClientId,
//            @Value("${spring.security.oauth2.kakao.client-secret}") String kakaoClientSecret,
//            @Value("${spring.security.oauth2.naver.client-id}") String naverClientId,
//            @Value("${spring.security.oauth2.naver.client-secret}") String naverClientSecret) {
//
//        List<ClientRegistration> registrations =
//                oAuth2ClientProperties.getRegistration().keySet().stream()
//                        .map(client -> getRegistration(oAuth2ClientProperties, client))
//                        .filter(Objects::nonNull)
//                        .collect(Collectors.toList());
//
//        registrations.add(OAuth2Provider.KAKAO.getBuilder("kakao")
//                .clientId(kakaoClientId)
//                .clientSecret(kakaoClientSecret)
//                .jwkSetUri("temp")
//                .build());
//
//        registrations.add(OAuth2Provider.NAVER.getBuilder("naver")
//                .clientId(naverClientId)
//                .clientSecret(naverClientSecret)
//                .jwkSetUri("temp")
//                .build());
//
//        return new InMemoryClientRegistrationRepository(registrations);
//
//    }

    private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String client) {
        if ("google".equals(client)) {
            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("google");
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(registration.getClientId())
                    .clientSecret(registration.getClientSecret())
                    .scope("email", "profile")
                    .build();
        }

        if ("facebook".equals(client)) {
            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("facebook");
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId(registration.getClientId())
                    .clientSecret(registration.getClientSecret())
                    .userInfoUri("https://graph.facebook.com/me?fields=id,name,email,link")
                    .scope("email")
                    .build();
        }

        return null;
    }


}