package kr.imcf.ott.comment;

import kr.imcf.ott.category.CategoryDTO;
import kr.imcf.ott.category.CategoryListResponse;
import kr.imcf.ott.category.CategoryResponse;
import kr.imcf.ott.domain.entity.Category;
import kr.imcf.ott.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/service/ott/comment") //valid
    public ResponseEntity<?> addComment(@RequestBody @Nullable Comment comment){
        CommentResponse response = commentService.addComment(comment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
