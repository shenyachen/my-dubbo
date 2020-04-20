package com.buge.dubboapi.算法;

import java.util.Arrays;

/**
 * @author: yachen.shen
 * @Date 2020/2/27 10:59
 */
public class 插入排序 {

    public static int[] insertSort(int[] a) {
        int length = a.length;

        for (int i = 1; i < a.length; i++) {
            int j = i - 1;
            int v = a[i];
            for (; j >= 0; j--) {
                if (a[j] > v) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[++j] = v;
        }

        return a;
    }

    public static void main(String[] args) {
        int[] a = {2,1,3,5,7};
        System.out.println(Arrays.toString(insertSort(a)));
    }


}
