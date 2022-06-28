package kr.imcf.ott.account;

import kr.imcf.ott.common.type.PlatformType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    public UserDetails loadUserByUsername(String s, PlatformType platformType) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    @Transactional(readOnly = false)
    public boolean signup(){
        // 회원가입에 대한 프로세스
        return false;
    }

    @Transactional(readOnly = false)
    public boolean sendMailForSignup() {
        // Java mail 메일전송
        return false;
    }

    @Transactional(readOnly = true)
    public boolean validCheckForSignup(){
        // Mail 유효성 체크 (비밀키, 계정 1대1)
        return false;
    }

    @Transactional(readOnly = true)
    public MyInfoResponse showMyInfo() {
        // 내정보 조회
        return null;
    }

    @Transactional(readOnly = false)
    public boolean editMyInfo(){
        // 내 정보 수정
        return true;
    }


}
