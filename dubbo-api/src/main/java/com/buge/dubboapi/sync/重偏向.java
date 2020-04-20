package com.buge.dubboapi.sync;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author: yachen.shen
 * @Date 2020/3/10 16:17
 */
public class 重偏向 {
    public static void main(String[] args) throws InterruptedException {
        test();
    }

    private static void test() throws InterruptedException {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Object> list = new ArrayList<>();
        int count = 50;
        while (count > 0) {
            list.add(new Lock());
            count --;
        }
        for (int i = 0; i < list.size(); i++) {
            Object o = list.get(i);
            synchronized (o) {
                if (i == 18 || i == 19 || i == 20)
                System.out.println(i + ": " + ClassLayout.parseInstance(o).toPrintable());
            }
        }
        new Thread(() -> {
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                synchronized (o) {
                    if (i == 18 || i == 19 || i == 20 || i==49)
                        System.out.println(i + ": " + ClassLayout.parseInstance(o).toPrintable());
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                synchronized (o) {
                    if (i == 1 || i == 17 || i == 20 || i==49)
                        System.out.println(i + ": " + ClassLayout.parseInstance(o).toPrintable());
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(2000);
    }
}
