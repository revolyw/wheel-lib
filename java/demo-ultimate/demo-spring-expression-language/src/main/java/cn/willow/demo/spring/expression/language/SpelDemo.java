package cn.willow.demo.spring.expression.language;

import lombok.Data;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring Expression Language (SpEL) 演示类
 * 展示SpEL的各种常用功能和语法
 */
public class  SpelDemo {
    
    public static void main(String[] args) {
        // 创建表达式解析器
        ExpressionParser parser = new SpelExpressionParser();
        
        // 1. 文字表达式 (Literal Expressions)
        System.out.println("=== 文字表达式 ===");
        System.out.println("'Hello SpEL': " + parser.parseExpression("'Hello SpEL'").getValue());
        System.out.println("3.14159: " + parser.parseExpression("3.14159").getValue());
        System.out.println("true: " + parser.parseExpression("true").getValue());
        System.out.println("null: " + parser.parseExpression("null").getValue());
        
        // 2. 属性、数组、列表、映射访问
        System.out.println("\n=== 属性、数组、列表、映射访问 ===");
        // 创建测试对象
        Inventor inventor = new Inventor("张三", 30, "中国");
        
        StandardEvaluationContext context = new StandardEvaluationContext(inventor);
        
        // 访问属性
        System.out.println("姓名: " + parser.parseExpression("name").getValue(context));
        System.out.println("年龄: " + parser.parseExpression("age").getValue(context));
        
        // 设置列表属性
        inventor.setInventions(new String[]{"电灯", "电话", "留声机"});
        System.out.println("第一项发明: " + parser.parseExpression("inventions[0]").getValue(context));
        
        // 设置映射属性
        Map<String, String> addresses = new HashMap<>();
        addresses.put("home", "北京市朝阳区");
        addresses.put("work", "北京市海淀区");
        inventor.setAddresses(addresses);
        System.out.println("家庭地址: " + parser.parseExpression("addresses['home']").getValue(context));
        
        // 3. 内联列表和映射
        System.out.println("\n=== 内联列表和映射 ===");
        List<Integer> numbers = (List<Integer>) parser.parseExpression("{1,2,3,4}").getValue();
        System.out.println("内联列表: " + numbers);
        
        Map<String, Object> map = (Map<String, Object>) parser.parseExpression("{'name':'张三', 'age':30}").getValue();
        System.out.println("内联映射: " + map);
        
        // 4. 数组构造
        System.out.println("\n=== 数组构造 ===");
        int[] array = (int[]) parser.parseExpression("new int[]{1,2,3}").getValue();
        System.out.println("构造的数组: " + Arrays.toString(array));
        
        // 5. 方法调用
        System.out.println("\n=== 方法调用 ===");
        System.out.println("转大写: " + parser.parseExpression("'abc'.toUpperCase()").getValue(context));
        
        // 6. 运算符
        System.out.println("\n=== 运算符 ===");
        System.out.println("加法运算: " + parser.parseExpression("1 + 2").getValue());
        System.out.println("关系运算: " + parser.parseExpression("3 > 2").getValue());
        System.out.println("逻辑运算: " + parser.parseExpression("true and false").getValue());
        System.out.println("三元运算符: " + parser.parseExpression("true ? '是' : '否'").getValue());
        System.out.println("Elvis运算符: " + parser.parseExpression("'姓名' ?: '未知'").getValue());
        
        // 7. 变量
        System.out.println("\n=== 变量 ===");
        context.setVariable("newName", "李四");
        parser.parseExpression("name = #newName").getValue(context);
        System.out.println("修改后的姓名: " + inventor.getName());
        
        // 8. 集合选择和投影
        System.out.println("\n=== 集合选择和投影 ===");
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6);
        StandardEvaluationContext listContext = new StandardEvaluationContext();
        listContext.setVariable("numList", numList);
        
        // 选择偶数
        List<Integer> evenNumbers = (List<Integer>) parser.parseExpression("#numList.?[#this % 2 == 0]").getValue(listContext);
        System.out.println("偶数: " + evenNumbers);
        
        // 投影平方
        List<Integer> squares = (List<Integer>) parser.parseExpression("#numList.![#this * #this]").getValue(listContext);
        System.out.println("平方: " + squares);
        
        // 9. 安全导航操作符
        System.out.println("\n=== 安全导航操作符 ===");
        System.out.println("安全访问可能为null的对象属性: " + parser.parseExpression("addresses.?['work']").getValue(context));

        // 10. 模板表达式
        System.out.println("\n=== 模板表达式 ===");
        context.setVariable("name", "王五");
        String template = parser.parseExpression("Hello, #{#name}!", 
                new org.springframework.expression.ParserContext() {
                    @Override
                    public boolean isTemplate() {
                        return true;
                    }
                    
                    @Override
                    public String getExpressionPrefix() {
                        return "#{";
                    }
                    
                    @Override
                    public String getExpressionSuffix() {
                        return "}";
                    }
                }).getValue(context, String.class);
        System.out.println("模板表达式结果: " + template);
    }
    
    /**
     * 发明家类，用于演示SpEL功能
     */
    @Data
    static class Inventor {
        private String name;
        private int age;
        private String nationality;
        private String[] inventions;
        private Map<String, String> addresses;
        private Date birthdate;
        
        public Inventor(String name, int age, String nationality) {
            this.name = name;
            this.age = age;
            this.nationality = nationality;
            this.birthdate = new GregorianCalendar(1856, Calendar.AUGUST, 26).getTime();
        }
    }
}