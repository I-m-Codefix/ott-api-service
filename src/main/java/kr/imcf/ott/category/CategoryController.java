package kr.imcf.ott.category;

import kr.imcf.ott.common.Message;
import kr.imcf.ott.domain.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import java.util.ArrayList;
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

    @PostMapping("/service/ott/category")
    public ResponseEntity<?> addCategory(@RequestParam @Nullable Category parentId, @RequestParam String categoryName){

        if(!categoryService.addCategory(parentId, categoryName))
            return new ResponseEntity<>(Message.builder().code(500).response("카테고리를 추가하지 못했습니다.").build(), HttpStatus.OK);

        return  new ResponseEntity<>(Message.builder().code(200).response("카테고리를 추가했습니다.").build(), HttpStatus.OK);
    }
}
