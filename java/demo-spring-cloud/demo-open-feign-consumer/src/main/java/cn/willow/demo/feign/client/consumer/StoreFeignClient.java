/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2025 All Rights Reserved.
 */
package cn.willow.demo.feign.client.consumer;

import cn.willow.demo.feign.client.facade.StoreFacade;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author willow
 * Created by on 2025-07-31 14:09
 */
@FeignClient(name = "${provider.name}")
public interface StoreFeignClient extends StoreFacade {
}