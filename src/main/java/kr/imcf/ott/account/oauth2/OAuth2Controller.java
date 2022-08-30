package kr.imcf.ott.account.oauth2;

import kr.imcf.ott.account.oauth2.login.nonsocial.NonSocialLoginService;
import kr.imcf.ott.account.oauth2.login.nonsocial.ServiceLoginRequest;
import kr.imcf.ott.account.oauth2.login.social.kakao.KakaoService;
import kr.imcf.ott.common.props.OAuth2Props;
import kr.imcf.ott.common.type.PlatformType;
import kr.imcf.ott.common.util.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2Props oAuth2Props;
    private final NonSocialLoginService nonSocialLoginService;
    private final KakaoService kakaoService;

    @GetMapping("/info/oauth2/kakao/client-id")
    public ResponseEntity<OAuth2ClientInfoWrapper> kakaoClientId(@RequestParam String redirect_uri){
        OAuth2ClientInfoWrapper kakaoClientInfoWrapper = null;

        if(redirect_uri == null || redirect_uri.equals("")) {
            kakaoClientInfoWrapper = OAuth2ClientInfoWrapper.builder()
                    .clientId(oAuth2Props.kakaoClientId)
                    .redirectUri(oAuth2Props.kakaoRedirectUri)
                    .platformType(PlatformType.KAKAO)
                    .uri(String.format("%s?client_id=%s&redirect_uri=%s&response_type=%s",
                            oAuth2Props.kakaoAuthUri, oAuth2Props.kakaoClientId, oAuth2Props.kakaoRedirectUri, "code"))
                    .reqTime(TimeUtils.now()).build();
        }
        else{
            kakaoClientInfoWrapper = OAuth2ClientInfoWrapper.builder()
                    .clientId(oAuth2Props.kakaoClientId)
                    .redirectUri(redirect_uri)
                    .platformType(PlatformType.KAKAO)
                    .uri(String.format("%s?client_id=%s&redirect_uri=%s&response_type=%s",
                            oAuth2Props.kakaoAuthUri, oAuth2Props.kakaoClientId, redirect_uri, "code"))
                    .reqTime(TimeUtils.now()).build();
        }

        return new ResponseEntity<>(kakaoClientInfoWrapper, HttpStatus.OK);
    }

    /**
     * @method : serviceLogin
     * @description : 소셜 로그인이 아닌 서비스 회원 전용 로그인
     * @author: rojae
     * @date : 2022/08/13
     **/
    @PostMapping("/login/oauth2/service")
    public ResponseEntity<OAuth2LoginResponse> serviceLogin(@RequestBody ServiceLoginRequest request) {
        OAuth2LoginResponse response = nonSocialLoginService.login(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @method : kakaoLogin
     * @description : KAKAO 로그인은 브라이저의 Redirect로 이루어지기 때문에, GET으로 구현해야함
     * @author: rojae
     * @date : 2022/08/13
     **/
    @GetMapping("/login/oauth2/kakao")
    public ResponseEntity<OAuth2LoginResponse> kakaoLogin(@RequestParam String code){
        OAuth2LoginResponse response = kakaoService.login(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}