package com.advanced.java_block.blockchain.example3;

import com.advanced.java_block.blockchain.StringUtil;

import java.util.ArrayList;
import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    public String merkleRoot;
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>(); //数据改为交易的列表
    public long timeStamp; //时间戳
    public int nonce;

    //构造器
    public Block(String previousHash) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    //根据区块的内容计算hash值
    public String calculateHash() {
        String plaintext=previousHash +Long.toString(timeStamp) +Integer.toString(nonce) +merkleRoot;
        String calculatedhash = StringUtil.applySha256(plaintext);
        return calculatedhash;
    }

    //增加随机数来完成计算难题
    public void mineBlock(int difficulty) {
        merkleRoot = StringUtil.getMerkleRoot(transactions);
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    //在区块中增加交易
    public boolean addTransaction(Transaction transaction) {
        //处理交易并检查是否有效，创世纪区块将被忽略
        if (transaction == null) return false;
        if ((previousHash != "0")) {
            if ((transaction.processTransaction() != true)) {
                System.out.println("Transaction failed to process. Discarded.");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }
}