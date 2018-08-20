package com.javase.dynamicproxy.example2;

import org.junit.Test;

import java.lang.reflect.Modifier;

/**
 * 接口代理
 */
public class MainClass {

    @Test
    public  void test1(){
        IHello hello = FacadeProxy.newMapperProxy(IHello.class);
        System.out.println(hello.say("hello world"));
    }

    /**
     * 判断是否为公共接口
     */
    @Test
    public  void test2(){
        Class<?> interfaceClass = null;
        try {
            interfaceClass = Class.forName(IHello.class.getName(), false, IHello.class.getClassLoader());
        } catch (ClassNotFoundException e) {
        }
        System.out.println("interfaceClass name:"+interfaceClass.getName());
        System.out.println("interfaceClass.isInterface():"+interfaceClass.isInterface());

        int flags = IHello.class.getModifiers();
        System.out.println("flags:"+flags+";ispublic:"+ Modifier.isPublic(flags));
    }

}  