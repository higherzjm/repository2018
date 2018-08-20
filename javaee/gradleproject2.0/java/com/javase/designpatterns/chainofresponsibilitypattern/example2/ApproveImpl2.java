package com.javase.designpatterns.chainofresponsibilitypattern.example2;

import java.util.List;

/**
 * Created by zjm on 2018/2/9.
 */
public class ApproveImpl2 implements ApproveInter {
    @Override
    public String approveing(Double amount,int index,List<ApproveInter> responsibilityChains,ApproveChain approveChain) {
        if (amount<50000){
            return "ApproveImpl--2 审核通过";
        }else{
            if (index==responsibilityChains.size()-1) {
                return "数额太大,无法审核通过";
            }else {
                System.out.println("金额大于50000,转交到下一个责任链处理");
                return approveChain.processor(amount,index+1, responsibilityChains);
            }
        }
    }
}
