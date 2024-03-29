package kr.imcf.ott.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity(name = "TBL_STREAMING")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Streaming extends TimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Category category;

    @Column(name = "ott_name", unique = true, nullable = false)
    private String ottName;

    @Column(name = "ott_thumbnail", unique = true, nullable = false)
    private String ottThumbnail;

    @Column(name = "ott_s3_id", unique = true, nullable = false)
    private String ottS3Id;

    @Column(name = "ott_desc", nullable = false)
    private String ottDesc;

    @Column(name = "ott_desc_detail", nullable = false)
    private String ottDescDetail;

    @Column(name = "play_uri", nullable = false)
    private String playUri;

    @Column(name = "play_time", nullable = false)
    private String playTime;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "reg_admin_id", nullable = false)
    private String regAdminId;

    @Column(name = "view_count", nullable = false)
    private long viewCount;

    @Column(name = "like_count", nullable = false)
    private long likeCount;

    @Column(name = "unlike_count", nullable = false)
    private long unLikeCount;
}
