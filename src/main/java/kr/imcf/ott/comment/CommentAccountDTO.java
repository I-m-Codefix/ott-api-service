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
public class CommentAccountDTO {
    private Long id;
    private String content;
    private char useYn;
    private Long writerId;
    private String writerName;
    private String writerProfileImageUri;
    private Long streamingId;
    private String streamingName;

    public static CommentAccountDTO of(Comment comment) {
        return new CommentAccountDTO(
                comment.getId(),
                comment.getContent(),
                comment.getUseYn(),
                comment.getWriter().getId(),
                comment.getWriter().getName(),
                comment.getWriter().getProfileImage(),
                comment.getStreaming().getId(),
                comment.getStreaming().getOttName()
        );
    }


}
