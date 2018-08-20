package com.jvm;

import org.junit.Test;

/**
 * Created by zjm on 2018/4/1.
 */
public class jvmParams {
    public String a="";
    private  int b=0;
    public static void main(String[] args){

    }

    /**
     * 获取虚拟机内存
     */
    @Test
    public void jvmMemory(){
        long maxMemory = Runtime.getRuntime().maxMemory();//返回Java虚拟机试图使用的最大内存量。
        Long totalMemory = Runtime. getRuntime().totalMemory();//返回Java虚拟机中的内存总量。
        long freeMemory = Runtime.getRuntime().freeMemory();//空闲内存大小
        System.out.println("MAX_MEMORY ="+maxMemory +"(字节),"+(maxMemory/(double)1024/1024) + "MB");
        System.out.println("TOTAL_ MEMORY = "+totalMemory +",(字节)"+(totalMemory/(double)1024/1024) + "MB");
        System.out.println("freeMemory ="+freeMemory +"(字节),"+(freeMemory/(double)1024/1024) + "MB");
    }
}
