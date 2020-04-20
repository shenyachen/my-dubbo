package com.buge.dubboconsumer.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author: yachen.shen
 * @Date 2020/1/28 13:03
 */
@Service
public class A implements ApplicationContextAware {

    //TomcatServletWebServerFactory


    static final Object o = null;

    @Autowired
    private B b;

    public A() {
        System.out.println("a init");
    }

    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.o);
        System.gc();
        System.out.println(a.o);
    }

    @PostConstruct
    public void post() {
        System.out.println("a=====post construct");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext.getBean("b"));
    }
}
