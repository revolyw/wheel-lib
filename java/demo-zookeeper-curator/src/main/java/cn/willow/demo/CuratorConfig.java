package cn.willow.demo;

import com.google.common.collect.Lists;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * zookeeper 客户端初始化
 */
@Configuration
public class CuratorConfig {

    private final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("localhost:2181")
    private String connectString;
    private String namespace;
    private String aclId;
    private String auth;

    @Bean("curator4GatewayDiscovery")
    public CuratorFramework initDiscovery(){
                logger.debug(" curator开始初始化");
                RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
                ACLProvider provider = new ACLProvider() {
                    @Override
                    public List<ACL> getDefaultAcl() {
                return Lists.newArrayList(
                        new ACL(
                                ZooDefs.Perms.ALL,
                                new Id("digest", aclId)
                        )
                );
            }
            @Override
            public List<ACL> getAclForPath(String s) {
                return null;
            }
        };
        CuratorFramework framework = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .namespace(namespace)
                .retryPolicy(retryPolicy)
                .authorization("digest", auth.getBytes())
                .aclProvider(provider)
                .build();
        framework.start();
        Runtime.getRuntime().addShutdownHook(new Thread(framework::close));
        logger.debug("curator初始化完成 ");
        return framework;
    }

}
