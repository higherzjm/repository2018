package com.javase.objects;

import org.junit.Test;

import java.util.Objects;

/**
 * Created by zjm on 2018/7/5.
 */
public class test20180705 {
    @Test
    public void  test1_requireNonNull(){
        String str= Objects.requireNonNull(null);
        System.out.println(str);
    }
}
