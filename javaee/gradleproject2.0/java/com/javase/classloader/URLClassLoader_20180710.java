package com.javase.classloader;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by zjm on 2018/7/10.
 */
public class URLClassLoader_20180710 {

    @Test
    public  void test20180710(){
        URL url1= null;
        try {
            url1 = new URL("file:c:/test1.jar");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLClassLoader myClassLoader1=new URLClassLoader(new URL[]{url1},
                Thread.currentThread().getContextClassLoader());

        try {
            Class myClass1=myClassLoader1.loadClass("actionImplement.TestAction");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
