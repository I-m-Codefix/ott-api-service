package kr.imcf.ott.account.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String secretKey;

    public String generateRefreshToken(CustomOAuth2User customOAuth2User) {
        /* ... */
        return "";
    }

    public String generateAccessToken(String refreshToken) {
        /* ... */
        return "";
    }

    public String resolveToken(HttpServletRequest request) {
        return "";
    }

    public Authentication getAuthentication(String accessToken) {
        /* ... */
        return null;
    }

}

