package com.advanced.java_block.smart_contract.example1;

public class Main {

    public static void main(String[] args){
        BallotContract.create(getCtx("voter_0x001"),new String[]{"提案1","提案2","提案3","提案4"});
        String address001 = "voter_0x001";
        boolean result = BallotContract.getDefault(getCtx(address001)).giveVoteRight(address001);
        log("giveVoteRight result = "+result);
    }

    private static Ctx getCtx(String sender){
        return new Ctx(new Ctx.Msg(sender));
    }

    private static void log(String msg){
        System.out.println(msg);
    }
}