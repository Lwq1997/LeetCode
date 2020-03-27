package com.lwq.single;

public class MySingleton02 {
    private static MySingleton02 instance = null;

    private MySingleton02(){

    }

    public synchronized static MySingleton02 getInstance(){
        if(instance == null){
            try {
                Thread.sleep(100);
                instance = new MySingleton02();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
