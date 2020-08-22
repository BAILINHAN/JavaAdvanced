package com.solo.linhan.bai.java2;

/**
 * 线程通信的运用：生产者、消费者问题
 *
 * 生产者(Producer)将产品交给店员，消费者(Customer)从店员处取走产品，店员一次只能持有固定数量的产品
 * 如果生产者试图生产更多的产品，店员会让生产者停止生产，如果店中有空位了生产者继续生产；如果店中没有产品了
 * 店员会告诉消费者等一下，如果店中有产品了会通知消费者取走产品
 *
 * @Author: solo.linhan.bai
 * @Create: 2020-08-20 16:35
 */

class Clerk{

    private int count = 0;

    //生产产品
    public synchronized void produceProduct() {
        if(count < 20){
            count++;
            System.out.println(Thread.currentThread().getName() + "开始生产第" + count + "个产品");
            notify();
        }else{
            //等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //消费产品
    public synchronized void consumeProduct() {
        if(count >0){
            System.out.println(Thread.currentThread().getName() + "开始消费第" + count + "个产品");
            count--;
            notify();
        }else{
            //等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer extends Thread{//生产者

    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("生产者：" + Thread.currentThread().getName() + "--->开始生产产品");

        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }

    }
}

class Customer extends Thread{//消费者

    private Clerk clerk;

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("消费者：" + Thread.currentThread().getName() + "--->开始消费产品");

        while (true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }

    }
}

public class ProductTest {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer producer = new Producer(clerk);
        producer.setName("生产者");

        Customer customer = new Customer(clerk);
        customer.setName("消费者");

        Customer customer2 = new Customer(clerk);
        customer2.setName("消费者2");

        Customer customer3 = new Customer(clerk);
        customer3.setName("消费者3");

        producer.start();
        customer.start();
        customer2.start();
        customer3.start();

    }

}
