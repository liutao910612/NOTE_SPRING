# 1 Spring国际化使用场景
+ 普通国际化文案
+ Bean Validation校验国际化文案
+ Web站点页面渲染
+ Web MVC错误消息提示

# 2 Spring国际化接口
**核心接口**<br/>
+ org.springframework.context.MessageSource

**主要概念**<br/>
+ 文案模板编码（code）
+ 文案模板参数（args）
+ 区域（Locale）

# 3 Java国际化标准实现
**核心接口**<br/>
+ 抽象实现-java.util.ResourceBundle
+ Properties资源实现-java.util.PropertyResourceBundle
+ 列举实现-java.util.ListResourceBundle

**ResourceBundle核心特性**<br/>
+ key-value设计
+ 层次性设计
+ 缓存设计
+ 字符编码控制-java.util.ResourceBundle.Control(@since1.6)
+ Control SPI扩展-java.util.spi.ResourceBundleControlProvider(@since1.8)

# 4 Java文本格式
**核心接口**<br/>
+ java.text.MessageFormat
**基本用法**<br/>
+ 设置消息格式模板-new MessageFormat(...)
+ 格式化-format(new Object[]{...})
**消息格式模式**<br/>
+ 格式元素：{ArgumentIndex(,FormatType,(FormatStyle))}
+ FormatType:消息格式类型，可选项，每种类型在number,data,time和choice类型选其一
+ FormatStyle:消息格式风格，可选项，包括：short,medium,long,full,integer,currency和percent.
**高级特性**<br/>
+ 重置消息格式模板
+ 重置java.util.Locale
+ 重置java.text.Format

# 5 MessageSource开箱即用实现
**基于ResourceBundle+MessageFormat组合MessageSource实现**<br/>
+ org.springframework.context.support.ResourceBundleMessageSource
**可重载Properties+MessageFormat组合MessageSource实现**<br/>
+ org.springframework.context.support.ReloadableResourceBundleMessageSource

# 6 MessageSource内建依赖
**MessageSource内建Bean可能来源**<br/>
+ 预注册Bean名称为：“messageSource”，类型为：MessageSource Bean
_在Springboot中已经预先创建了这个bean_
+ 默认内建实现-DelegatingMessageSource
+ &nbsp;&nbsp;层次性查找MessageSource对象

# 7 Spring Boot为什么要新建MessageSource Bean
+ AbstractApplicationContext的实现决定了MessageSource内建实现
+ SpringBoot通过外部化配置简化MessageSource Bean构建
+ Spring Boot基于Bean Validation校验非常普遍
