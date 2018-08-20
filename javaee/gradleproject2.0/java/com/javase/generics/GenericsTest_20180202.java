package com.javase.generics;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 泛型测试
 * Created by zjm on 2018/2/2.
 */
public class GenericsTest_20180202 {
    Logger logger = Logger.getLogger(GenericsTest_20180202.class);
    @Test
    public void main_test1(){
      Map<String,Integer> retdatas=AbstractClass.abstractClass_test1("张三",27,Integer.class);
      logger.info("retdatas:"+retdatas);
    }
    @Test
    public void main_test2(){
        CustomMap<String,Integer> customMap=new CustomMap<>();
        Integer ret=customMap.put("a",88);
        System.out.println("ret:"+ret);
    }
}


abstract  class AbstractClass{

    public  static <T> Map<String, T> abstractClass_test1(String key, Integer value, Class<T> type){
        Map<String,T>  datas=new HashMap<>();
        datas.put(key, (T) value);
        datas.put("李四", (T) value);
        return datas;
    }
}
class CustomMap<k,V>{
    V put(k a,V b){
        System.out.println(a+":"+b);
        return b;
    }
}