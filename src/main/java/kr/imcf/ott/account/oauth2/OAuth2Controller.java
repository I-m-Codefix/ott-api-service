package kr.imcf.ott.account.oauth2;

import kr.imcf.ott.common.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class OAuth2Controller {

    @GetMapping("/login/oauth2/code/kakao")
    public Message loginByKakao(HttpServletResponse response){
        return null;
    }

}