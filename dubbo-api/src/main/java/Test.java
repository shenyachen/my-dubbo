
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yachen.shen
 * @Date 2020/4/2 11:09
 */
public class Test implements InvocationHandler {
    public static List list = new ArrayList();
    public static int full = 10;
    public static Object lock = new Object();
    public Object target;

    public Test(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("============");
        Object invoke = method.invoke(target);
        return invoke;
    }

    /**
     * 消费者
     */
    public static class Consumer implements Runnable{
        @Override
        public void run() {
            for (;;) {
                synchronized (lock) {
                    if (Test.list.size() > 0) {
                        Object remove = list.remove(list.size() - 1);
                        System.out.println("消费====" + list.size());
                        lock.notifyAll();
                    } else {
                        try {
                            lock.notifyAll();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 生产者
     */
    public static class Product implements Runnable{
        @Override
        public void run() {
            for (int i = 20; i > 0; i--) {
                synchronized (lock) {
                    if (Test.list.size() < Test.full) {
                        list.add(1);
                        System.out.println("生产======" + list.size());
                        if (Test.list.size() == Test.full) {
                            lock.notifyAll();
                        }
                    } else {
                        try {
                            lock.notifyAll();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Product()).start();
        new Thread(new Consumer()).start();
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.tryLock();
    }

}
