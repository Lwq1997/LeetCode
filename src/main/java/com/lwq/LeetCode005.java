package com.lwq;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class LeetCode005 {
    public static void main(String[] args) {
        longestPalindrome1("bb");
    }
    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        // 最不济也有一个字符
        int max_len = 1;
        int begin = 0;
        for(int i = 0; i < len - 1; i++){
            for(int j = i + 1; j < len; j++){
                if(max_len < j - i + 1 && isVaildStr(s,i,j)){
                    max_len = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin,begin+max_len);
    }

    private static boolean isVaildStr(String s, int i, int j) {
        while (i <= j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }else {
                i++;
                j--;
            }
        }
        return true;
    }

    public static String longestPalindrome1(String s) {
        int len = s.length();
        if(len < 2){
            return s;
        }

        String res = s.substring(0,1);
        // i作为中心，向两边扩散，i只需要走到len-2的位置即可，因为到了len-1，右边也没有字符了
        for(int i = 0; i < len - 1; i++){
            String str1 = centerSpread(s,i,i);
            String str2 = centerSpread(s,i,i+1);
            String str = str1.length() > str2.length() ? str1:str2;
            if(str.length() > res.length()){
                res = str;
            }
        }
        return res;
    }

    private static String centerSpread(String s, int left, int right) {
        while (left>=0 && right < s.length()){
            if(s.charAt(left) == s.charAt(right)){
                left--;
                right++;
            }else {
                break;
            }
        }
        return s.substring(left+1,right);
    }
}
