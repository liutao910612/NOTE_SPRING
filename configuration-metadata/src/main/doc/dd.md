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
|beans元素信息|默认值|使用场景|
|:-|:-|:-|
|profile|null(留空)|Spring Profiles配置值|
|default-lazy-init|default|当outter beans “default-lazy-init”属性存在时，继承该值，否则为“false”|
|default-merge|null(留空)|当outter beans “default-merge”属性存在时，继承该值，否则为“false”|
|default-autowire|default|当outter beans “default-autowire”属性存在时，继承该值，否则为“no”|
|default-autowire-candidates|null(留空)|默认Spring Beans名称pattern|
|default-init-method|null(留空)|默认Spring Beans自定义初始化方法|
|default-destroy-method|null(留空)|默认Spring Beans自定义销毁方法|
### SpringXML配置元信息-应用上下文相关
|XML元素|使用场景|
|:-|:-|
|<context:annotation-config>|激活Spring注解驱动|
|<context:component-scan>|Spring @Component以及自定义注解扫描|
|<context:load-time-weaver>|激活Spring LoadTimeWeaver|
|<context:mbean-export>|暴露Spring Beans作为JMX Beans|
|<context:mbean-server>|将当前平台作为MBeanServer|
|<context:property-placeholder>|加载外部化配置资源作为Spring属性配置|
|<context:property-override>|利用外部化配置资源覆盖Spring属性值|

**总结**BeanDefinitionParserDelegate#populateDefaults方法展示了具体的使用逻辑。

## 5 基于XML资源装载Spring Bean配置元信息
### Spring Bean配置元信息
|XML元素|使用场景|
|:-|:-|
|<beans:beans>|单XML资源下的多个Spring Beans配置|
|<beans:bean>|单个Spring Bean定义（BeanDefinition）配置|
|<beans:alias>|为Spring Bean定义（BeanDefinition）映射别名|
|<beans:import>|加载外部Spring XML配置资源|

**SUMMARY**这里的配置读取是由XmlBeanDefinitionReader来完成的,具体步骤如下:<br/>
+ step1:根据location路径生成org.w3c.dom.Document 对象，Document对象其实就是对XML文件的封装。
+ step2:通过org.springframework.beans.factory.xml.BeanDefinitionParserDelegate对象的
parseBeanDefinitionElement方法来将org.w3c.dom.Element对象解析成org.springframework.beans.factory.config.BeanDefinitionHolder对象。
注意这里的org.springframework.beans.factory.config.BeanDefinitionHolder就是对BeanDefinition信息即beanName信息的
封装。
+ step3:通过org.springframework.beans.factory.support.BeanDefinitionRegistry.registerBeanDefinition来注册BeanDefinition.<br/>
上面的step2和step3都是在org.springframework.beans.factory.xml.BeanDefinitionDocumentReader中来完成的。
## 6 基于Properties资源装载Spring Bean配置元信息
### Spring Bean配置元信息
|Properties属性名|使用场景|
|:-|:-|
|(class)|Bean类全限定名|
|(abstract)|是否为抽象的BeanDefinition|
|(parent)|指定parent BeanDefinition名称|
|(lazy-init)|是否为延迟初始化|
|(ref)|应用其他Bean的名称|
|(scope)|设置Bean的scope属性|
|${n}|n表示第n+1个构造器参数|

_这里的底层实现为PropertiesBeanDefinitionReader_
**SUMMARY**这里是通过ropertiesBeanDefinitionReader.loadBeanDefinitions读取配置文件来完成的，具体如下：<br/>
+ step1:通过java.util.Properties.load(java.io.InputStream)将资源读取成Properties对象，这里的Properties其实就是继承自HashMap。
+ step2:通过org.springframework.beans.factory.support.PropertiesBeanDefinitionReader.registerBeanDefinition来将Properties
读取转换成BeanDefinition的对象，然后进行注册。
**From geek**Spring Properties资源BeanDefinition解析与注册<br/>
+ 核心API-PropertiesBeanDefinitionReader<br/>
&nbsp;&nbsp;**资源**<br/>
&nbsp;&nbsp;&nbsp;&nbsp;字节流-Resource
&nbsp;&nbsp;&nbsp;&nbsp;字符流-EncodedResource(has encode , convenient to read)
&nbsp;&nbsp;**底层**<br/>
&nbsp;&nbsp;&nbsp;&nbsp;存储-java.util.Properties<br/>
&nbsp;&nbsp;&nbsp;&nbsp;BeanDefinition解析-API内部实现<br/>
&nbsp;&nbsp;&nbsp;&nbsp;BeanDefinition注册-BeanDefinitionRegistry<br/>


## 7 基于Java注解装载Spring Bean配置元信息
### 1 Spring模式注解
|Spring注解|场景说明|起始版本|
|:-|:-|:-|
|@Repository|数据仓储模式注解|2.0|
|@Component|通用组件模式注解|2.5|
|@Service|服务模式注解|2.5|
|@Controller|Web控制器模式注解|2.5|
|@Configuration|配置类模式注解|3.0|

### 2 Spring Bean 依赖注入注解
|Spring注解|场景说明|起始版本|
|:-|:-|:-|
|@Autowired|Bean依赖注入，支持多种依赖查找方式|2.5|
|@Qualifier|细粒度的@Autowired依赖查找|2.5|

|Java注解|场景说明|起始版本|
|:-|:-|:-|
|@Resource|类似于@Autowired|2.5|
|@Inject|类似于@Autowired|2.5|

### 3 Spring Bean条件装配组件
|Spring注解|场景说明|起始版本|
|:-|:-|:-|
|@Profile|配置化条件装配|2.5|
|@Conditional|编程条件装配|2.5|

**Detail is as follow** : <br/>
+ step1,org.springframework.context.annotation.ConfigurationClassPostProcessor#postProcessBeanFactory 来处理指定BeanFactory。
+ step2,org.springframework.context.annotation.ConfigurationClassParser#processConfigurationClass来处理ConfigurationClass
+ step3,org.springframework.context.annotation.ClassPathBeanDefinitionScanner#doScan 根据包名扫描来获得具体得BeanDefinition
+ step4,org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider#scanCandidateComponents 根据包名转换成packageSearchPath,
以获取到指定注解(For example : @Component)的BeanDefinition。
+ step5,最后使用BeanDefinitionRegistry进行注册。

