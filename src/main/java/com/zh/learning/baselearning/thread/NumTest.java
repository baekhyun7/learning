package com.zh.learning.baselearning.thread;

/**
 * @author zh
 * @version 1.0
 * @date 2020/12/15 11:28
 */
public class NumTest {
    private int x = 100;

    public  int getX() {
        return x;
    }

    public  int fix(int y) {
        x = x - y;
        return x;
    }
}
