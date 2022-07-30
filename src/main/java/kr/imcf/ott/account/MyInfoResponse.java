package kr.imcf.ott.account;

import kr.imcf.ott.common.Message;
import kr.imcf.ott.common.type.PlatformType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MyInfoResponse extends Message {
    private String name;
    private String email;
    private String platformType;
    private String profileImage;
}
