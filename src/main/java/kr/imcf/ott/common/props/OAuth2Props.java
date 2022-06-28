package kr.imcf.ott.common.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OAuth2Props {

    @Value("${spring.security.oauth2.kakao.client-id}")
    public String kakaoClientId;

    @Value("${spring.security.oauth2.kakao.client-secret}")
    public String kakaoClientSecret;

    @Value("${spring.security.oauth2.facebook.client-id}")
    public String facebookClientId;

    @Value("${spring.security.oauth2.facebook.client-secret}")
    public String facebookClientSecret;

}
