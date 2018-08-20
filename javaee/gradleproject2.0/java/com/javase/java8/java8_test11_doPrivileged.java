package com.javase.java8;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by zjm on 2018/7/9.
 */
public class java8_test11_doPrivileged {
    public java8_test11_doPrivileged() {
        System.out.print("java8_test_doPrivileged  初始化");
    }

    /**
     * 授权
     */
    @Test
    public void  test20180709(){
        Constructor<?> constructor=null;
        try {
            constructor=java8_test11_doPrivileged.class.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        final  Constructor<?> finalConstructor = constructor;
        java.security.AccessController.doPrivileged(
                new java.security.PrivilegedAction<Void>() {
                    public Void run() {
                        System.out.println("授权区域");
                        finalConstructor.setAccessible(true);
                        return null;
                    }
                });
        try {
            constructor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

