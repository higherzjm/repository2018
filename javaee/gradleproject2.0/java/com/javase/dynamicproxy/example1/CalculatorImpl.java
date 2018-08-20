package com.javase.dynamicproxy.example1;

/**
 * Created by zjm on 2018/1/13.
 */
public class CalculatorImpl implements  Calculator {
    @Override
    public int add(int a, int b) {
        int sum=a+b;
        return sum;
    }
}
