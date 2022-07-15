package kr.imcf.ott.mail;

import kr.imcf.ott.common.type.MailType;
import kr.imcf.ott.common.type.PlatformType;
import kr.imcf.ott.domain.entity.Account;
import kr.imcf.ott.domain.entity.Mail;
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
@Rollback(value = false)
public class MailSendTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MailRepository mailRepository;

    private final String testUserEmail = "test@gmail.com";

    @Before
    @Transactional(readOnly = false)
    public void createSampleUser() {
        Account newAccount = Account.builder()
                .email(testUserEmail)
                .name("TestUser")
                .password("password")
                .platformType(PlatformType.IMCF)
                .accessToken("ACCESSTOKEN")
                .refreshToken("REFRESHTOKEN")
                .profileImage("PROFILE")
                .build();

        accountRepository.save(newAccount);
    }

    @Test
    @Transactional(readOnly = false)
    public void sendMail() throws MessagingException {
        Account selectedAccount = accountRepository.findByEmail(testUserEmail);

        Mail newMail = Mail.builder()
                .mailType(MailType.SIGNUP)
                .email(testUserEmail)
                .account(selectedAccount)
                .expireDate(LocalDateTime.now().plusMinutes(10))
                .secretKey("SECRETKEY")
                .build();

        mailRepository.save(newMail);

        mailService.signupSend(newMail);
    }

}
