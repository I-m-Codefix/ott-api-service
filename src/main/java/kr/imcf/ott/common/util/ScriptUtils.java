package kr.imcf.ott.common.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

// 서버 사이드에서 서블릿 조작을 돕는 클래스
// script를 사용하기 위한 유틸리티
public class ScriptUtils {
    public static void init(HttpServletResponse response) {
        response.setContentType("text/html; charset=euc-kr");
        response.setCharacterEncoding("euc-kr");
    }

    public static void alert(HttpServletResponse response, String alertText) throws IOException {
        init(response);
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + alertText + "');</script> ");
        out.flush();
    }

    public static void alertAndMovePage(HttpServletResponse response, String alertText, String nextPage) throws IOException {
        init(response);
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + alertText + "'); location.href='" + nextPage + "';</script> ");
        out.flush();
    }

    public static void alertAndBackPage(HttpServletResponse response, String alertText) throws IOException {
        init(response);
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + alertText + "'); history.go(-1);</script>");
        out.flush();
    }

    public static void sendJsonData(HttpServletResponse response, String sendUri, Object obj) throws IOException, InvocationTargetException, IllegalAccessException {
        init(response);
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        PrintWriter out = response.getWriter();
        out.println(String.format("로그인 데이터를 전송합니다... <script> location.href='%s';</script>", getQueryString(sendUri, obj)));
        out.flush();
    }

    public static String getQueryString(String url, Object classObject)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return URLEncoder.encode(url + ReflectionUtils.getInstanceAll(classObject, "?", "&", "="), StandardCharsets.UTF_8);
    }
}
