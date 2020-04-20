package com.buge.dubboapi.lock;

import java.util.Scanner;

/**
 * @author: yachen.shen
 * @Date 2020/1/13 12:19
 */
public class DeadLock implements Runnable{

    private String lockA;
    private String lockB;

    public DeadLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            try {
                System.out.println(Thread.currentThread().getName() + lockA);
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + lockB);

            }
         }
    }

    public static void main(String[] args) throws Throwable{
        //deadTest();
        //Thread.sleep(Integer.MAX_VALUE);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(getCount(scanner.next()));
        }
    }

    public static int getCount(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        int[] ints = new int[128];
        for (char c : chars) {
            if (c >= 0 && c <= 127) {
                if (ints[c] == 0) {//代表着这个索引的值是空的
                    count ++;
                    ints[c] = c;
                }
            }

        }
        return count;
    }

    /**
     * 死锁测试
     */
    private static void deadTest() {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new DeadLock(lockA, lockB)).start();
        new Thread(new DeadLock(lockB, lockA)).start();
    }
}
