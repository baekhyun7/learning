package com.zh.learning.baselearning.thread;

import java.time.LocalDateTime;
import java.util.Date;

public class TestRunable implements Runnable {

    int i;
    public TestRunable(int i) {
    this.i = i;
    }

    @Override
    public void run() {
        System.out.println(i + "线程Start. Time = " + LocalDateTime.now().getSecond());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i + "线程end. Time = " + LocalDateTime.now().getSecond());
    }
}
