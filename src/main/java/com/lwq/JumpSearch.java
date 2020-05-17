package com.lwq;

import java.util.HashMap;

/**
 * 跳表查找
 */
public class JumpSearch {
    public static void main(String[] args) {
        int arr[] = {5, 5, 13, 19, 21, 37, 37, 56, 64, 75, 80,
                88, 92, 101, 156, 218, 315, 458};
        int x = 5;
        int index = jumpSearch(arr, x);
        System.out.println("\nNumber " + x +
                " is at index " + index);
    }

    private static int jumpSearch(int[] arr, int x) {
        int n = arr.length;
        int step = (int) Math.floor(Math.sqrt(n));
        int fast = step;
        int pre;

        // 找到要查询的区间
        for (pre = 0; pre < n - step; pre += step) {
            if (arr[fast - 1] < x) {
                fast += step;
            } else {
                break;
            }
        }
        // 线性查询
        while (pre < n) {
            if (arr[pre] == x) {
                return pre;
            }
            pre++;
        }
        return -1;
    }
}
