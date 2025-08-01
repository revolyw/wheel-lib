/**
 * @author willow
 * <p>
 * Created by
 * on 2025-08-01
 */
package cn.willow.demo.feign.client.consumer.ribbon;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * 默认Ribbon配置类，支持的配置如下
 * IClientConfig：客户端配置接口，
 * 用于获取和设置 Ribbon
 * 客户端的配置属性。
 * ServerList：服务列表接口，定义了如何获取服务器列表。
 * ServerListFilter：服务列表过滤器接口，允许对服务器列表进行过滤。
 * ILoadBalancer：负载均衡器接口，定义了负载均衡的基本操作。
 * IPing：用于健康检查。
 * IRule：用于负载均衡策略。
 */
public class DefaultRibbonConfig {
    /**
     * 负载均衡配置
     * 可选：
     * （默认）区域可用性规则，避免故障区域 com.netflix.loadbalancer.ZoneAvoidanceRule
     * 选择最低并发请求的服务器 com.netflix.loadbalancer.BestAvailableRule
     * 启用客户端配置的轮询规则 com.netflix.loadbalancer.ClientConfigEnabledRoundRobinRule
     * 随机选择服务器 com.netflix.loadbalancer.RandomRule
     * 根据响应时间权重选择服务器 com.netflix.loadbalancer.ResponseTimeWeightedRule
     * 重试 com.netflix.loadbalancer.RetryRule
     * 轮询 com.netflix.loadbalancer.RoundRobinRule
     * 基于响应时间权重的轮询 com.netflix.loadbalancer.WeightedResponseTimeRule
     *
     * @return 负载均衡实例
     */
    @ConditionalOnMissingBean
    @Bean
    public IRule defaultRibbonRule() {
        return new RoundRobinRule();
    }

    /**
     * 健康检查机制
     * 可选：
     * 默认选项，不进行健康检查 com.netflix.loadbalancer.DummyPing
     * 空操作，始终返回存活状态 com.netflix.loadbalancer.NoOpPing
     * 通过 http 请求检查 com.netflix.loadbalancer.PingUrl
     * 根据配置常量判断实例状态 com.netflix.loadbalancer.PingConstant
     * 结合 Eureka 服务进行健康检查 com.netflix.niws.loadbalancer.NIWSDiscoveryPing
     *
     * @return IPing 实例
     */
    @ConditionalOnMissingBean
    @Bean
    public IPing defaultRibbonPing() {
        return new NIWSDiscoveryPing();
    }
}