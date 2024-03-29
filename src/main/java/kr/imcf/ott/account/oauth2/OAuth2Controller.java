package kr.imcf.ott.account.oauth2;

import kr.imcf.ott.account.oauth2.login.nonsocial.NonSocialLoginService;
import kr.imcf.ott.account.oauth2.login.nonsocial.ServiceLoginRequest;
import kr.imcf.ott.account.oauth2.login.social.kakao.KakaoService;
import kr.imcf.ott.account.oauth2.logout.LogoutService;
import kr.imcf.ott.common.Message;
import kr.imcf.ott.common.props.JwtProps;
import kr.imcf.ott.common.props.OAuth2Props;
import kr.imcf.ott.common.type.PlatformType;
import kr.imcf.ott.common.util.ScriptUtils;
import kr.imcf.ott.common.util.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Controller
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2Props oAuth2Props;
    private final NonSocialLoginService nonSocialLoginService;
    private final KakaoService kakaoService;
    private final JwtProps jwtProps;
    private final LogoutService logoutService;

    /**
    * @methodName: kakaoClientId
    * @author: rojae
    * @date: 2022/09/24
    * @Description: 카카오 로그인 요청을 위한 정보 요청
    */
    @GetMapping("/info/oauth2/kakao/client-id")
    public ResponseEntity<OAuth2ClientInfoWrapper> kakaoClientId() {
        OAuth2ClientInfoWrapper kakaoClientInfoWrapper = null;

        kakaoClientInfoWrapper = OAuth2ClientInfoWrapper.builder()
                .clientId(oAuth2Props.kakaoClientId)
                .redirectUri(oAuth2Props.kakaoRedirectUri)
                .platformType(PlatformType.KAKAO)
                .uri(String.format("%s?client_id=%s&redirect_uri=%s&response_type=%s",
                        oAuth2Props.kakaoAuthUri, oAuth2Props.kakaoClientId, oAuth2Props.kakaoRedirectUri, "code"))
                .reqTime(TimeUtils.now()).build();

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
     * @methodName: logOut
     * @author: rojae
     * @date: 2022/09/24
     * @Description: 로그아웃 API
     */
    @GetMapping("/login/oauth2/logout")
    public ResponseEntity<Message> logout(HttpServletRequest httpServletRequest){
        if(logoutService.logout(httpServletRequest.getHeader(jwtProps.jwtHeaderName)))
            return new ResponseEntity<>(Message.builder().code(200).response("로그아웃에 성공했습니다").build(), HttpStatus.OK);
        else
            return new ResponseEntity<>(Message.builder().code(403).response("로그아웃에 실패했습니다").build(), HttpStatus.OK);
    }

    /**
     * @method : kakaoLogin
     * @description : KAKAO 로그인은 브라이저의 Redirect로 이루어지기 때문에, GET으로 구현해야함
     * @author: rojae
     * @date : 2022/08/13
     **/
    @GetMapping("/login/oauth2/kakao")
    public ResponseEntity<OAuth2LoginResponse> kakaoLogin(HttpServletResponse servletResponse, @RequestParam String code) throws IOException, InvocationTargetException, IllegalAccessException {
        OAuth2LoginResponse response = kakaoService.login(code);
        ScriptUtils.sendJsonData(servletResponse, oAuth2Props.kakaoEndPoint, response);
        return null;
    }

}