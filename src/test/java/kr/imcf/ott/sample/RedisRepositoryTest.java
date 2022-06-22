package kr.imcf.ott.sample;

import kr.imcf.ott.common.type.PlatformType;
import kr.imcf.ott.domain.redis.RAccount;
import kr.imcf.ott.persistence.repository.AccountRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class RedisRepositoryTest {

    @Autowired
    private AccountRedisRepository accountRedisRepository;

    @Test
    void save() {
        RAccount account = createNew();

        // 저장
        accountRedisRepository.save(account);

        // `keyspace:id` 값을 가져옴
        log.info(accountRedisRepository.findById(account.getId()).get().getId().toString());
    }

    @Test
    void count(){
        // RAccount Entity 의 @RedisHash 에 정의되어 있는 keyspace (people) 에 속한 키의 갯수를 구함
        long count = accountRedisRepository.count();
        log.info("Redis Count : {}", count);
    }

    @Test
    void delete(){
        RAccount account = createNew();

        // 삭제
        accountRedisRepository.delete(account);
    }

    @Test
    void findOne(){
        Optional<RAccount> byId = accountRedisRepository.findById((1L));
        byId.ifPresent(rAccount -> log.info("Name : {} || AccessToken : {} || RefreshToken : {}", rAccount.getName(), rAccount.getAccessToken(), rAccount.getRefreshToken()));
    }

    RAccount createNew(){
        return new RAccount((long) 1, "rojae", "rojae@kakao.com",
                '1', PlatformType.IMCF, "",
                "ACCESSTOKEN", "REFRESHTOKEN");
    }



}