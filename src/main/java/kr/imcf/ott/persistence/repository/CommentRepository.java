package kr.imcf.ott.persistence.repository;

import kr.imcf.ott.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends CommentRepositorySupport, JpaRepository<Comment, Long>{
    List<Comment> findByParentIsNull();
    List<Comment> findByWriterId(Long id);

}
