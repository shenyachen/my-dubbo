package com.buge.dubboapi;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;

/**
 * @author: yachen.shen
 * @Date 2020/1/15 12:36
 */
public class Refer {

    public static void weak() {
        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o);
        o = null;//去除强引用
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }

    public static void weakHashMap() {
        WeakHashMap<Object, Object> map = new WeakHashMap<>();
        map.put(1,1);
    }

    public static void main(String[] args) {
        //weak();
        //referQueue();
        //phantomReference();
        /*String a = "申雅晨";
        char c = a.charAt(0);
        System.out.println(c);*/
        System.out.println(Refer.class.getClassLoader().getParent());
        System.out.println(Refer.class.getClassLoader().getResource(""));
        Properties p = System.getProperties();
        Iterator it = p.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

    private static void phantomReference() {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<Object>(o, referenceQueue);

        System.out.println(o);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("====================");
        o = null;
        System.gc();
        System.out.println(o);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

    }

    private static void referQueue() {
        Object o = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<Object>(o, queue);

        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(queue.poll());
        o = null;
        System.gc();
        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(queue.poll());
    }
}
