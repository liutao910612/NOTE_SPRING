# Spring 资源管理
## 1 Spring资源接口
|类型|接口|
|:-|:-|
|输入流|org.springframework.core.io.InputStreamSource|
|只读资源|org.springframework.core.io.Resource|
|可写资源|org.springframework.core.io.WritableResource|
|编码资源|org.springframework.core.io.support.EncodedResource|
|上下文资源|org.springframework.core.io.ContextResource|

## 2 Spring内建Resource实现
|资源来源|资源协议|实现类|
|:-|:-|:-|
|Bean定义|无|org.springframework.beans.factory.support.BeanDefinitionResource|
|数组|无|org.springframework.core.io.ByteArrayResource|
|类路径|classpath:/|org.springframework.core.io.ClassPathResource|
|文件系统|file:/|org.springframework.core.io.FileSystemResource|
|URL|URL支持的协议|org.springframework.core.io.UrlResource|
|ServletContext|无|org.springframework.web.context.support.ServletContextResource|

## 3 Spring Resource接口扩展
**可写资源接口**<br/>
+ org.springframework.core.io.WritableResource
+ &nbsp;&nbsp;org.springframework.core.io.FileSystemResource
+ &nbsp;&nbsp;org.springframework.core.io.FileUrlResource
+ &nbsp;&nbsp;org.springframework.core.io.PathResource<br/>

**编码资源接口**
+ org.springframework.core.io.support.EncodedResource