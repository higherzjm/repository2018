package com.javase.reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zjm on 2018/3/25.
 */
public class ReflectionBase20180325 {
    private int filed1=100;
    private String  filed2="张三";
    public void method1(){

        System.out.println("method 1 invocation");
    }
    public void method2(Integer a,String b)
    {
        System.out.println("method 2 invocation:a="+a+";b="+b);
    }
    private String method3(Integer a,String b){
        System.out.println("method 3 invocation:a="+a+";b="+b);
        return a+";"+b;
    }

    /**
     * 域反射
     */
    @Test
    public void main_filed1(){
        ReflectionBase20180325 reflect=new ReflectionBase20180325();

        Field[]  fields=reflect.getClass().getDeclaredFields();
        for (Field field:fields){
            System.out.println("field name:"+field.getName());
        }

        try {
            Field filed1=reflect.getClass().getDeclaredField("filed1");
            Integer returnValue=filed1.getInt(reflect);
            System.out.println("returnValue:"+returnValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Field filed2=reflect.getClass().getDeclaredField("filed2");
            filed2.set(reflect,"修改为李四");
            String returnValue= (String) filed2.get(reflect);
            System.out.println("returnValue:"+returnValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法反射
     */
    @Test
    public void main_method1(){

        Method[]  methods=ReflectionBase20180325.class.getDeclaredMethods();
        for (Method method:methods){
            System.out.println("method name:"+method.getName());
        }

        try {
            Method method11=ReflectionBase20180325.class.getMethod("method1");
            String returnValue = (String) method11.invoke(this);
            System.out.println("returnValue:"+returnValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Method method2=ReflectionBase20180325.class.getMethod("method2",Integer.class,String.class);
            String returnValue = (String) method2.invoke(this,25,"张三");
            System.out.println("returnValue:"+returnValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //可以访问私有方法
            Method method3=ReflectionBase20180325.class.getDeclaredMethod("method3",Integer.class,String.class);
            String returnValue = (String) method3.invoke(this,25,"张三");
            System.out.println("returnValue:"+returnValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构造函数反射
     * getDeclaredConstructor:包括私有的构造函数
     * getConstructor:只能是公共的构造函数
     */
    @Test
    public void main_constructor1_20180704(){
        System.out.println("-----------------------------");
        try {
            Class c=Class.forName("com.javase.reflection.A");

            //以下调用无参的、私有构造函数
            Constructor c0=c.getDeclaredConstructor();
            c0.setAccessible(true);
            A a=(A)c0.newInstance();

            //以下调用带参的、私有构造函数
            Constructor c1=c.getDeclaredConstructor(new Class[]{int.class,int.class});
            c1.setAccessible(true);
            A a1=(A)c1.newInstance(new Object[]{5,6});

            //以下调用带参的、非私有构造函数
            Constructor c2=c.getConstructor(new Class[]{int.class,int.class,int.class});
            c2.setAccessible(true);
            A a2=(A)c2.newInstance(new Object[]{5,6,7});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void main_constructor2_20180705(){
        System.out.println("-----------------------------");
        try {
            Class<?> b=Class.forName("com.javase.reflection.B");
            A a=new A(1,2,3);
            Class<?>[] constructorParams = { A.class };
            Constructor<?> cons = b.getConstructor(constructorParams);
            cons.newInstance(new Object[]{a});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class A {
    private A() {
            System.out.println("A's constructor is called.");
    }

    private A(int a, int b) {
        System.out.println("a:" + a + " b:" + b);
    }

    public A(int a, int b,int c) {
        System.out.println("a:" + a + " b:" + b+" c:"+c);
    }
}

class  B{
    public B(A a) {
        System.out.println("B's constructor is called:a="+a);
    }
}

