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