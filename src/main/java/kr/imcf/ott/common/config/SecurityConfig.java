package kr.imcf.ott.common.config;

import kr.imcf.ott.account.AccountService;
import kr.imcf.ott.account.oauth2.*;
import kr.imcf.ott.common.props.OAuth2Props;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuth2Props oAuth2Props;

    private final AccountService accountService;

    private final CustomOAuth2UserService customOAuth2UserService;

    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    private final OAuth2LoginFailHanlder oAuth2LoginFailHandler;


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
        http.httpBasic();

        http.logout()
                .deleteCookies("JSESSIONID");

        http.authorizeRequests()
                .mvcMatchers("/", "/signUp", "/access-denied", "/exception/**").permitAll()
                .mvcMatchers("/dashboard").hasRole("USER")
                .mvcMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .expressionHandler(configExpressionHandler());

        http.exceptionHandling()
                .authenticationEntryPoint(configAuthenticationEntryPoint())
                .accessDeniedHandler(configAccessDeniedHandler());


        http.oauth2Login()
                .userInfoEndpoint().userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2LoginSuccessHandler)
                .failureHandler(oAuth2LoginFailHandler)
                .permitAll();

        http.csrf().disable();

        http.logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/index");

    }


    private SecurityExpressionHandler<FilterInvocation> configExpressionHandler() {
        /* ... */
        return null;
    }

    private CustomAuthenticationEntryPoint configAuthenticationEntryPoint() {
        /* ... */
        return null;
    }

    private CustomAccessDeniedHandler configAccessDeniedHandler() {
        /* ... */
        return null;
    }

    private CustomAuthenticationSuccessHandler configSuccessHandler() {
        /* ... */
        return null;
    }

    private CustomAuthenticationFailureHandler configFailureHandler() {
        /* ... */
        return null;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(accountService);
//    }
//
//    // method security에서 authenticationManager를 bean으로 접근 가능하도록
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    /*
//        Oauth2 로그인
//        현재 카카오 로그인까지만 개발완료
//     */
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//
//        List<ClientRegistration> registrations =
//                oAuth2ClientProperties.getRegistration().keySet().stream()
//                        .map(client -> getRegistration(oAuth2ClientProperties, client))
//                        .filter(Objects::nonNull)
//                        .collect(Collectors.toList());
//
//        registrations.add(OAuth2Provider.KAKAO.getBuilder("kakao")
//                .clientId(oAuth2Props.kakaoClientId)
//                .clientSecret(oAuth2Props.kakaoClientSecret)
//                .jwkSetUri("temp")
//                .build());
//
//        registrations.add(OAuth2Provider.NAVER.getBuilder("facebook")
//                .clientId(oAuth2Props.facebookClientId)
//                .clientSecret(oAuth2Props.facebookClientSecret)
//                .jwkSetUri("temp")
//                .build());
//
//        return new InMemoryClientRegistrationRepository(registrations);
//
//    }
//
//    private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String client) {
//        if ("google".equals(client)) {
//            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("google");
//            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
//                    .clientId(registration.getClientId())
//                    .clientSecret(registration.getClientSecret())
//                    .scope("email", "profile")
//                    .build();
//        }
//
//        if ("facebook".equals(client)) {
//            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("facebook");
//            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
//                    .clientId(registration.getClientId())
//                    .clientSecret(registration.getClientSecret())
//                    .userInfoUri("https://graph.facebook.com/me?fields=id,name,email,link")
//                    .scope("email")
//                    .build();
//        }
//
//        return null;
//    }


}