package kr.imcf.ott.category;

import kr.imcf.ott.common.Message;
import kr.imcf.ott.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse extends Message {
    private Category result;
}
