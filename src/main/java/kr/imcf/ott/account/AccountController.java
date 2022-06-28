package kr.imcf.ott.account;

import kr.imcf.ott.common.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("account/info")
    public ResponseEntity<MyInfoResponse> showMyInfo() {
        // 원래라면 HTTP Header에 존재하는 토큰을 사용해서, 사용자 구분이 가능
        // 현재 토큰 인증 정보 미개발로 인해서, 사용자의 이메일이나 ID를 임의로 넣어서 추출

        //임시 email로 해당 email를 가진 사용자 조회
        String tempEmail = "rojae@kakao.com";

        MyInfoResponse showMessage = accountService.showMyInfo(tempEmail);

        //builder 패턴, 코드 개선 필요
        MyInfoResponse message = MyInfoResponse.builder().name(showMessage.getName()).email(showMessage.getEmail()).code(200).response("조회완료").build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/account/info")
    public ResponseEntity<Message> editMyInfo(@RequestBody MyInfoEditRequest request){
        // 내 정보 수정
        if(accountService.editMyInfo(request))
            return new ResponseEntity<>(Message.builder().code(200).response("수정되었습니다.").build(), HttpStatus.OK);
        else
            return new ResponseEntity<>(Message.builder().code(500).response("수정되지않았습니다.").build(), HttpStatus.OK);

    }
}
