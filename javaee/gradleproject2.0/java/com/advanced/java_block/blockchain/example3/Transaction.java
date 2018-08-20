package com.advanced.java_block.blockchain.example3;

import com.advanced.java_block.blockchain.StringUtil;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

public class Transaction {
    public String transactionId; // 交易的hash编号
    public PublicKey sender; // 付款人地址 公钥
    public PublicKey reciepient; // 接受人地址 公钥
    public float value;//转移金额
    public byte[] signature; // 数字签名，防止其他人从我们的钱包中发送资金、
    //输入列表
    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    //输出列表
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    //多少个交易已经被创建
    private static int sequence = 0;

    //构造器
    public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.reciepient = to;
        this.value = value;
        this.inputs = inputs;
    }

    // 计算交易的hash值（用于交易编号）
    private String calulateHash() {
        sequence++; //增加sequence，用来防治两个不同的交易有相同的hash值
        return StringUtil.applySha256(
                StringUtil.getStringFromKey(sender) +
                        StringUtil.getStringFromKey(reciepient) +
                        Float.toString(value) + sequence
        );
    }

    //签名所有我们不想被篡改的数据
    public void generateSignature(PrivateKey privateKey) {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value)	;
        signature = StringUtil.applyECDSASig(privateKey,data);//别人的私钥加密
    }
    //验证我们已签名的数据没有被窜给过
    public boolean verifiySignature() {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value)	;
        return StringUtil.verifyECDSASig(sender, data, signature);//自己的公钥验证
    }

    public boolean processTransaction() {
        //验证签名
        if(verifiySignature() == false) {
            System.out.println("#Transaction Signature failed to verify");
            return false;
        }
       //收集交易的输入（必须注意的是输入是未被使用的）
        for(TransactionInput i : inputs) {
            i.UTXO = NoobChain.UTXOs.get(i.transactionOutputId);
        }

      //检查交易是否是有效的
        if(getInputsValue() < NoobChain.minimumTransaction) {
            System.out.println("#Transaction Inputs to small: " + getInputsValue());
            return false;
        }
       //创建交易输出
        float leftOver = getInputsValue() - value; //获得输入的剩余金额
        transactionId = calulateHash();
        outputs.add(new TransactionOutput( this.reciepient, value,transactionId)); //发送金额给收款人
        outputs.add(new TransactionOutput( this.sender, leftOver,transactionId)); //把剩余金额返回给付款人
       //把输出增加到未使用的列表中
        for(TransactionOutput o : outputs) {
            NoobChain.UTXOs.put(o.id , o);
        }
      //把已经使用的交易的输入从UTXO中移除
        for(TransactionInput i : inputs) {
            if(i.UTXO == null) continue; //if Transaction can't be found skip it
            NoobChain.UTXOs.remove(i.UTXO.id);
        }
        return true;
    }

    public float getInputsValue() {
        float total = 0;
        for(TransactionInput i : inputs) {
            if(i.UTXO == null) continue; //如果交易不存在忽略它
            total += i.UTXO.value;
        }
        return total;
    }

    //返回输出的总和
    public float getOutputsValue() {
        float total = 0;
        for(TransactionOutput o : outputs) {
            total += o.value;
        }
        return total;
    }

}