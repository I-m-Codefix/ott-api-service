package kr.imcf.ott.common.jwt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import kr.imcf.ott.account.oauth2.OAuth2Principal;
import kr.imcf.ott.common.crypt.AESCrypt;
import kr.imcf.ott.common.props.JwtProps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProps jwtProps;

    public SignatureAlgorithm algorithm() {
        return SignatureAlgorithm.HS256;
    }

    public String dataClaims(OAuth2Principal principal) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AESCrypt aesCrypt = new AESCrypt(jwtProps.jwtCryptSecretKey, jwtProps.jwtCryptIv);
        String data = mapper.writeValueAsString(principal);

        return aesCrypt.encrypt(data);
    }

    public String generateToken(OAuth2Principal principal) {
        Date now = new Date();

        try {
            return Jwts.builder()
                    .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                    .claim(jwtProps.jwtDataClaims, this.dataClaims(principal))          // NOTICE : claims 먼저 셋팅해야함 (그렇지 않을 시, 나머지 데이터 미셋팅 문제 발생)
                    .setIssuer(jwtProps.jwtIssuer)
                    .setIssuedAt(now)
                    .setSubject(jwtProps.jwtSubject)
                    .setExpiration(new Date(now.getTime() + Duration.ofHours(jwtProps.jwtExpireDurationHour).toMillis()))
                    .signWith(this.algorithm(), DatatypeConverter.parseBase64Binary(jwtProps.jwtSecretKey))
                    .compact();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getSubject(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtProps.jwtSecretKey))
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public Date getExpiration(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtProps.jwtSecretKey))
                .parseClaimsJws(token).getBody();
        System.out.println(claims.toString());

        return claims.getExpiration();
    }

    public String get(String token, String key) {
        return Jwts.parser()
                .setSigningKey((DatatypeConverter.parseBase64Binary(jwtProps.jwtSecretKey)))
                .parseClaimsJwt(token)
                .getBody()
                .get(key, String.class);
    }

    public boolean verify(String token){
        Map<String, Object> claimMap = null;
        try {
            claimMap = Jwts.parser()
                    .setSigningKey(jwtProps.jwtSecretKey)
                    .parseClaimsJws(token) // Parsing, Verify
                    .getBody();
        } catch (ExpiredJwtException e) {
            // token Expired
            e.getCause();
            e.printStackTrace();
        }
        return claimMap != null;
    }
}
