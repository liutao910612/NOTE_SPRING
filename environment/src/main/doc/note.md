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