package com.buge.dubboapi.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author: yachen.shen
 * @Date 2020/3/1 22:05
 */
public class Client {

    public static void main(String[] args) {
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void start() throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(8080));
        InputStream in = System.in;
        byte[] bytes = new byte[1024];
        byte[] rep = new byte[1024];
        while (true) {
            int read = in.read(bytes);
            socket.getOutputStream().write(bytes);
            int read1 = socket.getInputStream().read(rep);
            System.out.println(new String(rep));
        }
    }
}
