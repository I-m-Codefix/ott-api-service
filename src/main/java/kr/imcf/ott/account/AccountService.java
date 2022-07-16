package kr.imcf.ott.account;

import kr.imcf.ott.common.type.PlatformType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import kr.imcf.ott.domain.entity.Account;
import kr.imcf.ott.persistence.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = false)
    public boolean signup(SignupRequest request){
        // 회원가입에 대한 프로세스
        Account account = new Account();
        if(accountRepository.existsByEmail(request.getEmail()))
            return false;
        account.setName(request.getName());
        account.setPassword(request.getPassword());
        account.setEmail(request.getEmail());
        account.setPlatformType(request.getPlatformType());
        account.setProfileImage(request.getPlatformImage());
        accountRepository.save(account);

        return true;
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
    public MyInfoResponse showMyInfo(String email) {

        // 내정보 조회
        Account account = accountRepository.findByEmail(email);

        MyInfoResponse myInfoResponse = new MyInfoResponse();
        myInfoResponse.setName(account.getName());
        myInfoResponse.setEmail(account.getEmail());
        myInfoResponse.setProfileImage(account.getProfileImage());
        myInfoResponse.setPlatformType(account.getPlatformType().toString());

        return myInfoResponse;
    }

    @Transactional(readOnly = false)
    public boolean editMyInfo(MyInfoEditRequest request){

        // 내 정보 수정
        Account updateAccount = accountRepository.findByEmail(request.getEmail());

        if(updateAccount != null) {
            updateAccount.setName(request.getName());
            updateAccount.setProfileImage(request.getProfileImage());
            return true;
        }
        return false;
    }

}
