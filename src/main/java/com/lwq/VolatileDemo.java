package com.lwq;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t updated number: " + myData.number);
        },"AAA").start();
        //如果上面的线程改了number，才会退出下面的循环，删除volatile，就算改了，循环也退不出去
        while (myData.number == 0) {}
        System.out.println(Thread.currentThread().getName() + "\t mission is over");

    }
}
class MyData {
    volatile int number = 0;
    public void addTo60() {
        number = 60;
    }
}