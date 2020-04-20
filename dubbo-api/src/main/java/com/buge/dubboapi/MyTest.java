package com.buge.dubboapi;

/**
 * @author: yachen.shen
 * @Date 2020/4/2 21:20
 */
public class MyTest {

    public static void main(String[] args) {
        int [] a = {5,6,7,8,0,1,2,3,4};
        System.out.println(findMin(a));
    }

    private static int findMin(int[] a) {
        int low = 0;
        int high = a.length - 1;
        int min = 0;
        while (low <= high) {
            int mindle = (low + high) / 2;
            int v = a[mindle];
            if (v > a[mindle - 1] && v > a[mindle + 1]) {
                min = mindle + 1;
                break;
            }
            if (v > a[mindle - 1]) {
                low = mindle;
            }
            if (v < a[mindle + 1]) {
                high = mindle;
            }
        }
        return a[min] < a[0] ? a[min] : a[0];
    }
}
