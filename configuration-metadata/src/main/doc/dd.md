# Spring配置元信息
## 1 配置元信息
1. SpringBean配置元信息-BeanDefinition
2. SpringBean属性元信息-PropertyValues
3. Spring容器配置元信息
4. Spring外部化配置元信息-PropertySource
5. Spring Profile元信息-@Profile

## 2 Bean配置元信息 - BeanDefinition
1. GenericBeanDefinition：通用型BeanDefinition，拥有设定parent BeanDefinition的方法。
2. RootBeanDefinition：没有parent的BeanDefinition或者合并后的BeanDefinition，另外拥有为了提高性能而
设置的一些字段，例如缓存resolvedConstructorArguments。
3. AnnotatedBeanDefinition：注解标注的BeanDefinition,exposes {@link org.springframework.core.type.AnnotationMetadata}
about its bean class.

## 3 Spring Bean属性元信息
### 3.1 Bean属性元信息 - PropertyValues
+ 可修改实现 - MutablePropertyValues
+ 元素成员 - PropertyValue

### 3.2 Bean属性上下文存储 - AttributeAccessor
BeanDefinition的继承链中拥有这个接口，可以用于存储临时数据，便于需要时获取，起到辅助作用。
### 3.3 Bean元信息元素 - BeanMetadataElement
BeanDefinition的继承链中拥有这个接口，可以用于存放环境信息等，便于需要时获取，起到辅助作用,与AttributeAccessor
不用的是，BeanMetadataElement只能存储一个值。

## 4 Spring容器配置元信息
### SpringXML配置元信息-beans元信息

|123|234|345|
|:-|:-|:-|
|abc|bcd|cde|
