package com.buge.dubboconsumer;

import com.buge.dubboapi.HelloWorld;
import com.netflix.ribbon.Ribbon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: yachen.shen
 * @Date 2019/12/14 16:19
 */
//@EnableAsync
@RequestMapping("/say")
@RestController
public class HelloController extends Object{

    //@Autowired
    private HelloWorld helloWorld;
    //@Autowired
    private AsyncTest asyncTest;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HelloInterfaceFeign feign;

    @RequestMapping("a")
    public String a() {
        //restTemplate.getForEntity()
        String object = restTemplate.getForObject("http://SERVER-PRODUCT/hi", String.class);
        //System.out.println(object);
        return object;
    }

    @RequestMapping("b")
    public String b() {
        String hi = feign.hi();
        return hi;
    }


    @RequestMapping("/hello")
    public void hello(HttpServletRequest request,HttpServletResponse response) {
        //return helloWorld.sayHello();
        String ipAddress = getIpAddress(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    response.getWriter().write("hello" + ipAddress);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).run();
    }
    @RequestMapping("gc")
    public void gc() {
        System.out.println("run start");
        int count = 1;
        while (count > 0) {
            new Thread(() -> {
                StringBuffer buffer = new StringBuffer();
                while (true) {
                    buffer.append("123");
                }
            }).start();
            count --;
        }
    }

    public static String getIpAddress(HttpServletRequest request) {
         String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                         ip = request.getHeader("WL-Proxy-Client-IP");
                     }
               if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                     ip = request.getHeader("HTTP_CLIENT_IP");
                     }
               if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                         ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                     }
               if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                         ip = request.getRemoteAddr();
                     }
               return ip;
             }

    @RequestMapping("/hi")
    public void hi(HttpServletResponse response) {
        //return helloWorld.sayHello();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    response.getWriter().write("hi");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    response.getWriter().write("...hi");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
        //return "hello";
    }

    @RequestMapping("async")
    public void test() {
        asyncTest.test1();
        System.out.println(Thread.currentThread().getName());
        Future<Boolean> future = asyncTest.test2();
        while (!future.isDone()){
            //System.out.println("===============");
        }
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {


        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("redis")
    public void redis(String k, String v) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMinIdle(5);
        jedisPoolConfig.setEvictorShutdownTimeoutMillis(6000);
        // 哨兵信息
        Set<String> sentinels = new HashSet<>(Arrays.asList("192.168.1.7:26379",
                "192.168.1.9:26379"));
        // 创建连接池
        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels,jedisPoolConfig,6000,"123456");
        // 获取客户端
        Jedis jedis = pool.getResource();
        // 执行两个命令
        jedis.set(k, v);
        String value = jedis.get(k);
        System.out.println(value);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
