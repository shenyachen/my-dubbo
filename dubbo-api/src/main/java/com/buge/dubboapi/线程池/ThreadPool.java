package com.buge.dubboapi.线程池;

import java.util.TreeSet;

/**
 * @author: yachen.shen
 * @Date 2020/1/8 15:36
 */
public class ThreadPool {

    public static void main(String[] args) {
        TreeSet<Integer> integers = new TreeSet<>();
        integers.add(1);
        integers.add(1);
        System.out.println(integers);
        StringBuffer buffer = new StringBuffer();
        buffer.append("1");

        StringBuilder builder = new StringBuilder();
        builder.append("1");
    }



    public static class MetaSpaceOomMock {


    }
}


