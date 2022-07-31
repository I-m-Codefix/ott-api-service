package kr.imcf.ott.persistence.repository;

import kr.imcf.ott.domain.redis.RAccount;
import org.springframework.data.repository.CrudRepository;


public interface AccountRedisRepository extends CrudRepository<RAccount, String> {
}
