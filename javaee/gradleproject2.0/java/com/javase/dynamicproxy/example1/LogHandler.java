package com.javase.dynamicproxy.example1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by zjm on 2018/1/13.
 */
public class LogHandler implements InvocationHandler {
    Object obj;

    public LogHandler(Object obj) {
        this.obj = obj;
    }

    public LogHandler() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用前");
        System.out.println("method name:"+method.getName());
        System.out.println("method args:"+ Arrays.toString(args));
        Object ret=method.invoke(obj,args);
        System.out.println("调用后");
        return ret;
    }


    public static <T> T newMapperProxy(Class<T> mapperInterface,Object object) {
        ClassLoader classLoader = mapperInterface.getClassLoader();
        Class<?>[] interfaces = new Class[]{mapperInterface};
        LogHandler proxy = new LogHandler();
        proxy.obj=object;
        return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
    }

}
