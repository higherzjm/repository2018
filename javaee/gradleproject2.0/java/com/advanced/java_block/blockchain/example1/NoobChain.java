package com.advanced.java_block.blockchain.example1;

import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.ArrayList;

public class NoobChain {

    public static void main(String[] args) {
        //创世纪区块
        Block genesisBlock = new Block("Hi im the first block", "0");
        System.out.println("Hash for block 1 : " + genesisBlock.hash);
        //第二个区块，链接在创世纪区块之后
        Block secondBlock = new Block("Yo im the second block",genesisBlock.hash);
        System.out.println("Hash for block 2 : " + secondBlock.hash);
         //第三个区块，链接在第二个区块之后
        Block thirdBlock = new Block("Hey im the third block",secondBlock.hash);
        System.out.println("Hash for block 3 : " + thirdBlock.hash);
    }

    public static ArrayList<Block> blockchain = new ArrayList<Block>();

    @Test
    public  void test2() {
        //增加区块到数组中去
        blockchain.add(new Block("Hi im the first block", "0"));
        blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
        blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
        System.out.println("isChainValid:"+this.isChainValid());
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
       //循环遍历区块链来检查hash值
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
          //比对hash值和计算的hash值
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
          //比对前一个区块的hash值和previousHash值
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}