package com.lwq.primary_algorithm.string;

public class countAndSay {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(countAndSay(n));
    }


    private static String countAndSay(int n) {
        String s = "1";
        for (int i = 1; i < n; i++) {
            int count = 1;
            String str = "";
            for (int j = 0; j < s.length(); j++) {
                if (j + 1 < s.length() && s.charAt(j) == s.charAt(j + 1)) {
                    count += 1;
                } else {
                    str += count;
                    str += s.charAt(j);
                    count = 1;
                }
            }
            s = str;
        }
        return s;

    }
}
