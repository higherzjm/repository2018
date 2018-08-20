package com.javase.java8;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by zjm on 2017/7/23.
 */
public class java8_test8_Base64 {
    @Test
    public  void test(){
        final String text = "Base64 finally in Java 8 我是中国人!";

        final String encoded = Base64.getEncoder().encodeToString(text.getBytes( StandardCharsets.UTF_8 ) );
        System.out.println( encoded );
        final String decoded = new String(Base64.getDecoder().decode( encoded ), StandardCharsets.UTF_8 );
        System.out.println( decoded );
    }
}
