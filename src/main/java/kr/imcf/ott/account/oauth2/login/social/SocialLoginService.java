package kr.imcf.ott.account.oauth2.login.social;

import kr.imcf.ott.account.oauth2.OAuth2LoginResponse;
import kr.imcf.ott.account.oauth2.login.LoginService;

public interface SocialLoginService extends LoginService {

    /**
     * @method : login()
     * @description : Social Login method
     * @author: rojae
     * @date : 2022/07/31
     **/
    OAuth2LoginResponse login(String code);


}
