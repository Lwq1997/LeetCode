package com.lwq;

import java.util.ArrayDeque;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class LeetCode84 {
    public static void main(String[] args) {
        largestRectangleArea1(new int[]{1,2,3});
    }
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            int left = i;
            int right = i;
            int curHeight = heights[i];
            while (left > 0 && heights[left - 1] >= curHeight) {
                left--;
            }
            while (right < len - 1 && heights[right + 1] >= curHeight) {
                right++;
            }
            int width = right - left + 1;
            res = Math.max(res, width * curHeight);
        }
        return res;
    }
    public static int largestRectangleArea1(int[] heights) {
        // 这里为了代码简便，在柱体数组的头和尾加了两个高度为 0 的柱体。
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights, 0, tmp, 1, heights.length);
//        for(int i = 0; i < heights.length ; i++){
//            tmp[i+1] = heights[i];
//        }
        int res = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < tmp.length ; i++){
            // 对栈中柱体来说，栈中的下一个柱体就是其「左边第一个小于自身的柱体」；
            // 若当前柱体 i 的高度小于栈顶柱体的高度，说明 i 是栈顶柱体的「右边第一个小于栈顶柱体的柱体」。
            // 因此以栈顶柱体为高的矩形的左右宽度边界就确定了，可以计算面积
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                int h = tmp[stack.pop()];
                // i 是当前栈顶元素的右边界
                // 因为栈顶元素已经弹出，所以现在的stack.peek()就是栈顶元素的左边界
                res = Math.max(res, (i - stack.peek() - 1) * h);
            }
            stack.push(i);
        }
        return res;
    }
}
