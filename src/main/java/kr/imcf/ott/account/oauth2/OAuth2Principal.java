package kr.imcf.ott.account.oauth2;

import kr.imcf.ott.common.type.PlatformType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OAuth2Principal{
    private String name;
    private PlatformType platformType;
    private String email;
    private String profileImage;
}
