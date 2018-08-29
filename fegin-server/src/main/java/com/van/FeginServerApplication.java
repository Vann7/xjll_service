package com.van;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FeginServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeginServerApplication.class, args);
    }
}
