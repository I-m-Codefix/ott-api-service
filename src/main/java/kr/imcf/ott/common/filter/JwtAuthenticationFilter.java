package kr.imcf.ott.common.filter;

import kr.imcf.ott.account.oauth2.AuthenticationToken;
import kr.imcf.ott.account.oauth2.OAuth2Principal;
import kr.imcf.ott.common.jwt.JwtProvider;
import kr.imcf.ott.common.props.JwtProps;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final JwtProps jwtProps;

    public JwtAuthenticationFilter(JwtProvider jwtProvider, JwtProps jwtProps) {
        this.jwtProvider = jwtProvider;
        this.jwtProps = jwtProps;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(jwtProps.jwtHeaderName);
        System.out.println(jwtProps.jwtHeaderName + " : " + token);

        if (token == null || token.equals("")) {
            emptyTokenResponse(httpServletResponse);
        } else if (!jwtProvider.verify(token)) {
            setDenialResponse(httpServletResponse);
        } else{
            try {
                OAuth2Principal oAuth2Principal = jwtProvider.toPrincipal(token);
                AuthenticationToken authenticationToken = new AuthenticationToken(oAuth2Principal);
                authenticationToken.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                System.out.println("SET AUTHENTICATION :: " + authenticationToken);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void emptyTokenResponse(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        httpServletResponse.getWriter().println(
                "{ \"status\" : \"" + 403
                        + "\", \"message\" : \"" + "헤더에 토큰이 존재하지 않습니다\"" + "}"
        );
    }

    private void setDenialResponse(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.getWriter().println(
                "{ \"status\" : \"" + 403
                        + "\", \"message\" : \"" + "토큰 검증에 실패하였습니다\"" + "}"
        );
    }

}
