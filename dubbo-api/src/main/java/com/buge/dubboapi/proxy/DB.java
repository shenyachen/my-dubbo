package com.buge.dubboapi.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @author: yachen.shen
 * @Date 2020/1/6 18:00
 */
public class DB implements Mysql{

    public int insert() {
        System.out.println("插入一条记录");
        System.out.println("更新一条记录");
        return 1;
    }

    public int update(int a) {
        System.out.println("更新接口"+a);
        return 0;
    }

    public static int update2(int a) {
        System.out.println("更新接口static"+a);
        return 0;
    }

    public static void main(String[] args) throws IOException {
        DB db = new DB();
        TransactionProxy transactionProxy = new TransactionProxy(db);
        Mysql o = (Mysql)Proxy.newProxyInstance(DB.class.getClassLoader(), new Class[]{Mysql.class}, transactionProxy);
        o.insert();
        o.update(5);

        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                "MysqlProxy2", new Class[]{Mysql.class});
        String className = DB.class.getClassLoader().getResource("").getPath()+"MysqlProxy.class";
        FileOutputStream outputStream = new FileOutputStream(className);
        outputStream.write(proxyClassFile);

        CGIIBProxy<DB> proxy = new CGIIBProxy(new DB());
        DB dbProxy = proxy.getProxy(DB.class);
        dbProxy.insert();

        dbProxy.update2(1);

    }
}
