package com.javase.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by zjm on 2017/7/23.
 *  并行（parallel）数组
 *  Java 8增加了大量的新方法来对数组进行并行处理。
 *  可以说，最重要的是parallelSort()方法，因为它可以在多核机器上极大提高数组排序的速度。
 *  下面的例子展示了新方法（parallelXxx）的使用。
 */
public class java8_test9_parallel {
    @Test
    public  void test(){
        long[] arrayOfLong = new long [ 20000 ];
        Arrays.parallelSetAll(arrayOfLong, index -> ThreadLocalRandom.current().nextInt( 1000000 ) );
        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(i -> System.out.print( i + " " ) );
        System.out.println();
        Arrays.parallelSort( arrayOfLong );
        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(i -> System.out.print( i + " " ) );
        System.out.println();
        /*
         上面的代码片段使用了parallelSetAll()方法来对一个有20000个元素的数组进行随机赋值。
         然后，调用parallelSort方法。这个程序首先打印出前10个元素的值，
         之后对整个数组排序。这个程序在控制台上的输出如下（请注意数组元素是随机生产的）：
         */
    }
}
