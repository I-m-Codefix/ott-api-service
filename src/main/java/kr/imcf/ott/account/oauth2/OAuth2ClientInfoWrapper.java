package kr.imcf.ott.account.oauth2;

import kr.imcf.ott.common.type.PlatformType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OAuth2ClientInfoWrapper {
    private PlatformType platformType;
    private String clientId;
    private String redirectUri;
    private String reqTime;
    private String uri;
}
