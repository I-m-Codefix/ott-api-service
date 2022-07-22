package kr.imcf.ott.SignupTest;

import kr.imcf.ott.account.AccountService;
import kr.imcf.ott.account.SignupRequest;
import kr.imcf.ott.common.type.MailType;
import kr.imcf.ott.common.type.PlatformType;
import kr.imcf.ott.domain.entity.Account;
import kr.imcf.ott.domain.entity.Mail;
import kr.imcf.ott.mail.MailService;
import kr.imcf.ott.persistence.repository.AccountRepository;
import kr.imcf.ott.persistence.repository.MailRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Rollback(value = true)
public class SignupTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    @Transactional(readOnly = false)
    public void accountSignUp()  {
        SignupRequest sampleRequest = new SignupRequest("jinyong", "password",
                "jinyong0826@naver.com",PlatformType.IMCF,"image");

        accountService.signup(sampleRequest); //assert로 고치기
    }

}
