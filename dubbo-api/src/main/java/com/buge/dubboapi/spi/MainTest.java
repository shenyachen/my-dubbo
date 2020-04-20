package com.buge.dubboapi.spi;

import com.alibaba.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ServiceLoader;

/**
 * @author: yachen.shen
 * @Date 2020/3/14 11:49
 */
public class MainTest {

    public static void main(String[] args) {
        /*ServiceLoader<Car> load = ServiceLoader.load(Car.class);
        Iterator<Car> iterator = load.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getColor());
        }*/
        HashMap<String, String> map = new HashMap<>();
        map.put("XXX", "配置文件中name");
        URL url = new URL("", "", 8080, map);
        ExtensionLoader<Car> extensionLoader = ExtensionLoader.getExtensionLoader(Car.class);
        Car red = extensionLoader.getExtension("nb");
        System.out.println(red.getColor());

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
    }
}
