package com.lwq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 找所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 *
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 */
public class LeetCode438 {
    public static void main(String[] args) {

    }

    public List<Integer> findAnagrams(String s, String t) {
        System.out.println(s.length()+" "+t.length());
        HashMap<Character, Integer> window = new HashMap<>(), need = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        char[] target = t.toCharArray();
        char[] chars = s.toCharArray();
        for (char c : target) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        //valid变量表示窗口中满足need条件的字符个数
        int left = 0, right = 0, valid = 0;
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
            while (right - left >= target.length) {
                // 在这里判断是否是字母异位词
                if (valid == need.size()){
                    res.add(left);
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
        return res;

    }
}
