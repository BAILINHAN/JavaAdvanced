package com.solo.linhan.bai;

/**
 *
 * 例子：创建三个窗口卖票，总票数为100张
 * 使用同步代码块解决继承Thread类的方式的线程安全问题
 * 使用继承Thread类的方式实现的
 * 存在线程的安全问题(待解决)
 * @Author: solo.linhan.bai
 * @Create: 2020-08-16 16:24
 */

class Window2 extends Thread{

    private static int ticket = 100;
    private static Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (Window2.class) {//Class class = Window2.class，Window2.class只加载一次
                //这里不能用this，this代表着t1，t2，t3三个对象
                //也可以写成synchronize(Window2.class)，类也是一个对象
                if (ticket > 0) {

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }

}

public class WindowTest2 {

    public static void main(String[] args) {
        Window2 w1 = new Window2();
        Window2 w2 = new Window2();
        Window2 w3 = new Window2();

        w1.setName("窗口-1");
        w2.setName("窗口-2");
        w3.setName("窗口-3");

        w1.start();
        w2.start();
        w3.start();

    }

}

