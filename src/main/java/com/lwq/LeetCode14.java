package com.lwq;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 */
public class LeetCode14 {
    public static void main(String[] args) {
        LeetCode14 leetCode14 = new LeetCode14();
        String[] strs = {"flower","flow","flight"};
        System.out.println(leetCode14.longestCommonPrefix(strs));
    }

    public String longestCommonPrefix(String[] strs) {
        int count = strs.length;
        String pre = "";
        if(count > 0){
            // 先默认取第一个字符串为最长公共前缀
            pre = strs[0];
        }
        for (int i = 1; i < strs.length ; i++) {
            String str = strs[i];
            // 用前缀去匹配其余的字符串，如果匹配不成功，就删除前缀字符的的最后一个字符
            while (!str.startsWith(pre)){
                pre = pre.substring(0,pre.length()-1);
            }
        }
        return pre;
    }
}
