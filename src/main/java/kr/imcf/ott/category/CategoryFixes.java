package kr.imcf.ott.category;

import kr.imcf.ott.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryFixes {

    private String reqType;
    private Long id;
    private Category newParent;
    private String newCategoryName;


}
