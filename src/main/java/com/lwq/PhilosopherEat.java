package com.lwq;

public class PhilosopherEat {
    class Philosopher extends Thread {
        private String name;
        private Fork fork;

        public Philosopher(String name, Fork fork) {
            super(name);
            this.name = name;
            this.fork = fork;
        }

        @Override
        public void run() {
            // 哲学家需要完成要的一系列动作
            while (true) {
                thinking();
                fork.takeFork();
                eating();
                fork.putFork();
            }
        }

        public void eating() {
            System.out.println("I am Eating:" + name);
            try {
                //模拟吃饭
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void thinking() {
            System.out.println("I am Thinking:" + name);
            try {
                //模拟思考
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Fork {
        // 5根筷子
        private boolean[] used = {false, false, false, false, false, false};

        public synchronized void takeFork() {
            String name = Thread.currentThread().getName();
            int i = Integer.parseInt(name);
            // 如果左右手有一只正被使用就等待
            while (used[i] || used[(i + 1) % 5]) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            used[i] = true;
            used[(i + 1) % 5] = true;
        }

        // 同时释放左右手的筷子
        public synchronized void putFork() {
            String name = Thread.currentThread().getName();
            int i = Integer.parseInt(name);
            used[i] = false;
            used[(i + 1) % 5] = false;
            notifyAll();
        }
    }

    public static void main(String[] args) {
        PhilosopherEat philosopher = new PhilosopherEat();
        Fork fork = philosopher.new Fork();
        philosopher.new Philosopher("0", fork).start();
        philosopher.new Philosopher("1", fork).start();
        philosopher.new Philosopher("2", fork).start();
        philosopher.new Philosopher("3", fork).start();
        philosopher.new Philosopher("4", fork).start();
    }
}
