package kr.imcf.ott.common.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProps {
    @Value("${oauth2.jwt.issuer}")
    public String jwtIssuer;

    @Value("${oauth2.jwt.subject}")
    public String jwtSubject;

    @Value("${oauth2.jwt.data-claims-name}")
    public String jwtDataClaims;

    @Value("${oauth2.jwt.secret-key}")
    public String jwtSecretKey;

    @Value("${oauth2.jwt.expire-duration-hour}")
    public long jwtExpireDurationHour;

    @Value("${oauth2.jwt.crypt.algorithm}")
    public String jwtCryptAlgorithm;

    @Value("${oauth2.jwt.crypt.algorithm}")
    public String jwtCryptSecretKey;
}
