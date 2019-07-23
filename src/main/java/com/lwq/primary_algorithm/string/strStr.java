package com.lwq.primary_algorithm.string;

public class strStr {
    public static void main(String[] args) {
        String s = "abcde";
        String k = "cd";
        System.out.println(strStr(s, k));
    }

    private static int strStr(String s, String k) {
        if (k == null) {
            return 0;
        }
        int l = k.length();
        for (int i = 0; i < s.length() - l + 1; i++) {
            if (s.substring(i, i + l).equals(k)) {
                return i;
            }
        }
        return -1;
    }
}
