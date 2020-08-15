package bai.java;

/**
 *
 * 例子：创建三个窗口卖票，总票数为100张
 * 使用继承Thread类的方式实现的
 * 存在线程的安全问题(待解决)
 *
 * @Author: solo.linhan.bai
 * @Create: 2020-08-12 12:05
 */

class Window extends Thread{

    private static int ticket = 100;

    @Override
    public void run() {
        while (true){
            if(ticket > 0){
                System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
                ticket--;
            }else{
                break;
            }
        }
    }

}

public class WindowTest {

    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口-1");
        w2.setName("窗口-2");
        w3.setName("窗口-3");

        w1.start();
        w2.start();
        w3.start();

    }

}
