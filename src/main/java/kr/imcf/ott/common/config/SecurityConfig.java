package kr.imcf.ott.common.config;

import kr.imcf.ott.account.oauth2.CustomAccessDeniedHandler;
import kr.imcf.ott.account.oauth2.CustomAuthenticationEntryPoint;
import kr.imcf.ott.account.oauth2.CustomTokenAuthenticationProvider;
import kr.imcf.ott.common.filter.JwtAuthenticationFilter;
import kr.imcf.ott.common.jwt.JwtProvider;
import kr.imcf.ott.common.props.JwtProps;
import kr.imcf.ott.common.props.SecurityProps;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;
    private final JwtProps jwtProps;
    private final SecurityProps securityProps;

    @Override
    public void configure(WebSecurity web) {
        String[] staticResources = {
        };

        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .mvcMatchers(securityProps.IgnoreMatchers)
                .mvcMatchers(staticResources);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider, jwtProps), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint(jwtProvider, jwtProps))
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
//              .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .authenticationProvider(new CustomTokenAuthenticationProvider())
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}

