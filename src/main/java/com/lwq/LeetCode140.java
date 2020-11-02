package com.lwq;

import java.util.*;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 *
 * 说明：
 *
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * 示例 2：
 *
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode140 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("pen");
        list.add("applepen");
        list.add("pine");
        list.add("pineapple");
        wordBreak("pineapplepenapple",list);
    }
    public static List<String> wordBreak(String s, List<String> wordDict) {
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

        List<String> res = new ArrayList<>();
        if(dp[s.length()]){
            LinkedList<String> queue = new LinkedList<>();
            dfs(s,s.length(),set,res,queue,dp);
        }
        return res;
    }

    private static void dfs(String s, int length, Set<String> set, List<String> res, LinkedList<String> queue, boolean[] dp) {
        if(length == 0){
            StringBuilder sb = new StringBuilder();
            for (String word : queue) {
                sb.append(word).append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            res.add(sb.toString());
            return;
        }
        for(int i = 0; i < length ; i++){
            if(dp[i]){
                // 后缀的字符串
                String suffix = s.substring(i, length);
                if(set.contains(suffix)){
                    queue.addFirst(suffix);
                    dfs(s,i,set,res,queue,dp);
                    queue.removeFirst();
                }
            }
        }
    }
}
