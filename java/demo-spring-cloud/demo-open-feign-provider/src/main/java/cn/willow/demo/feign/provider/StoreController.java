/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2025 All Rights Reserved.
 */
package cn.willow.demo.feign.provider;

import cn.willow.demo.feign.client.facade.StoreFacade;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author willow
 * Created by on 2025-07-31 14:52
 */

@RestController
public class StoreController implements StoreFacade {
    @Override
    public String getStore() {
        return "hello world!";
    }
}