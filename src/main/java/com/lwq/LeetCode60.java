package com.lwq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 第k个排列
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 说明：
 * <p>
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * <p>
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * <p>
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class LeetCode60 {
    public static void main(String[] args) {
        getPermutation1(4, 9);
    }

    public static String getPermutation(int n, int k) {

        ArrayList<Integer> path = new ArrayList<>(n);

        // 记录数字是否使用过
        boolean[] used = new boolean[n + 1];
        Arrays.fill(used, false);

        // 计算阶乘数组
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        dfs(0, factorial, path, n, k, used);

        StringBuilder res = new StringBuilder();
        for (Integer integer : path) {
            res.append(integer);
        }
        return res.toString();
    }

    private static void dfs(int index, int[] factorial, ArrayList<Integer> path, int n, int k, boolean[] used) {
        if (index == n) {
            return;
        }
        // cnt代表这一层级往下走还有多少种情况
        int cnt = factorial[n - index - 1];
        for (int i = 1; i <= n; i++) {
            // 每次进来都从1开始判断数字是否使用过
            if (used[i]) {
                continue;
            }
            if (cnt < k) {
                k -= cnt;
                continue;
            }
            path.add(i);
            used[i] = true;
            dfs(index + 1, factorial, path, n, k, used);
        }
    }

    public static String getPermutation1(int n, int k) {
        // 注意：相当于在 n 个数字的全排列中找到索引为 k - 1 的那个数，因此 k 先减 1
        --k;

        int[] factorial = new int[n];
        factorial[0] = 1;
        // 先算出所有的阶乘值
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        // 因为要频繁做删除，使用链表
        List<Integer> nums = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            // i代表层级
            // 如果是第3层，factorial[i] = 6
            // index = k / factorial[i]。
            // 如果k大于factorial[i]。说明nums连表中第i个数不能填充。那应该是第index个数据填充
            // 如果k小于factorial[i]。说明应该是第0个数据填充
            int index = k / factorial[i];
            sb.append(nums.remove(index));
            // 每次减少一个值
            k -= index * factorial[i];
        }
        return sb.toString();
    }
}
