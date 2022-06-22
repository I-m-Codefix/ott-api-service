package kr.imcf.ott.account;

import kr.imcf.ott.persistence.repository.AccountRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public void saveAuthorizedClient(OAuth2AuthorizedClient oAuth2AuthorizedClient, Authentication authentication) {
        String providerType = oAuth2AuthorizedClient.getClientRegistration().getRegistrationId();
        OAuth2AccessToken accessToken = oAuth2AuthorizedClient.getAccessToken();

        /** SNS 간편 로그인 처리 비즈니스 로직**/
//        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
//        KakaoUser kakaoUser = new KakaoUser(oauth2User.getAttributes(), accessToken, "USER");
//        UserAccount account = new UserAccount(kakaoUser);
//
//        Account savedAccount = accountRepository.findAccountByEmail(account.getAccount().getEmail());
//
//        if(savedAccount == null)
//            accountRepository.save(account.getAccount());
//        else if(!savedAccount.getProfileImage().equals(account.getAccount().getProfileImage())) {
//            System.out.println("[" + savedAccount.getPlatformType() + "] - 프로필 사진 변경을 감지하여, 업데이트를 합니다.");
//            savedAccount.setProfileImage(account.getAccount().getProfileImage());
//        }

    }

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String s, String s1) {
        throw new NotYetImplementedException();
    }

    @Override
    public void removeAuthorizedClient(String s, String s1) {
        throw new NotYetImplementedException();
    }
}