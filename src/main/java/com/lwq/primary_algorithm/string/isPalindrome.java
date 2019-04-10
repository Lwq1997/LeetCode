package com.lwq.primary_algorithm.string;

/**
 * @Author: Lwq
 * @Date: 2018/8/25 17:07
 * @Version 1.0
 * @Describe  验证回文字符串
 */
public class isPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        char[] chars = s.toCharArray();
        int first = 0;
        int last = chars.length-1;
        while (first<last){
            if(!Character.isDigit(chars[first])&&!Character.isLetter(chars[first])){
                first++;
                continue;
            }
            if(!Character.isDigit(chars[last])&&!Character.isLetter(chars[last])){
                last--;
                continue;
            }
            if(chars[first]!=chars[last]){
                return false;
            }
            first++;
            last--;
        }
        return true;
    }

    public static boolean isPalindrome1(String s) {
        s = s.toLowerCase();
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < s.length();i++){
            if((s.charAt(i)>='a'&&s.charAt(i)<='z' )||(s.charAt(i)>='0'&&s.charAt(i)<='9'))
                result.append(s.charAt(i));
        }
        if(result.length()==0){
            return true;
        }
        int start = 0;
        int end = result.length()-1;
        while (start<end){
            if(result.charAt(start)!=result.charAt(end)){
                return false;
            }else {
                start++;
                end--;
            }
        }
        return true;
    }
}
