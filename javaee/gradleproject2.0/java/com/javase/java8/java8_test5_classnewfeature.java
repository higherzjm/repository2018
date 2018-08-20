package com.javase.java8;

import org.junit.Test;

import java.util.Optional;

/**
 * Created by zjm on 2017/7/23.
 */
public class java8_test5_classnewfeature {

    @Test
    public  void  test1_Optional(){
        Optional< String > fullName = Optional.ofNullable( null );
        System.out.println( "Full Name is set? " + fullName.isPresent() );
        System.out.println( "Full Name: " + fullName.orElseGet( () -> "[none]" ) );//orElse()方法和orElseGet()方法类似，但是orElse接受一个默认值而不是一个回调函数
        System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );

        Optional< String > firstName = Optional.of( "Tom" );
        System.out.println( "First Name is set? " + firstName.isPresent() );
        System.out.println( "First Name: " + firstName.orElseGet( () -> "[none]" ) );
        System.out.println( firstName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
        System.out.println();
    }
}
