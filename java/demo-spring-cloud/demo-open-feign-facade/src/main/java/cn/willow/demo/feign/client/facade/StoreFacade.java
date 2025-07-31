/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2025 All Rights Reserved.
 */
package cn.willow.demo.feign.client.facade;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author willow
 * Created by on 2025-07-31 18:08
 */
@RequestMapping
public interface StoreFacade {
    @RequestMapping(method = RequestMethod.GET, value = "/getStore")
    String getStore();
}