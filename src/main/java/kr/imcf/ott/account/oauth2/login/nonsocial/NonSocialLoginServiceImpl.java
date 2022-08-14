package kr.imcf.ott.account.oauth2.login.nonsocial;

import kr.imcf.ott.account.oauth2.OAuth2LoginResponse;
import kr.imcf.ott.account.oauth2.OAuth2Principal;
import kr.imcf.ott.common.jwt.JwtProvider;
import kr.imcf.ott.common.props.OAuth2Props;
import kr.imcf.ott.common.type.PlatformType;
import kr.imcf.ott.common.util.TimeUtils;
import kr.imcf.ott.domain.entity.Account;
import kr.imcf.ott.domain.redis.RAccount;
import kr.imcf.ott.persistence.repository.AccountRedisRepository;
import kr.imcf.ott.persistence.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
@Slf4j
public class NonSocialLoginServiceImpl implements NonSocialLoginService {

    private final JwtProvider jwtProvider;
    private final OAuth2Props oAuth2Props;
    private final AccountRepository accountRepository;
    private final AccountRedisRepository accountRedisRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2LoginResponse login(String email, String password) {
        Account selectedAccount = accountRepository.findByEmail(email);

        System.out.println(password);
        System.out.println(selectedAccount.getPassword());

        if(passwordEncoder.matches(password, selectedAccount.getPassword())){
            log.info("SUCCESS LOGIN :: {}", email);

            OAuth2Principal oAuth2Principal = new OAuth2Principal();
            oAuth2Principal.setName(selectedAccount.getName());
            oAuth2Principal.setEmail(selectedAccount.getEmail());
            oAuth2Principal.setProfileImage(selectedAccount.getProfileImage());
            oAuth2Principal.setPlatformType(selectedAccount.getPlatformType());

            String token = this.publishToken(oAuth2Principal);

            return OAuth2LoginResponse.builder()
                    .name(selectedAccount.getName())
                    .email(selectedAccount.getEmail())
                    .platformType(PlatformType.IMCF)
                    .profileImage(selectedAccount.getProfileImage())
                    .token(token)
                    .expireTime(TimeUtils.dateFomat(jwtProvider.getExpiration(token), "yyyy.MM.dd HH:mm:ss"))
                    .build();
        }
        else
            throw new UsernameNotFoundException(email);
    }

    @Override
    public String publishToken(OAuth2Principal oAuth2Principal) {
        log.info("STEP 1 :: TOKEN CREATE");
        String token = this.generateToken(oAuth2Principal);

        log.info("STEP 2 :: USER INFO SAVE DATABASE");
        this.saveDB(oAuth2Principal, token);

        log.info("STEP 3 :: TOKEN INFO SAVE REDIS");
        this.saveRedis(oAuth2Principal, token);

        return token;
    }

    @Override
    public String generateToken(OAuth2Principal oAuth2Principal) {
        String token = jwtProvider.generateToken(oAuth2Principal);
        try {
            jwtProvider.verify(token);
        } catch (Exception e) {
            log.error("발급할 토큰에 검증에 실패했습니다.");
            e.printStackTrace();
        }
        return token;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean saveDB(OAuth2Principal oAuth2Principal, String token) {
        Account selectedAccount = accountRepository.findByEmail(oAuth2Principal.getEmail());

        // 이미 저장된 계정 정보는 업데이트 처리
        log.info("기가입된 회원으로 정보를 최신화합니다.");
        selectedAccount.setAccessToken(token);

        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean saveRedis(OAuth2Principal oAuth2Principal, String token) {
        // 기저장된 Redis 정보는 만료처리
        Optional<RAccount> beforeTokenInfo = accountRedisRepository.findById(RAccount.idFormat(oAuth2Principal.getPlatformType(), oAuth2Principal.getEmail()));

        beforeTokenInfo.ifPresent(
            rAccount ->{
                // 기저장된 Redis 정보 삭제
                log.info("사용 가능한 기발급 토큰정보를 삭제합니다.");
                log.info("Id = {} | Name = {} | Token = {}", rAccount.getId(), rAccount.getName(), rAccount.getAccessToken());
                accountRedisRepository.delete(rAccount);
            }
        );

        // 새로운 토큰정보 Redis 저장
        log.info("새로운 토큰정보를 저장합니다.");
        RAccount newTokenInfo = RAccount.builder()
                .id(RAccount.idFormat(oAuth2Principal.getPlatformType(), oAuth2Principal.getEmail()))
                .name(oAuth2Principal.getName())
                .profileImage(oAuth2Principal.getProfileImage())
                .accessToken(token)
                .build();

        accountRedisRepository.save(newTokenInfo);
        return true;
    }
}
