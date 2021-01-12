# 1.Spring 模式注解（Stereotype Annotations）
**@Component"派生性"原理**<br/>
+ 核心组件-org.springframework.context.annotation.ClassPathBeanDefinitionScanner

# 2.Spring组合注解（Composed Annotations）
**基本定义**<br/>
Spring组合注解（Composed Annotations）中允许是Spring模式注解（Stereotype Annotations）与其他Spring
功能性注解的任意组合。

# 3.Spring @Enable模块驱动
**@Enable模块驱动**<br/>
@Enable模块驱动是以@Enable为前缀的注解驱动编程模型。所谓“模块”是指具备相同领域的功能组件集合，组合所形成一个独立
的单元。比如Web MVC模块、AspectJ代理模块、Caching(缓存)模块、JMX(Java管理扩展)模块、Async(异步处理)模块等。<br/>
For example : <br/>
1. @EnableWebMvc
2. @EnableTransactionManagement
3. @EnableCaching
4. @EnableMBeanExport
5. @EnableAsync

**@Enable模块驱动编程模式**<br/>
1. 驱动注解：@EnableXXX
2. 导入注解：@Import具体实现
3. 具体实现
+ &nbsp;&nbsp;基于Configuration Class
+ &nbsp;&nbsp;基于ImportSelector接口实现
+ &nbsp;&nbsp;基于ImportBeanDefinitionRegistrar接口实现

