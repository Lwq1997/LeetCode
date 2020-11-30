package com.lwq;

/**
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * <p>
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: S = "aab"
 * 输出: "aba"
 * 示例 2:
 * <p>
 * 输入: S = "aaab"
 * 输出: ""
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorganize-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode767 {
    public static void main(String[] args) {
        System.out.println(reorganizeString("aaab"));
    }
    public static String reorganizeString(String S) {
        char[] chars = S.toCharArray();
        // 记录每个字符出现的次数，下标是asc码值-97
        int[] nums = new int[26];
        // 重复字符出现的最大次数
        int max = 0;
        // 重复字符出现的最大边界
        int maxLength = (S.length() + 1) >> 1;
        int alphabet = 0;
        for (char c : chars) {
            nums[c - 'a']++;
            if (nums[c - 'a'] > max) {
                max = nums[c - 'a'];
                alphabet = c - 'a';
                if (max > maxLength) {
                    return "";
                }
            }
        }
        char[] res = new char[S.length()];
        int index = 0;
        // 先把最频繁的字符填充到偶数位
        while (nums[alphabet]-- > 0) {
            res[index] = (char) (alphabet + 'a');
            index += 2;
        }
        //然后再把剩下的字符存储在其他位置上
        for (int i = 0; i < 26; i++) {
            while (nums[i] > 0) {
                if (index >= res.length) {
                    index = 1;
                }
                res[index] = (char) (i + 'a');
                index += 2;
                nums[i]--;
            }
        }
        return new String(res);
    }
}
