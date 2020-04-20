package com.buge.dubboprovider;

import com.buge.dubboapi.HelloWorld;

/**
 * @author: yachen.shen
 * @Date 2019/12/14 13:59
 */
public class HelloWordImpl implements HelloWorld {
    @Override
    public String sayHello() {
        System.out.println("===========provider--------1================");
        return "hello dubbo1111111!";
    }
}
