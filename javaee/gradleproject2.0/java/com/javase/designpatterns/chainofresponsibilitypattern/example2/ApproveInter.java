package com.javase.designpatterns.chainofresponsibilitypattern.example2;

import java.util.List;

/**
 * Created by zjm on 2018/2/9.
 */
public interface ApproveInter {
    public String approveing(Double amount,int index,List<ApproveInter> responsibilityChains,ApproveChain approveChain);
}
