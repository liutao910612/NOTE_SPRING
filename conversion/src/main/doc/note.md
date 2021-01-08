# 1.基于JavaBeans接口的类型转换
**核心职责**<br/>
+ 将Spring类型的内容转换为目标类型的对象。

**扩展原理**<br/>
+ Spring框架将文本内容传递到PropertyEditor实现的setAsText(String)方法。
+ PropertyEditor#setAsText(String)方法实现将String类型转换为目标类型的对象。
+ 将目标类型的对象传入PropertyEditor#setValue(Object)方法。
+ PropertyEditor#setValue(Object)方法实现需要临时存储对象。
+ Spring框架通过PropertyEditor#getValue()获取类型转换后的对象。