package kr.imcf.ott.domain.entity;

import kr.imcf.ott.common.type.MailType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "TBL_ACCOUNT_MAIL")
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "isAuth", nullable = false, columnDefinition = "CHAR(1) DEFAULT 'N'", length = 1)
    private char isAuth = 'N';

    @Column(name = "expireDate", nullable = false)
    private LocalDateTime expireDate;

    @Column(name = "secretKey", nullable = false)
    private String secretKey;

    @Column(name = "sendDate", nullable = false)
    @CreatedDate
    private LocalDateTime sendDate;

    @Column(name = "mailType", nullable = false, columnDefinition = "VARCHAR(20)", length = 20)
    @Enumerated(EnumType.STRING)
    private MailType mailType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refAccountId", nullable = false)
    private Account account;

}
