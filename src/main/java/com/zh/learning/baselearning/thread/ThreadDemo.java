package com.zh.learning.baselearning.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zh
 * @version 1.0
 * @date 2020/11/26 15:32
 */
public class ThreadDemo {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 5;
    private static final int QUEUE_CAPACITY = 6;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) throws InterruptedException {
        //必须在主线程中创建新的线程对象。任何线程一般具有5种状态，创建，就绪，运行，阻塞，终止
        //question 定义了一个线程池 到底能创建多少个线程执行或者说 容量到底有多大？
        //可以通过拒绝策略来得出
        //核心线程数量是已经开辟了内存的线程 如果数量小于核心线程数量 来一个执行一个
        //当大于核心线程数量时， 首先是往队列中增加任务，队列分有界队列和无界队列，这个时候是不管最大核心线程数量有多少
        // 都先往队列中追加 队列满了之后再来计算最大线程数量与当前线程池中的数量，如果最大<小于当前 就执行拒绝策略 不然这个时候执行新的线程去处理
        //存活时间也就是执行新的线程完成之后空闲线程存活时间大于设置的这个时间 会被回收掉 只剩核心线程
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 11; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
            Runnable worker = new TestRunable(i);
            //执行Runnable
            executor.execute(worker);
        }
        //终止线程池
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");

    }
}
