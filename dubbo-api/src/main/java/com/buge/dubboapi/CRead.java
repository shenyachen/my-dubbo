package com.buge.dubboapi;

import java.io.*;

/**
 * @author: yachen.shen
 * @Date 2020/4/3 13:35
 */
public class CRead {

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("d:/test.txt"))));
        ReadThread readThread = new ReadThread(reader);
    }

    public static class ReadThread implements Runnable{
        BufferedReader reader;
        public ReadThread(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            synchronized (reader) {
                String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
