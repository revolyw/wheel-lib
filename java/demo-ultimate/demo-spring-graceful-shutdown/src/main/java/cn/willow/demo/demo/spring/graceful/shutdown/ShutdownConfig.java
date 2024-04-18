package cn.willow.demo.demo.spring.graceful.shutdown;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author willow
 * @date 2024/4/16
 */
@Configuration
public class ShutdownConfig {
    @Bean
    public TerminateBean terminateBean() {
        return new TerminateBean();
    }
}
