package kr.imcf.ott.account;

import kr.imcf.ott.common.type.PlatformType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    private String name;
    private String email;

    public Map<String, Object> toMap() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", name);
        claims.put("email", email);
        return claims;
    }
}
