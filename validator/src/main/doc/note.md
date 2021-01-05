# 1 Spring校验使用场景
+ Spring常规校验（Validator）
+ Spring数据绑定（DataBinder）
+ Spring Web 参数绑定（WebDataBinder）
+ Spring WebMVC/WebFlux处理方法参数校验

# 2 Validator接口设计
**接口职责**<br/>
+ Spring内部校验器接口，通过编程的方式校验目标对象
**核心方法**<br/>
+ supports(Class)：校验目标类能否校验
+ validate(Object,Errors)：校验目标对象，并将校验失败的内容输出至Errors对象
**配套组件**
+ 错误收集器：org.springframework.validation.Errors
+ Validator工具类：org.springframework.validation.ValidationUtils

# 3 Errors接口设计
**接口职责**<br/>
+ 数据绑定和校验错误收集接口，与Java Bean和其属性有强关联的关系。
**核心方法**<br/>
+ reject方法(重载)：收集错误文案。
+ rejectValue方法(重载)：收集字段对象中的错误文案。
**配套组件**
+ Java Bean错误描述：org.springframework.validation.ObjectError
+ Java Bean属性错误描述：org.springframework.validation.FieldError

# 4 Errors文案来源
**Errors文案生成步骤**<br/>
+ 选择Errors实现（如：org.springframework.validation.BeanPropertyBindingResult）
+ 调用reject或rejectValue方法
+ 获取Errors对象中ObjectError或FieldError
+ 将ObjectError或FieldError中的code和args，关联MessageSource实现（如：org.springframework.context.support.ResourceBundleMessageSource）

# 5 自定义Validator
**实现org.springframework.validation.Validator接口**<br/>
+ 实现support方法
+ 实现validate方法
+ &nbsp;&nbsp;通过Errors对象收集错误信息
+ &nbsp;&nbsp;&nbsp;&nbsp;ObjectError:对象（Bean）错误。
+ &nbsp;&nbsp;&nbsp;&nbsp;FieldError：对象（Bean）属性（Property）错误
+ &nbsp;&nbsp;通过ObjectError和FieldError关联MessageSource实现获取最终文案

# 6 Validator的救赎
+ 核心组件-org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
+ 依赖Bean Validation - JSR-303 or JSR-349 provider
+ Bean方法参数校验-org.springframework.validation.beanvalidation.MethodValidationPostProcessor