package kr.imcf.ott;

import kr.imcf.ott.common.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    @GetMapping("/test/info/security")
    public ResponseEntity<Message> tokenCheck(){
        Message message = Message.builder().code(200).response("Spring Security Context Info").build();
        log.info("SESSION INFORMATION :: " + SecurityContextHolder.getContext().getAuthentication());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
