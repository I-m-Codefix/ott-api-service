package kr.imcf.ott.account;

import kr.imcf.ott.common.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/account/info")
    public ResponseEntity<MyInfoResponse> showMyInfo() {
        // 원래라면 HTTP Header에 존재하는 토큰을 사용해서, 사용자 구분이 가능
        // 현재 토큰 인증 정보 미개발로 인해서, 사용자의 이메일이나 ID를 임의로 넣어서 추출
        accountService.showMyInfo();

        MyInfoResponse message = MyInfoResponse.builder().code(200).response("조회완료").name("성함")
                .email("rojae@kakao.com").profileImage("프로필사진").platformType("IMCF").build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/account/info")
    public ResponseEntity<Message> editMyInfo(@RequestBody MyInfoEditRequest request){
        // 내 정보 수정
        accountService.editMyInfo();

        Message message = Message.builder().code(200).response("수정되었습니다").build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
