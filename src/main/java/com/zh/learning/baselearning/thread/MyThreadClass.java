package com.zh.learning.baselearning.thread;

/**
 * @author zh
 * @version 1.0
 * @date 2020/11/26 15:28
 */
public class MyThreadClass extends Thread{

    private String name;

    public MyThreadClass(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10 ; i++) {
            System.out.println(name + "线程正在运行！第"+ i +"次");
        }
    }
}
