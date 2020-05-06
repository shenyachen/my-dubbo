package com.buge.dubboapi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yachen.shen
 * @Date 2020/4/3 13:50
 */
public class Test2 {

    public static void main(String[] args) {
        int[] a = {};
        int n = 0;
        sortN(a,n);
    }

    private static void sortN(int[] a, int n) {
        int length = a.length;
        int b = length / n;
        List<int[]> split = split(b, a);
        toSort(split);


    }

    private static void toSort(List<int[]> split) {
        for (int [] a : split) {
            sort(a, 0, a.length);
        }
    }

    private static void sort(int[] a, int low, int high) {
        int m = s(a,low,high);
        h(a, low, high, m);
    }

    private static void h(int[] a, int low, int high, int m) {
        int[] ints = new int[high - low];
        int end = m;
        for (int i = low; low < end; low ++) {
            if (end < high) {
                //if (a[i] > a[])
            }
        }
    }

    private static int s(int[] a, int low, int high) {
        if (low < high && low == high - 1) {
            int c = a[low];
            int d = a[high];
            if (c > d) {
                a[low] = d;
                a[high] = c;
            }
            return 0;
        }
        int mindle = (low + high) / 2;
        sort(a, low, mindle);
        sort(a, mindle + 1, high);
        return 0;
    }

    private static List<int[]> split(int b, int[] a) {
        ArrayList<int[]> list = new ArrayList<>();
        int count = 0;
        int[] ints = new int[b];
        list.add(ints);
        for (int i = 0; i < a.length; i ++) {
            if (count == b) {
                count = 0;
                ints = new int[b];
                list.add(ints);
                continue;
            }
            ints[count] = a[i];
            count ++;
        }
        return list;
    }
}
