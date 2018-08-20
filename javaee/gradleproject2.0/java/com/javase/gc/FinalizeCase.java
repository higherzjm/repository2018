package com.javase.gc;

/**
 * Created by zjm on 2018/1/10.
 */
public class FinalizeCase {
    private static Block holder = null;

    public static void main(String[] args) throws Exception {
        holder = new Block();
        holder = null;
        System.gc();
        System.in.read();
    }

    static class Block {
        byte[] _200M = new byte[200*1024*1024];
        //垃圾回收重写的方法
        @Override
        protected void finalize() throws Throwable {
            System.out.println("invoke finalize");
        }
    }

}
