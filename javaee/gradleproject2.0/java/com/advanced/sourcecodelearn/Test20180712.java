package com.advanced.sourcecodelearn;

import org.junit.Test;

import java.util.*;

/**
 * Created by zjm on 2018/7/12.
 * 源码学习
 */
public class Test20180712 {

    /**
     * map接口
     */
    @Test
    public void mapInterface_20180712(){
        Map<String,Integer> hashMap=new HashMap<>();
        for (int i=0;i<4;i++){
            hashMap.put("num"+i,i);
        }
        System.out.println("numMap:"+hashMap);
        int age=hashMap.get("num3");
        System.out.println("num:"+age);
        for (Map.Entry<String,Integer> entry:hashMap.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

        Map<String,String> hashtable=new Hashtable<>();
        hashtable.put("name","张三");
        String name=hashtable.get("name");
        System.out.println("name:"+name);

        Map<String,String>  linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("name","李四");
        String name2=linkedHashMap.get("name");
        System.out.println("name2:"+name2);

    }

    /**
     * map接口
     */
    @Test
    public void listInterface_20180713(){
        List<String>  list=new ArrayList<>();
        list.add("value1");
        list.add("value2");
        String value=list.get(0);
        System.out.println("value:"+value);

        List<String> vector=new Vector<>();
        vector.add("vectorValue1");
        vector.add("vectorValue2");
        String vectorValue=vector.get(0);
        System.out.println("vectorValue:"+vectorValue);
    }

    /**
     * 字符串变量
     */
    @Test
    public void string_20180716(){

        String str="123abc456efg";
        int strLength=str.length();
        System.out.println("strLength:"+strLength);
        char[] chars=new char[str.length()];
        str.getChars(2,strLength,chars,1);//拷贝指定字符串按指定长度拷贝至指定字符串数组
        System.out.println("chars:"+Arrays.toString(chars));

        char[] chars2=new char[12];
        for (int i=100,j=0;j<chars2.length;i++,j++){
            chars2[j]= (char)i;
        }
        chars2=Arrays.copyOf(chars2,24);//修改当前字符数组长度
        System.out.println("chars2 length:"+chars2.length+
                ";chars2:"+Arrays.toString(chars2));


        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("china");
        stringBuffer.append("usa");
        System.out.println("stringBuffer value:"+stringBuffer.toString());

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("中国");
        stringBuilder.append("美国");
        System.out.println("stringBuilder value:"+stringBuilder.toString());





    }


}
