package com.javase.ryptography.RSA;

import org.junit.Test;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试公钥加密，私钥解密
 */
public class Mainclass_TestEncryptAndDecrypt {
    public static void main(String[] args) throws Exception {  
        String input = "我是中国abcdefg123456";
        Cipher cipher = Cipher.getInstance("RSA");
        RSAPublicKey pubKey = (RSAPublicKey) PublicKeyReader.get("F:/publicKeyFile");
        RSAPrivateKey privKey = (RSAPrivateKey) PrivateKeyReader.get("F:/privateKeyFile");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        //公钥加密后的东西
        System.out.println("cipher: " + new String(cipherText));
        //私钥开始解密
        cipher.init(Cipher.DECRYPT_MODE, privKey);
        byte[] plainText = cipher.doFinal(cipherText);
        System.out.println("plain : " + new String(plainText));
    }

    @Test
    public void encode() throws Exception{
        String input = "我是中国abcdefg123456";
        Cipher cipher = Cipher.getInstance("RSA");
        RSAPublicKey pubKey = (RSAPublicKey) PublicKeyReader.get("F:/publicKeyFile");
        //开始加密
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        System.out.println("cipher: " + new String(cipherText));
        Map<String,Object>  map=new HashMap<>();
        map.put("endcode",cipherText);
        decode(map);

    }

    public void decode(Map<String,Object>  map) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        RSAPrivateKey privKey = (RSAPrivateKey) PrivateKeyReader.get("F:/privateKeyFile");
        //开始解密
        cipher.init(Cipher.DECRYPT_MODE, privKey);
        byte[]  endcodeStr= (byte[]) map.get("endcode");
        byte[] plainText = cipher.doFinal(endcodeStr);
        System.out.println("解密后的东西: " + new String(plainText));
    }

    @Test
    public void test11(){
        String a="我是中国人abcdefg123456";
        for (int i=0;i<20;i++) {
            byte[] bytes= new byte[0];
            try {
                bytes = a.getBytes("UTF-8");
                System.out.println(bytes+":"+new String(bytes)+":"+new String(bytes).getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
    }
  
}  