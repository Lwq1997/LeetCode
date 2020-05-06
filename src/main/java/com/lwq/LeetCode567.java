package com.lwq;

import java.util.HashMap;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * <p>
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *  
 * <p>
 * 示例2:
 * <p>
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *  
 * <p>
 * 注意：
 * <p>
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 */
public class LeetCode567 {
    public static void main(String[] args) {

    }

    public boolean checkInclusion(String t, String s) {
        System.out.println(s.length()+" "+t.length());
        HashMap<Character, Integer> window = new HashMap<>(), need = new HashMap<>();
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
                // 在这里判断是否找到了合法的子串
                if (valid == need.size()){
                    return true;
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
        // 未找到符合条件的子串
        return false;

    }

}
