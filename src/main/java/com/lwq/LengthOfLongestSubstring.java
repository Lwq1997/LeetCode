package com.lwq;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Lwq
 * @create 2018-06-12 19:45
 * @desc 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * <p>
 * 示例：
 * <p>
 * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
 * <p>
 * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
 * <p>
 * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
 **/
public class LengthOfLongestSubstring {
    /**
     * 假设开始和结束的索引分别为 ii 和 jj。
     * 那么我们有 0≤i<j≤n （这里的结束索引 jj 是按惯例排除的）。
     * 因此，使用 i 从0到 n - 1 以及 j 从 i+1 到 n 这两个嵌套的循环，
     * 我们可以枚举出 s 的所有子字符串。
     * <p>
     * 要检查一个字符串是否有重复字符，我们可以使用集合。
     * 我们遍历字符串中的所有字符，并将它们逐个放入 set 中。
     * 在放置一个字符之前，我们检查该集合是否已经包含它。
     * 如果包含，我们会返回 false。循环结束后，我们返回 true。
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring01(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (allUnique(s, i, j)) {
                    ans = Math.max(ans, j - i);
                }
            }
        }
        return ans;
    }

    public static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }

    /**
     * 利用滑动窗口的模型。
     * 滑动窗口是数组/字符串问题中常用的抽象概念。
     * 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，
     * 即 [i,j)（左闭，右开）。而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。
     * 例如，我们将 [i,j) 向右滑动 11 个元素，
     * 则它将变为 [i+1,j+1)（左闭，右开）。
     * <p>
     * <p>
     * 使用 HashSet 将字符存储在当前窗口 [i,j)（最初j=i）中。
     * 然后我们向右侧滑动索引 j，如果它不在 HashSet 中，
     * 我们会继续滑动 j。直到 s[j] 已经存在于 HashSet 中。
     * 此时，我们找到的没有重复字符的最长子字符串将会以索引 i 开头。
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring02(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));    //如果窗口中没有当前值，就在加一个进来
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * [j] 在[i,j) 范围内有与 j′  重复的字符，我们不需要逐渐增加 i 。
     * 我们可以直接跳过 [i，j′] 范围内的所有元素，并将 i 变为 j' + 1
     * ​′
     */
    public static int lengthOfLongestSubstring03(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            map.put(s.charAt(j), j + 1);
            ans = Math.max(ans, j - i - 1);
        }
        return ans;
    }
}
