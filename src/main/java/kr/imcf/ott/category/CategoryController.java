package kr.imcf.ott.category;

import kr.imcf.ott.common.Message;
import kr.imcf.ott.domain.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
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
            return new ResponseEntity<>(Message.builder().code(500).response("조회할 카테고리가 없습니다.").build(), HttpStatus.OK);

        CategoryResponse categoryResponse =
                        CategoryResponse.builder()
                        .code(200)
                        .httpStatus(HttpStatus.OK)
                        .message("카테고리를 조회합니다.")
                        .result(response)
                        .build();

        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/service/ott/category") //valid
    public ResponseEntity<?> addCategory(@RequestBody @Nullable Category category){

        if(!categoryService.addCategory(category))
            return new ResponseEntity<>(Message.builder().code(500).response("카테고리를 추가하지 못했습니다.").build(), HttpStatus.OK);

        return new ResponseEntity<>(Message.builder().code(200).response("카테고리를 추가했습니다.").build(), HttpStatus.OK);
    }

    @PutMapping("/service/ott/category")
    public ResponseEntity<?> modifyCategory(@RequestBody CategoryFixes categoryFixes){

        if (categoryFixes.getReqType().equals("UPDATE"))
            categoryService.modifyCategory(categoryFixes);
        else if (categoryFixes.getReqType().equals("DELETE"))
            categoryService.deleteCategory(categoryFixes.getId());


        return new ResponseEntity<>(Message.builder().code(200).response("카테고리의 수정이 완료되었습니다.").build(), HttpStatus.OK);
    }
}
