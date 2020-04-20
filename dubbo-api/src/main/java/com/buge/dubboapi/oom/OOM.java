package com.buge.dubboapi.oom;

/**
 * @author: yachen.shen
 * @Date 2020/1/15 23:05
 */
public class OOM {
    static int test = 0;
    static {
        test = 2;
        test2 = 3;
    }
    static int test2 = 0;


    public static void main(String[] args) {
        System.out.println(test);
        System.out.println(test2);
    }
}
