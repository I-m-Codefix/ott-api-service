package kr.imcf.ott.account.platform;

import kr.imcf.ott.common.type.PlatformType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.util.Map;

public class KakaoUser {

    private Map<String, Object> attributes;
    private OAuth2AccessToken accessToken;
    private String role;

    public KakaoUser(Map<String, Object> attributes, OAuth2AccessToken accessToken, String role) {
        this.attributes = attributes;
        this.accessToken = accessToken;
        this.role = role;
    }

    public String getUserName() {
        return ((Map<String, Object>) (((Map<String, Object>) attributes.get("kakao_account")).get("profile"))).get("nickname").toString();
    }

    public String getEmail() {
        return ((Map<String, Object>) attributes.get("kakao_account")).get("email").toString();
    }

    public String getProfileImage() {
        if(((Map<String, Object>) (((Map<String, Object>) attributes.get("kakao_account")).get("profile"))).get("profile_image_url") == null)
            return "";
        return ((Map<String, Object>) (((Map<String, Object>) attributes.get("kakao_account")).get("profile"))).get("profile_image_url").toString();
    }

    public OAuth2AccessToken getAccessToken() {
        return this.accessToken;
    }

    public PlatformType getPlatformType() {
        return PlatformType.KAKAO;
    }

    public String getRole(){
        return this.role;
    }
}