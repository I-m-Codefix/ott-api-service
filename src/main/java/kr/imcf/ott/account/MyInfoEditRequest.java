package kr.imcf.ott.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyInfoEditRequest {
    private String name;
    private String email;
    private String profileImage;
}
