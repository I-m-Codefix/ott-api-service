package kr.imcf.ott.common.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OAuth2Props {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    public String kakaoClientId;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    public String kakaoClientSecret;

    @Value("${spring.security.oauth2.client.registration.kakao.scope}")
    public String kakaoScope;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    public String kakaoRedirectUri;

    @Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
    public String kakaoAuthGrantType;

    @Value("${spring.security.oauth2.client.registration.kakao.client-name}")
    public String kakaoClientName;

    @Value("${spring.security.oauth2.client.registration.kakao.client-authentication-method}")
    public String kakaoAuthMethod;

    @Value("${spring.security.oauth2.client.provider.kakao.authorization-uri}")
    public String kakaoAuthUri;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    public String kakaoTokenUri;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    public String kakaoUserInfoUri;

    @Value("${spring.security.oauth2.client.provider.kakao.user-name-attribute}")
    public String kakaoUserNameAttribute;

    @Value("${spring.security.oauth2.client.registration.kakao.end-point}")
    public String kakaoEndPoint;
}
