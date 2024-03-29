package kr.imcf.ott.comment;

import kr.imcf.ott.domain.entity.Comment;
import kr.imcf.ott.persistence.repository.AccountRepository;
import kr.imcf.ott.persistence.repository.CommentRepository;
import kr.imcf.ott.persistence.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final StreamingRepository streamingRepository;

    public List<CommentDTO> getAllCommentList() {
        List<CommentDTO> results = commentRepository.findByParentIsNull().stream().filter(c -> c.getUseYn() == 'Y').map(CommentDTO::of).collect(Collectors.toList());
        return results;
    }

    //paging
    public CommentMyInfoResponse getAccountCommentList(Long id) {
        //== equal
        List<CommentAccountDTO> results = commentRepository.findByWriterId(id).stream().filter(c -> c.getWriter().getId() == id).map(CommentAccountDTO::of).collect(Collectors.toList());

        CommentMyInfoResponse response =
                CommentMyInfoResponse
                        .builder()
                        .code(200)
                        .response("회원님이 작성한 모든 댓글을 조회합니다.")//.get(0).getName 하면 index오류가 있음. 댓글이 없을때.
                        .result(results)
                        .build();

        return response;
    }

    //paging
    public CommentStreamingResponse getSteamingCommentList(Long id) {
        //== equal
        List<CommentStreamingDTO> results = commentRepository.findByParentIsNull().stream().filter(c -> c.getStreaming().getId() == id).filter(c -> c.getUseYn() == 'Y').map(CommentStreamingDTO::of).collect(Collectors.toList());


        CommentStreamingResponse response =
                CommentStreamingResponse
                        .builder()
                        .code(200)
                        .response("영화에 작성된 모든 댓글을 조회합니다.") //.get(0).getName 하면 index오류가 있음. 댓글이 없을때.
                        .result(results)
                        .build();

        if (results.isEmpty()) {
            response.setResponse("아무런 댓글이 작성되지 않았습니다.");
        }

        return response;
    }

    @Transactional(readOnly = true)
    public CommentResponse addComment(Comment comment) {

//        Optional<Comment> parent = commentRepositoryJPA.findById(comment.getParent().getId());
//        Optional<Account> writer = accountRepository.findById(comment.getWriter().getId());
//        Optional<Streaming> streaming = streamingRepository.findById(comment.getStreaming().getId());
//
//        if(parent.isPresent())
//            comment.setParent(parent.get());
//        else
//            comment.setParent(null);
//        comment.setWriter(writer.get());
//        comment.setStreaming(streaming.get());


        //responsebody 입력양식
        //"content": "댓글 내용"
        //"parent": null
        //"writer": {"id" : 작성자id}
        //"streaming": {"id" : }"


        commentRepository.save(comment);
        CommentResponse response =
                CommentResponse
                        .builder()
                        .code(200)
                        .response("댓글이 작성되었습니다.")
                        .result(comment)
                        .build();
        return response;
    }

    @Transactional(readOnly = false)
    public String modifyComment(CommentFixesRequest request) {
        Optional<Comment> comment = commentRepository.findById(request.getId());
        comment.get().setContent(request.getContent());
        return "댓글이 수정 되었습니다.";
    }

    @Transactional(readOnly = false)
    public String deleteComment(CommentFixesRequest request) {
        Optional<Comment> comment = commentRepository.findById(request.getId());
        comment.get().setUseYn('N');
        return "댓글이 삭제 되었습니다.";
    }

}





