package kr.imcf.ott.account.oauth2;

import kr.imcf.ott.common.type.PlatformType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OAuth2ClientInfoWrapper {
    private PlatformType platformType;
    private String reqTime;
    private String uri;
    private QueryString queryString;

    @Builder
    @Getter
    static class QueryString{
        private String client_id;
        private String redirect_uri;
        private String response_type;
    }
}
