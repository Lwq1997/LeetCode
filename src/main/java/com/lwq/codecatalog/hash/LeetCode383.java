package com.lwq.codecatalog.hash;

import java.util.HashMap;

/**
 *给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 *
 * 如果可以，返回 true ；否则返回 false 。
 *
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 * 示例 2：
 *
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 * 示例 3：
 *
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote 和 magazine 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ransom-note
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] ints = new int[26];
        char[] ranChat = ransomNote.toCharArray();
        char[] magaChar = magazine.toCharArray();
        for (char c : magaChar) {
            ints[c-'a']++;
        }
        for (char c : ranChat) {
            ints[c-'a']--;
            if(ints[c-'a'] < 0){
                return false;
            }
        }
        return true;
    }
}
