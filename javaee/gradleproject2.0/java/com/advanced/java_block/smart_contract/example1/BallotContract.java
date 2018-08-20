package com.advanced.java_block.smart_contract.example1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 以太坊智能合约投票示例Java模拟实现
 */

public class BallotContract {
    private static final String TAG = "BallotContract";
    /**身份审核（赋予投票权）截止时间*/
    public static final long VERIFY_DEADLINE = 1539100800000l;//2018/10/10 00:00:00.000
    /**投票（委托）截止时间*/
    public static final long VOTE_DEADLINE = VERIFY_DEADLINE + (1000 * 60 * 2);//身份审核结束后的2分钟内为投票时间
    /**合约单实例*/
    private static BallotContract INSTANCE;
    /**合约创建者（投票主持人）地址*/
    private final String mChairAddress;
    /**投票者状态保存*/
    private final Map<String,Voter> mVoterMap = new HashMap<>();
    /**提案列表*/
    private final Proposal[] mProposals;
    /**投票结果，得票数最多的提案索引*/
    private int[] mVoteResult;

    /**每次调用合约都会包含的上下文*/
    private Ctx ctx;

    /**
     * 创建合约，只会执行一次
     * @param ctx 调用时的上下文
     * @param names 提案名称数组
     */
    public static void create(Ctx ctx,String[] names){
        if(INSTANCE == null){
            INSTANCE = new BallotContract(ctx,names);
        }
    }

    /**
     * 获取合约实例
     * @param ctx 调用时的上下文
     * @return 投票合约实例
     */
    public static BallotContract getDefault(Ctx ctx){
        INSTANCE.ctx = ctx;
        return INSTANCE;
    }

    /**
     * 投票合约构造函数，初始化一些变量
     * @param ctx 上下文
     * @param names 提案名称数组
     */
    private BallotContract(Ctx ctx, String[] names) {
        mChairAddress = ctx.msg.sender;
        Voter chair = new Voter();
        chair.weight = 1;
        mVoterMap.put(mChairAddress,chair);
        mProposals = new Proposal[names.length];
        for(int i = 0 ; i < mProposals.length ; i++){
            mProposals[i] = new Proposal();
            mProposals[i].name = names[i];
        }
    }

    /**
     * 获取投票结果，只有当投票阶段结束且调用了一次统票方法才会有值
     * @return 投票结果
     */
    public int[] getVoteResult(){
        return mVoteResult;
    }

    /**
     * 赋予投票权，只能主持人调用
     * @param address 赋予的目标地址
     * @return 赋予投票权结果
     */
    public boolean giveVoteRight(String address){
        if(System.currentTimeMillis() > VERIFY_DEADLINE){
            log("giveVoteRight time already over");
            return false;
        }
        if(!mChairAddress.equals(ctx.msg.sender)){
            log("giveVoteRight only the chairman can call");
            return false;
        }
        Voter voter = mVoterMap.get(address);
        if(voter == null){
            voter = new Voter();
            voter.weight = 1;
            mVoterMap.put(address,voter);
            log("giveVoteRight success , address = " + address);
            return true;
        }else{
            log("giveVoteRight failed , yet give,address = "+address);
        }
        return false;
    }

    /**
     * 委托操作，将投票权委托给他人
     * @param to 受托人地址
     * @return 委托结果
     */
    public boolean delegate(final String to){
        final long curr = System.currentTimeMillis();
        if(curr <= VERIFY_DEADLINE){
            log("delegate failed,The delegate hasn't begun");
            return false;
        }
        if(curr > VOTE_DEADLINE){
            log("delegate failed,delegate already over ");
            return false;
        }
        final String sender = ctx.msg.sender;
        Voter fromVoter = mVoterMap.get(sender);
        if(fromVoter == null //没有投票权
                || fromVoter.weight <= 0 //没有投票权
                || sender.equals(to) //委托给自己
                ){
            log("delegate failed");
            return false;
        }
        String toTemp = to;
        Voter toVoter = null;
        while (true){
            toVoter = mVoterMap.get(toTemp);
            if(toVoter == null//受托人没有投票权
                    || toVoter.weight <= 0 //受托人没有投票权
                    ){
                return false;
            }
            if(toVoter.delegate == null){
                toVoter.weight += fromVoter.weight;
                fromVoter.weight = 0;
                fromVoter.delegate = toTemp;
                return true;
            }else{
                toTemp = toVoter.delegate;
            }
        }
    }

    /**
     * 执行投票
     * @param index 提案的索引
     * @return 投票结果
     */
    public boolean vote(int index){
        final long curr = System.currentTimeMillis();
        if(curr <= VERIFY_DEADLINE){
            log("vote failed,The delegate hasn't begun");
            return false;
        }
        if(curr > VOTE_DEADLINE){
            log("vote failed,vote already over");
            return false;
        }
        if(index < 0 || index >= mProposals.length){
            log("proposal index error , index = "+index);
            return false;
        }
        final String sender = ctx.msg.sender;
        Voter voter = mVoterMap.get(sender);
        if(voter == null //没有投票权
                || voter.weight <= 0 //没有投票权
                ){
            log("vote failed");
            return false;
        }
        final Proposal proposal = mProposals[index];
        proposal.voteCount += voter.weight;
        voter.weight = 0;
        voter.proposalIndex = index;
        return true;
    }

    /**
     * 统计票数，任何人都可以随时调用，当投票阶段结束后，调用此方法会将会保存最终的投票结果
     * @return 当前统票结果
     */
    public int[] statisticsVote(){
        if(System.currentTimeMillis() <= VERIFY_DEADLINE){
            log("The statistics vote hasn't begun");
            return new int[0];
        }
        final List<Integer> result = new ArrayList<>(1);
        int maxCount = -1;
        for(int i = 0 ; i < mProposals.length ; i++){
            int count = mProposals[i].voteCount;
            if(count > maxCount){
                maxCount = count;
                result.clear();
                result.add(i);
            }else if(count == maxCount){
                result.add(i);
            }
        }
        int[] arr = new int[result.size()];
        for(int i = 0 ; i < arr.length ; i++){
            arr[i] = result.get(i);
        }
        if(System.currentTimeMillis() > VOTE_DEADLINE){
            //投票阶段已结束，保存统票结果
            mVoteResult = arr;
        }
        log("statistics vote result = "+ Arrays.toString(arr));
        return arr;
    }

    private static void log(String msg){
        System.out.println(String.format("[%s]%s",TAG,msg));
    }

    /**
     * 投票者数据结构
     */
    private static class Voter{
        int weight;//票数
        String delegate;//受托者
        int proposalIndex;//提案索引号
    }

    /**
     * 提案数据结构
     */
    private static class Proposal{
        String name;//提案名称
        int voteCount;//提案所得票数
    }
}