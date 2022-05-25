package com.lwq.violencerecursive;

/**
 * 两个人玩纸牌游戏，纸牌是一个数组，
 * 每个人一次只能拿一张（这一张只能从最左边或者最右边去拿），作为自己的分数
 * 最后拍被拿完了，返回最大的分数
 */
public class CardsInline {
    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 100, 4};
        System.out.println(win(ints));
    }

    private static int win(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    /**
     * 后手拿牌的递归
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int s(int[] arr, int L, int R) {
        if (L == R) {
            //说明此时只有一张牌了，说明此时没有牌了，返回0
            return 0;
        }
        return Math.min(f(arr, L + 1, R), f(arr, L, R - 1));
    }

    /**
     * 先手拿牌的递归
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int f(int[] arr, int L, int R) {
        if (L == R) {
            //说明此时只有一张牌了，只能拿到这张牌，返回分数为arr[L]
            return arr[L];
        }
        //arr[L] + s(arr, L + 1, R)   拿左边的牌，下一个人剔除这张牌
        //arr[R] + f(arr, L, R - 1)   拿右边的牌，下一个人剔除这张牌
        return Math.max(arr[L] + s(arr, L + 1, R), arr[R] + f(arr, L, R - 1));
    }
}
