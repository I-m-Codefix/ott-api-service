package kr.imcf.ott.persistence.repository;

import kr.imcf.ott.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositorySupport {
}
