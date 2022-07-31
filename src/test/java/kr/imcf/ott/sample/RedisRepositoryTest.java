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
        String id = RAccount.idFormat(PlatformType.IMCF, "rojae@kakao.com");

        RAccount account = createNew(id);

        // 저장
        accountRedisRepository.save(account);

        // `keyspace:id` 값을 가져옴
        log.info(String.valueOf(accountRedisRepository.findById(id)));
    }

    @Test
    void count() {
        // RAccount Entity 의 @RedisHash 에 정의되어 있는 keyspace (people) 에 속한 키의 갯수를 구함
        long count = accountRedisRepository.count();
        log.info("Redis Count : {}", count);
    }

    @Test
    void delete() {
        String id = RAccount.idFormat(PlatformType.IMCF, "rojae@kakao.com");

        RAccount account = createNew(id);

        // 삭제
        accountRedisRepository.delete(account);
    }

    @Test
    void findOne() {
        Optional<RAccount> byId = accountRedisRepository.findById(RAccount.idFormat(PlatformType.IMCF, "rojae@kakao.com"));
        byId.ifPresent(rAccount -> log.info("Id : {} || Name : {} || AccessToken : {}", rAccount.getId(), rAccount.getName(), rAccount.getAccessToken()));
    }

    RAccount createNew(String id) {
        return RAccount.builder()
                .id(id)
                .name("테스트_사용자")
                .profileImage("")
                .accessToken("ACCESSTOKEN")
                .build();
    }


}