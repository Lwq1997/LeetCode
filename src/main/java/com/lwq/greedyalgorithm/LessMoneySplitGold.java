package com.lwq.greedyalgorithm;

import java.util.PriorityQueue;

/**
 * 长度为N的金子，分割起来需要代价为N
 * <p>
 * 给你一个数组，里面有N个金子，每个金子的价值为数组中的一个数字。
 * 计算出分割金子（分割为数组中的大小）的最小代价。
 * 比如【10，20，30】
 * 方法一：60先分割出30，代价60，再分割出10和20，代价30，最终代价为60+30=90
 * 方法二：60先分割出10，代价60，再分割出20和30，代价50，最终代价为60+50=110
 */
public class LessMoneySplitGold {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(lessMoney(arr));
    }

    /**
     * 贪心，哈夫曼算法
     *
     * @param arr
     * @return
     */
    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            // 将数组中的数据放入优先队列中
            priorityQueue.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (priorityQueue.size() > 1) {
            // 取出最小的两个数
            int a = priorityQueue.poll();
            int b = priorityQueue.poll();
            // 将两个数相加
            cur = a + b;
            // 将当前的和加入优先队列中
            priorityQueue.add(cur);
            sum += cur;
        }
        return sum;
    }
}
