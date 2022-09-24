package kr.imcf.ott.cryptTest;

import kr.imcf.ott.common.crypt.RSACrypt;
import kr.imcf.ott.common.crypt.RSAKeyObject;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class RSACryptTest {

    private String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnYnBg+M9tvlKtzC31/996WWHQlXkGJs6TuRa6vh6GOJ0lXCHYogYnqcruUJ/m4NulUY57yyli++XsH9Ib6INT5W4LbaGmdr0zeJfBbL6CexdSPYvQr4ccP4OAcaBwlv/SCzcFp5bzJDiqtco5P5xB9jndko0Ait01oz2i/C6KlQIDAQAB";
    private String privatekey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKdicGD4z22+Uq3MLfX/33pZYdCVeQYmzpO5Frq+HoY4nSVcIdiiBiepyu5Qn+bg26VRjnvLKWL75ewf0hvog1PlbgttoaZ2vTN4l8FsvoJ7F1I9i9Cvhxw/g4BxoHCW/9ILNwWnlvMkOKq1yjk/nEH2Od2SjQCK3TWjPaL8LoqVAgMBAAECgYEAlj/auHEfYVdSSAfbydIg/GZ3UxCZTlhPXnLqYD6Aj+MJzJtjFl56P+g97KYaoth80WzgBWdcgymyPb5ikAKqvpHuPVVux4Ln91UQD/2d25JekbGENF/RjKgwEPYoZBKoAM5XNx9jC1KOSqTWNBGjlVJp7hVckbs1Bb8UGtxVwIECQQDp7Kopjoxd8/qB28OQM2D1kRlJLXTYzzzLblzAGDnVUnt8vqfpqD63Dc2n2lHjfn+h2/vUmRC3YgrAnDuJEm+JAkEAty4/tQ+U6TdB/k8Sz9QcqgRMfAcpFK16oFytBcHR/lJWna77YgQImSEyk8X8CiZT5JWUvOoSOQCV5q2demITrQJABg252Zl2yHen4/Rr+y9ZMwGuRdaHT5UNXbZdcBswY/TefTvx3HoeJV6WE+HyfJCpfObUfYv4gsZmwNVKNSHlAQJBAJJcdbKpvIWLDUOw0osUI8MEpEgFQKGw6uXupqCO+MaH64UaJult56DSkaLY8kT50tY1Xh6DuAOroBosZkM+0hECQQCxr2h9LIdFA9siM13qdljaSKS30+XHm/NYcTqJC06bZiPKgEmxs5eqD7WdJSUJRHQ4HEgDGbQA+YJEXUusK3X7";
    private String plainText = "박수정";

    /**
     * @methodName: set()
     * @author: rojae
     * @date: 2022/09/25
     * @Description: JUnit 테스트 이전에 rsaKey 오브젝트를 셋팅
     * 실제 API 개발에서 KEY를 만들어주지는 않음
     */
//    @Before
//    public void set() throws NoSuchAlgorithmException {
//        rsaKeyObject = RSACrypt.generateRsaKeyPair();
//        System.out.println(String.format("공개키 : %s", rsaKeyObject.getRsaPublicKey()));
//        System.out.println(String.format("개인키 : %s", rsaKeyObject.getRsaPrivateKey()));
//    }

    // 암호화 테스트
    @Test
    public void encryptTest() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeySpecException, InvalidKeyException {
        System.out.println(String.format("암호화 된 데이터 문자열 : %s", RSACrypt.encryptRSA(plainText, new RSAKeyObject(publicKey, privatekey))));
    }

    // 복호화 테스트
    @Test
    public void decryptTest() throws Exception {
        String encryptedData = "RjG1xlrPFFofA9t67Dg0/GoJSOJif7nnksPERID/XxUF8nUXgUuHIZd3CRg7BicyT0EA7epOIqa5RVU1/TYlH06EFbJ9JI52RKcPoyZoq+p3biZP1/kWPc0VHP02MTukY8jwqm6/riSOXRObHWM/n4kgTySvRvYROMWUog5ZvOc=";
        System.out.println(String.format("복호화 된 데이터 : %s", RSACrypt.decryptRSA(encryptedData, new RSAKeyObject(publicKey, privatekey))));
    }

}
