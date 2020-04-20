package com.buge.dubboapi.io.nio;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author: yachen.shen
 * @Date 2020/3/1 22:50
 */
public class Client {

    public static void main(String[] args) throws Exception {
        start();
    }

    private static void start() throws Exception {
        SocketChannel socket = SocketChannel.open();
        socket.connect(new InetSocketAddress(6060));
        InputStream in = System.in;
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        byte[] rep = new byte[1024];
        byte[] cache = new byte[1024];
        while (true) {
            allocate.clear();
            int read = in.read(allocate.array());
            socket.write((allocate));

        }
    }
}
