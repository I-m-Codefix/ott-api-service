package kr.imcf.ott.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupValidRequest {
    private String email;
    private String secretKey;
}
