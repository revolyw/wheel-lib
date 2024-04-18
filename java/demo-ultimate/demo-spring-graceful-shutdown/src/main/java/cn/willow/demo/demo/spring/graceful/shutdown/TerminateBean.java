package cn.willow.demo.demo.spring.graceful.shutdown;

import jakarta.annotation.PreDestroy;

/**
 * @author willow
 * @date 2024/4/16
 */
public class TerminateBean {
    @PreDestroy
    public void onDestroy() {
        System.out.println("spring container is destroy!");
    }
}
