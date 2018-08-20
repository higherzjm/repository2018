package com.javase.designpatterns.strategypatterns;

/**
 * 具体策略(ConcreteStrategy)角色：包装了相关的算法或行为
 */
public class IntermediateMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double booksPrice) {

        System.out.println("对于中级会员的折扣为10%");
        return booksPrice * 0.9;
    }

}