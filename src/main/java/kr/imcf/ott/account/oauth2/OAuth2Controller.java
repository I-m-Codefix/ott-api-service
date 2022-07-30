package kr.imcf.ott.account.oauth2;

import kr.imcf.ott.common.Message;
import kr.imcf.ott.common.props.OAuth2Props;
import kr.imcf.ott.common.type.PlatformType;
import kr.imcf.ott.common.util.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2Props oAuth2Props;

    @GetMapping("/info/oauth2/kakao/client-id")
    public ResponseEntity<OAuth2ClientInfoWrapper> kakaoClientId(){
        OAuth2ClientInfoWrapper kakaoClientInfoWrapper = OAuth2ClientInfoWrapper.builder()
                .clientId(oAuth2Props.kakaoClientId)
                .redirectUri(oAuth2Props.kakaoRedirectUri)
                .platformType(PlatformType.KAKAO)
                .uri(String.format("%s?client_id=%s&redirect_uri=%s&response_type=%s",
                        oAuth2Props.kakaoAuthUri,oAuth2Props.kakaoClientId, oAuth2Props.kakaoRedirectUri, "code"))
                .reqTime(TimeUtils.now()).build();

        return new ResponseEntity<OAuth2ClientInfoWrapper>(kakaoClientInfoWrapper, HttpStatus.OK);
    }

}