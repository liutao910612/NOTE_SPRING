#AOP常见使用场景
1日志场景   
诊断上下文，如：log4j或logback中的MDC   
辅助信息，如：方法实行时间   
2统计场景   
方法调用次数   
执行异常次数   
数据抽样   
数值累加   
3安防场景   
熔断，如：Netflix Hystrix   
限流和降级，如：Alibaba Sentinel   
认证和授权，如：Spring Security   
监控，如：JMX   
4性能场景   
缓存，如：Spring Cache   
超市控制   
#Java AOP代理模式（Proxy）
+ Java静态代理
常用OOP继承和组合相结合
+ Java动态代理   
JDK动态代理   
字节码提升，如CGLIB  
 
#Java AOP拦截模式
+ 拦截类型   
前置拦截（Before）   
后置拦截（After）
异常拦截（Exception）   

