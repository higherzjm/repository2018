package com.javase.designpatterns.chainofresponsibilitypattern.example2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjm on 2018/2/9.
 */
public class ApproveChain {

    public void executeChain(Double amount){
        List<ApproveInter> responsibilityChains=new ArrayList<>();
        responsibilityChains.add(new ApproveImpl1());
        responsibilityChains.add(new ApproveImpl2());
        responsibilityChains.add(new ApproveImpl3());
        String approveResult=processor(amount,0,responsibilityChains);
        System.out.println("approveResult:"+approveResult);

    }

    public  String processor(Double amount,int index,List<ApproveInter> responsibilityChains) {
       return responsibilityChains.get(index).approveing(amount,index,responsibilityChains,this);
    }


}
