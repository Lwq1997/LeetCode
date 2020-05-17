package com.lwq;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0 个字符。）
 *
 * 给定待查询列表 queries，和模式串 pattern，返回由布尔值组成的答案列表 answer。只有在待查项 queries[i] 与模式串 pattern 匹配时， answer[i] 才为 true，否则为 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
 * 输出：[true,false,true,true,false]
 * 示例：
 * "FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
 * "FootBall" 可以这样生成："F" + "oot" + "B" + "all".
 * "FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer".
 * 示例 2：
 *
 * 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
 * 输出：[true,false,true,false,false]
 * 解释：
 * "FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
 * "FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll".
 * 示例 3：
 *
 * 输出：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
 * 输入：[false,true,false,false,false]
 * 解释：
 * "FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est".
 *  
 *
 * 提示：
 *
 * 1 <= queries.length <= 100
 * 1 <= queries[i].length <= 100
 * 1 <= pattern.length <= 100
 * 所有字符串都仅由大写和小写英文字母组成。
 */
public class LeetCode1023 {
    public static void main(String[] args) {
        List<Boolean> res = camelMatch(new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"}, "FB");
        for (Boolean re : res) {
            System.out.println(re);
        }
    }

    public static List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new ArrayList<>();
        for (String query : queries) {
            res.add(isMatch(query, pattern));
        }
        return res;
    }

    private static Boolean isMatch(String query, String pattern) {
        int index1 = 0;
        int index2 = 0;
        int n1 = query.length();
        int n2 = pattern.length();

        while (index1 < n1 && index2 < n2) {
            int c1 = query.charAt(index1);
            int c2 = pattern.charAt(index2);
            if (c1 == c2) {
                index1++;
                index2++;
            } else {
                if (c1 >= 'A' && c1 <= 'Z') {
                    return false;
                }
                index1++;
            }
        }
        if (index2 != n2) {
            // 说明字符串匹配完了，模式串还没有匹配完
            return false;
        }
        while (index1 < n1) {
            // 说明模式串匹配完了，需要检验剩下字符串的字符是否全是小写
            char c1 = query.charAt(index1++);
            if (c1 >= 'A' && c1 <= 'Z') {
                return false;
            }
        }
        return true;
    }
}
