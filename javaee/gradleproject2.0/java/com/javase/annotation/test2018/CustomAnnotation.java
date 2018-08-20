package com.javase.annotation.test2018;

import org.junit.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 自定义注解
 */
@HiAnnotation(id = 18, address = "福建省厦门市", name = "朱总")
public class CustomAnnotation {



    @Test
    public void test0724_2(){
        HiAnnotation ha=null;
        ha=CustomAnnotation.class.getDeclaredAnnotation(HiAnnotation.class);
        if (ha != null) {
            int id = ha.id();
            String name = ha.name();
            String address = ha.address();
            System.out.println("类的注解:name:"+name+";address:"+address+";name:"+name);
        }


        Class<?> t=UserAnnotation.class;
        // 方法中的注解
        for (Method m : t.getDeclaredMethods()) {
            String totalmethod = m.toString();
            String method = totalmethod.substring(0, totalmethod.indexOf("("));
            method = method.substring(method.lastIndexOf(".") + 1);
             ha = m.getAnnotation(HiAnnotation.class);
            if (ha != null) {
                int id=ha.id();
                String name=ha.name();
                String address=ha.address();
                //调用对象方法
                if (method.equals("useannoptation1")) {
                    try {
                        m.invoke(t.newInstance(),id,name);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        m.invoke(t.newInstance(),id,name,address);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("field");

        // 域中的注解
        for (Field field : t.getDeclaredFields()) {
             ha = field.getAnnotation(HiAnnotation.class);
            if (ha != null) {
                System.out.println("field:"+field+":id=" + ha.id() + ";name=" + ha.name()+ ";address=" + ha.address());
            }
        }
    }

    // 注解处理器——————反射
    @Test
    public void test0724() {
        Class<?> t=UserAnnotation.class;
        System.out.println("method");
        Userinterface userinterface=null;
        try {
            userinterface= (Userinterface) t.newInstance();//实例化使用对象
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 方法中的注解
        for (Method m : t.getDeclaredMethods()) {
            String totalmethod = m.toString();
            String method = totalmethod.substring(0, totalmethod.indexOf("("));
            method = method.substring(method.lastIndexOf(".") + 1);
            HiAnnotation ha = m.getAnnotation(HiAnnotation.class);
            if (ha != null) {
                int id=ha.id();
                String name=ha.name();
                String address=ha.address();
                //调用对象方法
                if (method.equals("useannoptation1")) {
                    userinterface.useannoptation1(id, name);

                }else {
                    userinterface.useannoptation2(id,name,address);

                }
            }
        }
        System.out.println("field");

        // 域中的注解
        for (Field field : t.getDeclaredFields()) {
            HiAnnotation ha = field.getAnnotation(HiAnnotation.class);
            if (ha != null) {
                System.out.println("field:"+field+":id=" + ha.id() + ";name=" + ha.name()+ ";address=" + ha.address());
            }
        }
    }
}

// 注解声明
// @Target(ElementType.METHOD)//元注解,如果想应用于所有的elementtype则省去这行
@Retention(RetentionPolicy.RUNTIME)
@interface HiAnnotation {

    public int id();

    public String address();// 元素

    public String name() default "allen";

}

//接口，方便反射器器实例化对象
interface Userinterface{
    void useannoptation1(Integer a, String b);
    void useannoptation2(Integer a, String b,String c);

}

// 使用注解
class UserAnnotation implements Userinterface{
    @HiAnnotation(id = 20, address = "好莱坞")
    @Override
    public void useannoptation1(Integer a, String b) {
        System.out.println("a:"+a + ";b:" + b);
    }
    @Override
    @HiAnnotation(id = 21, address = "flb", name = "张三")
    public void useannoptation2(Integer a, String b,String c) {
        System.out.println("a:"+a + ";b:" + b+";c:"+c);
    }

    //参数使用注意，一般意义不大
    @HiAnnotation(name = "李四", id = 22, address = "哈哈")
    private int a;

}

class AAA{
    public void useannoptation1(Integer a, String b) {
        System.out.println("a:"+a + ";b:" + b);
    }
}


