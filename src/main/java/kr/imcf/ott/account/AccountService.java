package kr.imcf.ott.account;

import kr.imcf.ott.common.type.MailType;
import kr.imcf.ott.domain.entity.Account;
import kr.imcf.ott.domain.entity.Mail;
import kr.imcf.ott.mail.MailService;
import kr.imcf.ott.persistence.repository.AccountRepository;
import kr.imcf.ott.persistence.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    //JPA repository 사용
    private final AccountRepository accountRepository;
    private final MailRepository mailRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = false)
    public boolean signup(SignupRequest request){
        // 회원가입에 대한 프로세스
        Account account = new Account();

        if(accountRepository.existsByEmail(request.getEmail()))
            return false;

        account.setName(request.getName());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setEmail(request.getEmail());
        account.setPlatformType(request.getPlatformType());
        account.setProfileImage(request.getPlatformImage());
        accountRepository.save(account);

        if(!sendMailForSignup(account))
            return false;

        return true;
    }

    private boolean sendMailForSignup(Account selectedAccount) {

        Mail newMail = Mail.builder()
                .mailType(MailType.SIGNUP)
                .email(selectedAccount.getEmail())
                .account(selectedAccount)
                .expireDate(LocalDateTime.now().plusMinutes(10))
                .secretKey(UUID.randomUUID().toString())
                .build();

        try {
            mailRepository.save(newMail);
            mailService.signupSend(newMail);
        }
        catch (Exception e){
            System.out.println("인증 메일 전송을 실패하였습니다. : "+e.toString());
            return false;
        }

        return true;
    }

    public boolean alreadyValidCheckMail(SignupValidRequest request){
        return mailRepository.existsBySecretKeyAndEmailAndIsAuth(request.getSecretKey(), request.getEmail(), 'Y');
    }

    @Transactional(readOnly = false)
    public boolean validCheckForSignup(SignupValidRequest request){
        Optional<Mail> selectedMail = mailRepository.findBySecretKeyAndEmail(request.getSecretKey(), request.getEmail());
        if(selectedMail.isEmpty()){
            return false;
        }
        else{
            Account selectedAccount = accountRepository.findByEmail(request.getEmail());
            selectedMail.get().setIsAuth('Y');
            selectedAccount.setIsAuth('Y');
            return true;
        }
    }

    @Transactional(readOnly = true)
    public MyInfoResponse showMyInfo(String email) {

        // 내정보 조회
        Account account = accountRepository.findByEmail(email);

        MyInfoResponse myInfoResponse = new MyInfoResponse();
        myInfoResponse.setId(account.getId());
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

    public MyInfoResponse showIdInfo(Long id){

        Account account = accountRepository.findById(id).get();
        MyInfoResponse myInfoResponse = new MyInfoResponse();
        myInfoResponse.setId(id);
        myInfoResponse.setName(account.getName());
        myInfoResponse.setEmail(account.getEmail());
        myInfoResponse.setProfileImage(account.getProfileImage());
        myInfoResponse.setPlatformType(account.getPlatformType().toString());

        return myInfoResponse;
    }

    public boolean isDuplicate(SignupRequest request) {
        return accountRepository.existsByEmail(request.getEmail());
    }
}
