package com.lwq.single;

public class MySingleton03 {
    private static MySingleton03 instance = null;

    private MySingleton03(){

    }

    public static MySingleton03 getInstance(){
        synchronized (MySingleton03.class){
            if(instance == null){
                try {
                    Thread.sleep(100);
                    instance = new MySingleton03();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return instance;
        }
    }
}
