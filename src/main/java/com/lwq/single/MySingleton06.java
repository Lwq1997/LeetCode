package com.lwq.single;



public class MySingleton06{

    private static MySingleton06 instance = null;

    private MySingleton06(){

    }

    static{
        instance = new MySingleton06();
    }

    public static MySingleton06 getInstance(){
        return instance;
    }

}
