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
        if(accountService.signup(request))
            return new ResponseEntity<>(Message.builder().code(200).response("인증메일을 확인해주세요.").build(), HttpStatus.OK);
        else
            return new ResponseEntity<>(Message.builder().code(500).response("회원 가입이 실패하였습니다").build(), HttpStatus.OK);

    }

    // 이거 없어도 되니까 삭제하고 signup 에 메일 발송을 넣기.
    // postman에 작성되어있는 회원가입에 api를 회원가입뿐만아니라 메일발송을 추가한다. 이때 응답메세지에 회원가입 x 인증메일을 확인해주세요라고 response 되게.
    // 인증하기 눌렀을 때 back에 인증됐다는 사실을 보낸 후 front단에서 '인증되었습니다'가 출력되어야함.
//    @PostMapping("/account/signup/mail")
//    public ResponseEntity<SignupMailResponse> signupMail(@RequestBody SignupMailRequest request){
//        // 회원가입 메일 발송   (만료기간 5분)
//        accountService.sendMailForSignup();
//
//        SignupMailResponse message = SignupMailResponse.builder().code(200).response("회원 가입이 완료되었습니다").expireDate("2022.06.25 12:12:30").build();
//        return new ResponseEntity<>(message, HttpStatus.OK);
//    }

    @PutMapping("/account/signup/mail")
    public ResponseEntity<Message> signupValidCheck(@RequestBody SignupValidRequest request){
        // 회원가입 메일 인증 절차
        accountService.validCheckForSignup();

        Message message = Message.builder().code(200).response("메일 인증이 완료되었습니다").build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
