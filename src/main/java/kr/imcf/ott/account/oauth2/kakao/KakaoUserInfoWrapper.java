package kr.imcf.ott.account.oauth2.kakao;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class KakaoUserInfoWrapper {
    public long id;
    public Date connected_at;
    public Properties properties;
    public KakaoAccount kakao_account;
}

@Getter
@Setter
@NoArgsConstructor
@ToString
class KakaoAccount{
    public boolean profile_nickname_needs_agreement;
    public boolean profile_image_needs_agreement;
    public Profile profile;
    public boolean has_email;
    public boolean email_needs_agreement;
    public boolean is_email_valid;
    public boolean is_email_verified;
    public String email;
    public boolean has_age_range;
    public boolean age_range_needs_agreement;
    public String age_range;
    public boolean has_birthday;
    public boolean birthday_needs_agreement;
    public String birthday;
    public String birthday_type;
    public boolean has_gender;
    public boolean gender_needs_agreement;
    public String gender;
}

@Getter
@Setter
@NoArgsConstructor
@ToString
class Profile{
    public String nickname;
    public String thumbnail_image_url;
    public String profile_image_url;
    public boolean is_default_image;
}

@Getter
@Setter
@NoArgsConstructor
@ToString
class Properties{
    public String nickname;
    public String profile_image;
    public String thumbnail_image;
}
