package com.seungse.amadda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class AmaddaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmaddaApplication.class, args);
    }

}
