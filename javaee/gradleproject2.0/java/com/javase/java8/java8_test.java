package com.javase.java8;

//import java.util.stream.IntStream;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by zjm on 2017/7/21.
 */
public class java8_test {


    // Lambda表达式
    @Test
    public  void test1(){

        List<Map<String,String>> items =new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        map.put("name","张三");
        items.add(map);

        map=new HashMap<>();
        map.put("name","李四");
        items.add(map);

        map=new HashMap<>();
        map.put("name","王五");
        items.add(map);

        map=new HashMap<>();
        map.put("name","朱八");
        items.add(map);

        System.out.println("items 1"+items);
        if(!CollectionUtils.isEmpty(items)){
            items.stream().filter(item -> "朱八".equals(item.get("name"))).forEach(item -> {
                System.out.println("item:"+item);
                item.put("name","范一");
            });
        }
        System.out.println("items 2"+items);




        List<Integer> nums=new ArrayList<>();
        nums.add(100);nums.add(9);nums.add(20);nums.add(55);
        nums.add(3);nums.add(21);nums.add(33);nums.add(6);
        System.out.println("nums 1"+nums);
        nums.sort( (e1, e2 ) -> e1.compareTo( e2 ) );
        System.out.println("nums 2"+nums);
    }

    //线程函数式接口
    @Test
    public  void test2(){
        ExecutorService executor = Executors.newFixedThreadPool(5);
        IntStream.range(0, 5).forEach(i -> executor.execute(() -> {
             System.out.println("线程数--->i="+i);
        }));
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread=new Thread(()->{
           System.out.println("简单线程");
        });
        thread.start();
    }
    //自定义函数式接口--无用
    @Test
    public  void test3(){
        ApplayFunctionalInterface applayFunctionalInterface=new ApplayFunctionalInterface();
        //applayFunctionalInterface.peat(()->{return "";});
    }

    //接口的默认方法与静态方法
    @Test
    public  void test4(){
        class DefaulableImpl implements Defaulable{
            @Override
            public String method2(String name) {
                return "my name is "+name;
            }
        }
        DefaulableImpl imp=new DefaulableImpl();
        System.out.println(imp.method2("张三"));
        imp.method1("李四");
    }
}

//函数式接口
@FunctionalInterface
 interface Functional {
    String name="";
    String getName(String name);
}

//自定义函数式接口
class  ApplayFunctionalInterface{
    public Functional functional;
    public void peat(Functional functional){
        this.functional=functional;
    }
}

//接口的默认方法与静态方法
interface Defaulable {
    //子类可重写
    default void method1(String name) {
        System.out.println("her name is "+name);
    }
    String method2(String name);
}






