package kr.imcf.ott.account.oauth2.kakao;

import kr.imcf.ott.common.http.HttpHeader;
import kr.imcf.ott.common.http.RestProvider;
import kr.imcf.ott.common.props.OAuth2Props;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class KakaoService {
    private final RestProvider restProvider;
    private final OAuth2Props oAuth2Props;

    public ResponseEntity<KakaoTokenResponse> getKakaoToken(String code){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("client_id", Collections.singletonList(oAuth2Props.kakaoClientId));
        params.put("client_secret", Collections.singletonList(oAuth2Props.kakaoClientSecret));
        params.put("redirect_uri", Collections.singletonList(oAuth2Props.kakaoRedirectUri));
        params.put("grant_type", Collections.singletonList(oAuth2Props.kakaoAuthGrantType));
        params.put("code", Collections.singletonList(code));

        return restProvider.send(HttpMethod.POST, oAuth2Props.kakaoTokenUri, params
                , new HttpHeader(MediaType.APPLICATION_FORM_URLENCODED), KakaoTokenResponse.class);
    }
}
