package kr.imcf.ott.streaming;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StreamingController {
    private final StreamingService streamingService;

//    @GetMapping("/service/ott/stream")


//    @GetMapping("/service/ott/stream/{id}")
//    id는 pathVariable을 통해서 가져오자

}
