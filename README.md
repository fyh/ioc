# ioc

A simple ioc container.

一个简单的ioc容器，实现了以下功能：
- 属性注入（public/protected/private）
- 注解方式配置：@Config容器扫描，@Bean容器创建, @Inject容器注入

不支持的功能：
- 字符串表示的basePackageName
- 不支持构造时和方法注入
- 只支持单实例bean

包含了一个小的test，证明可以运行起来。

欢迎来指导，让我知道哪里可以改进。
