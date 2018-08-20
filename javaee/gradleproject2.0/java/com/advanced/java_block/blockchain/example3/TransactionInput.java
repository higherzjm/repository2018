package com.advanced.java_block.blockchain.example3;

/**
 * Created by zjm on 2018/7/31.
 */
public class TransactionInput {
    public String transactionOutputId; //指向交易输出类 -> transactionId
    public TransactionOutput UTXO; //包含所有未使用的交易输出
    public TransactionInput(String transactionOutputId) {
        this.transactionOutputId = transactionOutputId;
    }
}
