package com.buge.dubboconsumer;

import java.util.Random;

/**
 * @author: yachen.shen
 * @Date 2019/12/23 15:53
 */
public class CP {
    int count = 0;
    Object o = new Object();
    Thread p = new Thread();
    Thread c = new Thread();

    public CP(Thread p, Thread c) {
        this.p = p;
        this.c = c;
    }

    public CP() {
    }

    void P (){
        while (true) {
            synchronized (o) {
                while (count > 0) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("=====生产====="+ ++count + ";  生产者" +Thread.currentThread().getName());
                try {
                    Random random = new Random();
                    Thread.sleep(0);
                    Thread.yield();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                o.notifyAll();
            }
        }
    }

    void C (){
        while (true) {
            synchronized (o) {
                while (count == 0) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("====消费===" + count-- + ";  消费者：" + Thread.currentThread().getName());
                o.notifyAll();
            }
            try {
                Random random = new Random();
                Thread.sleep(0);
                Thread.yield();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CP cp = new CP();
        new Thread(new Runnable() {
            @Override
            public void run() {
                cp.P();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                cp.P();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                cp.P();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                cp.C();
            }
        }).start();
    }
}
