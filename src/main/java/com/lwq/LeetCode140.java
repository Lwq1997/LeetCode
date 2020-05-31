package com.lwq;

import java.util.*;

public class LeetCode140 {
    public List<String> wordBreak(String s, List<String> wordDict) {
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

    private void dfs(String s, int length, Set<String> set, List<String> res, LinkedList<String> queue, boolean[] dp) {
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
