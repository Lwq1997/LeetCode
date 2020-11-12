package com.lwq;

/**
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * <p>
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * <p>
 * 你可以返回任何满足上述条件的数组作为答案。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 */
public class LeetCode922 {

    /**
     * 遍历一遍数组把所有的偶数放进 ans[0]，ans[2]，ans[4]，依次类推。
     * <p>
     * 再遍历一遍数组把所有的奇数依次放进 ans[1]，ans[3]，ans[5]，依次类推。
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII1(int[] A) {
        int n = A.length;
        int[] ans = new int[n];

        int i = 0;
        for (int x : A) {
            if (x % 2 == 0) {
                ans[i] = x;
                i += 2;
            }
        }
        i = 1;
        for (int x : A) {
            if (x % 2 == 1) {
                ans[i] = x;
                i += 2;
            }
        }
        return ans;
    }

    public int[] sortArrayByParityII(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (i % 2 == 0 && A[i] % 2 == 1) {
                // 下标偶数，实际奇数
                for (int j = i + 1; j < A.length; j++) {
                    // 往后找到一个偶数
                    if (A[j] % 2 == 0) {
                        swap(A, i, j);
                        break;
                    }
                }
            }
            else if (i % 2 == 1 && A[i] % 2 == 0) {
                // 下标奇数，实际偶数
                for (int j = i + 1; j < A.length; j++) {
                    // 往后找到一个奇数
                    if (A[j] % 2 == 1) {
                        swap(A, i, j);
                        break;
                    }
                }
            }
        }
        return A;
    }

    /**
     * 为数组的偶数下标部分和奇数下标部分分别维护指针 i, j
     * 随后，在每一步中，如果 A[i] 为奇数，则不断地向前移动 j（每次移动两个单位），直到遇见下一个偶数。
     * 此时，可以直接将 A[i] 与 A[j] 交换。我们不断进行这样的过程，最终能够将所有的整数放在正确的位置上。
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII2(int[] A) {
        int n = A.length;
        int j = 1;
        for (int i = 0; i < n; i += 2) {
            if (A[i] % 2 == 1) {
                // 下标是偶数，值是奇数
                while (A[j] % 2 == 1) {
                    // 找到下一个奇数
                    j += 2;
                }
                swap(A, i, j);
            }
        }
        return A;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
