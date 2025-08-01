# wheel-lib 项目结构总结

## 一级目录结构

```
.
├── ai/
├── docker_images/
├── front-end/
├── groovy/
├── java/
├── js/
├── k8s/
├── leetcode/
├── python/
└── spring/
```


## 二级目录详细内容

### ai 目录
- demo-mcp/ (包含标准 Maven 项目结构，有 src 目录和 pom.xml 等)
- demo-mcp-client/ (Maven 客户端项目)
- langchain/ (可能与 AI 相关的库)
- langchain4j-demo/ (Java 的 Langchain 演示项目)

### docker_images 目录
- example_multi_stage_build/ (多阶段构建的 Docker 示例)
- example_rsyslog/ (rsyslog 相关的 Docker 示例)

### front-end 目录
- hello-world/ (前端 Hello World 示例项目，包含 Vue.js 项目结构)

### groovy 目录
- jpa-util/ (JPA 工具，包含 Generate POJOs.groovy 脚本)

### java 目录
- demo-dubbo-with-zookeeper/ (基于 Zookeeper 的 Dubbo 示例)
- demo-spring-cloud/ (Spring Cloud 微服务示例集合)
  - demo-eureka/ (Eureka 服务注册中心)
  - demo-eureka-client/ (Eureka 客户端)
  - demo-open-feign-consumer/ (Feign 消费者)
  - demo-open-feign-facade/ (Feign 接口定义)
  - demo-open-feign-provider/ (Feign 提供者)
  - demo-spring-cloud-parent/ (父项目)
  - demo-spring-gateway/ (Spring 网关)
- demo-ultimate/ (综合 Java 示例集合，包含多个子模块)
  - demo-aviator/ (Aviator 表达式引擎)
  - demo-guava/ (Google Guava 工具库)
  - demo-hystrix/ (Hystrix 熔断器)
  - demo-ioc/ (IoC 控制反转)
  - demo-java-agent/ (Java Agent)
  - demo-jmh/ (JMH 性能测试)
  - demo-spring-graceful-shutdown/ (Spring 优雅关闭)
  - demo-thread-pool/ (线程池示例)
  - demo-transaction/ (事务示例)
  - demo-utils/ (工具类示例)
  - demo-vulnerability/ (漏洞演示)
  - demo-websocket-with-springboot/ (WebSocket 示例)
  - demo-zookeeper-curator/ (Zookeeper Curator 示例)
  - 等等...
- jdk7/ (JDK7 相关示例)
- jdk8/ (JDK8 相关示例)
- sso-demo/ (单点登录相关示例)

### js 目录
- common/ (通用 JS 工具)
- component/ (JS 组件示例)
- markdown_editor/ (Markdown 编辑器)
- share_sdk_demo/ (分享 SDK 示例)
- webpack-demo/ (Webpack 示例项目)

### k8s 目录
- k8s-spring-boot-demo/ (K8s 上的 Spring Boot 示例)
- kind/ (Kind 工具相关配置，用于本地 Kubernetes)

### leetcode 目录
- editor/ (LeetCode 编辑器相关)

### python 目录
- apk-spider/ (APK 爬虫)
- douban-girl-spider/ (豆瓣爬虫)
- script/ (Python 脚本)

### spring 目录
- spring-ai-demo/ (Spring AI 示例)
- spring-boot-cli-demo/ (Spring Boot CLI 示例)
- spring-boot-rmi/ (Spring Boot RMI 示例)
- spring-mvc-analysis/ (Spring MVC 分析)

这个项目是一个综合性的代码库，包含了多种编程语言和技术栈的示例代码，主要用于学习和演示各种技术。