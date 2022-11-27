package kr.imcf.ott.persistence.repository;

import kr.imcf.ott.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository <Account, Long> {
    Account findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Account> findById(Long id);
}
