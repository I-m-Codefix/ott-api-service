package kr.imcf.ott.domain.entity;


import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "TBL_CATEGORY")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Category extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "categoryName",  nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    private String categoryName;

    @Column(name = "useYN", nullable = false)
    private char useYn = 'Y';

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> subCategoryList = new ArrayList<>();

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private Set<Streaming> streamings = new HashSet<Streaming>();

    @Override
    public String toString(){
        return "id : " +this.id+", categoryName : "+this.categoryName+ ", parent : { "+this.parent+" }";
    }
}
