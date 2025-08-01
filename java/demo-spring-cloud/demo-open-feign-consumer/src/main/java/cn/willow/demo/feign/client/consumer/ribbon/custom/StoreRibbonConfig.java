/**
 * @author willow
 * <p>
 * Created by
 * on 2025-08-01
 */
package cn.willow.demo.feign.client.consumer.ribbon.custom;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;

public class StoreRibbonConfig {
    @Bean
    public IRule storeRibbonRule() {
        return new RoundRobinRule();
    }

    @Bean
    public IPing storeRibbonPing() {
        return new PingUrl();
    }
}