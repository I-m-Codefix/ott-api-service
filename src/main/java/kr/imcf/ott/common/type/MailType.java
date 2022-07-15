package kr.imcf.ott.common.type;

import lombok.Getter;

// 인증메일 타입 (SIGNUP : 회원가입, RESETPWD : 비밀번호 변경)
@Getter
public enum MailType {
    SIGNUP,
    RESETPWD,
}
