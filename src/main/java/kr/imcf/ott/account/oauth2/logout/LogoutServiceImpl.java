package kr.imcf.ott.account.oauth2.logout;

import kr.imcf.ott.account.oauth2.OAuth2Principal;
import kr.imcf.ott.common.jwt.JwtProvider;
import kr.imcf.ott.domain.redis.RAccount;
import kr.imcf.ott.persistence.repository.AccountRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutService {

    private final AccountRedisRepository accountRedisRepository;
    private final JwtProvider jwtProvider;

    @Override
    public boolean logout(String token) {
        OAuth2Principal oAuth2Principal = null;

        try {
            oAuth2Principal = jwtProvider.toPrincipal(token);
            Optional<RAccount> currentTokenInfo = accountRedisRepository.findById(RAccount.idFormat(oAuth2Principal.getPlatformType(), oAuth2Principal.getEmail()));
            accountRedisRepository.delete(currentTokenInfo.get());
        } catch (Exception e) {
            log.info(String.format("Parsing Failed :: %s", token));
            return false;
        }
        return true;
    }

}
