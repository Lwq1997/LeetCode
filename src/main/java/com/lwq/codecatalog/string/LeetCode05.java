package com.lwq.codecatalog.string;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= s 的长度 <= 10000
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ti-huan-kong-ge-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode05 {
    /**
     * 使用一个新的对象，复制 str，复制的过程对其判断，是空格则替换，否则直接复制，类似于数组复制
     *
     * @param s
     * @return
     */
    public String replaceSpace01(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 双指针法
     *
     * @param s
     * @return
     */
    public String replaceSpace02(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        //扩充空间，空格数量的2倍
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("  ");//这里是两个空格
            }
        }
        if (sb.length() == 0) {
            //说明s中没有空格
            return s;
        }
        int left = s.length() - 1; //左指针：指向原始字符串最后一个位置
        s += sb.toString();
        int right = s.length() - 1;//右指针：指向扩展字符串的最后一个位置
        char[] chars = s.toCharArray();
        while (left >= 0) {
            if (chars[left] == ' ') {
                chars[right--] = '0';
                chars[right--] = '2';
                chars[right] = '%';
            } else {
                chars[right] = chars[left];
            }
            left--;
            right--;
        }
        return new String(chars);
    }
}
