package com.javase.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjm on 2018/3/1.
 */
public class MainClass {
    public static  void main(String[] arg){
        Map<String,Integer> map=new HashMap<>();
        Integer putValue=map.put("a",1);
        System.out.println("putValue="+putValue);
        map.put("b",2);
        Object b=map.get("b");
        System.out.println(map);
        System.out.println("map b:"+b);
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

    }
}
