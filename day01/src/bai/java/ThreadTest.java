package bai.java;

/**
 * 多线程的创建，方式一：继承于Thread类
 * 1.创建一个继承于Thread类的子类
 * 2.重写Thread类的run()方法 --> 将此线程执行的操作声明在run()中
 * 3.创建Thread类的子类对象
 * 4.通过子对象调用start()
 *
 * 例子：遍历100以内的所有偶数
 *
 * @Author: solo.linhan.bai
 * @Create: 2020-08-05 18:37
 */

//创建一个继承于Thread类的子类
class MyThread extends Thread{
    //重写Thread类的run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }

}

public class ThreadTest  {
    public static void main(String[] args) {
        //创建Thread类的子类的对象
        MyThread myThread = new MyThread();
        //通过此对象调用start():①启用当前线程 ②调用当前线程run()
        myThread.start();
        //问题一：我们不能通过直接调用run()的方式启动线程
        //myThread.run();

        //问题二：再启动一个线程，遍历100以内的偶数。不可以让已经start()的线程去执行。会报IllegalThreadStateException异常
        //myThread.start();
        //需要重新创建一个线程的对象
        MyThread myThread2 = new MyThread();
        myThread2.start();

        for (int i =0; i<100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" +"Hello");
        }
    }
}
