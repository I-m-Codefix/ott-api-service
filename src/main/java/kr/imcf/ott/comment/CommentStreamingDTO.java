package kr.imcf.ott.comment;
import kr.imcf.ott.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentStreamingDTO {
    private Long id;
    private String content;
    private char useYn;
    private Long writerId;
    private String writerName;
    private String writerProfileImageUri;
    private Long streamingId;
    private String streamingName;
    private List<CommentDTO> subCommentList;

    public static CommentStreamingDTO of(Comment comment) {
        return new CommentStreamingDTO(
                comment.getId(),
                comment.getContent(),
                comment.getUseYn(),
                comment.getWriter().getId(),
                comment.getWriter().getName(),
                comment.getWriter().getProfileImage(),
                comment.getStreaming().getId(),
                comment.getStreaming().getOttName(),
                comment.getSubCommentList().stream().filter(CommentDTO -> CommentDTO.getUseYn() == 'Y').map(CommentDTO::of).collect(Collectors.toList())
        );
    }


}
