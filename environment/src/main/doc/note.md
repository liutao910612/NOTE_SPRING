# 1.理解Spring Environment抽象
+ 统一的Spring配置属性管理
Spring Framework3.1开始引入Environment抽象，它统一Spring配置属性的存储，包括占位符处理和
类型转换，不仅完整地替换PropertyPlaceholderConfigurer,而且还支持更丰富的配置属性源（PropertySource）

+ 条件化Spring Bean装配管理
通过Environment Profiles信息，帮助Spring容器提供条件化地装配Bean。

# 2.Spring Environment接口使用场景
1. 用于属性占位符处理。
2. 用于转换Spring配置属性类型。
3. 用于存储Spring配置属性源（PropertySource）。
4. 用于Profiles状态的维护。

# 3.Environment占位符处理
1. Spring3.1前占位符处理
+ 组件：org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
+ 接口：org.springframework.util.StringValueResolver

2. Spring3.1+占位符处理
+ 组件：org.springframework.context.support.PropertySourcesPlaceholderConfigurer
+ 接口：org.springframework.beans.factory.config.EmbeddedValueResolver

# 4.理解条件化配置
1. Spring3.1条件配置
+ API：org.springframework.core.env.ConfigurableEnvironment
+ 注解：org.springframework.context.annotation.Profile

# 5.Spring4重构@Profile
1. 基于Spring4 org.springframework.context.annotation.Condition
+ org.springframework.context.annotation.ProfileCondition

# 6.依赖注入Environment
1. 直接将注入
+ 通过EnvironmentAware接口回调
+ 通过@Autowired注入Environment

2. 间接依赖注入
+ 通过ApplicationContextAware接口回调
+ 通过@Autowired注入ApplicationContext

# 7.依赖注入@Value
1. 通过注入@Value
+ 实现-org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
这里需要再次强调的是@Autowired和@Value的具体实现均来之AutowiredAnnotationBeanPostProcessor

# 8.Spring类型转换在Environment中的运用
1. Environment底层实现
+ 底层实现-org.springframework.core.env.PropertySourcesPropertyResolver
+ 核心方法-convertValueIfNecessary

2. 底层服务-org.springframework.core.convert.ConversionService
+ 默认实现-org.springframework.core.convert.support.DefaultConversionService

# 9.Spring类型转换在@Value中的运用
1. @Value底层实现
+ 底层实现-org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
+ &nbsp;&nbsp;org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency
``
+ 底层服务-org.springframework.beans.TypeConverter
+ &nbsp;&nbsp;默认实现-org.springframework.beans.TypeConverterDelegate
+ &nbsp;&nbsp;java.beans.PropertyEditor
+ &nbsp;&nbsp;org.springframework.core.convert.ConversionService

# 10.Spring 配置属性源PropertySource
1. API
+ 单配置属性源-org.springframework.core.env.PropertySource
+ 多配置属性源-org.springframework.core.env.PropertySources

2. 注解
+ 单配置属性源-org.springframework.context.annotation.PropertySource
+ 多配置属性源-org.springframework.context.annotation.PropertySources

3. 关联
+ 存储对象-org.springframework.core.env.MutablePropertySources
+ 关联方法-org.springframework.core.env.ConfigurableEnvironment.getPropertySources
