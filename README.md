# ioc

A simple ioc container.

# 示例

根配置 RootConfig.java （支持多个配置类）
```java
@Config(basePackageClasses = RootConfig.class)
public class RootConfig {
}
```

Cat bean:
```java
@Bean
public class Cat {

    @Inject
    private Dog dog;

    public String getName() {
        return "M";
    }

    public void talk() {
        System.out.println("I'm a Cat. I've a dog who named " + dog.getName() + ".");
    }

}

```
Dog bean
```java
@Bean
public class Dog {

    @Inject
    private Cat cat;

    public String getName() {
        return "W";
    }

    public void talk() {
        System.out.println("I'm a Dog. I've a cat who named " + cat.getName() + ".");
    }

}

```

App.java:
```java
public class App {

    public static void main(String[] args) {
        Container c = new Container(RootConfig.class);
        Cat cat = c.find(Cat.class);
        cat.talk();
        Dog dog = c.find(Dog.class);
        dog.talk();
    }

}

```

# 简介

一个简单的ioc容器，实现了以下功能：
- 属性注入（public/protected/private）
- 注解方式配置：@Config容器扫描，@Bean容器创建, @Inject容器注入

不支持的功能：
- 字符串表示的basePackageName
- 不支持构造时和方法注入
- 只支持单实例bean

包含了一个小的test，证明可以运行起来。

欢迎来指导，让我知道哪里可以改进。
