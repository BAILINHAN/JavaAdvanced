package com.solo.linhan.bai;

/**
 * 使用同步方法处理继承Thread类的方式中的线程安全问题
 *
 * @Author: solo.linhan.bai
 * @Create: 2020-08-16 17:32
 */
class Window4 extends Thread{

    private static int ticket = 100;
    private static Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    private static synchronized void show(){//当前对象有三个，同步监视器为t1，t2，t3；需要将方法改为静态的，同步监视器变为Window4.class
        if (ticket > 0) {

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
            ticket--;
        }
    }

}

public class WindowTest4 {

    public static void main(String[] args) {
        Window4 w1 = new Window4();
        Window4 w2 = new Window4();
        Window4 w3 = new Window4();

        w1.setName("窗口-1");
        w2.setName("窗口-2");
        w3.setName("窗口-3");

        w1.start();
        w2.start();
        w3.start();

    }

}
