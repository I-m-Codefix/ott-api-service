package kr.imcf.ott.persistence.repository;
import kr.imcf.ott.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentRepositorySupport {
    public List<Comment> findAll();
    public List<Comment> findAllByAccountId(Long id);
    public List<Comment> findAllByStreamingId(Long id);
}
