package com.yajunw.java.ioc.test;

import com.yajunw.java.ioc.Container;
import com.yajunw.java.ioc.test.sub.Dog;

public class App {

    public static void main(String[] args) {
        Container c = new Container(RootConfig.class);
        Cat cat = c.find(Cat.class);
        cat.talk();
        Dog dog = c.find(Dog.class);
        dog.talk();
    }

}
