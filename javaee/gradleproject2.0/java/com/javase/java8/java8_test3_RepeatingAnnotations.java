package com.javase.java8;

import org.junit.Test;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by zjm on 2017/7/22.
 * 1 重复注解 2 更好的类型推测机制
 */
public class java8_test3_RepeatingAnnotations {
    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    public @interface Filters {
        Filter[] value();
    }
    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    @Repeatable( Filters.class )
    public @interface Filter {
         String value();
    }
    @Filter( "filter1" )
    @Filter( "filter2" )
    @Filter( "filter3" )
    public interface Filterable {

    }

    // 重复注解
    @Test
   public  void test1(){
       for(Filter filter: Filterable.class.getAnnotationsByType( Filter.class ) ) {
           System.out.println( filter.value() );
       }
   }

   //更好的类型推测机制
    @Test
    public  void test2(){
        final Value<String> value = new Value<>();
        System.out.println(value.getOrDefault(null,Value.defaultValue("100") ));
    }

    static class Value< T > {
        public static <T> T defaultValue(T value) {
            return value;
        }

        public T getOrDefault( T value, T defaultValue ) {
            return ( value != null ) ? value : defaultValue;
        }
    }

    //扩展注解的支持
    @Test
    public  void test3(){
        Annotations.test();
    }

}

class Annotations {
    @Retention( RetentionPolicy.RUNTIME )
    @Target( { ElementType.TYPE_USE, ElementType.TYPE_PARAMETER } )
    public @interface NonEmpty {
    }

    public static class Holder< @NonEmpty T > extends @NonEmpty Object {
        public void method() throws @NonEmpty Exception {
        }
    }

    @SuppressWarnings( "unused" )
    public static void test(){
        final Holder< String > holder = new @NonEmpty Holder< String >();
        @NonEmpty Collection< @NonEmpty String > strings = new ArrayList<>();
    }
}
