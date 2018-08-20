package com.javase.designpatterns.chainofresponsibilitypattern.example2;

import org.junit.Test;

/**
 * 责任链设计模式
 */
public class MainClass {
    @Test
    public void test1(){
        ApproveChain approveChain=new ApproveChain();
        approveChain.executeChain(80000.00);
    }
}
