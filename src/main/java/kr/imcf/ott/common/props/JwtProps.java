package kr.imcf.ott.common.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProps {

    @Value("${oauth2.header.name}")
    public String jwtHeaderName;

    @Value("${oauth2.jwt.issuer}")
    public String jwtIssuer;

    @Value("${oauth2.jwt.subject}")
    public String jwtSubject;

    @Value("${oauth2.jwt.data-claims-name}")
    public String jwtDataClaims;

    @Value("${oauth2.jwt.secret-key}")
    public String jwtSecretKey;

    // jwtExpireDurationHour :: Redis에서 TimeToLive 셋팅을 위해서 static 선언
    public static long jwtExpireDurationHour;

    @Value("${oauth2.jwt.expire-duration-hour}")
    public void setJwtExpireDurationHour(long value){
        jwtExpireDurationHour = value;
    }

    @Value("${oauth2.jwt.crypt.secret-key}")
    public String jwtCryptSecretKey;

    @Value("${oauth2.jwt.crypt.iv}")
    public String jwtCryptIv;
}
