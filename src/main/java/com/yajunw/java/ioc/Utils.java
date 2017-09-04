package com.yajunw.java.ioc;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Set;

import javax.management.RuntimeErrorException;
import javax.xml.transform.ErrorListener;

import java.util.HashSet;
import java.util.List;

public class Utils {
    public static Set<Class<?>> getAllClasses(String... basePackage) {
        Set<Class<?>> result = new HashSet<Class<?>>();
        for (String p : basePackage) {
            List<File> dirs = new ArrayList<File>();
            try {
                Enumeration<URL> resources = getClassLoader().getResources(p.replace(".", "/"));
                while (resources.hasMoreElements()) {
                    URL ele = resources.nextElement();
                    dirs.add(new File(ele.getFile()));
                }
            } catch (IOException e) {
                throw new RuntimeException("", e);
            }
            for (File f : dirs) {
                result.addAll(findAllClasses(f, p));
            }
        }
        return result;
    }

    static Set<Class<?>> findAllClasses(File dir, String basePackage) {
        Set<Class<?>> result = new HashSet<Class<?>>();
        File[] files = dir.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                result.addAll(findAllClasses(f, basePackage+"."+f.getName()));
            } else if (f.getName().endsWith(".class")) {
                try {
                    Class<?> k = Class.forName(basePackage+"."+f.getName().substring(0, f.getName().length()-".class".length()));
                    if (k.getAnnotation(Bean.class) != null) {
                        result.add(k);
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("classloader exception:", e);
                }
            }
        }
        return result;
    }

    public static <T> T instantiate(Class<T> klass, Object... args) {
        try {
            Constructor<T> ctor = klass.getDeclaredConstructor();
            return ctor.newInstance(args);
        } catch(NoSuchMethodException e) {
            throw new RuntimeException("", e);
        } catch(InvocationTargetException e) {
            throw new RuntimeException("", e);
        } catch(IllegalAccessException e) {
            throw new RuntimeException("", e);
        } catch(InstantiationException e) {
            throw new RuntimeException("", e);
        }
    }
    
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}