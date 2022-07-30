package kr.imcf.ott.category;

import kr.imcf.ott.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(rollbackFor = Exception.class)
    public List<CategoryDTO> getCategoryList() {
        List<CategoryDTO> results = categoryRepository.findAll().stream().map(CategoryDTO::of).collect(Collectors.toList());
        return results;
    }

}
