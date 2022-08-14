package kr.imcf.ott.account.oauth2;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomTokenAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        return authentication;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return OAuth2Principal.class.isAssignableFrom(authentication);
    }
}