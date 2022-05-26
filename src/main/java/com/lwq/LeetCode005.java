package com.lwq;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class LeetCode005 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome1("babad"));
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome2("babad"));
    }

    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        // 最不济也有一个字符
        int max_len = 1;
        int begin = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (max_len < j - i + 1 && isVaildStr(s, i, j)) {
                    max_len = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + max_len);
    }

    private static boolean isVaildStr(String s, int i, int j) {
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    /**
     * 从某一个字符往两表扩张，注意回文串为偶数的情况
     *
     * @param s
     * @return
     */
    public static String longestPalindrome1(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        String res = s.substring(0, 1);
        // i作为中心，向两边扩散，i只需要走到len-2的位置即可，因为到了len-1，右边也没有字符了
        for (int i = 0; i < len - 1; i++) {
            //一个点往外扩，无法解决1221问题
            String str1 = centerSpread(s, i, i);
            //一个片段往外扩，可以解决1221问题
            String str2 = centerSpread(s, i, i + 1);
            String str = str1.length() > str2.length() ? str1 : str2;
            if (str.length() > res.length()) {
                res = str;
            }
        }
        return res;
    }

    /**
     * 该方法是传入一个标识位，返回从这个标志位往两边扩展的回文子串
     * 如果标识位重合，说明扩展的回文是奇数
     * 如果标识为不重合，说明扩展的回文是偶数，可以理解成两个标志位中间的间隙是最中点
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    private static String centerSpread(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return s.substring(left + 1, right);
    }
/*

当前扩展点：i
历史扩展到的最大右边界：R
历史扩展到的最大左边界：L
历史扩展到最大边界时候的中心店：C
当前扩展点基于C的对称点位：j

当前扩展点（i）不在回文最大右边界，则暴力扩展
当前扩展点（i）在回文最大右边界R内
    （1）j的当时的回文整体在L和R内部的，则i的回文也肯定在 L和R内部
    （2）j的当时的回文超出了LR范围（主要是L超出了），则i的回文半径就到R。
    （2）j的当时的回文压线了L，则i的回文肯定是大于等于R的，具体到底是否大于R，需要拿R+1的数据和i的回文左半径-1的数据再比较
 */

    /**
     * manacher算法
     *
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        //先把S处理一下，1221==》#1#2#2#1#
        char[] chars = manacherString(s);
        //回文半价数组，存储每个位置的最大回文半径
        int[] pArr = new int[chars.length];
        int start = -1;
        int C = -1;//中心
        int R = -1;//最大回文右边界再往右一个位置
        int max = Integer.MIN_VALUE;//扩出来的最大值
        for (int i = 0; i != chars.length; i++) {//每个位置都算一次回文半径
            //pArr[2 * C - i]这个是j的最大回文半径
            //可以根据4种情况，确定一个不用验证的最小区域
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < chars.length && i - pArr[i] > -1) {
                //保守起见，所有的逻辑都走一下扩展
                if (chars[i + pArr[i]] == chars[i - pArr[i]]) {
                    //理论上只有情况1和4才会进入这个分支
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            if(max<pArr[i]){
                max = pArr[i];
                start = C;
            }
        }
//        System.out.println(chars.toString());
//        System.out.println(start + " " + " " + max);
//        System.out.println(start + " " + " " + (max - 1) / 2);
        StringBuilder sb = new StringBuilder();
        for(int i = start-max+1; i < start+max;i++){
            sb.append(chars[i]);
        }
//        System.out.println(sb.toString());
//        System.out.println(sb.toString().replace("#",""));
        return sb.toString().replace("#","");
    }

    /**
     * @param s
     * @return
     */
    private static char[] manacherString(String s) {
        char[] chars = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            //交替插入#
            res[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return res;
    }
}
