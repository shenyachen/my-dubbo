package com.buge.dubboapi.classLoader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedHashMap;

/**
 * @author: yachen.shen
 * @Date 2020/4/1 12:23
 */
public class MyClassLoader2 {

    public static void main(String[] args) throws Exception {
        URL url = new URL("file:///d:/com/buge/dubboapi/classLoader");
        //URL url = new File("d:/com/buge/dubboapi/classLoader/MyClass2.class").toURL();
        URL[] urls = {url};
        URLClassLoader loader = new URLClassLoader(urls);
        ClassLoader parent = loader.getParent();
        System.out.println(parent);
        Class<?> aClass = loader.loadClass("com.buge.dubboapi.classLoader.MyClass2");

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //map.put()
    }
}
