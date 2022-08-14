package kr.imcf.ott.account.oauth2.login;

import kr.imcf.ott.account.oauth2.OAuth2Principal;

public interface LoginService {

    /**
     * @method : publishToken()
     * @description : JWT Token Create, Save
     * @author: rojae
     * @date : 2022/07/31
     **/
    String publishToken(OAuth2Principal oAuth2Principal);

    /**
     * @method : generateToken()
     * @description : JWT Token Create
     * @author: rojae
     * @date : 2022/07/31
     **/
    String generateToken(OAuth2Principal oAuth2Principal);

    /**
     * @method : SaveDB()
     * @description : UserInfo Save to Database user table
     * @author: rojae
     * @date : 2022/07/31
     **/
    boolean saveDB(OAuth2Principal oAuth2Principal, String token);

    /**
     * @method : saveRedis()
     * @description : Token Save to Redis server
     * @author: rojae
     * @date : 2022/07/31
     **/
    boolean saveRedis(OAuth2Principal oAuth2Principal, String token);
}
