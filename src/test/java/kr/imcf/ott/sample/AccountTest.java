package kr.imcf.ott.sample;

import kr.imcf.ott.domain.TblAccount;
import kr.imcf.ott.persistence.mapper.TblAccountMapper;
import kr.imcf.ott.persistence.repository.TblAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import kr.imcf.ott.domain.entity.Account;

import java.util.List;

@SpringBootTest
@Slf4j
public class AccountTest {

    @Autowired
    private TblAccountMapper tblAccountMapper;

    @Autowired
    private TblAccountRepository tblAccountRepository;

    @Test
    @Rollback(value = true)
    public void mybatisTest(){
        log.info("Mybatis 계정 조회 테스트");
        List<TblAccount> selectedAll = tblAccountMapper.selectAll();
        log.info("현재 서비스 계정 개수 : {} ", selectedAll.size());
    }

    @Test
    @Rollback(value = true)
    public void jpaTest(){
        log.info("JPA 계정 조회 테스트");
        List<Account> selectedAll = tblAccountRepository.findAll();
        log.info("현재 서비스 계정 개수 : {} ", selectedAll.size());
    }


}
