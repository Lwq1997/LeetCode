package com.lwq;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * <p>
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 */
public class LeetCode06 {
    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING", 3));
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1 || s.length() < numRows) {
            return s;
        }
        ArrayList<StringBuilder> lists = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            lists.add(new StringBuilder());
        }

        int currRow = 0;
        boolean goDown = false; // 默认是先不反转

        for (char c : s.toCharArray()) {
            lists.get(currRow).append(c);
            if (currRow == 0 || currRow == numRows - 1) {
                goDown = !goDown;
            }
            currRow += goDown ? 1 : -1;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder list : lists) {
            res.append(list);
        }
        return res.toString();
    }
}
