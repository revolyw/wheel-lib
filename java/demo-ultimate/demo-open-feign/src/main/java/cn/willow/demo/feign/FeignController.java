/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2025 All Rights Reserved.
 */
package cn.willow.demo.feign;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author willow
 * Created by on 2025-07-31 14:33
 */
@RestController
public class FeignController {
    @RequestMapping(value="/getStores")
    public String getStores() {
        return "Hello Feign";
    }
}