package com.buge.dubboconsumer.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author: yachen.shen
 * @Date 2020/1/28 13:03
 */
@Service
public class B {
    @Autowired
    private A a;

    public B() {
        System.out.println("b init");
    }

    @PostConstruct
    public void post() {
        System.out.println(
                "b====post construct"
        );
    }
}
