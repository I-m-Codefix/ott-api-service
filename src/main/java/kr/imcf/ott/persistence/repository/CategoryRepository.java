package kr.imcf.ott.persistence.repository;

import kr.imcf.ott.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CategoryRepositorySupport, JpaRepository<Category, Long>  {

}
