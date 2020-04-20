package com.buge.dubboapi.算法;

import java.util.Arrays;

/**
 * @author: yachen.shen
 * @Date 2020/1/9 20:47
 */
public class 冒泡 {


    public static int[] sort(int[] arrays) {
        for (int i = 1; i < arrays.length; i++) {
            for (int j = 1; j < arrays.length; j++) {
                int a,b;
                if ((a = arrays[j]) < (b =arrays[j-1])) {
                    arrays[j] = b;
                    arrays[j-1] = a;
                }
            }
        }
        return arrays;
    }

    public static int[] insertSort(int[] arrays) {
        for (int i = 1; i < arrays.length; i++) {
            int a = arrays[i];
            for (int j = i - 1; j >= 0; j--) {
                if (a < arrays[j]) {
                    arrays[j + 1] = arrays[j];
                    arrays[j] = a;
                } else {
                    break;
                }
            }
        }
        return arrays;
    }



    public static void main(String[] args) {
        int[] sort = insertSort(new int[]{3,1,1,5,6,2,90,0,66});
        for (int i : sort) {
            System.out.println(i);
        }
    }
}
