package kr.imcf.ott.account.oauth2;

import kr.imcf.ott.common.type.PlatformType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuth2LoginResponse {
    private String name;
    private PlatformType platformType;
    private String email;
    private String profileImage;
    private String token;
    private String expireTime;

    public OAuth2Principal toPrincipal() {
        OAuth2Principal oAuth2Principal = new OAuth2Principal();
        oAuth2Principal.setName(this.name);
        oAuth2Principal.setEmail(this.email);
        oAuth2Principal.setPlatformType(this.platformType);
        oAuth2Principal.setProfileImage(this.profileImage);
        return oAuth2Principal;
    }
}
