package com.zh.learning.baselearning.thread;

/**
 * @author zh
 * @version 1.0
 * @date 2020/12/15 11:29
 */
public class NumRunnable implements Runnable {
    public NumTest numTest = new NumTest();

    @Override
    public void run() {
        synchronized (numTest) {
            this.fix(30);
            try {
                //这里为了巩固记忆
                //sleep方法不会释放锁  wait会释放锁
                //可以从运行结果看出 sleep 还是按照70 40 10 -20 -50打印
                //wait 就是杂乱得
                Thread.sleep(1);
//                numTest.wait(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " :当前foo对象的x值= " + numTest.getX());
        }
    }

    public int fix(int y) {
        return numTest.fix(y);
    }

    public static void main(String[] args) {
        NumRunnable r = new NumRunnable();
        Thread ta = new Thread(r, "Thread-A");
        Thread tb = new Thread(r, "Thread-B");
        Thread tc = new Thread(r, "Thread-C");
        Thread td = new Thread(r, "Thread-D");
        Thread te = new Thread(r, "Thread-E");
        ta.start();
        tb.start();
        tc.start();
        td.start();
        te.start();
    }
}
