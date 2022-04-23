package com.lwq;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LeetCode003 {
    public static void main(String[] args) {
        LeetCode003 leetCode003 = new LeetCode003();
        System.out.println(leetCode003.lengthOfLongestSubstring01("abcabcbb"));
        System.out.println(leetCode003.lengthOfLongestSubstring02("abcabcbb"));
    }

    /**
     * 滑动窗口
     * 左边界为
     * 有边界为i
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring01(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //判断C是否出现过
            if (map.containsKey(c)) {
                //C当时的下标
                Integer index = map.get(c);
                left = Math.max(left, index + 1);
            }
            //把字符填充进去
            map.put(c, i);
            res = Math.max(res, i - left + 1);
        }
        return res;
    }

    /**
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring02(String s) {
        HashMap<Character, Integer> window = new HashMap<>();
        char[] chars = s.toCharArray();

        int left = 0, right = 0, res = 0;
        //开始滑动
        while (right < chars.length) {
            // c 是将移入窗口的字符
            char c = chars[right];
            // 右移窗口
            right++;
            // 进行窗口内数据一系列更新
            window.put(c, window.getOrDefault(c, 0) + 1);


            System.out.println(left + " " + right);

            // 判断左侧窗口是否要收缩
            // 当window[c]值大于 1 时，说明窗口中存在重复字符，不符合条件，就该移动left缩小窗口了嘛。
            while (window.get(c) > 1) {
                // d 是将移出窗口的字符
                char d = chars[left];
                // 左移窗口
                left++;
                // 进行窗口内数据一系列更新
                window.put(d, window.get(d) - 1);
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

}
