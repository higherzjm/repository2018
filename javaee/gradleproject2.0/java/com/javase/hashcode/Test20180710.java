package com.javase.hashcode;

import org.junit.Test;

/**
 * Created by zjm on 2018/7/10.
 */
public class Test20180710 {

    @Test
    public void  test20180710(){
        String key="fick";
        int h;
        int keyhashcode=(key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        System.out.println("keyhashcode:"+keyhashcode);
        System.out.println(100 & keyhashcode);
    }

    @Test
    public void  test20180716(){
      String a="B";
      int b=a.hashCode()&0x7FFFFFFF;
      System.out.println("aa hashcode:"+a.hashCode()+":"+b);
      System.out.println("---:"+"朱".hashCode());
      int i=2147483647;//int类型的最大值
      System.out.println(0x7FFFFFFF);
    }


}
