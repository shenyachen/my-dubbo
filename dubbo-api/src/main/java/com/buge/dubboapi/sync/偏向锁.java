package com.buge.dubboapi.sync;

import org.apache.tomcat.util.buf.HexUtils;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.layouters.HotSpotLayouter;

import java.util.concurrent.TimeUnit;

/**
 * @author: yachen.shen
 * @Date 2020/3/10 9:34
 */
public class 偏向锁 {

    public static void main(String[] args) throws InterruptedException {
        test();
    }

    private static void test() throws InterruptedException {

        /*try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Lock lock = new Lock();
        System.out.println(lock.hashCode());
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());//无锁
        //System.out.println("hash code :");
        //System.out.println(Integer.toHexString(lock.hashCode()));
        /*synchronized (lock) {
            *//**
             * 轻量锁
             *//*
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            Thread.sleep(2000);
        }*/
        new Thread(() -> {
            synchronized (lock) {

                System.out.println("t1 : " +ClassLayout.parseInstance(lock).toPrintable());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(1000);
        System.out.println("t2前 : " + ClassLayout.parseInstance(lock).toPrintable());
        new Thread(() -> {
            synchronized (lock) {

                System.out.println("t2 : " +ClassLayout.parseInstance(lock).toPrintable());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        /**
         * 无锁
         */
        System.out.println("t2后 : " + ClassLayout.parseInstance(lock).toPrintable());
        Thread.sleep(8000);
        System.out.println("t2执行后 : " + ClassLayout.parseInstance(lock).toPrintable());
    }
}
