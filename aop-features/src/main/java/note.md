## 1.编程方式创建@AspectJ代理   
实现类 ： org.springframework.aop.aspectj.annotation.AspectJProxyFactory

## 2.XML配置驱动-创建AOP代理
**实现方法**   
配置：org.springframework.aop.framework.ProxyFactoryBean   
Spring Schema-Based配置   
+ <aop:config>
+ <aop:aspectj-autoproxy/>

## 3.标准代理工厂API
实现类-org.springframework.aop.framework.ProxyFactory

## 4.API实现Around Advice
思考：为什么Spring AOP不需要设计Around Advice?   
线索   
+ AspectJ @Around 与org.aspectj.lang.ProceedingJoinPoint配合执行被代理方法。
+ ProceedingJoinPoint#proceed()方法类似于Java Method#invoke(Object,Object,...)。
+ Spring AOP底层API ProxyFactory可以通过addAdvice方法与Advice实现关联。
+ 接口Advice是Interceptor的父亲接口，而接口MethodInterceptor又扩展了Interceptor。
+ MethodInterceptor的invoke方法参数MethodInvocation与ProceedingJoinPoint类似。

## 5.@AspectJ前置动作：@Before与@Around谁优先级执行？
通常@Around的优先级高于@Before,但是我们也可以通过Ordered接口来改变优先级。   

## 6.@AspectJ后置动作
**After Advice注解**
+ 方法返回后：@org.aspectj.lang.annotation.AfterReturning
+ 异常发生后：@org.aspectj.lang.annotation.AfterThrowing
+ finally执行：@org.aspectj.lang.annotation.After

## 7.API实现三种After Advice
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

## 8.自动动态代理
**代表实现**   
+ org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator
+ org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
+ org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator

## 9.Pointcut操作
组合实现-org.springframework.aop.support.ComposablePointcut   
**工具类**   
+ ClassFilter工具类-ClassFilters   
+ MethodMatcher工具类-MethodMatchers
+ Pointcut工具类-Pointcuts

## 10.Pointcut便利实现
+ 静态Pointcut-StaticMethodMatcherPointcut
+ 正则表达式Pointcut-JdkRegexpMethodPointcut
+ 控制流Pointcut-ControlFlowPointcut

## 11.Pointcut AspectJ实现
1、实现类-org.springframework.aop.aspectj.AspectJExpressionPointcut
2、指令支持-SUPPORTED_PRIMITIVES 字段   
3、表达式-org.aspectj.weaver.tools.PointcutExpression

## 12.Joinpoint执行动作接口-Advice
**Around Advice-Interceptor**
+ 方法拦截器-MethodInterceptor
+ 构造器拦截器-ConstructorInterceptor

**前置动作**   
+ 标准接口-org.springframework.aop.BeforeAdvice
+ 方法级别-org.springframework.aop.MethodBeforeAdvice

**后置动作**   
+ org.springframework.aop.AfterAdvice
+ org.springframework.aop.AfterReturningAdvice
+ org.springframework.aop.ThrowsAdvice   

## 13.Joinpoint Before Advice标准实现   
**接口**   
+ 标准接口-org.springframework.aop.BeforeAdvice
+ 方法级别-org.springframework.aop.MethodBeforeAdvice   

**实现**   
org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor

## 14.Joinpoint After Advice标准实现
**接口**   
+ org.springframework.aop.AfterAdvice
+ org.springframework.aop.AfterReturningAdvice
+ org.springframework.aop.ThrowsAdvice   

**实现**   
+ org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor
+ org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor   

## 15.Joinpoint After Advice AspectJ实现   
**接口**   
+ org.springframework.aop.AfterAdvice
+ org.springframework.aop.AfterReturningAdvice
+ org.springframework.aop.ThrowsAdvice 

**实现**  
+ org.springframework.aop.aspectj.AspectJAfterAdvice
+ org.springframework.aop.aspectj.AspectJAfterReturningAdvice
+ org.springframework.aop.aspectj.AspectJAfterThrowingAdvice  

## 16.Pointcut与Advice连接器-PointcutAdvisor
**接口**   
-org.springframework.aop.PointcutAdvisor   
**通用实现**   
-org.springframework.aop.support.DefaultPointcutAdvisor   
**AspectJ实现**   
-org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor
-org.springframework.aop.aspectj.AspectJPointcutAdvisor   
**静态方法实现**   
-org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor   
**IoC容器实现**   
-org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor

## 17.Introduction与Advice连接器
**接口**   
-org.springframework.aop.IntroductionAdvisor   
**元信息**   
-org.springframework.aop.IntroductionInfo，实现持有代理对象实现接口的约束范围，这个范围其实就是jdk动态代理里面的interfaces。   
**通用实现**   
-org.springframework.aop.support.DefaultIntroductionAdvisor   
**AspectJ实现**   
-org.springframework.aop.aspectj.DeclareParentsAdvisor

## 18.Advisor的Interceptor适配器
**接口**   
-org.springframework.aop.framework.adapter.AdvisorAdapter   
**MethodBeforeAdvice实现**   
-org.springframework.aop.framework.adapter.MethodBeforeAdviceAdapter    
**AfterReturningAdvice实现**   
-org.springframework.aop.framework.adapter.AfterReturningAdviceAdapter   
**ThrowsAdvice实现**   
-org.springframework.aop.framework.adapter.ThrowsAdviceAdapter   

## 19.Aop代理接口-AopProxy   
**接口**   
-org.springframework.aop.framework.AopProxy
**实现**   
-Jdk动态代理：org.springframework.aop.framework.JdkDynamicAopProxy   
-CGLIB字节码提升：org.springframework.aop.framework.CglibAopProxy   
org.springframework.aop.framework.ObjenesisCglibAopProxy   

## 20.AopProxy工厂接口与实现   
**接口**   
-org.springframework.aop.framework.AopProxyFactory   
**默认实现**   
-org.springframework.aop.framework.DefaultAopProxyFactory   
**返回类型**   
+ org.springframework.aop.framework.JdkDynamicAopProxy
+ org.springframework.aop.framework.ObjenesisCglibAopProxy   

## 21.AopProxyFactory配置管理器   
**核心API**   
-org.springframework.aop.framework.AdvisedSupport   
**语义-代理配置**   
**基类**   
-org.springframework.aop.framework.ProxyConfig   
**实现接口**   
-org.springframework.aop.framework.Advised   
**使用场景**   
-org.springframework.aop.framework.AopProxy实现   

## 22.Advisor链工厂接口与实现   
**核心API**   
-org.springframework.aop.framework.AdvisorChainFactory   
**特殊实现**   
-org.springframework.aop.framework.InterceptorAndDynamicMethodMatcher   
**默认实现**   
-org.springframework.aop.framework.DefaultAdvisorChainFactory   

## 23.目标对象来源接口与实现   
**核心API**   
-org.springframework.aop.TargetSource   
**实现**   
+ org.springframework.aop.target.HotSwappableTargetSource
+ org.springframework.aop.target.AbstractPoolingTargetSource
+ org.springframework.aop.target.PrototypeTargetSource
+ org.springframework.aop.target.ThreadLocalTargetSource
+ org.springframework.aop.target.SingletonTargetSource   

## 24.代理对象创建基础类
**核心API**   
-org.springframework.aop.framework.ProxyCreatorSupport   
**语义**   
代理对象创建基础类   
**基类**   
-org.springframework.aop.framework.AdvisedSupport   

## 25.AdvisedSupport事件监听器   
**核心API**   
-org.springframework.aop.framework.AdvisedSupportListener   
**事件对象**   
-org.springframework.aop.framework.AdvisedSupport   
**事件来源**   
-org.springframework.aop.framework.ProxyCreatorSupport   
**激活事件触发**   
ProxyCreatorSupport#createAopProxy   
**变更事件触发**   
代理接口变化时、Advisor变化时、配置复制     

## 26.ProxyCreatorSupport标准实现   
**核心API**   
-org.springframework.aop.framework.ProxyFactory   
**基类**   
-org.springframework.aop.framework.ProxyCreatorSupport   
**特性增强**   
提供一些便利操作   

## 27.ProxyCreatorSupport IoC容器实现   
**核心API**   
-org.springframework.aop.framework.ProxyFactoryBean   
**基类**   
-org.springframework.aop.framework.ProxyCreatorSupport   
**特点**   
Spring IoC容器整合   
+ org.springframework.beans.factory.BeanClassLoaderAware   
+ org.springframework.beans.factory.BeanFactoryAware   
**特性增强**   
实现org.springframework.beans.factory.FactoryBean   

## 28.ProxyCreatorSupport AspectJ容器实现   
**核心API**   
-org.springframework.aop.aspectj.annotation.AspectJProxyFactory   
**特点**   
AspectJ注解整合   
**相关API**     
+ AspectJ元数据-org.springframework.aop.aspectj.annotation.AspectMetadata   
+ AspectJ Advisor工厂-org.springframework.aop.aspectj.annotation.AspectJAdvisorFactory   

## 29.IoC容器自动代理标准实现   
**基类**    
-org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator   
**默认实现**   
-org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator   
**Bean名称匹配实现**   
-org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator   
**Infrastructure Bean实现**   
-org.springframework.aop.framework.autoproxy.InfrastructureAdvisorAutoProxyCreator   

## 30.AOP Infrastructure Bean接口   
**接口**   
-org.springframework.aop.framework.AopInfrastructureBean   
**语义**   
Spring AOP基础Bean标记接口   
**实现**   
+ org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator
+ org.springframework.aop.scope.ScopedProxyFactoryBean   

**判断逻辑**   
+ AbstractAutoProxyCreator#isInfrastructureClass
+ ConfigurationClassUtils#checkConfigurationClassCandidate   

## 31.AOP上下文辅助类   
API-org.springframework.aop.framework.AopContext   
**语义**   
ThreadLocal的扩展，临时存储AOP对象   

## 32.AOP代理工具类   
API-org.springframework.aop.framework.AopProxyUtils   
**代表方法**   
+ getSingletonTarget-从实例中获取单例对象
+ ultimateTargetClass-从实例中获取最终目标类
+ completeProxiedInterfaces-计算AdvisedSupport配置中所有被代理的接口
+ proxiedUserInterfaces-从代理对象中获取代理接口   

## 33.AOP工具类

## 34.AspectJ Enable模块驱动实现   
**注解**   
-org.springframework.context.annotation.EnableAspectJAutoProxy   
**属性方法**   
+ proxyTargetClass-是否开启CGLIB代理   
+ exposeProxy-是否将代理对象暴露在AopContext中   
**设计模式-@Enable模块驱动**   
ImportBeanDefinitionRegistrar实现-org.springframework.context.annotation.AspectJAutoProxyRegistrar   
**底层实现**   
-org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator    

## 35.AspectJ XML配置驱动实现   
**XML元素-<aop:aspectj-autoproxy/>**     
**属性**   
+ proxy-target-class - 是否开启CGLIB代理   
+ expose-proxy - 是否将代理对象暴露在AopContext中   
**设计模式-Extensible XML Authoring**    
**底层实现**   
org.springframework.aop.config.AspectJAutoProxyBeanDefinitionParser

## 36.AOP配置Schema-based实现   
**XML元素-<aop:config/>**     
**属性**   
+ proxy-target-class - 是否开启CGLIB代理   
+ expose-proxy - 是否将代理对象暴露在AopContext中   
**嵌套元素**   
+ pointcut
+ advisor
+ advice    
**底层实现**   
-org.springframework.aop.config.ConfigBeanDefinitionParser




