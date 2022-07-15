package kr.imcf.ott.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static net.minidev.json.JSONValue.toJSONString;

@Component
@Aspect
public class LogAspect {

    // 데이터 : Header, Body, QueryString 데이터..
    // 메소드 타입, 요청 URI
    // 요청 시작 시작, 요청 응답 시간
    // custom annotation -> @Logging
    // @Logging

    // 변수명, 클래스, 메소드명 가져오는 기능
    // Java Reflection

//
//    @Pointcut("within(kr.imcf.ott..*)")
//    public void onRequest() {}
//
//    @Around("onRequest()")
//    public Object logPostMapping(ProceedingJoinPoint joinPoint) throws Throwable {
//        Class clazz = joinPoint.getTarget().getClass();
//        Logger logger = LoggerFactory.getLogger(clazz);
//        Object result = null;
//        try {
//            result = joinPoint.proceed(joinPoint.getArgs());
//            return result;
//        } finally {
//            logger.info(getRequestUrl(joinPoint, clazz));
//            logger.info("parameters" + toJSONString(params(joinPoint)));
//            logger.info("response: " + toJSONString(result));
//        }
//    }
//
//    private String getRequestUrl(JoinPoint joinPoint, Class clazz) {
//
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//        RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
//        String baseUrl = requestMapping.value()[0];
//
//        String url = Stream.of(GetMapping.class, PutMapping.class, PostMapping.class,
//                PatchMapping.class, DeleteMapping.class, RequestMapping.class)
//                .filter(method::isAnnotationPresent)
//                .map(mappingClass -> getUrl(method, mappingClass, baseUrl))
//                .findFirst().orElse(null);
//        return url;
//
//    }
//
//    private String getUrl(Method method, Class<? extends Annotation> annotationClass, String baseUrl) {
//
//        Annotation annotation = method.getAnnotation(annotationClass);
//        String[] value;
//        String httpMethod = null;
//        try {
//            value = (String[]) annotationClass.getMethod("value").invoke(annotation);
//            httpMethod = (annotationClass.getSimpleName().replace("Mapping", "")).toUpperCase();
//        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
//            return null;
//        }
//        return String.format("%s %s%s", httpMethod, baseUrl, value.length > 0 ? value[0] : "");
//
//    }
//
//    private Map params(JoinPoint joinPoint) {
//
//        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
//        String[] parameterNames = codeSignature.getParameterNames();
//        Object[] args = joinPoint.getArgs();
//        Map<String, Object> params = new HashMap<>();
//        for (int i = 0; i < parameterNames.length; i++)
//            params.put(parameterNames[i], args[i]);
//        return params;
//
//    }

}
