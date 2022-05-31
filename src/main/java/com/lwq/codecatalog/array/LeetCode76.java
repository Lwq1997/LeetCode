package com.lwq.codecatalog.array;

import java.util.HashMap;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 *  
 * <p>
 * 注意：
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3:
 * <p>
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode76 {
    public static void main(String[] args) {
        new LeetCode76().minWindow("ADOBECODEBANC", "ABC");
    }

    public String minWindow(String s, String t) {
        char[] chars = s.toCharArray();
        char[] target = t.toCharArray();
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0, start = 0, valid = 0;
        long len = Long.MAX_VALUE;
        for (char c : target) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        while (right < chars.length) {
            char rightChar = chars[right];

            right++;
            //把右字符移入窗口，并且次数+1
            window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);
            if (need.containsKey(rightChar) && need.get(rightChar).intValue() == window.get(rightChar).intValue()) {
                //只有need数组包含右字符，这时候次数相等，才是有效的。0==0是无效的
                valid++;
            }
            while (valid == need.size()) {
                if (len > right - left) {
                    //这里求最小边界
                    start = left;
                    len = right - left;
                }

                char leftChar = chars[left];
                left++;
                if (need.containsKey(leftChar) && need.get(leftChar).intValue() == window.get(leftChar).intValue()) {
                    valid--;
                }
                window.put(leftChar, window.getOrDefault(leftChar, 0) - 1);
            }
        }
        return len == Long.MAX_VALUE ? "" : s.substring(start, (int) (start + len));
    }
}
