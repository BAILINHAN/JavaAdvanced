package com.solo.linhan.bai.java2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建线程的方式四：使用线程池
 *
 * 好处：
 * 1.提高响应速度(减少了创建新线程的时间)
 * 2.降低资源消耗(重复利用线程池中线程，不需要每次都创建，便于线程管理)
 * 3.线程管理：1) corePoolSize:核心池大小
 *           2) maximumPoolSize:最大线程数
 *           3) keepAliveTime: 线程没有任务时，最多保持多长时间后会终止
 *
 * @Author: solo.linhan.bai
 * @Create: 2020-08-21 11:20
 */

class NumberThreadPool implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class NumberThreadPoolX implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

public class ThreadPool {

    public static void main(String[] args) {
        //
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new NumberThreadPool());//适合使用于Runnable
//        executorService.submit(new NumberThread());//适合使用于Callable

        executorService.execute(new NumberThreadPoolX());
        executorService.shutdown();
    }

}
