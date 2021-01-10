# 1.Java类型接口
+ Java5类型接口-java.lang.reflect.Type

|派生类或接口|说明|
|:-|:-|
|java.lang.Class|Java类API，如java.lang.String|
|java.lang.reflect.GenericArrayType|泛型类数组类型|
|java.lang.reflect.ParameterizedType|泛型参数类型|
|java.lang.reflect.TypeVariable|泛型类型变量，如Collection<E>中的E|
|java.lang.reflect.WildcardType|泛型通配变量|

+ Java反射泛型

|类型|API|
|:-|:-|
|泛型信息（Generics Info）|java.lang.Class#getGenericInfo()|
|泛型参数（Parameters）|java.lang.reflect.ParameterizedType|
|泛型父类（Super Classes）|java.lang.Class#getGenericSupperClass()|
|泛型接口（Interfaces）|java.lang.Class#getGenericInterfaces()|
|泛型声明（Generics Declaration）|java.lang.reflect.GenericDeclaration|

# 2.Spring方法参数封装
**核心API-org.springframework.core.MethodParameter**<br/>
+ 其实版本：[2.0,)
+ 元信息
+ &nbsp;&nbsp;关联的方法-Method
+ &nbsp;&nbsp;关联的构造器-Constructor
+ &nbsp;&nbsp;构造器或方法参数索引-parameterIndex
+ &nbsp;&nbsp;构造器或方法参数类型-parameterType
+ &nbsp;&nbsp;构造器或方法参数泛型类型-genericParameterType
+ &nbsp;&nbsp;构造器或方法参数名称-parameterName
+ &nbsp;&nbsp;所在类-containingClass

# 3.Spring4.0泛型优化实现-ResolvableType
**核心API-org.springframework.core.ResolvableType**<br/>
+ 起始版本：[4.0,)
+ 扮演角色：GenericTypeResolver和GenericCollectionTypeResolver替代者
+ 工厂方法：for*方法
+ 转换方法：as*方法
+ 处理方法：resolve*方法