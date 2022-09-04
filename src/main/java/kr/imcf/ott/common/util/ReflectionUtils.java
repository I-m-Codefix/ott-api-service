package kr.imcf.ott.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ReflectionUtils {

    public static String getInstanceAll(Object classObject, String first, String between, String eq)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class clz = classObject.getClass();
        Method[] methods = clz.getMethods();
        String queryString = "";

        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            String methodName = method.getName();
            if (!Objects.equals(methodName, "getClass") && methodName.startsWith("get") && methodName.length() > 3
                    && method.getParameterTypes().length == 0) {

                String field = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                Object value = method.invoke(classObject);
                if(value == null)
                    value = "";
                else if(AsciiUtils.isKorean(value.toString()))
                    value = URLEncoder.encode(value.toString(), StandardCharsets.UTF_8);

                if (queryString.equals(""))
                    queryString += first + field + eq + value;
                else
                    queryString += between + field + eq + value;
            }
        }

        return queryString;
    }
}
