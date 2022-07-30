package kr.imcf.ott.account.oauth2.kakao;

import com.nimbusds.jwt.util.DateUtils;
import kr.imcf.ott.account.oauth2.OAuth2LoginResponse;
import kr.imcf.ott.account.oauth2.OAuth2Principal;
import kr.imcf.ott.common.http.HttpHeader;
import kr.imcf.ott.common.http.RestProvider;
import kr.imcf.ott.common.jwt.JwtProvider;
import kr.imcf.ott.common.props.OAuth2Props;
import kr.imcf.ott.common.type.PlatformType;
import kr.imcf.ott.common.util.TimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoService {

    private final RestProvider restProvider;
    private final JwtProvider jwtProvider;
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

        // STEP 3 : Generate JWT Token including User's Info

        // Create User's Principal Data (토큰에 저장될 데이터)
        OAuth2Principal oAuth2Principal = OAuth2Principal.builder()
                .name(Objects.requireNonNull(userInfoResponse.getBody()).kakao_account.profile.nickname)
                .email(userInfoResponse.getBody().kakao_account.email)
                .platformType(PlatformType.KAKAO)
                .profileImage(userInfoResponse.getBody().kakao_account.profile.profile_image_url)       // 640 X 640
                .build();

        // Generate JWT Token
        String jwtToken = jwtProvider.generateToken(oAuth2Principal);           // TO DO : AES 256 Crypt

        // Generated JWT Token Verification
        try {
            jwtProvider.verify(jwtToken);
        } catch (Exception e) {
            log.error("발급할 토큰에 검증에 실패했습니다.");
            e.printStackTrace();
        }

        log.info("JWT Token :: {}", jwtToken);

        OAuth2LoginResponse oAuth2LoginResponse = OAuth2LoginResponse.builder()
                .name(Objects.requireNonNull(userInfoResponse.getBody()).kakao_account.profile.nickname)
                .email(userInfoResponse.getBody().kakao_account.email)
                .platformType(PlatformType.KAKAO)
                .profileImage(userInfoResponse.getBody().kakao_account.profile.profile_image_url)       // 640 X 640
                .token(jwtToken)
                .expireTime(TimeUtils.dateFomat(jwtProvider.getExpiration(jwtToken), "yyyy.MM.dd HH:mm:ss"))
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
