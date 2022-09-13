package kr.imcf.ott.persistence.repository;

import kr.imcf.ott.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositorySupportImpl implements CommentRepositorySupport{
    private final EntityManager em;

    //QueryDSL로 바꾸기
    //setParameter
    public List<Comment> findAll() {
        return em.createQuery("select c from TBL_COMMENT c where c.parent is NULL", Comment.class).getResultList();
    }
    public List<Comment> findAllByAccountId(Long id){
        return em.createQuery("select c from TBL_COMMENT c where c.writer.id = :id", Comment.class).setParameter("id", id).getResultList();
    }
    public List<Comment> findAllByStreamingId(Long id){
        return em.createQuery("select c from TBL_COMMENT c where c.streaming.id = :id", Comment.class).setParameter("id", id).getResultList();
    }

}
