package kr.imcf.ott.persistence.repository;

import kr.imcf.ott.domain.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository <Account, Long> {
    Account findByEmail(String email);
    boolean existsByEmail(String email);
}
