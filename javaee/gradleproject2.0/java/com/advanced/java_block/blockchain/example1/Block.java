package com.advanced.java_block.blockchain.example1;

import com.advanced.java_block.blockchain.StringUtil;

import java.util.Date;

public class Block {
        public String hash;
        public String previousHash;
        private String data; //数据
        private long timeStamp; //时间戳
        //区块构造函数
        public Block(String data,String previousHash ) {
                this.data = data;
                this.previousHash = previousHash;
                this.timeStamp = new Date().getTime();
                this.hash = calculateHash(); //确保hash值的来源
        }
        public String calculateHash() {
                String str=previousHash +Long.toString(timeStamp) +data;
                String calculatedhash = StringUtil.applySha256(str);
                return calculatedhash;
        }
}