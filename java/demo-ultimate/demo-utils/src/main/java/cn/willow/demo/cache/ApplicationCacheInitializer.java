package cn.willow.demo.cache;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author willow
 * @since 2025/1/19
 */
@Component
public class ApplicationCacheInitializer implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("do something after ApplicationCacheInitializer initialized");
    }
}
