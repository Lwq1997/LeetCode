package com.lwq;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 *
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode316 {
    public static void main(String[] args) {
        removeDuplicateLetters("cbacdcbc");
    }
    /**
     * 建立一个字典。其中 key 为 字符 c，value 为其出现的剩余次数。
     * 从左往右遍历字符串，每次遍历到一个字符，其剩余出现次数 - 1.
     * 对于每一个字符，如果其对应的剩余出现次数大于 1，我们可以选择丢弃也可以选择不丢弃
     * 如果栈中相邻的元素字典序更大，那么我们选择丢弃相邻的栈中的元素。
     *
     *
     *
     * @param s
     * @return
     */
    public static String removeDuplicateLetters(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        Deque<Character> stack = new ArrayDeque<>(s.length());
        boolean[] inStack = new boolean[256];
        char[] chars = s.toCharArray();
        for (char c :chars){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for (char c :chars){
            // 每遍历过一个字符，都将对应的计数减一
            map.put(c,map.get(c)-1);
            if(inStack[c]){
                //如果已经有了，就直接跳过
                continue;
            }
            // 插入之前，和之前的元素比较一下大小
            // 如果字典序比前面的小，pop 前面的元素
            while (!stack.isEmpty() && stack.peek() > c) {
                // 若之后不存在栈顶元素了，则停止 pop
                if (map.get(stack.peek()) == 0) {
                    break;
                }
                // 若之后还有，则可以 pop
                inStack[stack.pop()] = false;
            }
            stack.push(c);
            inStack[c] = true;
        }
        StringBuffer buffer = new StringBuffer();
        while(!stack.isEmpty()){
            buffer.append(stack.pollLast());
        }
        return buffer.toString();
    }
}
