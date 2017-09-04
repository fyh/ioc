package com.yajunw.java.ioc;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Container implements IContainer {

    private Map<Class<?>, Object> classHolder;

    private Container() {
        classHolder = new HashMap<Class<?>, Object>();
    }

    /**
     * @param configClass the config class
     */
    public Container(Class<?> configClass) {
        this();
        register(configClass);
        beforeInject();
        inject();
        afterInject();
    }

    @SuppressWarnings("unchecked")
	public <T> T find(Class<T> klass) {
        Object result = classHolder.get(klass);
        if (result != null && result.getClass() == klass) {
            return (T) result;
        }
		return null;
    }
    
    private void register(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(Config.class)) {
            throw new RuntimeException(configClass.getCanonicalName() + " doesn't annotated with " + Config.class.getCanonicalName());
        }
        Config config = configClass.getAnnotation(Config.class);
        Class<?>[] basePackageClasses = config.basePackageClasses();
        Set<String> basePackages = new HashSet<String>();
        for (Class<?> c : basePackageClasses) {
            basePackages.add(c.getPackage().getName());
        }
        Set<Class<?>> classSet = Utils.getAllClasses(basePackages.toArray(new String[basePackages.size()]));
        for (Class<?> c : classSet) {
            classHolder.put(c, Utils.instantiate(c));
        }
    }

    private void beforeInject() {}

    private void inject() {
        for (Map.Entry<Class<?>, Object> e : classHolder.entrySet()) {
            Field[] fields = e.getKey().getDeclaredFields();
            for (Field f : fields) {
                if (f.getAnnotation(Inject.class) != null) {
                    try {
                        f.setAccessible(true);
                        f.set(e.getValue(), find(f.getType()));
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException("inject error: class=" + e.getKey().getName(), ex);
                    }
                }
            }
        }
    }

    private void afterInject() {}

}