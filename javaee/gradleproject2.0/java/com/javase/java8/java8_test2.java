package com.javase.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by zjm on 2017/7/22.
 * 方法的引用
 */
public class java8_test2 {
    //方法的引用
    @Test
    public  void test1() {
        Car car=Car.create(Car::new );
        car.repair();
        final List< Car > cars = Arrays.asList( car );
        cars.forEach(Car::collide );//class
        cars.forEach( Car::repair );//class
        cars.forEach( car::follow );//instance--这个方法接受一个Car类型的参数
    }

    @Test
    public  void test2() {
        car2 car2=Car2Factory.create(car2impl::new);
        car2.what();
    }
}

interface Car2Factory {
    static car2 create( Supplier< car2 > supplier ) {
        return supplier.get();
    }
}

class car2impl implements car2{

    @Override
    public void what() {
        System.out.println("car2");
    }
}

interface car2{
    public void what();
}

//方法的引用
class Car {
    public static Car create( final Supplier< Car > supplier ) {
        return supplier.get();
    }

    public static void collide( final Car car ) {
        System.out.println( "Collided " + car.toString() );
    }

    public void follow( final Car another ) {
        System.out.println( "Following the " + another.toString() );
    }

    public void repair() {
        System.out.println( "Repaired " + this.toString() );
    }
}