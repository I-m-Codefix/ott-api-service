package kr.imcf.ott.persistence.repository;

import kr.imcf.ott.domain.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepositorySupportImp implements CategoryRepositorySupport {
    private final EntityManager em;

    public List<Category> findAllParent() {
        return em.createQuery("select c from TBL_CATEGORY c where c.parent is NULL", Category.class).getResultList();
    }
}
