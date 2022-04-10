package com.lwq;

import java.util.Arrays;

/**
 * 给你一个数组 seats 表示一排座位，其中 seats[i] = 1 代表有人坐在第 i 个座位上，seats[i] = 0 代表座位 i 上是空的（下标从 0 开始）。
 *
 * 至少有一个空座位，且至少有一人已经坐在座位上。(肯定有一个0和一个1)
 *
 * 亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。（坐到最远的地方）
 *
 * 返回他到离他最近的人的最大距离。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximize-distance-to-closest-person
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode849 {
    public static void main(String[] args) {
        int[] seats = new int[]{1,0,0,0,1,0,1};

        System.out.println(maxDistToClosest(seats));
    }

    /**
     * 令 left[i] 为座位 i 到坐在 i 左边的人的最近距离。同理 right[i] 为座位 i 到坐在 i 右边的人的最近距离。那么该座位到最近的人的距离为 min(left[i], right[i])。
     * 如果 i 左边的位置是空的，那么 left[i] = left[i - 1] + 1；否则 left[i] = 0。right[i] 的计算方法类似。
     * @param seats
     * @return
     */
    public static int maxDistToClosest(int[] seats) {
        int N = seats.length;
        int[] left = new int[N], right = new int[N];
        Arrays.fill(left, N);
        Arrays.fill(right, N);

        for (int i = 0; i < N; ++i) {
            if (seats[i] == 1) {
                left[i] = 0;
            } else if (i > 0) {
                left[i] = left[i-1] + 1;
            }
        }

        for (int i = N-1; i >= 0; --i) {
            if (seats[i] == 1) {
                right[i] = 0;
            } else if (i < N-1) {
                right[i] = right[i+1] + 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < N; ++i) {
            if (seats[i] == 0) {
                ans = Math.max(ans, Math.min(left[i], right[i]));
            }
        }
        return ans;
    }
}
