package kr.imcf.ott.category;

import kr.imcf.ott.domain.entity.Category;
import kr.imcf.ott.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getCategoryList() {
        List<CategoryDTO> results = categoryRepository.findAllParent().stream().filter(CategoryDTO -> CategoryDTO.getUseYn() == 'Y').map(CategoryDTO::of).collect(Collectors.toList());
        return results;
    }

    public CategoryResponse addCategory(Category category){
        if(category.getParent() != null)
            if(categoryRepository.findById(category.getParent().getId()).get().getUseYn() == 'N')
                return CategoryResponse.builder().code(500).response("상위카테고리가 없습니다. ").result(category).build();

        categoryRepository.save(category);
        return CategoryResponse.builder().code(200).response("카테고리 추가완료. ").result(category).build();
    }

    @Transactional(readOnly = false)
    public CategoryResponse modifyCategory(CategoryFixesRequest categoryFixes){

        Optional<Category> category = categoryRepository.findById(categoryFixes.getId());
        if(!category.isPresent())
            return CategoryResponse.builder().code(500).response("해당 id는 존재하지 않습니다.").result(category.get()).build();


        if(category.get().getParent() == null) { //수정하려는 카테고리가 최상단 카테고리일 경우 리팩토링
            //검사만 해주면 됨
        }
        else if(category.get().getParent().getUseYn() == 'N')
            return CategoryResponse.builder().code(500).response("상위카테고리가 없어서 카테고리 수정이 불가능합니다.").result(category.get()).build();

        category.get().setCategoryName(categoryFixes.getNewCategoryName());
        category.get().setParent(categoryFixes.getNewParent());
        category.get().setUseYn('Y');
//        categoryRepository.save(category.get());

        return CategoryResponse.builder().code(200).response("카테고리가 수정되었습니다. ").result(category.get()).build();
    }

    @Transactional(readOnly = false)
    public CategoryResponse deleteCategory(Long id) {

        Optional<Category> category = categoryRepository.findById(id);
        //해당 id의 카테고리가 존재하지 않으면 false
        //해당 id의 카테고리.parent가 null(최상단 카테고리)면 false
        if(category.get().getParent() == null)
            return CategoryResponse.builder().code(500).response("최상단 카테고리는 삭제가 불가능합니다. "+category).result(category.get()).build();
        //해당 id의 카테고리.subCategoryList가 존재하면(상위 카테고리가 존재하면) false
        if(category.get().getSubCategoryList().stream().anyMatch(c -> c.getUseYn() == 'Y') )
            return CategoryResponse.builder().code(500).response("하위 카테고리가 존재하는 카테고리는 삭제가 불가능합니다. ").result(category.get()).build();
        //해당 id의 카테고리.subCategoryList가 존재하지 않으면(하위 카테고리가 없으면)  useYn = N으로 바꾼다. true
        else {
            category.get().setUseYn('N');
//            categoryRepository.save(category.get());
        }
        return CategoryResponse.builder().code(200).response("카테고리가 삭제되었습니다. ").result(category.get()).build();
    }

}
