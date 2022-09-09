package kr.imcf.ott.comment;

import kr.imcf.ott.category.CategoryDTO;
import kr.imcf.ott.category.CategoryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/service/ott/comment")
    public ResponseEntity<?> getAllCommentList() {
        List<CommentDTO> response = commentService.getAllCommentList();
        return new ResponseEntity<>(CommentListResponse.builder().code(200).response("모든 댓글을 조회합니다.").result(response).build(), HttpStatus.OK);
    }

    @GetMapping("/service/ott/commentAccount/{id}")
    public ResponseEntity<?> getAccountCommentList(@PathVariable("id") Long id) {
        CommentAccountResponse response = commentService.getAccountCommentList(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/service/ott/commentStreaming/{id}")
    public ResponseEntity<?> getStreamingCommentList(@PathVariable("id") Long id) {
        CommentStreamingResponse response = commentService.getSteamingCommentList(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
