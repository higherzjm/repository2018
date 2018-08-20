package com.jvm;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjm on 2018/4/10.
 */
public class ExceptionTest {
    /**
     * java 堆异常测试
     * OutOfMemoryError
     * Xms,Xmx设置, 如-Xms512m -Xmx512m
     */
    @Test
    public void outOfHeap(){
        List<OutOfMemoryClass> list=new ArrayList<>();
       while (true){
           System.out.println(list.size());
           list.add(new OutOfMemoryClass());
       }
    }

    /**
     * 虚拟机栈异常测试
     * java.lang.StackOverflowError
     * Xss 设置，如 Xss512m
     */
    @Test
    public void stackSOF(){
        try {
            stackLeak();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private int stacklength=0;
    public void stackLeak(){
       stacklength++;
       System.out.println("stackLength:"+stacklength);
       stackLeak();
    }

    /**
     * 方法区运行时常量异常
     */
    @Test
    public void PermGenConstantException(){
        List<String> list=new ArrayList<>();
        try {
            int i=0;
            while (true){
                list.add(String.valueOf(i++).intern());
                System.out.println(String.valueOf(i++).intern());

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 方法区溢出
     */
    @Test
    public void PermGenException(){
        try {
            while (true) {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OutOfMemoryClass.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                        return proxy.invokeSuper(o, args);
                    }
                });
                OutOfMemoryClass outOfMemoryClass = (OutOfMemoryClass) enhancer.create();
                outOfMemoryClass.OutOfMemoryClassMethod();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *  本机直接内存溢出
     *  java.lang.OutOfMemoryError
     */
    @Test
    public void localMemory47Exception(){
        Field field= Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe=null;
        try {
             unsafe=(Unsafe)field.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        while (true){
            unsafe.allocateMemory(1024*1024*10);
        }
    }
}
class OutOfMemoryClass{
    public OutOfMemoryClass() {
    }


    public void OutOfMemoryClassMethod(){
        System.out.println("OutOfMemoryClassMeth方法调用");
    }
}
