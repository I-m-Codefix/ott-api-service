package kr.imcf.ott.account;

import kr.imcf.ott.common.Message;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignupController {

    private final AccountService accountService;

    @PostMapping("/account/signup")
    public ResponseEntity<Message> signup(@RequestBody SignupRequest request){
        // 회원가입 프로세스
        accountService.signup();

        Message message = Message.builder().code(200).response("회원 가입이 완료되었습니다").build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/account/signup/mail")
    public ResponseEntity<SignupMailResponse> signupMail(@RequestBody SignupMailRequest request){
        // 회원가입 메일 발송   (만료기간 5분)
        accountService.sendMailForSignup();

        SignupMailResponse message = SignupMailResponse.builder().code(200).response("회원 가입이 완료되었습니다").expireDate("2022.06.25 12:12:30").build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/account/signup/mail")
    public ResponseEntity<Message> signupValidCheck(@RequestBody SignupValidRequest request){
        // 회원가입 메일 인증 절차
        accountService.validCheckForSignup();

        Message message = Message.builder().code(200).response("메일 인증이 완료되었습니다").build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
