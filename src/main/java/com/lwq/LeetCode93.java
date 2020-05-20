package com.lwq;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 *
 *  
 *
 * 示例:
 *
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 */
public class LeetCode93 {
    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        ArrayList<String> res = new ArrayList<>();
        // 如果长度不够，不搜索
        if (len < 4 || len > 12) {
            return res;
        }

        // 记录从跟节点到叶子结点的路径，大小是4
        ArrayDeque<String> path = new ArrayDeque<>(4);
        // 记录已经分割出多少个 ip 段；
        int splitTimes = 0;
        // 记录已经遍历到哪个字符了
        int begin = 0;
        dfs(s, len, splitTimes, begin, path, res);
        return res;
    }

    private void dfs(String s, int len, int splitTimes, int begin, ArrayDeque<String> path, ArrayList<String> res) {
        if (begin == len) {
            // 说明遍历到最后了
            if (splitTimes == 4) {
                //说明遍历到了ip的最后 一个 段；
                res.add(String.join(".", path));
            }
            return;
        }
        // 单纯的比较剩余的字符个数是否满足。不满足直接剪
        if (len - begin < (4 - splitTimes) || len - begin > 3 * (4 - splitTimes)) {
            return;
        }
        for (int i = 0; i < 3; i++) {
            // 3指的是每一个ip段最大是3位的数字
            if (begin + i >= len) {
                break;
            }
            int ipSegment = judgeIfIpSegment(s, begin, begin + i);
            if(ipSegment != -1){
                // 在判断是 ip 段的情况下，才去做截取
                path.addLast(ipSegment+"");
                dfs(s,len,splitTimes+1,begin+i+1,path,res);
                path.removeLast();
            }
        }
    }

    /**
     * 判断 s 的子区间 [left, right] 是否能够成为一个 ip 段
     * 判断的同时顺便把类型转了
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    private int judgeIfIpSegment(String s, int left, int right) {
        int size = right-left+1;

        // 大于 1 位的时候，不能以 0 开头
        if(size > 1 && s.charAt(left)=='0'){
            return -1;
        }
        // 转成 int 类型
        int res = 0;
        for (int i = left; i <= right; i++) {
            res = res * 10 + s.charAt(i) - '0';
        }

        if (res > 255) {
            return -1;
        }
        return res;
    }
}
