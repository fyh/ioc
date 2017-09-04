package com.yajunw.java.ioc.test.sub;

import com.yajunw.java.ioc.Bean;
import com.yajunw.java.ioc.Inject;
import com.yajunw.java.ioc.test.Cat;

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
