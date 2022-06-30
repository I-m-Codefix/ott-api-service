package kr.imcf.ott.account.oauth2;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class OAuth2LoginFailHanlder extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        logger.info("login fail handler");

        String errorMessage = "";

        if (e instanceof BadCredentialsException || e instanceof InternalAuthenticationServiceException) {
            errorMessage = this.notEqualUserOrPassword();
        } else if (e instanceof UsernameNotFoundException) {
            errorMessage = this.notExistUser();
        } else {
            errorMessage = this.notLoginByUnknown();
        }
        errorMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        setDefaultFailureUrl("/login?error=true&exception=" + errorMessage);
        super.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
    }

    private String notLoginByUnknown() {
        return "";
    }

    private String notExistUser() {
        return "";
    }

    private String notEqualUserOrPassword() {
        return "";
    }


}
