package com.buge.dubboapi.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: yachen.shen
 * @Date 2020/1/6 18:01
 */
public class TransactionProxy implements InvocationHandler {

    private Object target;
    public TransactionProxy(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start transaction ！！！");
        System.out.println("set commit = 0");
        Object invoke = method.invoke(target, args);
        System.out.println("commit !!!");
        return invoke;
    }
}
