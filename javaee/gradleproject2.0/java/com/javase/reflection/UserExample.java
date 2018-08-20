package com.javase.reflection;

import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.function.Supplier;

public class UserExample {
    /**
     * 发放张数
     */
    private int count;
    /**
     * 有效期
     */
    private String days="3,7";
    /**
     * 优惠券面额
     */
    private String price="20,30";

    public int getDays(Integer i) {
        if (this.days.indexOf(",") == -1) { //取值相同
            return Integer.parseInt(days);
        } else {
            return Integer.parseInt(days.split(",")[i]);
        }
    }

    public int getPrice(Integer i) {
        if (this.price.indexOf(",") == -1) { // 取值相同
            return Integer.parseInt(price);
        } else {
            return Integer.parseInt(price.split(",")[i]);
        }
    }
    public int getEachValue(int i, String fieldName){
        Field field =ReflectionUtils.findField(this.getClass(),fieldName);
        String str= null;
        try {
            str = (String) field.get(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(str.indexOf(",")==-1){
            return Integer.parseInt(str);
        }else{
            return Integer.parseInt(str.split(",")[i]);
        }

    }
    public int getEachValue_java8(int i, Supplier<String> supplier){
        if(supplier.get().indexOf(",")==-1){
            return Integer.parseInt(supplier.get());
        }else{
            return Integer.parseInt(supplier.get().split(",")[i]);
        }
    }
    @Test
    public void mainOriginal(){
        for (int i = 0; i < 2; i++) {
            System.out.println(getDays(i));
            System.out.println(getPrice(i));
        }
    }

    /**
     * 通过参数名称反射
     */
    @Test
    public void maineflection(){
        for (int i = 0; i < 2; i++) {
            System.out.println(getEachValue(i, "days"));
            System.out.println(getEachValue(i, "price"));
        }
    }
    @Test
    public void maineflection_java8(){
        UserExample config=new UserExample();
        for (int i = 0; i < 2; i++) {
           // System.out.println(getEachValue_java8(i,config::getDays));
           // System.out.println(getEachValue_java8(i,config::getPrice));
        }
    }

}