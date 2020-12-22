# Spring Bean元信息配置阶段
## BeanDefinition配置
1. 面向资源
+ XML配置
+ Properties配置
2. 面向注解
3. 面向API

# Spring Bean元信息解析阶段
1. 面向资源BeanDefinition解析
+ BeanDefinitionReader
+ XML解析器-BeanDefinitionParser
2. 面向注解BeanDefinition解析
+ AnnotatedBeanDefinitionReader

# Spring BeanDefinition注册阶段
1. BeanDefinition注册接口
+ BeanDefinitionRegistry<br/>
**总结**注册BeanDefinition是调用的DefaultListableBeanFactory的registerBeanDefinition方法来进行注册，
其实注册的时候就是往一个key为bean name，value为BeanDefinition的Map里面存值，同时使用一个List来保存插入
顺序。

# Spring BeanDefinition合并阶段
+ BeanDefinition合并
+ 父子BeanDefinition合并
+ 当前BeanFactory查找
+ 层次性BeaFactory查找<br/>
**总结**使用BeanDefinition中的parentName属性来进行父类BeanDefinition的查找并进行合并。

# Spring Bean Class加载阶段
+ ClassLoader类加载
+ Java Security安全控制
+ ConfigurableBeanFactory临时ClassLoader

# Spring Bean实例化前阶段
+ 非主流生命周期-Bean实例化前阶段<br/>
InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation<br/>
**总结**这里在源码中体现为在调用createBean之前会扫描拥有的BeanPostProcessor，如果postProcessBeforeInstantiation返回为非空，就直接返回当前的值，
不再调用createBean.

# Spring Bean实例化阶段
+ 实例化方式
1. 传统实例化方式
  1. 实例化策略 - InstantiationStrategy
2. 构造器依赖注入

# Spring Bean实例化后阶段
+ Bean属性赋值（Populate）判断<br/>
InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation<br/>

# Spring Bean属性赋值前阶段
1. Bean属性值元信息
+ PropertyValues
2. Bean属性赋值前回调
+ Spring1.2-5.0:InstantiationAwareBeanPostProcessor#postProcessPropertyValues
+ Spring5.1:InstantiationAwareBeanPostProcessor#postProcessProperties

# Spring Bean Aware接口回调阶段
## Spring Aware接口
+ BeanNameAware
+ BeanClassLoaderAware
+ BeanFactoryAware
+ EnvironmentAware
+ EmbeddedValueResolverAware
+ ResourceLoaderAware
+ ApplicationEventPublisherAware
+ MessageSourceAware
+ ApplicationContextAware