package cn.willow.demo.feign.client.consumer;

import cn.willow.demo.feign.client.consumer.ribbon.DefaultRibbonConfig;
import cn.willow.demo.feign.client.consumer.ribbon.custom.StoreRibbonConfig;
import cn.willow.demo.feign.client.facade.StoreFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author willow
 * Created by on 2025-07-31 15:53
 */
@RibbonClients(value = {
        @RibbonClient(value = "${provider.name}", configuration = StoreRibbonConfig.class)
}, defaultConfiguration = DefaultRibbonConfig.class)
@EnableFeignClients
@RestController
@SpringBootApplication
public class FeignConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerApp.class, args);
    }

    @Resource
    private StoreFacade storeFacade;
    @Value("${provider.name}")
    private String providerName;
    @Autowired
    private LoadBalancerDetector loadBalancerDetector;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/")
    public String get() {
        return storeFacade.getStore();
    }

    /**
     * 查看指定服务的负载均衡信息
     *
     * @return 负载均衡信息
     */
    @GetMapping("/loadbalancer/info")
    public Map<String, Object> getLoadBalancerInfo() {
        return loadBalancerDetector.getLoadBalancerInfo(providerName);
    }

    /**
     * 查看所有服务的负载均衡信息
     *
     * @return 所有服务的负载均衡信息
     */
    @GetMapping("/loadbalancer/all")
    public Map<String, Map<String, Object>> getAllLoadBalancerInfo() {
        return loadBalancerDetector.getAllLoadBalancerInfo();
    }

    /**
     * 查看服务实例选择信息
     *
     * @return 实例选择信息
     */
    @GetMapping("/chooseServer")
    public String chooseServer() {
        ServiceInstance serviceInstance = loadBalancerClient.choose(providerName);
        System.out.println("getInstanceId: " + serviceInstance.getInstanceId());
        System.out.println("getServiceId: " + serviceInstance.getServiceId());
        System.out.println("getHost: " + serviceInstance.getHost());
        System.out.println("getPort: " + serviceInstance.getPort());
        System.out.println("getScheme: " + serviceInstance.getScheme());
        System.out.println("getUri: " + serviceInstance.getUri());
        return serviceInstance.getUri().toString();
    }
}