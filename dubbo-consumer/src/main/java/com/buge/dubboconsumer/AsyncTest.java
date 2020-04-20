package com.buge.dubboconsumer;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author: yachen.shen
 * @Date 2019/12/22 18:30
 */
@Component
public class AsyncTest {

    @Async
    public void test1() {
        System.out.println(Thread.currentThread().getName());
    }
    @Async
    public Future<Boolean> test2() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult(true);
    }
}
