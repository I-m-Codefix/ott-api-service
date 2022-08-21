package kr.imcf.ott.category;

import kr.imcf.ott.domain.entity.Category;
import kr.imcf.ott.persistence.repository.CategoryRepository;
import kr.imcf.ott.persistence.repository.CategoryRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryRepositoryJPA categoryRepositoryJPA;

    public List<CategoryDTO> getCategoryList() {
        List<CategoryDTO> results = categoryRepository.findAll().stream().map(CategoryDTO::of).collect(Collectors.toList());
        return results;
    }

    public boolean addCategory(Category category){
        if(category == null) return false;
        categoryRepositoryJPA.save(category);
        return true;
    }
}
