package cn.willow.demo.spring.expression.language;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * SpEL在注解中的使用示例
 */
public class SpelAnnotationDemo {
    
    public static void main(String[] args) throws Exception {
        // 创建测试对象
        UserService userService = new UserService();
        User user = new User(1L, "张三");
        
        // 模拟调用带注解的方法
        Method method = UserService.class.getMethod("getUserById", Long.class);
        RequirePermission annotation = method.getAnnotation(RequirePermission.class);
        
        if (annotation != null) {
            // 解析注解中的SpEL表达式
            ExpressionParser parser = new SpelExpressionParser();
            EvaluationContext context = new StandardEvaluationContext();
            context.setVariable("userId", user.getId());
            
            String permission = parser.parseExpression(annotation.value()).getValue(context, String.class);
            System.out.println("需要的权限: " + permission);
            
            // 检查权限
            if (userService.hasPermission(permission)) {
                System.out.println("权限检查通过");
                User result = userService.getUserById(user.getId());
                System.out.println("获取用户: " + result.getName());
            } else {
                System.out.println("权限检查失败");
            }
        }
    }
    
    /**
     * 用户实体类
     */
    static class User {
        private Long id;
        private String name;
        
        public User(Long id, String name) {
            this.id = id;
            this.name = name;
        }
        
        public Long getId() {
            return id;
        }
        
        public String getName() {
            return name;
        }
    }
    
    /**
     * 权限注解，支持SpEL表达式
     */
    @interface RequirePermission {
        String value();
    }
    
    /**
     * 用户服务类
     */
    static class UserService {
        /**
         * 根据ID获取用户信息
         * @param id 用户ID
         * @return 用户信息
         */
        @RequirePermission("user:view:#{#userId}")
        public User getUserById(Long id) {
            // 模拟从数据库获取用户
            return new User(id, "用户" + id);
        }
        
        /**
         * 检查是否有指定权限
         * @param permission 权限标识
         * @return 是否有权限
         */
        public boolean hasPermission(String permission) {
            // 模拟权限检查逻辑
            System.out.println("检查权限: " + permission);
            // 这里简化处理，实际应该查询数据库或缓存
            return true;
        }
    }
}