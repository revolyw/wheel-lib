/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2025 All Rights Reserved.
 */
package cn.willow.demo.feign.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author willow
 * Created by on 2025-07-31 13:33
 */
@EnableEurekaClient
@SpringBootApplication
public class FeignProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(FeignProviderApp.class, args);
    }
}