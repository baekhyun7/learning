package com.zh.learning.baselearning.thread;

/**
 * @author zh
 * @version 1.0
 * @date 2020/11/26 15:31
 */
public class MyThreadInterface implements Runnable {
    private String name;
    private int ticket = 0;    // 假设一共有5张票

    public MyThreadInterface(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            this.sale();   // 调用同步方法
        }
    }

    public  void sale() {    // 声明同步方法
            System.out.println(name +"卖票：ticket = " + ticket++);

    }
}
