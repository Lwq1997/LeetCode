package com.lwq.greedyalgorithm;


import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {
    public class MyProgram {
        int cost;
        int profit;

        public MyProgram(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public class MyCostComparator implements Comparator<MyProgram> {

        /**
         * 按照消费顺序排序，小根堆
         *
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(MyProgram o1, MyProgram o2) {
            return o1.cost - o2.cost;
        }
    }

    public class MyProfitComparator implements Comparator<MyProgram> {

        /**
         * 按照利润顺序排序，大根堆（从大到小）
         *
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(MyProgram o1, MyProgram o2) {
            return o2.profit - o1.profit;
        }
    }

    /**
     * 利用有限的初始财富，计算最大的收益
     *
     * @param k      最多完成的项目
     * @param init   初始资金
     * @param cost   每个项目的消费
     * @param profit 每个项目的利润
     * @return
     */
    public int ipo(int k, int init, int[] cost, int[] profit) {
        PriorityQueue<MyProgram> minCostQ = new PriorityQueue<>(new MyCostComparator());
        PriorityQueue<MyProgram> maxProfitQ = new PriorityQueue<>(new MyProfitComparator());
        for (int i = 0; i < cost.length; i++) {
            MyProgram program = new MyProgram(cost[i], profit[i]);
            minCostQ.add(program);
        }
        for (int i = 0; i < k; i++) {
            //解锁所有能解锁的项目
            while (!minCostQ.isEmpty() && minCostQ.peek().cost <= init) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                //没有可以解锁的项目，但是K次机会还没有用完
                return init;
            }
            init += maxProfitQ.poll().profit;
        }
        return init;
    }
}
