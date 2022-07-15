package kr.imcf.ott;

import kr.imcf.ott.common.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    @GetMapping("/")
    public ResponseEntity<Message> healthCheck(){
        Message message = Message.builder().code(200).response("Health Check OK").build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
