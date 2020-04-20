package com.buge.dubboapi.redis;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author: yachen.shen
 * @Date 2020/2/29 12:50
 */
public class RedisSocket {

    static Socket socket;
    static String host = "192.168.1.6";
    static int port = 6379;
    static int timeOut = 5000;

    public static void main(String[] args) {
        //setRedis();
        socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port), timeOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
