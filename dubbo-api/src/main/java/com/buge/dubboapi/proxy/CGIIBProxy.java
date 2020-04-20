package com.buge.dubboapi.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: yachen.shen
 * @Date 2020/1/9 12:15
 */
public class CGIIBProxy<T> implements MethodInterceptor {
    private T target;
    public CGIIBProxy(T target) {
        this.target = target;
    }

    public T getProxy(Class<T> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(this);
        enhancer.setSuperclass(clazz);
        return (T)enhancer.create();
    }



    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("增强");
        Object invoke = methodProxy.invoke(target, objects);
        return invoke;
    }
}
