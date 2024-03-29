package com.lwq;


import java.util.HashMap;

/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * <p>
 * 示例：
 * <p>
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * <p>
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
public class LeetCode76 {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }
    public static String minWindow(String s, String t) {
        System.out.println(s.length()+" "+t.length());
        HashMap<Character, Integer> window = new HashMap<>(), need = new HashMap<>();
        char[] target = t.toCharArray();
        char[] chars = s.toCharArray();
        for (char c : target) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        //valid变量表示窗口中满足need条件的字符个数
        int left = 0, right = 0, valid = 0;
        // 记录最小覆盖子串的起始索引及长度
        int start = 0;
        long len = Long.MAX_VALUE;
        //开始滑动
        while (right < chars.length) {
            // c 是将移入窗口的字符
            char c = chars[right];
            // 右移窗口
            right++;
            // 进行窗口内数据一系列更新
            if (need.containsKey(c)) {
                // 窗口中放入这个元素c
                window.put(c, window.getOrDefault(c, 0) + 1);
                // Integer类型的数据在-128～127之间时，会使用缓存，用==比较时会比较数值，超出这个范围不会拆箱，比较的是对象，所以遇到长字符串时是有问题的！
                if (window.get(c).intValue() == need.get(c).intValue()) {
                    valid++;
                }
            }

            System.out.println(left + " " + right);

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 在这里更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                // d 是将移出窗口的字符
                char d = chars[left];
                // 左移窗口
                left++;
                // 进行窗口内数据一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).intValue() == need.get(d).intValue()) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Long.MAX_VALUE ? "" : s.substring(start, (int) (start+len));
    }
}
