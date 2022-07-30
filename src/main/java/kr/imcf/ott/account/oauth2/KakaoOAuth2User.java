package kr.imcf.ott.account.oauth2;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoOAuth2User implements CustomOAuth2User {

    private Set<GrantedAuthority> authorities;
    private Map<String, Object> attributes;
    private String nameAttributeKey;

    public String getOAuth2Id() {
        return null;
    }

    public String getEmail() {
        return null;
    }

    public String getNickname() {
        return null;
    }

    public String getNameAttributeKey() {
        return null;
    }

    public Map<String, Object> getAttributes() {
        return null;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getName() {
        return null;
    }
}