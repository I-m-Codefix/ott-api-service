package kr.imcf.ott.domain.redis;


import kr.imcf.ott.common.type.PlatformType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "RAccount", timeToLive = 1800)
public class RAccount {
    @Id
    private Long id;

    private String name;

    private String email;

    private char isAuth;

    private PlatformType platformType;

    private String profileImage;

    private String accessToken;

    private String refreshToken;
}
