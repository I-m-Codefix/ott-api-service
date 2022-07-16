//package kr.imcf.ott.account.oauth2;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if (isAppropriateRequestForFilter(request)) {
//            try {
//                String token = jwtUtil.resolveToken(request);
//                Authentication authentication = jwtUtil.getAuthentication(token);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//            catch (JWTVerificationException e) {
//                /* ... */
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    private boolean isAppropriateRequestForFilter(HttpServletRequest request) {
//        return false;
//    }
//
//    /* ... */
//
//}