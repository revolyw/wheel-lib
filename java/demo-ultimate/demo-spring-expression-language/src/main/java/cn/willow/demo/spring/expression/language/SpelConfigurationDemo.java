package cn.willow.demo.spring.expression.language;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * SpEL在Spring配置中的使用示例
 * 演示如何在配置类中使用SpEL进行动态配置
 */
public class SpelConfigurationDemo {
    
    public static void main(String[] args) {
        // 模拟Spring环境中的配置解析
        
        // 创建系统属性
        Properties systemProperties = new Properties();
        systemProperties.setProperty("app.name", "lego-app");
        systemProperties.setProperty("app.version", "1.0.0");
        systemProperties.setProperty("app.environment", "development");
        systemProperties.setProperty("app.debug", "true");
        systemProperties.setProperty("server.port", "8080");
        
        // 创建配置上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("systemProperties", systemProperties);
        
        // 创建表达式解析器
        ExpressionParser parser = new SpelExpressionParser();
        
        System.out.println("=== SpEL在配置中的应用 ===");
        
        // 1. 读取系统属性
        String appName = parser.parseExpression("#systemProperties['app.name']").getValue(context, String.class);
        System.out.println("应用名称: " + appName);
        
        // 2. 条件表达式
        String environment = parser.parseExpression("#systemProperties['app.environment']").getValue(context, String.class);
        boolean isDev = parser.parseExpression("#systemProperties['app.environment'] == 'development'").getValue(context, Boolean.class);
        System.out.println("当前环境: " + environment);
        System.out.println("是否为开发环境: " + isDev);
        
        // 3. 数值计算
        int serverPort = parser.parseExpression("#systemProperties['server.port']").getValue(context, Integer.class);
        int managementPort = parser.parseExpression("#systemProperties['server.port'] + 1000").getValue(context, Integer.class);
        System.out.println("服务端口: " + serverPort);
        System.out.println("管理端口: " + managementPort);
        
        // 4. 字符串操作
        String appVersion = parser.parseExpression("#systemProperties['app.version']").getValue(context, String.class);
        String versionInfo = parser.parseExpression("'应用版本: ' + #systemProperties['app.name'] + ' v' + #systemProperties['app.version']").getValue(context, String.class);
        System.out.println(versionInfo);
        
        // 5. 三元操作符
        String debugMode = parser.parseExpression("#systemProperties['app.debug'] == 'true' ? '调试模式已启用' : '调试模式已禁用'").getValue(context, String.class);
        System.out.println(debugMode);
        
        // 6. 集合操作
        // 模拟多个数据库配置
        List<String> databaseUrls = Arrays.asList(
            "jdbc:mysql://localhost:3306/db1",
            "jdbc:mysql://localhost:3306/db2",
            "jdbc:mysql://localhost:3306/db3"
        );
        context.setVariable("databaseUrls", databaseUrls);
        
        // 选择第一个数据库URL
        String primaryDb = parser.parseExpression("#databaseUrls[0]").getValue(context, String.class);
        System.out.println("主数据库: " + primaryDb);
        
        // 7. 正则表达式
        boolean isValidPort = parser.parseExpression("#systemProperties['server.port'].matches('[0-9]{4}')").getValue(context, Boolean.class);
        System.out.println("端口格式是否正确: " + isValidPort);
        
        // 8. Elvis操作符（避免空指针）
        String description = parser.parseExpression("#systemProperties['app.description'] ?: '未提供描述信息'").getValue(context, String.class);
        System.out.println("应用描述: " + description);
        
        // 9. 安全导航操作符
        // 模拟可能为null的对象
        context.setVariable("config", null);
        String configValue = parser.parseExpression("#config?.name ?: '默认配置'").getValue(context, String.class);
        System.out.println("配置值: " + configValue);
        
        System.out.println("\n=== 复杂表达式示例 ===");
        
        // 10. 复杂表达式 - 基于环境的配置
        String logConfig = parser.parseExpression(
            "#systemProperties['app.environment'] == 'production' ? 'logback-prod.xml' : 'logback-dev.xml'")
            .getValue(context, String.class);
        System.out.println("日志配置文件: " + logConfig);
        
        // 11. 基于多个条件的复杂表达式
        String threadPoolSize = parser.parseExpression(
            "(#systemProperties['app.environment'] == 'development' ? 10 : " +
            "(#systemProperties['app.environment'] == 'test' ? 20 : 50))")
            .getValue(context, String.class);
        System.out.println("线程池大小: " + threadPoolSize);
    }
}