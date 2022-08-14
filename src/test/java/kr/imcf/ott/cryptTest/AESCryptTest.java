package kr.imcf.ott.cryptTest;

import kr.imcf.ott.common.crypt.AESCrypt;
import kr.imcf.ott.common.props.JwtProps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AESCryptTest {

    @Autowired
    private JwtProps jwtProps;

    private String plainData = "{\"name\":\"테스트 사용자\",\"platformType\":\"KAKAO\",\"email\":\"test@kakao.com\",\"profileImage\":\"https://cdn.pixabay.com/photo/2022/07/25/15/18/cat-7344042_960_720.jpg\"}\n";
    private String encryptData = "bzPQ48Az8uLEGXvwywqokgwehTqmNyHPbab9BehsU0GABSstxR8rorXLj360mS+seFWVfCrjD/luRlEDyUQ4sJegRjQTE1TkF9oUfelxpUhAViLDH/+U6FljYw4tbjj/aFHCvV4Ye8P7GaHMuuhqZj7rntLs65JAGYty5IoMkRE4ttEs5/3o1MzqeVyfIXqCUIbAC6PAyH+UkiK70YJ3jxv/fgDszPrV/N8NvlYv6ng=";

    @Test
    public void aesCrypt() throws Exception {
        AESCrypt aesCrypt = new AESCrypt(jwtProps.jwtCryptSecretKey, jwtProps.jwtCryptIv);
        System.out.println(aesCrypt.encrypt(plainData));
    }

    @Test
    public void aesDecrypt() throws Exception {
        AESCrypt aesCrypt = new AESCrypt(jwtProps.jwtCryptSecretKey, jwtProps.jwtCryptIv);
        String decryptData = aesCrypt.decrypt(encryptData);
        System.out.println(decryptData);
    }

}
