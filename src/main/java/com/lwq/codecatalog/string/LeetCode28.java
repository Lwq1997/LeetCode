package com.lwq.codecatalog.string;

/**
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 * 示例 2：
 * <p>
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= haystack.length, needle.length <= 104
 * haystack 和 needle 仅由小写英文字符组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode28 {
    /**
     * 暴力匹配
     *
     * @param origin
     * @param aim
     * @return
     */
    public int strStr1(String origin, String aim) {
        if (origin == null || aim == null) {
            return 0;
        }
        if (origin.length() < aim.length()) {
            return -1;
        }
        char[] originChar = origin.toCharArray();
        char[] aimChar = aim.toCharArray();

        int originIndex = 0;
        int aimIndex = 0;
        while (originIndex < originChar.length && aimIndex < aimChar.length) {
            if (originChar[originIndex] == aimChar[aimIndex]) {
                originIndex++;
                aimIndex++;
            } else {
                originIndex -= (aimIndex - 1);
                aimIndex = 0;
            }
        }
        if (aimIndex == aimChar.length) {
            return originIndex - aimIndex;
        }
        return -1;
    }


    /**
     * KMP算法（https://www.bilibili.com/video/BV13g41157hK/?p=13，看2小时处）
     *
     * @param origin
     * @param aim
     * @return
     */
    public int strStr2(String origin, String aim) {
        if (origin == null || aim == null || origin.equals(aim) || aim.length() == 0) {
            return 0;
        }
        if (origin.length() < aim.length() || origin.length() == 0) {
            return -1;
        }

        int[] next = getNextArray(aim);
        return KmpSearch(origin, aim, next);
    }

    private int KmpSearch(String origin, String aim, int[] next) {
        char[] originChar = origin.toCharArray();
        char[] aimChar = aim.toCharArray();

        int originIndex = 0;
        int aimIndex = 0;
        while (originIndex < originChar.length && aimIndex < aimChar.length) {
            if (originChar[originIndex] == aimChar[aimIndex]) {
                originIndex++;
                aimIndex++;
            } else if (aimIndex == 0) { // next[aimIndex] == -1 间接等于aimIndex == 0，说明前面没有最长公共前缀了，没办法加速了，则原始位置后移一位
                originIndex++;
            } else {
                // 主串索引不动，目标串索引回推到next数组的位置
                aimIndex = next[aimIndex];
            }
        }
        if (aimIndex == aimChar.length) {
            return originIndex - aimIndex;
        }
        return -1;
    }

    private int[] getNextArray(String aim) {
        if (aim.length() == 1) {
            return new int[]{-1};
        }
        int[] next = new int[aim.length()];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;//next数组的下标
        int cn = 0; //代表pos-1位置的最长公共前缀个数，也代表pos-1位置的字符和哪个位置的字符去比较
        while (pos < next.length) {
            if (aim.charAt(pos - 1) == aim.charAt(cn)) {
                // pos-1位置的字符与cn位置的字符相同,cn又是pos-1位置的最长公共前缀个数
                next[pos] = cn + 1;
                pos += 1; //pos位置求完了，需要计算pos+1的位置
                cn += 1;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                //此时说明pos位置的公共前缀个数为0
                next[pos++] = 0;
            }
        }
        return next;
    }
}
