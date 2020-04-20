package com.buge.dubboapi.starter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author: yachen.shen
 * @Date 2020/2/19 17:48
 */
@RequestMapping("/buge")
@RestController
public class BugeController {
    public static final String lock =  "lock1";
    public static final String lock2 =  "lock2";

    public BugeController() {
        System.err.println("=============================");
    }

    @RequestMapping("/mvp")
    public void coll() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put(1,1);
        int i = 0;
        while (true) {
            i ++;
        }
    }

    @RequestMapping("/lock")
    public void lock() {
        synchronized (lock) {
            try {
                TimeUnit.DAYS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BugeController controller = new BugeController();
        controller.deadLock();
    }

    @RequestMapping("/deadLock")
    public void deadLock() {
        new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread().getName()+"===========" + lock);
                    TimeUnit.SECONDS.sleep(10);
                    synchronized (lock2) {
                        System.out.println(Thread.currentThread().getName()+"===========" + lock2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                try {
                    System.out.println(Thread.currentThread().getName()+"===========" + lock2);
                    TimeUnit.SECONDS.sleep(10);
                    synchronized (lock) {
                        System.out.println(Thread.currentThread().getName()+"===========" + lock);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }




}
