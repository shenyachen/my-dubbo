package com.buge.dubboprovide2;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;

//@ImportResource(value = "classpath:dubbo-provider.xml")
@SpringBootApplication
@EnableEurekaClient
public class DubboProvide2Application {

    public static void main(String[] args) {
        SpringApplication.run(DubboProvide2Application.class, args);
    }

    public static void merge() {
        int[] a = {1,3,5,5,7,8,10};
        int[] b = {2,4,6};
        //merge(a,b);
        System.out.println(Arrays.toString(function(a,b)));
    }
    public static void merge(int[] m,int[] n) {
        int i = 0, j = 0, count = 0;
        int msize = m.length;
        int nsize = n.length;
        int[] k = new int[m.length + n.length];
        while (i < msize && j < nsize) {
            if (m[i] < n[j]) {
                k[count++] = m[i++];
            } else {
                k[count++] = n[j++];
            }
        }
        if (i >= msize) {
            while (j < n.length) {
                k[count++] = n[j++];
            }
        }
        if (j >= nsize) {
            while (i < m.length) {
                k[count++] = m[i++];
            }
        }
        for(int w=0 ; w<count; w++){
            System.out.println( k[w] );
        }
    }
    public static int[] function(int[] a, int[] b ) {
        int[] c = new int[a.length + b.length];
        int count = 0;
        int low = 0;
        int high = 0;
        for (; low < a.length; low ++) {
            int va = a[low];
            for(;high < b.length; high ++) {
                int vb = b[high];
                if (va > vb) {
                    c[count ++] = vb;
                } else {
                    c[count ++] = va;
                    break;
                }
            }
            if (high == b.length)
            c[count ++] = va;
        }

        /*for (;low < a.length; low ++) {
            c[count ++] = a[low];
        }*/
        for (;high < b.length; high ++) {
            c[count ++] = b[high];
        }
        return c;
    }

}
