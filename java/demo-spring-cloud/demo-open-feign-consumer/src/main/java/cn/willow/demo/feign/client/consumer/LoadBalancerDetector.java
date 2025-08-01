package cn.willow.demo.feign.client.consumer;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 负载均衡检测器，用于查看当前项目使用的负载均衡方法
 */
@Component
public class LoadBalancerDetector {

    @Autowired
    private SpringClientFactory springClientFactory;

    /**
     * 获取指定服务的负载均衡策略信息
     *
     * @param serviceName 服务名称
     * @return 负载均衡策略信息
     */
    public Map<String, Object> getLoadBalancerInfo(String serviceName) {
        Map<String, Object> info = new HashMap<>();

        // 获取服务的负载均衡规则
        IRule rule = springClientFactory.getInstance(serviceName, IRule.class);
        IPing ping = springClientFactory.getInstance(serviceName, IPing.class);

        if (rule != null) {
            info.put("serviceName", serviceName);
            info.put("loadBalancerRuleClass", rule.getClass().getName());
            info.put("description", getRuleDescription(rule));
        } else {
            info.put("serviceName", serviceName);
            info.put("loadBalancerRule", "Unknown");
            info.put("description", "无法确定负载均衡策略");
        }

        // 添加ping信息
        if (ping != null) {
            info.put("pingClass", ping.getClass().getName());
        }

        return info;
    }

    /**
     * 获取所有服务的负载均衡策略信息
     *
     * @return 所有服务的负载均衡策略信息
     */
    public Map<String, Map<String, Object>> getAllLoadBalancerInfo() {
        Map<String, Map<String, Object>> allInfo = new HashMap<>();

        // 获取配置的服务列表
        String[] serviceNames = springClientFactory.getContextNames().toArray(new String[0]);
        for (String serviceName : serviceNames) {
            allInfo.put(serviceName, getLoadBalancerInfo(serviceName));
        }

        return allInfo;
    }

    /**
     * 根据规则类型返回描述信息
     *
     * @param rule 负载均衡规则
     * @return 规则描述
     */
    private String getRuleDescription(IRule rule) {
        if (rule instanceof RoundRobinRule) {
            return "轮询策略：按顺序依次选择服务器";
        }
        // 可以添加更多规则类型的描述
        return "自定义或未知的负载均衡策略";
    }
}
