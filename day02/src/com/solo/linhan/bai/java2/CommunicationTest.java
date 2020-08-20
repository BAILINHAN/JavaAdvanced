package com.solo.linhan.bai.java2;


/**
 * 线程通信的例子：两个线程交替打印 1-100
 *
 * 涉及到的三个方法：
 * wait():一旦执行此方法，当前线程就进入阻塞状态，并释放同步监视器
 * notify():一旦执行此方法，就会唤醒被wait的一个线程。如果有多个线程被wait，就唤醒优先级高的线程
 * notifyAll():一旦执行此方法，就会唤醒所有被wait的线程
 *
 * 说明：
 * 1.wait(),notify(),notifyAll()三个方法必须使用在同步代码块或同步方法中。
 * 2.wait(),notify(),notifyAll()三个方法的调用者必须是同步代码块或者是同步方法中的同步监视器。
 *   否则会出现IllegalMonitorStateException异常
 * 3.wait(),notify(),notifyAll()是定义在Object类中的方法
 *
 * 面试题：
 * sleep()和wait()的异同
 * 1.相同点：一旦执行方法，都会使当前线程进入阻塞状态
 * 2.不同点：1)两个方法声明的位置不同:Thread类中声明的sleep()，Object类中声明的wait()
 *         2)调用的范围不同：sleep()可以在任何需要的场景下调用；wait()必须使用在同步代码块或同步方法中
 *         3)关于是否释放同步监视器：如果两个方法都使用在同步方法或同步代码块中，
 *           sleep()方法不会释放同步监视器，wait()会释放同步监视器。
 *
 *
 * @Author: solo.linhan.bai
 * @Create: 2020-08-20 10:32
 */

class Number implements Runnable{

    private int number = 1;
//    private ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while(true){
//            lock.lock();
            synchronized (this) {
                notify();
                if(number <= 100){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
//                        lock.unlock();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;

                    try {
                        //使得调用wait()方法的线程进入阻塞状态
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
//                    lock.unlock();
                    break;
                }
            }
        }
    }
}

public class CommunicationTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread thread = new Thread(number);
        Thread thread1 = new Thread(number);

        thread.setName("线程1");
        thread1.setName("线程2");

        thread.start();
        thread1.start();

    }
}
