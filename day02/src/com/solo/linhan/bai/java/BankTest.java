package com.solo.linhan.bai.java;

/**
 * 使用同步机制将单例模式中的懒汉式改为线程安全的
 *
 * @Author: solo.linhan.bai
 * @Create: 2020-08-17 11:24
 */
public class BankTest {
}

class Bank{

    private Bank(){}

    private static Bank instance = null;

    public static synchronized Bank getInstance(){
        //方式一：效率不高
        if(instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {
                    instance = new Bank();
                }
            }
        }
        return instance;
    }

}
