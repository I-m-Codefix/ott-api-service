package kr.imcf.ott.persistence.repository;

import kr.imcf.ott.domain.entity.Category;

import java.util.List;

public interface CategoryRepositorySupport {
    List<Category> findAllParent();
}
