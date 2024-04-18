package cn.willow.demo.demo.spring.graceful.shutdown;

import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author willow
 * @date 2024/4/16
 */
@Setter
@RestController
public class TestController implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @RequestMapping("/test")
    public String test() throws InterruptedException {
        Thread.sleep(10000);
        return "test";
    }

    @GetMapping("/shutdown")
    public String shutdown() {
        ((ConfigurableApplicationContext) applicationContext).close();
        return "done";
    }
}
