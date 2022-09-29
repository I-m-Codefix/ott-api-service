package kr.imcf.ott.account.oauth2.logout;

public interface LogoutService {
    /**
    * @methodName: logout
    * @author: rojae
    * @date: 2022/09/24
    * @Description: 로그아웃 서비스 (소셜, 서비스 공통)
    */
    boolean logout(String token);
}
