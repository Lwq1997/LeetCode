package com.lwq;

public class InterruptThread {
    public static void main(String[] args) {
        Thread thread = new MyThread();
        Thread thread1 = new MyThread1();
        thread.start();
        thread1.start();
        try {
            Thread.sleep(2000);
            thread.interrupt();
            thread1.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class MyThread extends Thread {
    public void run(){
        super.run();
        try {
            for(int i=0; i<500000; i++){
                if(this.interrupted()) { //这里的this指的是thread自身
                    System.out.println("线程已经终止， for循环不再执行");
                    throw new InterruptedException(); //如果不抛出异常，for之后的语句也会被执行
                }
                System.out.println("i="+(i+1));
            }

            System.out.println("这是for循环外面的语句，也会被执行");
        } catch (InterruptedException e) {
            System.out.println("进入MyThread.java类中的catch了。。。");
            e.printStackTrace();
        }
    }
}

class MyThread1 extends Thread {
    public void run(){
        super.run();

        try {
            System.out.println("线程开始。。。");
            this.sleep(200000);
            System.out.println("线程结束。");
        } catch (InterruptedException e) {
            System.out.println("在沉睡中被停止, 进入catch， 调用isInterrupted()方法的结果是：" + this.isInterrupted());
            e.printStackTrace();
        }

    }
}