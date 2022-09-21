package kr.imcf.ott.comment;

import kr.imcf.ott.common.Message;
import kr.imcf.ott.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse extends Message {
    private Comment result;
}
