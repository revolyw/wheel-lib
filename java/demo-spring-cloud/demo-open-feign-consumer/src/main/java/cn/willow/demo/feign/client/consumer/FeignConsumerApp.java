package cn.willow.demo.feign.client.consumer; /**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2025 All Rights Reserved.
 */

import cn.willow.demo.feign.client.facade.StoreFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author willow
 * Created by on 2025-07-31 15:53
 */
@EnableFeignClients
@RestController
@SpringBootApplication
public class FeignConsumerApp {
    @Resource
    private StoreFacade storeFacade;

    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerApp.class, args);
    }

    @GetMapping("/")
    public String get() {
        return storeFacade.getStore();
    }
}