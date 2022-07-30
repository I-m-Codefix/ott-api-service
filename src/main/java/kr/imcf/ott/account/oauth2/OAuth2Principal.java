package kr.imcf.ott.account.oauth2;

import kr.imcf.ott.common.type.PlatformType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OAuth2Principal {
    private String name;
    private PlatformType platformType;
    private String email;
    private String profileImage;
}
