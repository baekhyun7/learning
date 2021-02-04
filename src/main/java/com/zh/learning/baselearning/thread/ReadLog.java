package com.zh.learning.baselearning.thread;

import java.io.*;
import java.util.Collections;
import java.util.List;

/**
 * @author zh
 * @version 1.0
 * @date 2020/12/16 10:07
 */
public class ReadLog implements Runnable {

    private RandomAccessFile file;
    private int size;

    public List<String> list = Collections.emptyList();

    public ReadLog(RandomAccessFile file, int size) {
        this.file = file;
        this.size = size;
    }

    @Override
    public void run() {

        StringBuilder stringBuilder = new StringBuilder();
        byte[] tempbytes = new byte[1024];
        int readSize = 0;
        //读取文件 小于5M读取到缓存区中 大于得话就让写线程开始工作
        while (readSize < size){
            try {
                int read = file.read(tempbytes);
                String string = tempbytes.toString();
                stringBuilder.append(string);
                readSize = readSize+read;

            } catch (IOException e) {

            }
        }
        list.add(stringBuilder.toString());
    }
}
