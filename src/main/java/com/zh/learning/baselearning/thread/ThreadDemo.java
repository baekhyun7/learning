package com.zh.learning.baselearning.thread;

/**
 * @author zh
 * @version 1.0
 * @date 2020/11/26 15:32
 */
public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        //必须在主线程中创建新的线程对象。任何线程一般具有5种状态，创建，就绪，运行，阻塞，终止

        MyThreadClass thread11 = new MyThreadClass("继承thread11类的");
        MyThreadClass thread12 = new MyThreadClass("继承thread22类的");

        Thread thread112 = new Thread(new MyThreadInterface("实现Runnable1接口的"));
        Thread thread113 = new Thread(new MyThreadInterface("实现Runnable2接口的"));
        thread112.start();
        thread113.start();

    }
}
