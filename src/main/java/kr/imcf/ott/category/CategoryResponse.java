package kr.imcf.ott.category;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Integer code;
    private HttpStatus httpStatus;
    private String message;
    private List<CategoryDTO> result;
}
