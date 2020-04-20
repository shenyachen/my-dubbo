package com.buge.dubboapi.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.params.SetParams;

import java.net.Socket;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yachen.shen
 * @Date 2020/2/29 12:51
 */
public class RedisTest {

    static Socket socket;
    static String host = "192.168.1.5";
    static int port = 6379;
    static int timeOut = 5000;
    static Jedis jedis = new Jedis(host, port);
    static BlockingQueue<Jedis> jedisPool = new ArrayBlockingQueue(10);
    static transient boolean empty = false;

    static {
        int count = 10;
        while (count > 0) {
            jedisPool.add(new Jedis(host, port));
            count --;
        }
    }


    public static void main(String[] args) {
        /*stringRedis();
        setRedis();*/
        //incrDecr();
        incrDecr2();
    }

    private static void incrDecr2() {
        int count = 0;
        AtomicInteger success = new AtomicInteger(0);
        while (count < 10000) {
            new Thread(new Consumer(success)).start();
            count ++;
        }

    }


    public static class Consumer implements Runnable{
        Jedis jedis = null;
        AtomicInteger success;
        boolean add = false;

        public Consumer(AtomicInteger success) {
            this.success = success;
        }

        @Override
        public void run() {

            try {
                while (!empty && jedis == null) {
                    jedis = jedisPool.poll(2, TimeUnit.SECONDS);
                }

                if (jedis == null) {
                    System.out.println("我甚至连接都没抢到,就没货了！！！");
                    return;
                }
            } catch (InterruptedException e) {
                System.out.println("获取连接失败：" + e.getMessage());
                return;
            }
            try {
                jedis.watch("incr2");
                String incr2 = jedis.get("incr2");
                Transaction tr = jedis.multi();
                if (incr2 != null && (incr2.equals("OK") ||Integer.valueOf(incr2) > 0)) {
                    tr.decr("incr2");
                    List<Object> exec = tr.exec();
                    if (exec == null || exec.isEmpty()) {
                        run();
                    } else {
                        System.out.println(success.addAndGet(1));
                    }
                } else {
                    tr.exec();
                    empty = true;
                    System.out.println("没货了！");
                }

            } finally {
                if(jedis.isConnected() && !add) {
                    jedis.resetState();
                    jedisPool.add(jedis);
                    add = true;
                    System.out.println("放回连接！");
                }
            }
        }
    }

    private static void incrDecr() {
        int count = 100;
        while (count > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = new Jedis(host, port);
                    jedis.incr("incr");
                }
            }).start();
            count --;
        }
        while (count < 100) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = new Jedis(host, port);
                    jedis.decr("incr");
                }
            }).start();
            count ++;
        }
    }

    private static void setRedis() {
        //jedis.zadd
    }

    private static void stringRedis() {
        SetParams params = new SetParams();
        params.ex(20000);
        params.nx();
        jedis.set("lock", "a", params);
        jedis.close();
    }




}
