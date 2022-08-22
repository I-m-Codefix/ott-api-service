package kr.imcf.ott.persistence.repository;

import kr.imcf.ott.category.CategoryDTO;
import kr.imcf.ott.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryJPA extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long id);

}
