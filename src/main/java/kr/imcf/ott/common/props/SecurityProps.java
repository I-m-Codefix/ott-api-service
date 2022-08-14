package kr.imcf.ott.common.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityProps {

    @Value("${spring.security.ignore-matchers}")
    public String[] IgnoreMatchers;
}
