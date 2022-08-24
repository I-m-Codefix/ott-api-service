package kr.imcf.ott.category;

import kr.imcf.ott.common.Message;
import kr.imcf.ott.domain.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/service/ott/category")
    public ResponseEntity<?> getCategoryList() {

        List<CategoryDTO> response = categoryService.getCategoryList();

        if(response == null)
            return new ResponseEntity<>(CategoryListResponse.builder().code(500).response("조회할 카테고리가 없습니다.").build(), HttpStatus.OK);

        return new ResponseEntity<>(CategoryListResponse.builder().code(200).response("카테고리를 조회합니다.").result(response).build(), HttpStatus.OK);
    }

    @PostMapping("/service/ott/category") //valid
    public ResponseEntity<?> addCategory(@RequestBody @Nullable Category category){
        CategoryResponse response = categoryService.addCategory(category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/service/ott/category")
    public ResponseEntity<?> modifyCategory(@RequestBody @Nullable CategoryFixesRequest categoryFixesRequest){

        CategoryResponse response = null;

        if (categoryFixesRequest.getReqType().equals("UPDATE"))
            response = categoryService.modifyCategory(categoryFixesRequest);
        else if (categoryFixesRequest.getReqType().equals("DELETE"))
            response = categoryService.deleteCategory(categoryFixesRequest.getId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
