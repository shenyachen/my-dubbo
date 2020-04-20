package com.buge.dubboapi.算法;

/**
 * @author: yachen.shen
 * @Date 2020/1/9 21:46
 */
public class 归并 {
    public static int[] ints;
    public static int count = 0;

    public static int[] toSort(int[] arrays, int begin, int end) {
        ints = new int[end + 1];
        return sort(arrays, begin, end);
    }

    public static int[] sort(int[] arrays, int begin, int end) {
        if (begin == end) {
            return arrays;
        }
        int mid = (begin + end) / 2;
        sort(arrays, begin, mid);
        sort(arrays, mid + 1, end);
        merge(arrays, begin, mid, arrays, mid + 1, end);
        return arrays;
    }

    private static int[] merge(int[] a1, int begin1, int end1, int[] a2, int begin2, int end2) {
        int up = begin1;
        int down = begin2;
        for (; up < end1 + 1;) {
            if (down == end2 + 1) {
                break;
            }
            for (; down < end2 + 1;) {
                if (a1[up] < a2[down]) {
                    ints[count] = a1[up];
                    count ++;
                    up ++;
                    break;
                } else if (a1[up] > a2[down]){
                    ints[count] = a2[down];
                    count ++;
                    down ++;
                } else {
                    ints[count] = a1[up];
                    count ++;
                    ints[count] = a2[down];
                    count ++;
                    up ++;
                    down ++;
                    break;
                }
            }
        }
        for (; up < end1 + 1 ;up ++) {
            ints[count] = a1[up];
            count ++;
        }
        for (; down < end2 + 1 ;down ++) {
            ints[count] = a2[down];
            count ++;
        }
        for (int i = 0; i < count; i ++) {
            a1[begin1] = ints[i];
            begin1 ++;
        }
        count = 0;
        return ints;
    }

    public static void main(String[] args) {
        int[] ints = {2};
        int[] sort = toSort(ints, 0, ints.length - 1);
        for (int i:sort) {
            System.out.println(i);
        }
    }


}
