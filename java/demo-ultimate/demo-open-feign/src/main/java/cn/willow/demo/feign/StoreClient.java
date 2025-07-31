/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2025 All Rights Reserved.
 */
package cn.willow.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author willow
 * Created by on 2025-07-31 14:09
 */
@Component
@FeignClient("${spring.application.name}")
public interface StoreClient {
    @RequestMapping(method = RequestMethod.GET, value = "/getStores")
    List<String> getStores();
}