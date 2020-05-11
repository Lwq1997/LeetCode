package com.lwq;

/**
 *请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 */
public class LeetCode05 {
    public static void main(String[] args) {
        System.out.println(replaceSpace("hello world"));
    }
    public static String replaceSpace(String s) {
        int length = s.length();
        char[] chars = new char[length * 3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                chars[size++] = '%';
                chars[size++] = '2';
                chars[size++] = '0';
            } else {
                chars[size++] = c;
            }
        }
        return new String(chars,0,size);
    }
}
