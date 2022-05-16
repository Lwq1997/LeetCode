package com.lwq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 */
public class LeetCode20 {
    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
    }

    public boolean isValid2(String s) {
        if (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            return isValid2(s.replace("()", "").replace("[]", "").replace("{}", ""));
        } else {
            return "".equals(s);
        }
    }

    public static boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                //说明是右括号
                if(stack.isEmpty()){
                    //如果这时候栈是空，则肯定不合法
                    return false;
                }else {
                    Character pop = stack.pop();
                    if(!map.get(c).equals(pop)){
                        return false;
                    }
                }
            }else{
                //说明是左括号
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
