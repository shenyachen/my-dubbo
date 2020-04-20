package com.buge.dubboapi.算法;

import java.util.Map;

/**
 * @author: yachen.shen
 * @Date 2020/1/4 21:46
 */
public class 二分法 {

    public static void main(String[] args) {
        int[] a = {1,2,3,4};
        System.out.println(find(a, 2));
    }


    public static int find(int[] arrays, int a) {
        int max = max(arrays);
        int low = 0;
        int high = max;
        while (low <= high) {
            int midle = (low + high) / 2;
            if (arrays[midle] == a) {
                return midle;
            }
            if (arrays[midle] > a) {
                high = midle - 1;
            } else {
                low = midle + 1;
            }
        }
        low = max + 1;
        high = arrays.length - 1;
        while (low <= high) {
            int midle = (low + high) / 2;
            if (arrays[midle] == a) {
                return midle;
            }
            if (arrays[midle] > a) {
                low = midle + 1;
            } else {
                high = midle - 1;
            }
        }
        return -1;
    }

    public static int max(int[] arrays) {
        if (arrays.length == 1) {
            return 0;
        }
        if (arrays.length == 2) {
            return arrays[0] > arrays[1] ? 0 : 1;
        }
        int low = 0;
        int high = arrays.length - 1;
        while (low <= high) {
            int midle = (low + high) / 2;
            if (low == high) {
                return low;
            }
            if (arrays[midle] > arrays[midle - 1] && arrays[midle] > arrays[midle + 1]) {
                return midle;
            }
            if (arrays[midle] < arrays[midle - 1]) {
                high = midle - 1;
            } else {
                low = midle + 1;
            }
        }
        throw new RuntimeException("数据异常");
    }

    public static int find2(int[] arrays, int a) {
        int low = 0;
        int high = arrays.length - 1;
        while (low <= high) {
            int midle = (low + high) / 2;
            if (arrays[midle] == a) {
                if (midle == 0)
                    return midle;
                if (arrays[midle -1] == a) {
                    high = midle - 1;
                    continue;
                } else {
                    return midle;
                }
            }
            if (arrays[midle] > a) {
                high = midle - 1;
            } else {
                low = midle + 1;
            }
        }
        return -1;
    }

    /**
     * 求一个数的根号，保留n个小数
     */
    public static double gen(int a, int b) {
        int low = 0;
        int high = a;
        double d = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (mid * mid == a) {
                d = mid;
                break;
            }
            if (mid * mid > a) {
                if ((mid-1) * (mid-1) > a) {
                    high = mid - 1;
                } else {
                    d = mid - 1;
                    break;
                }
            } else {
                if ((mid + 1) * (mid + 1) < a) {
                    low = mid + 1;
                } else {
                    d = mid;
                    break;
                }
            }
        }
        /*if (d * d == a) {
            return d ;
        }*/
        StringBuffer sb = new StringBuffer(d+"");
        int e = 10;
        int cyc=1;
        while (b-- > 0) {
            int low2 = 1;
            int high2 = 9;
            while (low2 <= high2) {
                int mid2 = (low2 + high2) / 2;
                double v = d + Math.pow(10, -cyc) * mid2;
                double v2 = d + Math.pow(10, -cyc) * (mid2+1);
                double v3 = d + Math.pow(10, -cyc) * (mid2-1);
                if (v * v == a) {
                    d = d + Math.pow(10, -cyc) * mid2;
                    break;
                }
                if (v * v> a) {
                    if (v3 * v3> a) {
                        high2 = mid2 - 1;
                    } else {
                        d +=  Math.pow(10, -cyc) * (mid2-1);
                        break;
                    }
                } else {
                    if (v2 * v2< a) {
                        low2 = mid2 + 1;
                    } else {
                        d +=  Math.pow(10, -cyc) * mid2;
                        break;
                    }
                }
            }
            cyc ++;
        }
        return d;

    }

    public static double sqrt(double x, double precision) {
        if (x < 0) {
            return Double.NaN;
        }
        double low = 0;
        double up = x;
        if (x < 1 && x > 0) {
        /** 小于1的时候*/
            low = x;
            up = 1;
        }
        double mid = low + (up - low)/2;
        while(up - low > precision) {
            if (mid * mid > x ) {//TODO mid可能会溢出
                up = mid;
            } else if (mid * mid < x) {
                low = mid;
            } else {
                return mid;
            }
            mid = low + (up - low)/2;
        }
        return mid;
    }

    /*public static void main(String[] args) {
        System.out.println(gen(5,10));
        //System.out.println(1 + 0.1);
        int[] a= {1,1,1,1,1,1,2,2,2,3,3,4,5};
        System.out.println(find(a,3));
    }*/
}
