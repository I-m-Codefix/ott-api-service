package kr.imcf.ott.account.oauth2;

import kr.imcf.ott.common.jwt.JwtProvider;
import kr.imcf.ott.common.props.JwtProps;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final JwtProvider jwtProvider;
    private final JwtProps jwtProps;

    public CustomAuthenticationEntryPoint(JwtProvider jwtProvider, JwtProps jwtProps) {
        this.jwtProvider = jwtProvider;
        this.jwtProps = jwtProps;
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        String token = httpServletRequest.getHeader(jwtProps.jwtHeaderName);

//        try {
//            OAuth2Principal oAuth2Principal = jwtProvider.toPrincipal(token);
//            AuthenticationToken authenticationToken = new AuthenticationToken(oAuth2Principal);
//            authenticationToken.setAuthenticated(true);
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            System.out.println("SET AUTHENTICATION :: " + authenticationToken);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
    }

    private void debugRequestData(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = httpServletRequest.getHeader(headerName);
            out.println(headerName + " : " + headerValue + " <br> ");
        }
    }

}
