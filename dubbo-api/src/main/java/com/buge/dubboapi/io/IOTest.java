package com.buge.dubboapi.io;

import java.io.*;

/**
 * @author: yachen.shen
 * @Date 2020/2/28 11:26
 */
public class IOTest {

    public static void main(String[] args) throws IOException {

        File file = new File("e://125.txt");
        if(!file.exists()){
            file.createNewFile();
        }

        //file.mkdir();
        //file.delete();
        //FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileWriter fileWriter = new FileWriter(file,false);
        fileWriter.write("你好啊！！");
        fileWriter.flush();
        fileWriter.close();

        FileInputStream fileInputStream = new FileInputStream(new File("e://125.txt"));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        byte[] bytes = new byte[20];
        while (bufferedInputStream.read(bytes, 0, bytes.length) != -1){
            String s = new String(bytes);
            System.out.println(s);
        }
    }
}
