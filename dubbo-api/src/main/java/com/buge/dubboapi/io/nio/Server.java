package com.buge.dubboapi.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @author: yachen.shen
 * @Date 2020/3/1 22:34
 */
public class Server {

    public static void main(String[] args) throws Exception {
        start();
    }

    private static void start() throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ArrayList<SocketChannel> socketChannels = new ArrayList<>();
        //设置非阻塞
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(6060));
        while (true) {
            Thread.sleep(2000);
            System.out.println("等待连接！");
            SocketChannel accept = ssc.accept();
            if (accept != null) {
                accept.configureBlocking(false);
                socketChannels.add(accept);
            }
            for (SocketChannel socketChannel : socketChannels) {
                int read = socketChannel.read(buffer);
                System.out.println(read);
                if (read != 0) {
                    buffer.flip();
                    System.out.println(socketChannel.getRemoteAddress() + ":" + new String(buffer.array()));
                    buffer.clear();
                }
            }
        }
    }
}
