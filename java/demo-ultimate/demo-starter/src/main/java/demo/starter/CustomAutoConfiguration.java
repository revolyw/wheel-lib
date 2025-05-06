package demo.starter;

import com.google.common.util.concurrent.RateLimiter;
import demo.starter.contract.StartRateLimiter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author willow
 * @since 2024/12/17
 */
@Configuration
public class CustomAutoConfiguration {
    @Bean
    @ConditionalOnBean(StartRateLimiter.class)
    public RateLimiter qps1RateLimiter() {
        return RateLimiter.create(1);
    }
}
