package xyz.xhui.awardsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AwardsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwardsystemApplication.class, args);
    }

}
