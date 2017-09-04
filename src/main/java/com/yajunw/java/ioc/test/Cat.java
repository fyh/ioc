package com.yajunw.java.ioc.test;

import com.yajunw.java.ioc.Bean;
import com.yajunw.java.ioc.Inject;
import com.yajunw.java.ioc.test.sub.Dog;

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
