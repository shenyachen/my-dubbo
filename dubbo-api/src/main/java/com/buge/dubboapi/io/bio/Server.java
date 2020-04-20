package com.buge.dubboapi.io.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: yachen.shen
 * @Date 2020/3/1 22:04
 */
public class Server {

    public static void main(String[] args) {
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void start() throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8080));

        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                byte[] cache = new byte[1024];
                int read = 0;
                while (true) {
                    try {
                        read = socket.getInputStream().read(cache);
                        if (read > 0) {
                            System.out.println(new String(cache));
                            socket.getOutputStream().write("收到".getBytes());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
