package com.javase.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zjm on 2018/3/16.
 */
public class IteratorTest {

    @Test
    public void arrayToIterator(){
        Integer[] intarrays=new Integer[]{11,22,33,44,55,66};
        List<Integer> list=Arrays.asList(intarrays);
        Iterator iterator=list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void listToIterator(){
        List<String> datas=new ArrayList<>();
        datas.add("aa");datas.add("bb");datas.add("cc");
        datas.add("dd");datas.add("ee");datas.add("ff");
        Iterator iterator=datas.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
