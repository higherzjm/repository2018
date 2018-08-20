package com.javase.java8;

import org.junit.Test;

/**
 * Created by zjm on 2018/7/9.
 */
public class Java8_test12_modifiers {
    @Test
    public void  test20180708(){
        System.out.println(Java8_test12_modifiers.class.getModifiers());
        System.out.println(A.class.getModifiers());
        System.out.println(privateClass.class.getModifiers());
        System.out.println(protectedClass.class.getModifiers());
        System.out.println(publicinterface.class.getModifiers());
    }

    private class privateClass{

    }

    protected class protectedClass{

    }
   public interface publicinterface{

   }
}

interface A{

}

