package com.buge.dubboprovide2.jvm;

/**
 * @author: yachen.shen
 * @Date 2020/2/19 11:18
 */
public class TestMain {

    public static void main(String[] args) {
        FinalTest finalTest = new FinalTest();
        finalTest = null;
        System.gc();
    }
}
