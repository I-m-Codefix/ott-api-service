package kr.imcf.ott.persistence.repository;

import kr.imcf.ott.domain.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

    public interface MailRepository extends JpaRepository<Mail, Long> {

        Optional<Mail> findBySecretKeyAndEmail(String secretKey, String email);
        boolean existsBySecretKeyAndEmailAndIsAuth(String secretKey, String email, char isAuth);

//    /**
//     * @method : saveSignupMail
//     * @description : 회원가입 메일 DB 등록
//     * @author: rojae
//     * @date : 2022/07/13
//     **/
//    boolean saveSignupMail();
//
//    /**
//     * @method : processSignupMail
//     * @description : 회원가입 인증처리
//     * @author: rojae
//     * @date : 2022/07/13
//     **/
//    boolean processSignupMail();

}
