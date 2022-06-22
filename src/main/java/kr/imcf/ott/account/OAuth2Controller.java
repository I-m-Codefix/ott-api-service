package kr.imcf.ott.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class OAuth2Controller {

    @GetMapping("/login/oauth2/code/kakao")
    public String callback_KAKAO(HttpServletResponse response){
        return "redirect:/index";
    }

}