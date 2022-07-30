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
        return OAuth2Principal.builder()
                .name(this.name)
                .platformType(this.platformType)
                .email(this.email)
                .profileImage(this.profileImage)
                .build();
    }
}
