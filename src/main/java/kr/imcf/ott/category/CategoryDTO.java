package kr.imcf.ott.category;

import kr.imcf.ott.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String categoryName;
    private char useYn;
    private List<CategoryDTO> subCategoryList;

    public static CategoryDTO of(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getCategoryName(),
                category.getUseYn(),
                category.getSubCategoryList().stream().filter(CategoryDTO -> CategoryDTO.getUseYn() == 'Y').map(CategoryDTO::of).collect(Collectors.toList())
        );
    }
}