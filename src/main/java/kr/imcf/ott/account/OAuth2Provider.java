//package kr.imcf.ott.account;
//
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//
//public enum OAuth2Provider {
//
//    KAKAO {
//        @Override
//        public ClientRegistration.Builder getBuilder(String registrationId) {
//            ClientRegistration.Builder builder = getBuilder(registrationId,
//                    ClientAuthenticationMethod.POST);
//            builder.scope("profile,account_email");
//            builder.authorizationUri("https://kauth.kakao.com/oauth/authorize");
//            builder.tokenUri("https://kauth.kakao.com/oauth/token");
//            builder.userInfoUri("https://kapi.kakao.com/v2/user/me");
//            builder.userNameAttributeName("id");
//            builder.clientName("Kakao");
//            return builder;
//        }
//    },
//    NAVER {
//        @Override
//        public ClientRegistration.Builder getBuilder(String registrationId) {
//            ClientRegistration.Builder builder = getBuilder(registrationId,
//                    ClientAuthenticationMethod.POST);
//            builder.scope("profile");
//            builder.authorizationUri("https://nid.naver.com/oauth2.0/authorize");
//            builder.tokenUri("https://nid.naver.com/oauth2.0/token");
//            builder.userInfoUri("https://openapi.naver.com/v1/nid/me");
//            builder.userNameAttributeName("id");
//            builder.clientName("Naver");
//            return builder;
//        }
//    };
//
//
//    protected final ClientRegistration.Builder getBuilder(
//            String registrationId, ClientAuthenticationMethod method) {
//        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
//        builder.clientAuthenticationMethod(method);
//        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
//        builder.redirectUriTemplate("https://icmf.kr/login/oauth2/code/{registrationId}");
//        //builder.redirectUri("{baseUrl}/login/oauth2/code/{registrationId}");
//        return builder;
//    }
//
//    public abstract ClientRegistration.Builder getBuilder(String registrationId);
//}
