package com.advanced.java_block.smart_contract.example1;

/**
 * Created by zjm on 2018/8/13.
 */
public class Ctx {

    Msg msg;

    public Ctx(Msg msg){
        this.msg=msg;
    }

    static class Msg{
        String sender;
        public Msg(String sender){
            this.sender=sender;
        }
    }
}
