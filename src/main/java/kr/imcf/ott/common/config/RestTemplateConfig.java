package kr.imcf.ott.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
@Slf4j
public class RestTemplateConfig {

    @Bean
    HttpClient httpClient() {
        return HttpClientBuilder.create()
                .setMaxConnTotal(100)    //최대 오픈되는 커넥션 수, 연결을 유지할 최대 숫자
                .setMaxConnPerRoute(30)   //IP, 포트 1쌍에 대해 수행할 커넥션 수, 특정 경로당 최대 숫자
                .build();
    }

    @Bean
    HttpComponentsClientHttpRequestFactory factory(HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(5000);        
        factory.setConnectTimeout(3000);     
        factory.setHttpClient(httpClient);

        return factory;
    }

    @Bean
    RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);

        restTemplate.getInterceptors().add((request, body, execution) -> {
            log.info("HTTP : {} || Destination : {} || Header : {} || Body : {} ",
                    request.getMethod(), request.getURI(), request.getHeaders(), new String(body, StandardCharsets.UTF_8));
            return execution.execute(request, body);
        });

        return restTemplate;
    }
}