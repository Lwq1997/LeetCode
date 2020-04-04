package com.lwq;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();

        //保障可见性
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t updated number: " + myData.number1);
        },"AAA").start();
        //如果上面的线程改了number，才会退出下面的循环，删除volatile，就算改了，循环也退不出去
        while (myData.number1 == 0) {}
        System.out.println(Thread.currentThread().getName() + "\t mission is over");

        //不保障原子性
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally number value: " + myData.number2);

        //保障原子性
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addAtmic();
                }
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally number value: " + myData.atomicInteger);


    }
}
class MyData {
    volatile int number1 = 0;
    volatile int number2 = 0;
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtmic() {
        atomicInteger.getAndIncrement();
    }
    public void addTo60() {
        number1 = 60;
    }
    public void addPlusPlus() {
        number2++;
    }
}