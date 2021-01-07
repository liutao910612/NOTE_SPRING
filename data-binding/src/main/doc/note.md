# 1.Spring底层Java Beans替换实现
**JavaBeans核心实现-java.beans.BeanInfo**<br/>
+ 属性（Property）
+ &nbsp;&nbsp;java.beans.PropertyEditor
+ 方法（Method）
+ 事件（Event）
+ Expression

**Spring替换实现-org.springframework.beans.BeanWrapper**<br/>
+ 属性（Property）
+ &nbsp;&nbsp;java.beans.PropertyEditor
+ 嵌套属性路径（nested path）

# 2.标准的JavaBeans是如何操作属性的
|API|说明|
|:-|:-|
|java.beans.Introspector|Java Beans内省API|
|java.beans.BeanInfo|Java Beans元信息API|
|java.beans.BeanDescriptor|Java Beans信息描述符|
|java.beans.PropertyDescriptor|Java Beans属性描述符|
|java.beans.MethodDescriptor|Java Beans方法描述符|
|java.beans.EventSetDescriptor|Java Beans事件集合描述符|
