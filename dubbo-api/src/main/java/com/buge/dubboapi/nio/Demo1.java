package com.buge.dubboapi.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: yachen.shen
 * @Date 2020/2/21 19:30
 */
public class Demo1 {

    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("F:\\dubbo\\mydubbo\\dubbo-api\\target\\in.txt");
        FileOutputStream outputStream = new FileOutputStream("F:\\dubbo\\mydubbo\\dubbo-api\\target\\out.txt");

        FileChannel readChannel = inputStream.getChannel();
        FileChannel writeChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(100);

        while (true) {
            buffer.clear();
            System.out.println("position:" + buffer.position() + ",limit:" +buffer.limit() + ",capacity:" +
                    buffer.capacity());
            int read = readChannel.read(buffer);//从channel中读数据到buffer中
            System.out.println("position:" + buffer.position() + ",limit:" +buffer.limit() + ",capacity:" +
                    buffer.capacity()/* + ",mark:" +buffer.mark()*/);
            System.out.println(read);
            if (read == -1) {
                break;
            }
            buffer.flip();
            System.out.println("position:" + buffer.position() + ",limit:" +buffer.limit() + ",capacity:" +
                    buffer.capacity()/* + ",mark:" +buffer.mark()*/);

            int write = writeChannel.write(buffer);
            System.out.println("position:" + buffer.position() + ",limit:" +buffer.limit() + ",capacity:" +
                    buffer.capacity()/* + ",mark:" +buffer.mark()*/);
        }
    }
}
