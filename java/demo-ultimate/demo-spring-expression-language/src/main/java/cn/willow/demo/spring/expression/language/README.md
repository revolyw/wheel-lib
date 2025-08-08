# Spring Expression Language (SpEL) 快速掌握指南

## 什么是 SpEL？

Spring Expression Language (SpEL) 是一种强大的表达式语言，支持在运行时查询和操作对象图。它的语法类似于 Unified EL 但提供了额外的功能，最重要的是方法调用和基本的字符串模板功能。

## 核心特性

1. **文字表达式**: 支持字符串、日期、数值、布尔值和 null 值
2. **属性访问**: 可以访问对象的属性、数组、列表和映射
3. **内联集合**: 可以直接在表达式中创建列表和映射
4. **数组构造**: 支持动态创建数组
5. **运算符**: 包括算术、关系、逻辑和正则表达式运算符
6. **方法调用**: 可以调用对象的方法
7. **变量**: 支持在表达式中使用变量
8. **自定义函数**: 可以注册和使用自定义函数
9. **Bean 引用**: 可以引用 Spring 容器中的 Bean
10. **集合操作**: 支持集合选择和投影
11. **模板表达式**: 支持字符串模板功能

## 基本语法

### 表达式界定符

- `#{}`: SpEL 表达式界定符，用于执行表达式
- `${}`: 属性占位符，用于读取属性值

### 常用操作符

| 操作符 | 描述 | 示例 |
|-------|------|------|
| `+` | 加法/字符串连接 | `#{1 + 2}` 或 `#{name + ' ' + surname}` |
| `-` | 减法 | `#{5 - 3}` |
| `*` | 乘法 | `#{3 * 4}` |
| `/` | 除法 | `#{10 / 2}` |
| `%` | 取模 | `#{10 % 3}` |
| `==` | 等于 | `#{age == 25}` |
| `!=` | 不等于 | `#{name != 'John'}` |
| `<` | 小于 | `#{age < 30}` |
| `>` | 大于 | `#{price > 100}` |
| `<=` | 小于等于 | `#{count <= 10}` |
| `>=` | 大于等于 | `#{score >= 60}` |
| `and` | 逻辑与 | `#{hasLicense and hasInsurance}` |
| `or` | 逻辑或 | `#{isAdmin or isSuperUser}` |
| `!` | 逻辑非 | `#{!isActive}` |
| `&&` | 逻辑与 | `#{hasLicense && hasInsurance}` |
| `||` | 逻辑或 | `#{isAdmin || isSuperUser}` |

## 核心类

1. `ExpressionParser`: 解析表达式字符串
2. `Expression`: 定义表达式本身，可以调用 `getValue` 方法进行计算
3. `EvaluationContext`: 定义表达式求值的上下文，包括属性、方法、字段的解析以及类型转换

## 快速入门步骤

### 1. 添加依赖

在 Maven 项目中，SpEL 已经包含在 Spring 的核心模块中:

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>5.3.21</version>
</dependency>
```

### 2. 基本使用

```java
// 创建解析器
ExpressionParser parser = new SpelExpressionParser();

// 解析表达式
Expression exp = parser.parseExpression("'Hello World'");

// 求值
String message = (String) exp.getValue();
```

### 3. 使用上下文

```java
// 创建上下文
StandardEvaluationContext context = new StandardEvaluationContext();

// 设置变量
context.setVariable("name", "张三");

// 使用变量
Expression exp = parser.parseExpression("#name.toUpperCase()");
String result = exp.getValue(context, String.class);
```

## 常用功能示例

### 文字表达式

```java
// 字符串
String hello = parser.parseExpression("'Hello World'").getValue(String.class);

// 数字
int num = parser.parseExpression("123").getValue(Integer.class);

// 布尔值
boolean flag = parser.parseExpression("true").getValue(Boolean.class);

// null
Object nil = parser.parseExpression("null").getValue();
```

### 属性访问

```java
Inventor inventor = new Inventor();
inventor.setName("张三");

EvaluationContext context = new StandardEvaluationContext(inventor);

// 访问属性
String name = parser.parseExpression("name").getValue(context, String.class);

// 访问数组元素
String[] inventions = {"电灯", "电话"};
inventor.setInventions(inventions);
String firstInvention = parser.parseExpression("inventions[0]").getValue(context, String.class);

// 访问映射元素
Map<String, String> addresses = new HashMap<>();
addresses.put("home", "北京市");
inventor.setAddresses(addresses);
String homeAddress = parser.parseExpression("addresses['home']").getValue(context, String.class);
```

### 内联集合

```java
// 内联列表
List<Integer> numbers = (List<Integer>) parser.parseExpression("{1,2,3,4,5}").getValue();

// 内联映射
Map<String, Object> map = (Map<String, Object>) parser.parseExpression("{'name':'张三', 'age':25}").getValue();
```

### 方法调用

```java
String name = parser.parseExpression("'张三'.toUpperCase()").getValue(String.class);
```

### 运算符

```java
// 三元运算符
String result = parser.parseExpression("false ? 'trueExp' : 'falseExp'").getValue(String.class);

// Elvis 运算符 (避免 NullPointerException)
String name = parser.parseExpression("name?:'Unknown'").getValue(context, String.class);
```

### 集合选择和投影

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

EvaluationContext context = new StandardEvaluationContext();
context.setVariable("numbers", numbers);

// 选择操作 - 选择偶数
List<Integer> even = (List<Integer>) parser.parseExpression("#numbers.?[#this % 2 == 0]").getValue(context);

// 投影操作 - 计算平方
List<Integer> squares = (List<Integer>) parser.parseExpression("#numbers.![#this * #this]").getValue(context);
```

## 在 Spring 中的典型应用场景

### 1. 注解中的使用

```java
@RequirePermission("user:edit:#{#userId}")
public void updateUser(Long userId, User user) {
    // 方法实现
}
```

### 2. XML 配置中的使用

```xml
<bean id="numberGuess" class="org.spring.samples.NumberGuess">
    <property name="randomNumber" value="#{ T(java.lang.Math).random() * 100.0 }"/>
</bean>
```

### 3. @Value 注解中的使用

```java
public class MyBean {
    @Value("#{systemProperties['user.region']}")
    private String defaultLocale;
    
    @Value("#{ T(java.lang.Math).random() * 100.0 }")
    private double randomValue;
}
```

## 最佳实践

1. **性能考虑**: 对于复杂的表达式，考虑缓存解析后的 Expression 对象
2. **安全性**: 避免执行用户输入的表达式，防止代码注入攻击
3. **错误处理**: 合理处理 ExpressionEvaluationException 等异常
4. **类型安全**: 使用 getValue 方法的类型参数确保返回值类型正确

## 学习建议

1. **循序渐进**: 从基本的文字表达式开始，逐步学习更复杂的特性
2. **实践为主**: 通过实际编码来掌握各种语法和功能
3. **查阅文档**: 遇到问题时查阅官方文档获取详细信息
4. **关注安全**: 在生产环境中使用时特别注意表达式注入风险

通过以上指南和示例，您可以快速掌握 SpEL 的核心功能并应用到实际项目中。