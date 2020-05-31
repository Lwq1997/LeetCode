package com.lwq;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */
public class LeetCode131 {
    public static void main(String[] args) {
        partition("aab");
    }

    public static List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<>();

        if (len == 0) {
            return res;
        }

        ArrayDeque<String> path = new ArrayDeque<>();
        int start = 0;
        backtrack(s, start, len, path, res);
        return res;
    }

    /**
     * @param s
     * @param start 起始字符的索引
     * @param len   字符串 s 的长度，可以设置为全局变量
     * @param path  记录从根结点到叶子结点的路径
     * @param res   记录所有的结果
     */
    private static void backtrack(String s, int start, int len, ArrayDeque<String> path, List<List<String>> res) {
        if (len == start) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < len; i++) {
            // 判断是不是回文（start，i之间）
            if (!checkPalindrome(s, start, i)) {
                continue;
            }
            path.addLast(s.substring(start, i + 1));
            backtrack(s, i + 1, len, path, res);
            path.removeLast();
        }
    }

    /**
     * @param s
     * @param left
     * @param right
     * @returnÒ
     */
    private static boolean checkPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
