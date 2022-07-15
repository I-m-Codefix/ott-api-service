package kr.imcf.ott.domain.entity;


import kr.imcf.ott.common.type.PlatformType;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "TBL_ACCOUNT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "isAuth", nullable = false, columnDefinition = "CHAR(1) DEFAULT 'N'", length = 1)
    private char isAuth;

    @Column(name = "platformType", nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'IMCF' ")
    @Enumerated(EnumType.STRING)
    private PlatformType platformType;

    @Column(name = "profileImage", nullable = true)
    private String profileImage;

    @Column(name = "accessToken", nullable = true)
    private String accessToken;

    @Column(name = "refreshToken", nullable = true)
    private String refreshToken;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<Mail> mails;

    // 패스워드 암호화 기법
    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
