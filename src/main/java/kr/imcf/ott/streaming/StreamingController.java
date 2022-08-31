package kr.imcf.ott.streaming;

import kr.imcf.ott.common.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StreamingController {
    private final StreamingService streamingService;


    @GetMapping("/service/ott/stream")
    public ResponseEntity<?> getStreamingList(@RequestParam Long categoryId) {
        List<StreamingDTO> response = streamingService.getStreamingList(categoryId);
        return new ResponseEntity<>(StreamingListResponse.builder().code(200).response("해외영화를 조회합니다.").results(response).build(), HttpStatus.OK);
    }

    @GetMapping("/service/ott/stream/{id}")
    public ResponseEntity<Message> getStreamingDescription(@PathVariable("id") Long ottId) {
        StreamingDTO response = streamingService.getStreamingDescription(ottId);
        return new ResponseEntity<>(StreamingResponse.builder().code(200).response("상세 정보 조회").result(response).build(), HttpStatus.OK);
    }

}
