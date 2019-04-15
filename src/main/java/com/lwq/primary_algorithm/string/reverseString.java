package com.lwq.primary_algorithm.string;


/**
 * @Author: Lwq
 * @Date: 2018/8/25 16:04
 * @Version 1.0
 * @Describe  旋转一个字符串
 */
public class reverseString {
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

    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length-1;
        while(i<j){
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}
