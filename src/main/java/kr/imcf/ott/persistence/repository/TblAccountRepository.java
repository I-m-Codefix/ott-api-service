package kr.imcf.ott.persistence.repository;

import kr.imcf.ott.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblAccountRepository extends JpaRepository <Account, Long> {

}