package kr.imcf.ott.persistence.repository;

import kr.imcf.ott.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositoryJPA extends JpaRepository<Category, Long> {
}
