package com.yajunw.java.ioc;

/**
 * All class container must implement IContainer interface
 * 
 */
public interface IContainer {
    public <T> T find(Class<T> klass);
}