# Spring MVC 源码解析
本工程首先是一个使用 Spring MVC 搭建的 web 工程
通过对工程中不同组件的深入，以求对 Spring MVC 源码原理有一个全面的解析

## spring mvc 的本质是一个 servlet 的扩展框架
servlet 3.1 通过 web.xml 配置的 spring-mvc 参考 /web/WEB-INF/web.xml

## spring 的 xml 文件中通过命令空间配置的标签是如何解析的？
可以详细参考本工程 web/WEB-INF/let'sGo-servlet.xml 总的注释说明

## spring mvc 本身的创建过程
三个层次 HttpServletBean、FrameworkServlet、DispatcherServlet
HttpServletBean 直接继承自 HttpServlet，作用是将 Servlet 中配置的参数设置到相应的属性
FrameworkServlet 初始化了 WebApplicationContext。初始化有三种方式，过程中使用了 Servlet 中配置的一些参数
DispatcherServlet 初始化了自身的 9 个组件
特点：顶层结构设计简单，实现细节复杂（功能多、配置灵活）。