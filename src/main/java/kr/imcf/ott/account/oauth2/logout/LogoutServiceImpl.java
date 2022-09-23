package kr.imcf.ott.account.oauth2.logout;

import kr.imcf.ott.account.oauth2.OAuth2Principal;
import kr.imcf.ott.common.jwt.JwtProvider;
import kr.imcf.ott.domain.redis.RAccount;
import kr.imcf.ott.persistence.repository.AccountRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
        Optional<RAccount> currentTokenInfo = Optional.empty();

        try {
            oAuth2Principal = jwtProvider.toPrincipal(token);
            currentTokenInfo = accountRedisRepository.findById(RAccount.idFormat(oAuth2Principal.getPlatformType(), oAuth2Principal.getEmail()));

            if(currentTokenInfo.isPresent() && currentTokenInfo.get().getAccessToken().equals(token)){
                accountRedisRepository.delete(currentTokenInfo.get());
                log.info(String.format("Logout Complete :: %s", token));
                return true;
            }
            else{
                log.error(String.format("Token is not existed :: %s", token));
            }

        } catch (Exception e) {
            log.error(String.format("Parsing Failed :: %s", token));
        }

        return false;
    }

}
