package com.lwq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class LeetCode22 {
    public static void main(String[] args) {
        List<String> res = generateParenthesis(3);
        for (String re : res) {
            System.out.println(re);
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateAll(new char[2 * n], 0, res);
        return res;
    }

    private static void generateAll(char[] current, int pos, List<String> res) {
        if (pos == current.length) {
            if (valid(current)) {
                res.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, res);
            current[pos] = ')';
            generateAll(current, pos + 1, res);
        }
    }

    private static boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return (balance == 0);
    }

    public static List<String> generateParenthesis1(int n) {
        if(n==0){
            return null;
        }
        List<String> res = new ArrayList<>();
        backtrack(res,"",0,0,n);
        return res;
    }

    private static void backtrack(List<String> res, String cur, int leftNum, int rightNum, int max) {
        if(cur.length() == max*2){
            res.add(cur);
            return;
        }
        if(leftNum<rightNum){
            return;
        }
        if (leftNum < max)
            backtrack(res, cur+"(", leftNum+1, rightNum, max);
        if (rightNum < leftNum)
            backtrack(res, cur+")", leftNum, rightNum+1, max);
    }
}
