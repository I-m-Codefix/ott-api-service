package kr.imcf.ott.jwtTest;

import kr.imcf.ott.common.jwt.JwtProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JwtTest {

    @Autowired
    private JwtProvider jwtProvider;

    private String notExpireToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvdHQtc2VydmljZS10b2tlbiIsImRhdGEiOnsibmFtZSI6IuyYpOyerOyEsSIsInBsYXRmb3JtVHlwZSI6IktBS0FPIiwiZW1haWwiOiJyb2phZUBrYWthby5jb20iLCJwcm9maWxlSW1hZ2UiOiJodHRwOi8vay5rYWthb2Nkbi5uZXQvZG4vY2pwWTJZL2J0cmxOZHhuYmcwL0hoM3RZVklhdXpsbVBlT2htOFpoOUsvaW1nXzY0MHg2NDAuanBnIn0sImlzcyI6IklNQ0YiLCJleHAiOjE3NTkyMzk2NjUsImlhdCI6MTY1OTE5NjQ2NX0.BdvesOCcd8TctWqMAk9wa7TTzIoU8ms4Ggliu2rbSY4";
    private String expiredToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvdHQtc2VydmljZS10b2tlbiIsImRhdGEiOnsibmFtZSI6IuyYpOyerOyEsSIsInBsYXRmb3JtVHlwZSI6IktBS0FPIiwiZW1haWwiOiJyb2phZUBrYWthby5jb20iLCJwcm9maWxlSW1hZ2UiOiJodHRwOi8vay5rYWthb2Nkbi5uZXQvZG4vY2pwWTJZL2J0cmxOZHhuYmcwL0hoM3RZVklhdXpsbVBlT2htOFpoOUsvaW1nXzY0MHg2NDAuanBnIn0sImlzcyI6IklNQ0YiLCJleHAiOjE2NTkxOTY0NjUsImlhdCI6MTY1OTE5NjQ2NX0.4T-u_DYI1vHeyH0aw0fArXIC-RrbJwbcoJ31Vot4rMY";


    @Test
    public void verifyTrue() throws UnsupportedEncodingException {
        boolean result = jwtProvider.verify(notExpireToken);
        Assert.assertTrue(result);
        System.out.println("토큰 검증에 성공했습니다.");
    }

    @Test
    public void verifyFail() throws UnsupportedEncodingException {
        boolean result = jwtProvider.verify(expiredToken);
        Assert.assertFalse(result);
        System.out.println("만료된 토큰입니다.");
    }



}
