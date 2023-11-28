package me.ooify.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("me.ooify.api.mapper")
public class WebapiJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebapiJavaApplication.class, args);
    }

}
