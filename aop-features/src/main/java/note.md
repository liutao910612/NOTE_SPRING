#编程方式创建@AspectJ代理   
实现类 ： org.springframework.aop.aspectj.annotation.AspectJProxyFactory

#XML配置驱动-创建AOP代理
**实现方法**   
配置：org.springframework.aop.framework.ProxyFactoryBean   
Spring Schema-Based配置   
+ <aop:config>
+ <aop:aspectj-autoproxy/>

#标准代理工厂API
实现类-org.springframework.aop.framework.ProxyFactory

#API实现Around Advice
思考：为什么Spring AOP不需要设计Around Advice?   
线索   
+ AspectJ @Around 与org.aspectj.lang.ProceedingJoinPoint配合执行被代理方法。
+ ProceedingJoinPoint#proceed()方法类似于Java Method#invoke(Object,Object,...)。
+ Spring AOP底层API ProxyFactory可以通过addAdvice方法与Advice实现关联。
+ 接口Advice是Interceptor的父亲接口，而接口MethodInterceptor又扩展了Interceptor。
+ MethodInterceptor的invoke方法参数MethodInvocation与ProceedingJoinPoint类似。

#@AspectJ前置动作：@Before与@Around谁优先级执行？
通常@Around的优先级高于@Before,但是我们也可以通过Ordered接口来改变优先级。   

#@AspectJ后置动作
**After Advice注解**
+ 方法返回后：@org.aspectj.lang.annotation.AfterReturning
+ 异常发生后：@org.aspectj.lang.annotation.AfterThrowing
+ finally执行：@org.aspectj.lang.annotation.After

#API实现三种After Advice
核心接口-org.springframework.aop.AfterAdvice   
类型：标记接口，与org.aopalliance.aop.Advice类似    
扩展   
+ org.springframework.aop.AfterReturningAdvice
+ org.springframework.aop.ThrowsAdvice   

接受对象-org.springframework.aop.framework.AdvisedSupport   
基础实现类-org.springframework.aop.framework.ProxyCreatorSupport   
常见实现类   
+ org.springframework.aop.framework.ProxyFactory
+ org.springframework.aop.framework.ProxyFactoryBean
+ org.springframework.aop.aspectj.annotation.AspectJProxyFactory

#自动动态代理
**代表实现**   
+ org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator
+ org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
+ org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator


