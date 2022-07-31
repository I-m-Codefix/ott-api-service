package kr.imcf.ott.account.oauth2.social.kakao;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class KakaoTokenWrapper {
    private String token_type;
    private String access_token;
    private String refresh_token;
    private String scope;
}
