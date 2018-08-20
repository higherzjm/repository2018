package com.advanced.java_block.blockchain.example2;

import com.advanced.java_block.blockchain.StringUtil;

import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private String data; //数据
    private long timeStamp; //时间戳
    private int nonce;//增加一个随机数

    //构造函数
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    //计算hash值（把新增的随机数也计算在内）
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedhash;
    }

    //工作量证明<--->挖矿
    public void mineBlock(int difficulty) {
        //创建一个string值由难度的位数来决定
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("nonce:"+nonce+";Block Mined!!! : " + hash);
    }
}