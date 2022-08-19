package kr.imcf.ott.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService CategoryService;

    @GetMapping("/service/ott/category")
    public ResponseEntity<?> getCategoryList() {return ResponseEntity.ok(CategoryService.getCategoryList());}
}
