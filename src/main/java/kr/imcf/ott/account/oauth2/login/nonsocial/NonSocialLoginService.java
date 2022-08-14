package kr.imcf.ott.account.oauth2.login.nonsocial;

import kr.imcf.ott.account.oauth2.OAuth2LoginResponse;
import kr.imcf.ott.account.oauth2.login.LoginService;

public interface NonSocialLoginService extends LoginService {

    OAuth2LoginResponse login(String email, String password);


}
