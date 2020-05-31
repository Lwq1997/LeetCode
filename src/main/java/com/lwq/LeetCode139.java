package com.lwq;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class LeetCode139 {


    /**
     * 暴力递归
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return word_break(s, new HashSet<>(wordDict), 0);
    }

    private boolean word_break(String s, HashSet<String> set, int start) {
        if (start == s.length()) {
            return true;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            if (set.contains(s.substring(start, i)) && word_break(s, set, i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 动态规划
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak01(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            // 这一层循环拿到的是s的所有子串。i= 1 意味这长度为1的子串
            for (int j = 0; j < i; j++) {
                // [j,i) 代表子串的前半部分
                if (dp[j] && set.contains(s.substring(j,i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}
