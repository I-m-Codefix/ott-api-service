package kr.imcf.ott.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TBL_CATEGORY")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "category_name", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    private String category_name;

    @Column(name = "use_yn", nullable = false, columnDefinition = "CHAR(1) DEFAULT 'Y'", length = 1)
    private char use_yn = 'Y';

    @Column(name = "parent_id", nullable = false)
    private long parent_id;

}
