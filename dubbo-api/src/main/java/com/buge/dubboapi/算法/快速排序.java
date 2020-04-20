package com.buge.dubboapi.算法;

import java.util.Arrays;

/**
 * @author: yachen.shen
 * @Date 2020/2/27 12:36
 */
public class 快速排序 {

    public static int[] sort(int[] a, int low, int high) {
        if (low >= high)  {
            return a;
        }
        int point = partition(a, low, high);
        sort(a, low, point - 1);
        sort(a,  point + 1, high);

        return a;
    }

    private static int partition(int[] a, int low, int high) {
        int i = a[high];
        int s = low;
        for (int q = low ;q < high; q ++) {
            if (a[q] < i) {
                if (s != q) {
                    int i1 = a[s];
                    a[s] = a[q];
                    a[q] = i1;
                }
                s ++;
            }
        }
        if (s < high) {
            a[high] = a[s];
            a[s] = i;
        }
        return s;
    }

    public static void main(String[] args) {
        int[] a = {2,5,1,3,8,8,9,2};
        /*int partition = partition(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
        System.out.println(partition);*/

        //System.out.println(partition(a, 0, a.length - 1));
        //System.out.println(Arrays.toString(sort(a, 0, a.length - 1)));

        ClassLoader classLoader = String.class.getClassLoader();
        //System.out.println(快速排序.class.getClassLoader());
        //new MyClassLoad()

    }

    public static class MyClassLoad extends ClassLoader {
        public MyClassLoad(ClassLoader parent) {
            super(parent);
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            return super.loadClass(name);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            return super.findClass(name);
        }
    }
}
