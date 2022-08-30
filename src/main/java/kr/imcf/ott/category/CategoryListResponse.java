package kr.imcf.ott.category;

import kr.imcf.ott.common.Message;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListResponse extends Message {
    private List<CategoryDTO> result;
}
