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