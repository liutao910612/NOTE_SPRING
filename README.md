# NOTE_SPRING
## 为什么说ObjectFactory提供的是延迟依赖查找？
+ 原因
1. ObjectFactory(或ObjectProvider)可关联某一类型Bean。   
2. ObjectFactory和ObjectProvider对象在被依赖查找或者依赖注入的时候并没有实时查找关联类型的Bean。   
3. 当ObjectFactory(或ObjectProvider)调用getObject()方法时，目标Bean才被依赖查找。   
+ 总结   
ObjectFactory(或ObjectProvider)相当于某一类型Bean依赖查找代理对象。

## 依赖查找（注入）的Bean会被缓存吗？
+ 单例Bean(Singleton)-会   
缓存位置：org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.singletonObjects   
+ 原型Bean(Prototype)-不会   
当依赖查找或者注入的时候，根据BeanDefinition每次创建   
+ 其他Scope Bean   
request:每个ServletRequest内部缓存，生命周期维持在每次Http请求。   
session:每个HttpSession内部缓存，生命周期维持在每个用户Http会话。   
application:当前Servlet应用内部缓存。   

## @Bean的处理流程是怎样的？
+ 解析范围-Configuration Class中的@Bean方法
+ 方法类型-静态@Bean方法和实例@Bean方法   
@Bean的处理实在ConfigurationClassPostProcessor类里面完成的，通过解析Configuration class中标注有@Bean注解的类，来生成对象的BeanDefinition。
生成BeanDefinition的时候调用setFactoryMethodName和setFactoryBeanName来完成的。

## BeanFactory如何处理循环依赖
+ 循环依赖的开关（方法）-AbstractAutowireCapableBeanFactory#setAllowCircularReferences
+ 单例工程（属性）-DefaultSingletonBeanRegistry#singletonFactories，用于存储ObjectFactory
+ 早期未处理Bean(属性)-DefaultSingletonBeanRegistry#earlySingletonObjects，已经实例化了，但是还没有初始化。
+ 单例Bean（属性） -DefaultSingletonBeanRegistry.singletonObjects   
针对循环依赖，如果依赖的对象还没有被实例化，就将依赖对象对应的ObjectFactory进行缓存，同时将当前对象存储在earlySingletonObjects,
当依赖的对象被实例化时，就移除ObjectFactory的缓存和earlySingletonObjects中的缓存。

