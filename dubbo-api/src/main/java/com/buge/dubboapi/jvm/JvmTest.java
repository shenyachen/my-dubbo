package com.buge.dubboapi.jvm;

import sun.misc.VM;

import java.nio.ByteBuffer;
import java.util.Random;

/**
 * @author: yachen.shen
 * @Date 2020/1/12 10:17
 */
public class JvmTest {


    public static void OOM() throws Exception {
        try {
            while (true) {
                StringBuilder sb = new StringBuilder();
                int count = 0;
                while (count < 100) {
                    sb.append(count);
                    count ++;
                }
                System.out.println(sb.toString());
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Error error) {
            System.out.println(error.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        //OOM();
        String s = "a";
        String b = "b";
        while (true) {
            s = s + s + new Random().nextInt(66666666) + new Random().nextInt(888888888) + new Random().nextInt(66666666) + new Random().nextInt(888888888);
            s.intern();
        }
        /**
         * 串行收集器UseSerialGC（新生代DefNew、老年代Tenured皆为单线程GC）
         * -Xmx10m -Xms10m -XX:+PrintCommandLineFlags -XX:+UseSerialGC -XX:+PrintGCDetails
         *
         * 并行收集器UseParNewGC（新生代：ParNew多线程GC; 老年代：Tenured单线程GC）
         * -Xmx10m -Xms10m -XX:+PrintCommandLineFlags -XX:+UseParNewGC -XX:+PrintGCDetails
         *
         * 并行收集器UseParallelGC（新生代：PSYoungGen多线程GC; 老年代：ParOldGen多线程GC）
         * -Xmx10m -Xms10m -XX:+PrintCommandLineFlags -XX:+UseParallelGC -XX:+PrintGCDetails
         * 吞吐量优先的收集器，？？？？
         *
         *
         * CMS收集（优点：并发收集，停顿低。缺点：并发执行，对CPU的压力大。采用标记-清除算法，会导致大量的内存碎片；CMS必须在老年代内存用尽之前，
         * 回收完毕。否则，CMS回收失败。将触发担保机制，串行老年代收集器将会以STW的方式进行一次GC,从而造成大面积停顿）
         * -Xmx10m -Xms10m -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails
         */

    }
}
