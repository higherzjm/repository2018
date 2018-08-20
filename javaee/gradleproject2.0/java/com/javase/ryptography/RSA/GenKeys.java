package com.javase.ryptography.RSA;

import java.io.FileOutputStream;
import java.security.KeyPair;   
import java.security.KeyPairGenerator;   
import java.security.SecureRandom;   
import java.util.Date;

/**
 * 生成公钥与私钥
 */
public class GenKeys {  
    public static void main(String[] args) throws Exception {  
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");   
        SecureRandom secureRandom = new SecureRandom(new Date().toString().getBytes());  
        keyPairGenerator.initialize(1024, secureRandom);  
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        String publicKeyFilename = "F:/publicKeyFile";
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();  
        FileOutputStream fos = new FileOutputStream(publicKeyFilename);   
        fos.write(publicKeyBytes);   
        fos.close();

        String privateKeyFilename = "F:/privateKeyFile";
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();  
        fos = new FileOutputStream(privateKeyFilename);   
        fos.write(privateKeyBytes);   
        fos.close();  
    }  
}  