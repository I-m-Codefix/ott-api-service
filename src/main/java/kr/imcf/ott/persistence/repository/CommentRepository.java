package kr.imcf.ott.persistence.repository;
import kr.imcf.ott.domain.entity.Category;
import kr.imcf.ott.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;

    public List<Comment> findAll() {
        return em.createQuery("select c from TBL_COMMENT c where c.parent is NULL", Comment.class).getResultList();
    }

    public List<Comment> findAllByAccountId(Long id){
        return em.createQuery("select c from TBL_COMMENT c where c.writer.id = "+id, Comment.class).getResultList();
    }
}
