package kr.imcf.ott.account.oauth2;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken oAuth2AuthenticationToken = checkInvalidAuthenticationToken(authentication);
        CustomOAuth2User principal = checkInvalidPrincipal(oAuth2AuthenticationToken.getPrincipal());

        joinNewMember(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(), principal);

        clearAuthenticationAttributes(request);
        getRedirectStrategy().sendRedirect(request, response, "/auth/success");
    }

    private OAuth2AuthenticationToken checkInvalidAuthenticationToken(Authentication authentication) {
        return null;
    }

    private void joinNewMember(String authorizedClientRegistrationId, CustomOAuth2User principal) {
    }

    private CustomOAuth2User checkInvalidPrincipal(OAuth2User principal) {
        return null;
    }

}


