package com.buge.dubboconsumer;

import com.buge.dubboapi.HelloWorld;

/**
 * @author: yachen.shen
 * @Date 2019/12/14 14:00
 */
public class Say {
    HelloWorld helloWorld;

    public String say() {
        return helloWorld.sayHello();
    }
}
