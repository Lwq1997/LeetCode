package com.lwq.greedyalgorithm;

import java.util.Arrays;

/**
 * N皇后问题
 */
public class NQueens {
    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        long l = System.currentTimeMillis();
        System.out.println(nQueens.method1(14));
        System.out.println("方法一耗时：" + (System.currentTimeMillis() - l));
        l = System.currentTimeMillis();
        System.out.println(nQueens.method2(14));
        System.out.println("方法二耗时：" + (System.currentTimeMillis() - l));
    }


    public int method2(int n) {
        if (n < 2) {
            return n;
        }
        //n如果是8，则limit的低8位全是1
        //n如果是9，则limit的低9位全是1
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    public int method1(int n) {
        if (n < 2) {
            return n;
        }
        //代表了皇后的位置，下标表示行，value表示列
        int[] cols = new int[n];
        return process1(0, cols, n);
    }

    /**
     * @param limit       皇后位置
     * @param colLim      列限制，如果某一位为1，说明当前行的皇后不能放在这一列
     * @param leftDiaLim  左斜线限制，如果某一位为1，说明当前行的皇后不能放在这一列
     * @param rightDiaLim 右斜线限制，如果某一位为1，说明当前行的皇后不能放在这一列
     */
    private int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            //如果所有列都被限制了，说明找到了一种解法
            return 1;
        }
        int res = 0;
        // 三个限制或一下，代表总限制，再取反，代表可以放置的位置
        // 最后和limit与一下，则可以截断左侧没有意义的位置
        // pos代表本行可以放入皇后的位置
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        while (pos != 0) {
            // 取出最右边的一个1
            int mostRightOne = pos & (-pos);
            // 将最右边的1置为0，表示已经放置了皇后
            pos = pos - mostRightOne;
            res += process2(limit,
                    colLim | mostRightOne,
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1
            );
        }

        return res;
    }

    private int process1(int i, int[] cols, int n) {
        if (i == n) {
            //找到一种解法
//            System.out.println(Arrays.toString(cols));
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            //j表示列，i表示行
            //判断是否可以放置皇后
            if (isValid(i, j, cols)) {
                cols[i] = j;
                res += process1(i + 1, cols, n);
            }
        }
        return res;
    }

    /**
     * 判断是否可以放置皇后
     *
     * @param i
     * @param j
     * @param cols
     * @return
     */
    private boolean isValid(int i, int j, int[] cols) {
        for (int k = 0; k < i; k++) {
            // k代表之前行的皇后的位置
            // j == cols[k] 表示列相同
            // Math.abs(i-k) == Math.abs(j-cols[k]) 表示斜线相同
            // 行不可能相同
            if (j == cols[k] || Math.abs(i - k) == Math.abs(j - cols[k])) {
                return false;
            }
        }
        return true;
    }
}
