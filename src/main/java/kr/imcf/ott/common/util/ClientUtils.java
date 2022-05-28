package kr.imcf.ott.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @fileName : ClientUtils.java
 * @author : rojae
 * @date : 2021-08-21
 * @description : 클라이언트의 접속 시, 처리될 기능.
 * ===========================================================
 * DATE         AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021-08-21   rojae      최초생성
 */
public class ClientUtils {

    /**
     * ==================================================================
     *
     * @methodName : getClientIP
     * @description : 클라이언트의 IP 가져오기
     * @func1 : 각종 헤더를 감지하여 아이피 리턴.
     * @author: rojae
     * @date : 2021-08-21
     * ==================================================================
     **/
    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

}

