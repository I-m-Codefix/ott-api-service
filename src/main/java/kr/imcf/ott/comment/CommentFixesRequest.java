package kr.imcf.ott.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentFixesRequest {
    private Long id;
    private String content;
    private String reqType;
}
