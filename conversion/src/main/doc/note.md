# 1.基于JavaBeans接口的类型转换
**核心职责**<br/>
+ 将Spring类型的内容转换为目标类型的对象。

**扩展原理**<br/>
+ Spring框架将文本内容传递到PropertyEditor实现的setAsText(String)方法。
+ PropertyEditor#setAsText(String)方法实现将String类型转换为目标类型的对象。
+ 将目标类型的对象传入PropertyEditor#setValue(Object)方法。
+ PropertyEditor#setValue(Object)方法实现需要临时存储对象。
+ Spring框架通过PropertyEditor#getValue()获取类型转换后的对象。

# 2.Converter接口的局限性
**局限一：缺少Source Type 和Target Type前置判断**<br/>
+ 应对：增加org.springframework.core.convert.converter.ConditionalConverter实现
**局限二：仅能转换单一的Source Type和Target Type**<br/>
+ 应对：使用org.springframework.core.convert.converter.GenericConverter代替

# 3.GenericConverter接口
org.springframework.core.convert.converter.GenericConverter

|核心要素|说明|
|:-|:-|
|使用场景|用于"复合"类型转换场景，比如Collection、Map、数组等|
|转换范围|Set<ConvertiblePair> getConvertibleTypes()|
|配对类型|org.springframework.core.convert.converter.GenericConverter.ConvertiblePair|
|转换方法|convert(Object, TypeDescriptor, TypeDescriptor)|
|类型描述|org.springframework.core.convert.TypeDescriptor|

# 4.优化GenericConverter接口
**GenericConverter局限性**<br/>
+ 缺少Source Type和Target Type前置判断
+ 单一类型转换实现复杂

**GenericConverter优化接口-ConditionalGenericConverter**
+ 符合类型转换：org.springframework.core.convert.converter.GenericConverter
+ 类型条件判断：org.springframework.core.convert.converter.ConditionalConverter

# 5.扩展Spring类型转换器
**实现转换器接口**<br/>
+ org.springframework.core.convert.converter.Converter
+ org.springframework.core.convert.converter.ConverterFactory
+ org.springframework.core.convert.converter.GenericConverter

**注册转换器实现**<br/>
+ 通过ConversionServiceFactoryBean Spring Bean
+ 通过org.springframework.core.convert.ConversionService API
