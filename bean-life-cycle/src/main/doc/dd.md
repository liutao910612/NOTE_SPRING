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

# Spring Bean初始化阶段
## Bean初始化(Initialization)
+ @PostConstruct标注方法
+ 实现InitializingBean接口的afterPropertiesSet()方法
+ 自定义初始化方法

# Spring Bean初始化完成阶段
## 方法回调
+ Spring4.1+:SmartInitializingSingleton#afterSingletonInstantiated

# Spring Bean销毁前阶段
## 方法回调
+ DestructionAwareBeanPostProcessor#postProcessBeforeDestruction

# Spring Bean销毁阶段
## Bean销毁（Destroy）
+ @PreDestroy标注方法
+ 实现DisposableBean接口的destroy()方法
+ 自定义销毁方法

# SUMMARY
## BeanPostProcessor的使用场景有哪些？
BeanPostProcessor提供Spring Bean初始化前和初始化后的生命周期回调，分别对应postProcessBeforeIntialization
以及postProcessAfterInitialization方法，允许对关心的Bean进行扩展，甚至替换。<br/>
其中，ApplicationContext相关的Aware回调也是基于BeanPostProcessor实现，即ApplicationContextAwareProcessor。

## BeanFactoryPostProcessor与BeanPostProcessor的区别
BeanFactoryPostProcessor是Spring BeanFactory(实际为ConfigurableListableBeanFactory)的后置处理器，用于扩展
BeanFactory，或通过BeanFactory进行依赖查找和依赖注入。<>br/
BeanFactoryPostProcessor必须由Spring ApplicationContext执行，BeanFactory无法与其直接交互。<br/>
而BeanPostProcessor则直接与BeanFactory关联，属于N对1的关系。

## BeanFactory是怎样处理Bean生命周期？
BeanFactory的默认实现为DefaultListableBeanFactory，其中Bean生命周期与方法映射如下：<br/>
+ BeanDefinition注册阶段 - registerBeanDefinition
+ BeanDefinition合并阶段 - getMergedBeanDefinition
+ Bean实例化前阶段 - resolveBeforeInstantiation
+ Bean实例化阶段 - createBeanInstance
+ Bean实例化后阶段 - populateBean
+ Bean属性赋值前阶段 - populateBean
+ Bean属性赋值阶段 - populateBean
+ Bean Aware接口回调阶段 - intializeBean
+ Bean初始化前阶段 - intializeBean
+ Bean初始化阶段 - intializeBean
+ Bean初始化后阶段 - intializeBean
+ Bean初始化完成阶段 - preInstantiateSingletons
+ Bean销毁前阶段 - destroyBean
+ Bean销毁阶段 - destroyBean