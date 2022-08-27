package kr.imcf.ott.category;

import kr.imcf.ott.domain.entity.Category;
import kr.imcf.ott.persistence.repository.CategoryRepository;
import kr.imcf.ott.persistence.repository.CategoryRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryRepositoryJPA categoryRepositoryJPA;

    public List<CategoryDTO> getCategoryList() {
        List<CategoryDTO> results = categoryRepository.findAll().stream().filter(CategoryDTO -> CategoryDTO.getUseYn() == 'Y').map(CategoryDTO::of).collect(Collectors.toList());
        return results;
    }

    public CategoryResponse addCategory(Category category){
        if(category.getParent() != null)
            if(categoryRepositoryJPA.findById(category.getParent().getId()).get().getUseYn() == 'N')
                return CategoryResponse.builder().code(500).response("상위카테고리가 없습니다. ").result(category).build();

        categoryRepositoryJPA.save(category);
        return CategoryResponse.builder().code(200).response("카테고리 추가완료. ").result(category).build();
    }

    public CategoryResponse modifyCategory(CategoryFixesRequest categoryFixes){

        //CategoryResponse로 리펙토링??
        //이렇게 response를 service에서 작성해서 보내는게 맞는가? vs String으로 response를 보내주는게 맞는가?
        //CategoryResponse는 controller에서 빌드해주어서 보내는게 맞지않나?
        //service에서 response에 여러 상황에 따른 메세지를 각각 넣어서 보내주려고 할 때 최고의 방법이 무엇인가? 2개인 경우에는 boolean이 맞는가 << 메모리 관련해서 생각해볼일?
        //CRUD가 이루어 졌을 때 결과값을 모두 반환해주어야 하는가? 혹은 바뀐 데이터까지 보기 편하도록 보내주어야하나?
        //service 단에서 reqType을 비교해줘야하나?

        Optional<Category> category = categoryRepositoryJPA.findById(categoryFixes.getId());

        if(category.get().getParent().getUseYn() == 'N')
            return CategoryResponse.builder().code(500).response("상위카테고리가 없어서 카테고리 수정이 불가능합니다.. ").result(category.get()).build();

        category.get().setCategoryName(categoryFixes.getNewCategoryName());
        category.get().setParent(categoryFixes.getNewParent());
        category.get().setUseYn('Y');
        categoryRepositoryJPA.save(category.get());

        return CategoryResponse.builder().code(200).response("카테고리가 수정되었습니다. ").result(category.get()).build();
    }

    public CategoryResponse deleteCategory(Long id) {

        Optional<Category> category = categoryRepositoryJPA.findById(id);
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
            categoryRepositoryJPA.save(category.get());
        }
        return CategoryResponse.builder().code(200).response("카테고리가 삭제되었습니다. ").result(category.get()).build();
    }

}
