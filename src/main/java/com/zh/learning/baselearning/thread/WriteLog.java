package com.zh.learning.baselearning.thread;

import java.io.File;
import java.io.IOException;

/**
 * @author zh
 * @version 1.0
 * @date 2020/12/16 10:07
 */
public class WriteLog implements Runnable{


    @Override
    public void run() {

    }

    public void createFile(String filename){
        File file = new File("f://data//"+filename);
        if (!file.getParentFile().exists()){
            file.mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
