package cn.willow.demo.spring.springboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 完整的加载顺序如下：
 * 1. ~.spring-boot-devtools.properties
 * 2. @TestPropertySource
 * 3. @SpringTest 中的 properties
 * 4. 命令行参数
 * 5. SPRING_APPLICATION_JSON（内嵌在环境变量或系统属性中的内联 JSON）
 * 6. ServletConfig
 * 7. ServletContext
 * 8. JNDI
 * 9. Java System properties（System.getProperties）
 * 10. 操作系统环境变量
 * 11. RandomValuePropertySource
 * 12. jar 包外部 application-{profile}.properties
 * 13. jar 包内部 application-{profile}.properties
 * 14. jar 包外部 jar application.properties（通过 SPRING_CONFIG_LOCATION 指定）
 * 15. jar 包内部 application.properties
 * 16. 二方或三方 jar 包中的 application.properties
 * 17. @Configuration类上的 @Propertysource
 * 18. 默认配置（SpringApplication.setDefaultProperties）
 *
 * 说明：什么是 jar 包外部配置？例如通过 SPRING_CONFIG_LOCATION 指定，例如：java -jar myapp.jar --spring.config.location=file:/path/to/config/application.properties）
 * @author willow
 * @since 2024/12/17
 */
@Configuration
//@PropertySource("file:/tmp/application.properties")
public class CustomConfig {
}
