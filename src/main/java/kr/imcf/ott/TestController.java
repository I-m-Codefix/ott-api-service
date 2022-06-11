package kr.imcf.ott;

import kr.imcf.ott.domain.TblAccount;
import kr.imcf.ott.persistence.mapper.TblAccountMapper;
import kr.imcf.ott.persistence.repository.TblAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final TblAccountRepository tblAccountRepository;
    private final TblAccountMapper tblAccountMapper;

    @GetMapping("/test")
    public List<TblAccount> test1(){
        tblAccountRepository.findAll();             // JPA Test
        return tblAccountMapper.selectAll();       // Mybatis Test
    }

}
