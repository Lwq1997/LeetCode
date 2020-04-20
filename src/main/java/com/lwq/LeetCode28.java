package com.lwq;

/**
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class LeetCode28 {
    public static void main(String[] args) {

    }

    /**
     * sunday算法
     *
     * @param origin 目标串
     * @param aim    模式串
     * @return
     */
    public static int strStr(String origin, String aim) {
        if (origin == null || aim == null) {
            return 0;
        }
        if (origin.length() < aim.length()) {
            return -1;
        }

        //目标串的匹配索引
        int originIndex  = 0;

        //模式串的匹配索引
        int aimIndex  = 0;

        // 成功匹配完终止条件：所有aim均成功匹配
        while (aimIndex < aim.length()){
            // 针对origin匹配完，但aim未匹配完情况处理 如 mississippi sippia
            if(originIndex > origin.length() -1){
                return -1;
            }
            if(origin.charAt(originIndex) == aim.charAt(aimIndex)){
                // 匹配则index均加1
                originIndex++;
                aimIndex++;
            }else {
                int nextIndex = originIndex - aimIndex + aim.length();
                //判断下一个目标字符是否存在。
                if(nextIndex < origin.length()){
                    // 判断目标字符在模式串中匹配到，返回最后一个匹配的index
                    int step = aim.lastIndexOf(origin.charAt(nextIndex));
                    if(step == -1){
                        // 不存在的话，设置到目标字符的下一个元素
                        originIndex = nextIndex + 1;
                    } else{
                        // 存在的话，移动对应的数字
                        // step是aim中的索引（从前往后看的索引）
                        originIndex = nextIndex - step;
                    }
                    //模式串总是从第一个开始匹配
                    aimIndex = 0;
                }else {
                    return -1;
                }
            }
        }

        return originIndex - aimIndex;
    }
}
