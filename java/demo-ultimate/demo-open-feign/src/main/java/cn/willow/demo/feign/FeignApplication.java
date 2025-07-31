/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2025 All Rights Reserved.
 */
package cn.willow.demo.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author willow
 * Created by on 2025-07-31 13:33
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class FeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }
}