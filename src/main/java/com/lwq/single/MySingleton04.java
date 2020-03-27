package com.lwq.single;

public class MySingleton04 {

    private  volatile static MySingleton04 instance = null;

    private MySingleton04(){

    }

    public static MySingleton04 getInstance(){
        if(instance == null){
            try {
                Thread.sleep(1000);
                synchronized (MySingleton04.class){
                    if(instance == null){
                        instance = new MySingleton04();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

}
