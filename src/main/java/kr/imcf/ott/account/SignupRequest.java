package kr.imcf.ott.account;

import kr.imcf.ott.common.type.PlatformType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    private String name;
    private String password;
    private String email;
    private PlatformType platformType;
    private String platformImage;       // ImageFile encoded base64
}
