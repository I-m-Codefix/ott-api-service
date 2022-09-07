package kr.imcf.ott.comment;
import kr.imcf.ott.category.CategoryDTO;
import kr.imcf.ott.domain.entity.Account;
import kr.imcf.ott.domain.entity.Category;
import kr.imcf.ott.domain.entity.Comment;
import kr.imcf.ott.domain.entity.Streaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String content;
    private char useYn;
    private Account writer;
    private Streaming streaming;
    private List<CommentDTO> subCommentList;


    public static CommentDTO of(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getContent(),
                comment.getUseYn(),
                comment.getWriter(),
                comment.getStreaming(),
                comment.getSubCommentList().stream().filter(CommentDTO -> CommentDTO.getUseYn() == 'Y').map(CommentDTO::of).collect(Collectors.toList())
        );
    }


}
