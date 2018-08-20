package com.jvm;

import org.junit.Test;

/**
 * Created by zjm on 2018/4/12.
 */
public class GarbageCollection {
    Object instance=null;
    private  int _1M=1024_81024;
    private  byte[] bigSize=new byte[_1M];
    private  static  GarbageCollection GC_F;
    @Test
    public void  GCtest1(){
        GarbageCollection gc1=new GarbageCollection();
        GarbageCollection gc2=new GarbageCollection();
        gc1.instance=gc2;
        gc2.instance=gc1;
        System.gc();

    }

    @Override
    public void finalize() throws Throwable{//只会被调用一次
         super.finalize();
         System.out.println("finalize  method executed");
        GC_F=this;
    }
    @Test
    public void  GCtest2(){
        GC_F=new GarbageCollection();
        GC_F=null;
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (GC_F==null){
            System.out.println("yes i am deal");
        }else {
            System.out.println("no i am not deal");
        }

        GC_F=null;
        System.gc();//finalize重写方法此时不会再被调用
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (GC_F==null){
            System.out.println("yes i am deal");
        }else {
            System.out.println("no i am not deal");
        }
    }
}
