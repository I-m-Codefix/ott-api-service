package kr.imcf.ott.account.oauth2.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class KakaoTokenWrapper {
    private String token_type;
    private String access_token;
    private String refresh_token;
}
