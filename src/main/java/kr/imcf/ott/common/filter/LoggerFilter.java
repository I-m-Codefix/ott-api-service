package kr.imcf.ott.common.filter;

import kr.imcf.ott.common.util.ClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class LoggerFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(((HttpServletRequest)request).getRequestURI());
        chain.doFilter(request, response);       // next filter
        stopWatch.stop();

        logger.info("--- IP : " + ClientUtils.getClientIP((HttpServletRequest) request));
        logger.info("--- Agent : " + ((HttpServletRequest) request).getHeader("User-Agent"));
        logger.info("--- StopWatch : " + stopWatch.toString());
        logger.info("--- Refer : " + ((HttpServletRequest) request).getHeader("referer"));
    }
}