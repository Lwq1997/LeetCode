package com.lwq;

import java.util.LinkedList;
import java.util.List;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 在其中任意一行放置一个皇后，则与此皇后同行，同列，同对角线的都不允许再放其他皇后
 *
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 */
public class LeetCode51_4 {

    static List<List<Integer>> res =new LinkedList<>();
    static List<List<String>> res1 = new LinkedList<>();

    public static void main(String[] args) {
        List<List<String>> lists = solveNQueens(1);
        for (List<String> re : lists) {
            for (String integer : re) {
                System.out.println(integer+" ");
            }
            System.out.println();
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        List<Integer> board = new LinkedList<>();
        backtrack(board,n,0,0,0,0);
        for (List<Integer> re : res) {
            List<String> board1 = new LinkedList<>();
            for (Integer integer : re) {
                String string = Integer.toBinaryString(integer);
                string = toAnotherString(n,string);
                board1.add(string);
            }
            res1.add(board1);
        }
        return res1;
    }

    private static String toAnotherString(int n,String string) {
        char[] chars = string.toCharArray();
        char[] chars1 = new char[n];
        for(int i = chars.length;i < n; i++){
            chars1[i-chars.length] = '0';
        }
        for(int i = n-chars.length;i < n;i++){
            chars1[i] = chars[i-(n-chars.length)];
        }
        StringBuilder st = new StringBuilder();
        for(int i = 0;i < n;i++){
            if(chars1[i]=='0'){
                st.append(".");
            }else {
                st.append("Q");
            }
        }
        return st.toString();
    }

    public static void backtrack(List<Integer> board,int n,int row,int colomn,int pie,int na) {
        if (row == n) {
            List<Integer> board1 = new LinkedList<>();
            for (Integer integer : board) {
                board1.add(integer);
            }
            res.add(board1);
            return;
        }
        // 取出当前行可放置皇后的格子
        int bits = (~(colomn|pie|na)) & ((1 << n)-1);
        //如果bits = 00000 说明已经不能放了
        while(bits > 0) {
            // 每次从当前行可用的格子中取出最右边位为 1 的格子放置皇后
            int p = bits & -bits;
            board.add(p);

            // 紧接着在下一行继续放皇后
            backtrack(board,n,row+1, colomn | p, (pie|p) << 1, (na | p) >> 1);

            // 当前行最右边格子已经选完了，将其置成 0，代表这个格子已遍历过

            board.remove(board.size()-1);
            bits = bits & (bits-1);
        }
    }
}