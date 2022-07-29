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

    @Column(name = "category_name", nullable = false)
    private String category_name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent_id;

    @Column(name = "use_yn", nullable = false, columnDefinition = "CHAR(1) DEFAULT 'Y'", length = 1)
    private char use_yn = 'Y';


}
