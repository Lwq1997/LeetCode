package com.lwq;

import java.util.concurrent.Semaphore;

public class ReaderAndWriter {
    public static void main(String[] args) {
        // 实现写者与写者间、读者与写者间互斥
        Semaphore wmutex = new Semaphore(1);
        // 用于改变 readCount 变量时实现互斥
        Semaphore rmutex = new Semaphore(1);
        for (int i = 0; i < 3; ++i) {
//            new Reader(rmutex, wmutex).start();
            new Writer(wmutex).start();
        }
    }
}

class Reader extends Thread {
    private static int total = 0;
    private int id;
    private Semaphore rmutex, wmutex;
    private static int readCount = 0;

    public Reader(Semaphore rmutex, Semaphore wmutex) {
        id = ++total;
        this.rmutex = rmutex;
        this.wmutex = wmutex;
    }

    @Override
    public void run() {
        while (true) {
            try {
                rmutex.acquire();
                // 只有第一个读者进程需要执行 wmutex.p()
                // 这一步可以实现读写互斥
                if (readCount == 0) wmutex.acquire();
                readCount++;
                System.out.println(id + " 号读者在读");
            } catch (Exception e) {
            } finally {
                rmutex.release();
            }
            // 模拟读书
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 读书人出去了
            try {
                rmutex.acquire();
                readCount--;
                System.out.println(id + " 号读者结束阅读：当前还剩 " + readCount + " 位读者在读");
                if (readCount == 0) wmutex.release();
            } catch (Exception e) {
            } finally {
                rmutex.release();
            }
        }
    }
}

class Writer extends Thread {
    private static int total = 0;
    private int id;
    private Semaphore wmutex;

    public Writer(Semaphore wmutex) {
        id = ++total;
        this.wmutex = wmutex;
    }

    @Override
    public void run() {
        while (true) {
            try {
                wmutex.acquire();
                // 执行写操作
                System.out.println(id + " 号写者正在写");
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(id + " 号写者退出写");
                wmutex.release();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
