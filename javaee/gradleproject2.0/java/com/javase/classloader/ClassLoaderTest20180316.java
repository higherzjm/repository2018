package com.javase.classloader;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.Date;

/**
 * Created by zjm on 2018/3/16.
 */
public class ClassLoaderTest20180316
{
    public void test_20180316(int a){
        System.out.println("test:"+new Date()+":"+a);
    }
    @Test
    public void testmain_20180316(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoaderTest20180316 classTest= (ClassLoaderTest20180316) Class.forName("com.javase.classloader.ClassLoaderTest20180316",true,loader).newInstance();
            ClassLoaderTest20180316 classTest2= (ClassLoaderTest20180316) Class.forName("com.javase.classloader.ClassLoaderTest20180316").newInstance();
            ClassLoaderTest20180316 classTest3=ClassLoaderTest20180316.class.newInstance();
            Class<?> loadClass= ClassLoaderTest20180316.class.getClassLoader().loadClass("com.javase.classloader.ClassLoaderTest20180316");
            ClassLoaderTest20180316 classTest4= (ClassLoaderTest20180316)loadClass.newInstance();
            classTest.test_20180316(1);
            classTest2.test_20180316(2);
            classTest3.test_20180316(3);
            classTest4.test_20180316(4);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test20180709(){
        Class<?> loadClass= null;
        try {
            loadClass = ClassLoaderTest20180316.class.getClassLoader().loadClass("com.javase.classloader.ClassLoaderTest20180316");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ClassLoaderTest20180316 classLoaderTest20180316= (ClassLoaderTest20180316)loadClass.newInstance();
            classLoaderTest20180316.test_20180316(100);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实例化私有类
     */
    @Test
    public void test20180709_2(){
        Class<?> loadClass= null,loadClass2= null,loadClass3= null;
        try {
            loadClass = ClassLoaderTest20180316.class.getClassLoader().loadClass("com.javase.classloader.TestPrivateClass");
            loadClass2=Class.forName("com.javase.classloader.TestPrivateClass");
            loadClass3=Class.forName("com.javase.classloader.TestPrivateClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            TestPrivateClass testPrivateClass= (TestPrivateClass)loadClass.newInstance();
            testPrivateClass.test1("中国人");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            TestPrivateClass testPrivateClass2= (TestPrivateClass)loadClass2.newInstance();
            testPrivateClass2.test1("中国人");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Constructor<?> constructor =loadClass3.getDeclaredConstructor();
            constructor.setAccessible(true);
            TestPrivateClass testPrivateClass3= (TestPrivateClass) constructor.newInstance();
            testPrivateClass3.test1("中国人");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class  TestPrivateClass{
    private TestPrivateClass() {
        System.out.println("初始化TestPrivateClass");
    }

    public void test1(String param){
        System.out.println(param);
    }
}

