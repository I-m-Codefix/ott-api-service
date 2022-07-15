package kr.imcf.ott.account;

import kr.imcf.ott.account.platform.KakaoUser;
import kr.imcf.ott.common.type.PlatformType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component
public class OAuth2LoginSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    @Autowired
    AccountService accountService;

    /*
        카카오톡 SNS를 사용하여 로그인을 하였지만
        세션은 일반 로그인 사용자처럼 변환하여 처리해준다.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        KakaoUser kakaoUser = new KakaoUser(((DefaultOAuth2User) authentication.getPrincipal()).getAttributes(), ((DefaultOAuth2User) authentication.getPrincipal()).getAttribute("accessToken"), "ROLE");
        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                this.getUserAccount(kakaoUser.getEmail(), kakaoUser.getPlatformType()),
                String.valueOf(UUID.randomUUID()),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + kakaoUser.getRole()))
        );
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        response.sendRedirect("/");
    }

    public UserDetails getUserAccount(String email, PlatformType platformType){
        return accountService.loadUserByUsername(email, platformType);
    }

}


