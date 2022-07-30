package kr.imcf.ott.account.oauth2.kakao;

import kr.imcf.ott.account.oauth2.OAuth2LoginResponse;
import kr.imcf.ott.common.http.HttpHeader;
import kr.imcf.ott.common.http.RestProvider;
import kr.imcf.ott.common.props.OAuth2Props;
import kr.imcf.ott.common.type.PlatformType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoService {
    private final RestProvider restProvider;
    private final OAuth2Props oAuth2Props;

    public OAuth2LoginResponse kakaoLogin(String code) {
        log.info("START KAKAO LOGIN :: CODE => {}", code);

        // STEP 1 : KAKAO TOKEN
        ResponseEntity<KakaoTokenWrapper> tokenResponse = this.getKakaoToken(code);
        log.info("STEP 1 : KAKAO TOKEN => {}", tokenResponse.getBody());

        // STEP 2 : KAKAO USER INFO
        ResponseEntity<KakaoUserInfoWrapper> userInfoResponse =
                this.getKakaoUserInfo(Objects.requireNonNull(tokenResponse.getBody()).getAccess_token());
        log.info("STEP 2 : KAKAO USER INFO => {}", userInfoResponse.getBody());

        // STEP 3 : JWT SERVICE TOKEN
        String jwtToken = "JWT_TOKEN";      // TO DO : Implement to JWT AccessToken including User's Info

        OAuth2LoginResponse oAuth2LoginResponse = OAuth2LoginResponse.builder()
                .name(Objects.requireNonNull(userInfoResponse.getBody()).kakao_account.profile.nickname)
                .email(userInfoResponse.getBody().kakao_account.email)
                .platformType(PlatformType.KAKAO)
                .profileImage(userInfoResponse.getBody().kakao_account.profile.profile_image_url)       // 640 X 640
                .token(jwtToken)
                .expireTime("EXPIRE_DATE")
                .build();

        log.info("END KAKAO LOGIN :: CODE => {}", code);
        return oAuth2LoginResponse;

    }

    public ResponseEntity<KakaoTokenWrapper> getKakaoToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("client_id", Collections.singletonList(oAuth2Props.kakaoClientId));
        params.put("client_secret", Collections.singletonList(oAuth2Props.kakaoClientSecret));
        params.put("redirect_uri", Collections.singletonList(oAuth2Props.kakaoRedirectUri));
        params.put("grant_type", Collections.singletonList(oAuth2Props.kakaoAuthGrantType));
        params.put("code", Collections.singletonList(code));

        return restProvider.send(HttpMethod.POST, oAuth2Props.kakaoTokenUri, params
                , new HttpHeader(MediaType.APPLICATION_FORM_URLENCODED), KakaoTokenWrapper.class);
    }

    public ResponseEntity<KakaoUserInfoWrapper> getKakaoUserInfo(String accessToken) {
        HttpHeader headers = new HttpHeader(MediaType.APPLICATION_FORM_URLENCODED);
        headers.addHeader("Authorization", String.format("Bearer %s", accessToken));
        headers.addHeader("charset", "utf8");

        return restProvider.send(HttpMethod.POST, oAuth2Props.kakaoUserInfoUri, headers, KakaoUserInfoWrapper.class);
    }

}
