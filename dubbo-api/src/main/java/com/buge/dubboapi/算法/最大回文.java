package com.buge.dubboapi.算法;

/**
 * @author: yachen.shen
 * @Date 2020/3/5 12:19
 */
public class 最大回文 {

    public static void main(String[] args) {
        int count = 10000;
        while (count > 0) {
            new Thread(() -> {
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    stringBuffer.append("123");
                    System.out.println(stringBuffer.toString());
                }
            }).start();
            count --;
        }


    }


    public static int find(String s) {
        char[] chars = s.toCharArray();
        int max = 1;
        for (int i = 1; i < chars.length; i++) {
            char v = chars[i];
            int l = i - 1;
            int r = i + 1;
            char lc = chars[l];
            char rc = chars[r];
            if (lc == v)
            while (i >= 0 && r < chars.length) {
                if (chars[l] == chars[r]) {
                    max += 2;
                    continue;
                }
                if (chars[l] != v && chars[r] != v) {

                }
            }

        }
        return 1;

    }
}
