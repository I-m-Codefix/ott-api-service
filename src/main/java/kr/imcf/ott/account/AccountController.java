package kr.imcf.ott.account;

import kr.imcf.ott.account.oauth2.OAuth2Principal;
import kr.imcf.ott.common.Message;
import kr.imcf.ott.common.type.PlatformType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("account/info")
    public ResponseEntity<MyInfoResponse> showMyInfo(@AuthenticationPrincipal OAuth2Principal oAuth2Principal) {

        MyInfoResponse selectedAccount = accountService.showMyInfo(oAuth2Principal.getEmail());

        //builder 패턴, 코드 개선 필요
        MyInfoResponse message = MyInfoResponse.builder()
                .id(selectedAccount.getId())
                .name(selectedAccount.getName())
                .email(selectedAccount.getEmail())
                .platformType(selectedAccount.getPlatformType())
                .profileImage(selectedAccount.getProfileImage())
                .code(200)
                .response("조회완료")
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/account/info")
    public ResponseEntity<Message> editMyInfo(@AuthenticationPrincipal OAuth2Principal oAuth2Principal, @RequestBody MyInfoEditRequest request) {
        if(!oAuth2Principal.getEmail().equals(request.getEmail()))
            return new ResponseEntity<>(Message.builder().code(403).response("허용되지 않는 요청입니다.").build(), HttpStatus.OK);
        if (accountService.editMyInfo(request))        // 내 정보 수정
            return new ResponseEntity<>(Message.builder().code(200).response("수정되었습니다.").build(), HttpStatus.OK);
        else
            return new ResponseEntity<>(Message.builder().code(500).response("수정되지 않았습니다.").build(), HttpStatus.OK);

    }

    @GetMapping("/account")
    public ResponseEntity<? extends Message> showIdInfo(@RequestParam Optional<Long> accountId) {
        if(accountId.isEmpty())
            return new ResponseEntity<>(Message.builder().code(403).response("accountId를 입력하지 않았습니다.").build(), HttpStatus.OK);
        else {
            MyInfoResponse myInfoResponse = accountService.showIdInfo(accountId.get());

            MyInfoResponse message = MyInfoResponse.builder()
                    .name(myInfoResponse.getName())
                    .email(myInfoResponse.getEmail())
                    .platformType(myInfoResponse.getPlatformType())
                    .profileImage(myInfoResponse.getProfileImage())
                    .code(200)
                    .response("조회완료")
                    .build();

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}




















