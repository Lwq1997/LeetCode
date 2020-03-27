package com.lwq.single;


public class MySingleton01{
    private static MySingleton01 instance = new MySingleton01();

    private MySingleton01(){

    }

    public static MySingleton01 getInstance(){
        return instance;
    }
}
