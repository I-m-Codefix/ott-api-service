//package kr.imcf.ott.account.oauth2;
//
//import kr.imcf.ott.account.oauth2.KakaoOAuth2User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//import org.springframework.util.Assert;
//
//import java.util.Map;
//import java.util.Set;
//
//@Service
//public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        Assert.notNull(userRequest, "userRequest cannot be null");
//
//        ClientRegistration.ProviderDetails.UserInfoEndpoint userInfoEndpoint = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint();
//
//        String userInfoUri = userInfoEndpoint.getUri();
//        validateUserInfoUri(userRequest, userInfoUri);
//
//        String nameAttributeKey = userInfoEndpoint.getUserNameAttributeName();
//        validateUserNameAttributeName(userRequest, nameAttributeKey);
//
//        Map<String, Object> attributes = getAttributes(userRequest);
//        Set<GrantedAuthority> authorities = getAuthorities(userRequest, attributes);
//
//        return KakaoOAuth2User2User.builder()
//                .authorities(authorities)
//                .attributes(attributes)
//                .nameAttributeKey(nameAttributeKey)
//                .build();
//    }
//
//    private Set<GrantedAuthority> getAuthorities(OAuth2UserRequest userRequest, Map<String, Object> attributes) {
//        return null;
//    }
//
//    private Map<String, Object> getAttributes(OAuth2UserRequest userRequest) {
//        return null;
//    }
//
//    private void validateUserNameAttributeName(OAuth2UserRequest userRequest, String nameAttributeKey) {
//    }
//
//    public void validateUserInfoUri(OAuth2UserRequest userRequest, String userInfoUri) {
//    }
//
//}