package com.buge.dubboapi;

/**
 * @author: yachen.shen
 * @Date 2020/3/2 15:07
 */
public interface Test {
    String a = "123";
    default String get(){
        a();
        b();
        return "";
    }

    public String a();
    public String b();
}
