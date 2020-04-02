package com.lwq;

import java.util.ArrayList;
import java.util.Date;


/**
 * -Xms16m -Xmx32m
 */
public class OOMtest {
    public static void main(String[] args) {
        new Thread(()->{
            ArrayList<byte[]> list = new ArrayList<>();
            while (true){
                System.out.println(new Date().toString() + Thread.currentThread() + "==");
                byte[] b = new byte[1024 * 1024 * 1];
                list.add(b);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            while (true){
                System.out.println(new Date().toString() + Thread.currentThread() + "==");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
