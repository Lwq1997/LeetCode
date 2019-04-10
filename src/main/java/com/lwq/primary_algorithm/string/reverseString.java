package com.lwq.primary_algorithm.string;


/**
 * @Author: Lwq
 * @Date: 2018/8/25 16:04
 * @Version 1.0
 * @Describe  旋转一个字符串
 */
public class reverseString {
    /**
     * 借用一个辅助string
     * @param s
     * @return
     */
    public String reverseString(String s) {
        StringBuffer buffer = new StringBuffer();
        for(int i = s.length()-1; i>-1;i--){
            char c = s.charAt(i);
            buffer.append(c);
        }
        return buffer.toString();
    }

    /**
     * 另一种方法
     * @param s
     * @return
     */
    public String reverseString1(String s) {
       char[] chars = s.toCharArray();
       int length = s.length();
       char[] helps = new char[length];
       for(int i = 0; i < length;i++){
           helps[i] = chars[length-i-1];
       }
       return new String(helps);
    }
    /**
     *
     */
    public String reverseString2(String s) {
        char[] chars = s.toCharArray();
        int length = s.length();
        for(int i = 0; i < length/2;i++){
            char tmp = chars[i];
            chars[i] = chars[length-1-i];
            chars[length-1-i] = tmp;
        }
        return new String(chars);
    }
}
