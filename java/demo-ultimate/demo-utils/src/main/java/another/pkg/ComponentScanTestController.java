package another.pkg;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 {@link org.springframework.context.annotation.ComponentScan}
 * 说明：@Service @Controller @Component @Repository 等注解
 * 如果不在 @SpringBootApplication 注解的主类所在的包或者其子包中
 * 那么就需要显示的 @ComponentScan 对应的包，以支持 spring 容器将它们注册为 bean
 * @author willow
 * @since 2024/12/17
 */
@RestController
public class ComponentScanTestController {
    @RequestMapping("/test-scan-component")
    public String test() {
        return "test-scan-component";
    }
}
