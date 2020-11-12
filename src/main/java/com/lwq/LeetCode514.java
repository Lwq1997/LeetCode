package com.lwq;

import java.util.ArrayList;
import java.util.List;

/**
 * 电子游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
 * <p>
 * 给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 * <p>
 * 最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
 * <p>
 * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
 * <p>
 * 您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
 * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 * <p>
 * 输入: ring = "godding", key = "gd"
 * 输出: 4
 * 解释:
 * 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
 * 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 * 当然, 我们还需要1步进行拼写。
 * 因此最终的输出是 4。
 * 提示：
 * <p>
 * ring 和 key 的字符串长度取值范围均为 1 至 100；
 * 两个字符串中都只有小写字符，并且均可能存在重复字符；
 * 字符串 key 一定可以由字符串 ring 旋转拼出。
 */
public class LeetCode514 {
    /**
     *
     * @param ring
     * @param key
     * @return
     */
    List<Integer>[] ch_index = new ArrayList[26];
    // 使用数组保存已求出的解，下次递归时直接使用已经求得的解
    int[][] solution;

    public int findRotateSteps(String ring, String key) {
        int n = ring.length();
        int m = key.length();
        solution=new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //-1表示未求解
                solution[i][j]=-1;
            }
        }
        for (int i = 0; i < 26; i++) {
            ch_index[i] = new ArrayList<>();
        }
        // //记录字符出现的位置
        for (int i = 0; i < n; i++) {
            ch_index[ring.charAt(i) - 'a'].add(i);
        }
        return dfs(n, 0, key, 0);
    }

    private int dfs(int n, int now, String key, int index) {
        if (index == key.length()) {
            // 出口，关键字拼写完了
            return 0;
        }
        if(solution[now][index]>0) {
            // 有解直接返回
            return solution[now][index];
        }
        //找到key中每一个字符在ring中可能的出现位置，比如g可能在0，6出现
        List<Integer> list = ch_index[key.charAt(index) - 'a'];
        int min = 0xffff;
        for (Integer one : list) {
            // 往左查找需要的步数：Math.abs(now - one)
            // 往右查找需要的步数：n - Math.abs(now - one)
            // 寻找下一个：dfs(n, one, key, index + 1)
            // 按下按钮的步数：1
            int dis = Math.min(Math.abs(now - one), n - Math.abs(now - one)) + 1 + dfs(n, one, key, index + 1);
            if (dis < min) {
                min = dis;
            }
        }
        // 记录解
        solution[now][index]=min;
        return min;
    }
}
