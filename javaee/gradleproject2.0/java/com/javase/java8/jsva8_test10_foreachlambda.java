package com.javase.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjm on 2017/8/3.
 */
public class jsva8_test10_foreachlambda {
    @Test
    public void  list(){
        List<String> list=new ArrayList<>();
        List<String> list2=new ArrayList<>();
        list.add("1");list.add("2");list.add("3");
        list.add("a");list.add("B");list.add("c");
        list.add("B2");list.add("B3");
        list.forEach(i->System.out.println("I:"+i));
        list.forEach(i->{
            if (i.equals("B")) {
                list2.add("B0");
                list2.add("B1");
                list2.add("B2");
                System.out.println("II:"+i);
            }
        });

        list.forEach(System.out::println);

        list.stream()
                .filter(s->s.contains("B"))
                .forEach(System.out::println);
        list2.forEach(System.out::println);

    }
}
