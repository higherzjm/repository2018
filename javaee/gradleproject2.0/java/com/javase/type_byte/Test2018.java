package com.javase.type_byte;

import org.junit.Test;

/**
 * Created by zjm on 2018/8/6.
 */
public class Test2018{

    @Test
    public void test_20180806(){
        String str="abc123";
        str="张三";
        byte[] bytes=str.getBytes();
        str=new String(bytes);
        System.out.println("str:"+str);
        for (byte b:bytes){
            System.out.println(b+":"+(0xff &b));
        }

    }

    /**
     * char 与 byte 互相转换
     */
    @Test
    public void test_20180806_2(){
        char cr='a';
        byte be=(byte) cr;
        System.out.println(cr+":"+be);

        byte be2=98;
        char cr2= (char) be2;
        System.out.println(be2+":"+cr2);

    }
}
