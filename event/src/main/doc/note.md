# 1.Java事件/监听器编程模型
**设计模式-观察者模式扩展**
+ 可观察者对象（消息发送者）-java.util.Observable
+ 观察者-java.util.Observer

**标准化接口**
+ 事件对象-java.util.EventObject
+ 事件监听器-java.util.EventListener

# 2.Spring标准事件-ApplicationEvent
**Java标准事件 java.util.EventObject扩展**<br/>
+ 扩展特性：事件发生时间戳

**Spring应用上下文Application Event扩展-ApplicationContextEvent**<br/>
+ Spring应用上下文（ApplicationContext）作为事件源
+ 具体实现：
org.springframework.context.event.ContextClosedEvent<br/>
org.springframework.context.event.ContextRefreshedEvent<br/>
org.springframework.context.event.ContextStartedEvent<br/>
org.springframework.context.event.ContextStoppedEvent

# 3.基于接口的Spring事件监听器
**Java标准事件监听器java.util.EventListener扩展**<br/>
+ 扩展接口-org.springframework.context.ApplicationListener
+ 设计特点：单一类型事件处理
+ 处理方法：onApplicationEvent(ApplicationEvent)
+ 事件类型：org.springframework.context.ApplicationEvent

# 4.基于注解的Spring事件监听器
**Spring注解-@org.springframework.context.event.EventListener**<br/>
|特性|说明|
|:-|:-|
|特点|支持多ApplicationEvent类型，无需接口约束|
|注解目标|方法|
|是否支持异步执行|支持|
|是否支持泛型类型事件|支持|
|是否支持顺序控制|支持，配合@Order注解控制|

# 5.Spring层次性上下文事件传播
+ 发生说明
当Spring应用出现多层次Spring应用上下文（ApplicationContext）时，如Spring WebMVC 、Spring Boot或Spring Cloud
场景下，由子ApplicationContext发起Spring事件可能会传递到其parent ApplicationContext（直到Root）的过程。
+ 如何避免
定位Spring事件源（ApplicationContext）进行过滤处理


