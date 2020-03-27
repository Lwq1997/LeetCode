package com.lwq.single;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class MySingleton05 implements Serializable {


    private MySingleton05(){

    }

    private static class MySingletonInnerClass{
        private static MySingleton05 instance = new MySingleton05();
    }

    public static MySingleton05 getInstance(){
        return MySingletonInnerClass.instance;
    }

    //该方法在反序列化时会被调用，该方法不是接口定义的方法，有点儿约定俗成的感觉
    protected Object readResolve() throws ObjectStreamException {
        System.out.println("调用了readResolve方法！");
        return MySingletonInnerClass.instance;
    }
}
