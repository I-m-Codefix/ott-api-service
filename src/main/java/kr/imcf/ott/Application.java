package kr.imcf.ott;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@ServletComponentScan
@SpringBootApplication
@PropertySource({
        "classpath:application-oauth.properties",
        "classpath:application-path.properties"
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
