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