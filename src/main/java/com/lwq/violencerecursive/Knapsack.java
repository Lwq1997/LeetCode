package com.lwq.violencerecursive;

/**
 * weight表示物品的重量，value表示物品的价值
 * bag表示背包的容量
 * 返回值表示背包中物品的最大价值
 */
public class Knapsack {
    public static void main(String[] args) {
        int[] weight = {2, 3, 4, 5};
        int[] value = {3, 4, 5, 6};
        int bag = 10;
        System.out.println(knapsack(weight, value, bag, 0, 0));
        System.out.println(knapsack2(weight, value, bag, 0, 0, 0));
    }

    /**
     * 暴力递归
     * i-1之前的已经选择过了
     *
     * @param weight       物品重量
     * @param value        物品价值
     * @param bag          背包容量
     * @param index        当前选择的物品的下标
     * @param alreadWeight 当前背包中物品的重量
     * @return
     */
    private static int knapsack(int[] weight, int[] value, int bag, int index, int alreadWeight) {

        if (index == weight.length) {
            // 说明已经选择完了，返回0
            //数组的下标是从0开始的，最后一个元素的下标是weight.length-1
            return 0;
        }
        if (weight[index] + alreadWeight > bag) {
            //说明这个方案不行，返回0
            return 0;
        }
        return Math.max(
                knapsack(weight, value, bag, index + 1, alreadWeight), //不选择当前物品
                value[index] + knapsack(weight, value, bag, index + 1, alreadWeight + weight[index]) //选择当前物品
        );
    }

    /**
     * 暴力递归
     * i-1之前的已经选择过了
     *
     * @param weight       物品重量
     * @param value        物品价值
     * @param bag          背包容量
     * @param index        当前选择的物品的下标
     * @param alreadWeight 当前背包中物品的重量
     * @param alreadValue  返回值表示背包中物品的最大价值
     * @return
     */
    private static int knapsack2(int[] weight, int[] value, int bag, int index, int alreadWeight, int alreadValue) {
        if (alreadWeight > bag) {
            //说明这个方案不行，返回0
            return 0;
        }
        if (index == weight.length) {
            // 说明已经选择完了，返回0
            //数组的下标是从0开始的，最后一个元素的下标是weight.length-1
            return alreadValue;
        }
        return Math.max(
                knapsack2(weight, value, bag, index + 1, alreadWeight, alreadValue), //不选择当前物品
                knapsack2(weight, value, bag, index + 1, alreadWeight + weight[index], alreadValue + value[index]) //选择当前物品
        );
    }
}
