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

# Spring Bean注册阶段
1. BeanDefinition注册接口
+ BeanDefinitionRegistry<br/>
**总结**注册BeanDefinition是调用的DefaultListableBeanFactory的registerBeanDefinition方法来进行注册，
其实注册的时候就是往一个key为bean name，value为BeanDefinition的Map里面存值，同时使用一个List来保存插入
顺序。

