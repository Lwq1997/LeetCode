package com.lwq;

import java.util.ArrayList;

/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 * <p>
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode72 {
    public static void main(String[] args) {
        System.out.println(new LeetCode72().minDistance("horse", "ros"));
    }

    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 初始化二位数据的第一列和第一行，等价于word1为null或者word2为null
        // 初始化第一行为0-X，则代表word2为null，从dp[x][0]需要删除word1的x个字符
        // 初始化第一列为0-X，则代表word1为null，从dp[0][x]需要新增word1的x个字符
        for (int i = 0; i < len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < len2; j++) {
            dp[0][j] = j;
        }
//        // 插入操作
//        for (int i = 1; i <= len1; i++) {
//            dp[i][0] = dp[i - 1][0] + 1;
//        }
//
//        // 删除操作
//        for (int j = 1; j <= len2; j++) {
//            dp[0][j] = dp[0][j - 1] + 1;
//        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                /*
                31. 增，dp[i][j] = dp[i][j - 1] + 1
                32. 删，dp[i][j] = dp[i - 1][j] + 1
                33. 改，dp[i][j] = dp[i - 1][j - 1] + 1
                34. 按顺序计算，当计算 dp[i][j] 时，dp[i - 1][j] ， dp[i][j - 1] ， dp[i - 1][j - 1] 均已经确定了
                 */
                dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    //此时不用+1了，说明不用转换
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
