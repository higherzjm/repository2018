package com.javase.dynamicproxy.example3;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
  
/** 
 * 代理类的生成工具 
 * @author zyb 
 * @since 2012-8-9 
 */  
public class ProxyGeneratorUtils {  
  
    /** 
     * 把代理类的字节码写到硬盘上 
     * @param path 保存路径 
     */  
    public static void writeProxyClassToHardDisk(String path) {  

        // 获取代理类的字节码  
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy11", UserServiceImpl.class.getInterfaces());  
          
        FileOutputStream out = null;  
          
        try {  
            out = new FileOutputStream(path);  
            out.write(classFile);  
            out.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                out.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}  