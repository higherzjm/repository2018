package com.javase.designpatterns.strategypatterns;

/**
 * 具体策略(ConcreteStrategy)角色：包装了相关的算法或行为
 */
public class PrimaryMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double booksPrice) {
        
        System.out.println("对于初级会员的没有折扣");
        return booksPrice;
    }

}