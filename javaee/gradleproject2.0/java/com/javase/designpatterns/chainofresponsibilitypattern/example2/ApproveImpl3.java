package com.javase.designpatterns.chainofresponsibilitypattern.example2;

import java.util.List;

/**
 * Created by zjm on 2018/2/9.
 */
public class ApproveImpl3 implements ApproveInter {
    @Override
    public String approveing(Double amount,int index,List<ApproveInter> responsibilityChains,ApproveChain approveChain) {
        if (amount<100000){
            return "ApproveImpl--3 审核通过";
        }else{
            if (index==responsibilityChains.size()-1) {
                return "数额太大,无法审核通过";
            }else {
                return approveChain.processor(amount,index+1, responsibilityChains);
            }
        }
    }
}
