package kr.imcf.ott.domain.redis;


import kr.imcf.ott.common.props.JwtProps;
import kr.imcf.ott.common.type.PlatformType;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

@EqualsAndHashCode
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "RAccount")
public class RAccount implements Serializable {

    @Id
    private String id;

    private String name;

    private String profileImage;

    private String accessToken;

    public static String idFormat(PlatformType platformType, String email) {
        return String.format("%s:%s",platformType.name(),email);
    }

    @TimeToLive
    public long getTimeToLive() {
        return JwtProps.jwtExpireDurationHour * 3600;
    }


}
