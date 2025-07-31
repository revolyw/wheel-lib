/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2021 All Rights Reserved.
 */

package cn.willow.demo.gateway.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.DefaultCorsProcessor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class RegexCorsProcessor extends DefaultCorsProcessor {

    private static final Logger               logger      = LoggerFactory
        .getLogger(RegexCorsProcessor.class);

    private static final Map<String, Pattern> PATTERN_MAP = new ConcurrentHashMap<>(1);

    /**
     * 跨域请求，会通过此方法检测请求源是否被允许
     *
     * @param config        CORS 配置
     * @param requestOrigin 请求源
     * @return 如果请求源被允许，返回请求源；否则返回 null
     */
    @Override
    protected String checkOrigin(CorsConfiguration config, String requestOrigin) {

        // 先调用父类的 checkOrigin 方法，保证原来的方式继续支持
        String result = super.checkOrigin(config, requestOrigin);
        if (result != null) {
            return result;
        }

        // 获取 @CrossOrigin 中配置的 origins
        List<String> allowedOrigins = config.getAllowedOrigins();
        if (CollectionUtils.isEmpty(allowedOrigins)) {
            return null;
        }

        String localResult = checkOriginWithRegex(allowedOrigins, requestOrigin);
        logger.info("check regex result is: {}, allowedOrigins:{}, requestOrigin:{}", localResult,
                JSON.toJSONString(allowedOrigins), requestOrigin);
        return localResult;
    }

    /**
     * 用正则的方式来校验 requestOrigin
     */
    private String checkOriginWithRegex(List<String> allowedOrigins, String requestOrigin) {

        for (String allowedOrigin : allowedOrigins) {
            Pattern pattern = PATTERN_MAP.computeIfAbsent(allowedOrigin, Pattern::compile);
            if (pattern.matcher(requestOrigin).matches()) {
                return requestOrigin;
            }
        }

        return null;
    }

}
