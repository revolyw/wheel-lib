package cn.willow.demo.spring.mvc;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author willow
 * @since 2024/12/17
 */
@RestController
public class RateLimitController {
    @Autowired
    private RateLimiter qps1RateLimiter;
    @RequestMapping("/qps1")
    public String qps1() {
        double acquire = qps1RateLimiter.acquire(5);
        return String.valueOf(acquire + "-" + new Date());
    }
}
