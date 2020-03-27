package com.lwq.single;

public class MyThread extends Thread {
    @Override
    public void run() {
//        System.out.println(MySingleton01.getInstance().hashCode());
//        System.out.println(MySingleton02.getInstance().hashCode());
//        System.out.println(MySingleton03.getInstance().hashCode());
//        System.out.println(MySingleton04.getInstance());
//        System.out.println(MySingleton05.getInstance());
//        System.out.println(MySingleton06.getInstance());
        System.out.println(ClassFactory.getInstance());
    }


    public static void main(String[] args) {
        MyThread[] myThreads = new MyThread[10];

        for(int i = 0 ; i < myThreads.length ; i++){
            myThreads[i] = new MyThread();
        }

        for (int j = 0; j < myThreads.length; j++) {
            myThreads[j].start();
        }
    }
}
