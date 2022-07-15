package kr.imcf.ott;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@EnableAspectJAutoProxy
@ServletComponentScan
@SpringBootApplication
@EnableJpaAuditing
@PropertySource({
        "classpath:application-oauth.properties",
        "classpath:smtp.properties"
})
public class Application {

    @Value("${imcf.image.upload.path}")
    private String uploadPath;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean(name = "uploadPath")
    public String uploadPath() {
        return uploadPath;
    }
}
