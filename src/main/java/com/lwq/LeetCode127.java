package com.lwq;

import java.util.*;

/**
 * 127. 单词接龙
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * <p>
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * <p>
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出: 5
 * <p>
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * 示例 2:
 * <p>
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: 0
 * <p>
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode127 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");
        System.out.println(ladderLength2("hit", "cog", list));
    }

    /**
     * 简单来说就是求图的两点最短路径，每个单词是一个点，只有相差一个字符的点之间才有路径，路径权值全部为1.
     * 已知目标顶点的情况下，可以分别从起点和目标顶点（终点）执行广度优先遍历，直到遍历的部分有交集，这是双向广度优先遍历的思想。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        // visit中是能够达到的顶点
        HashSet<String> visited = new HashSet<>();
        // list中维护路径
        Queue<String> list = new LinkedList<>();
        list.offer(beginWord);
        visited.add(beginWord);

        int step = 0;
        while (!list.isEmpty()) {
            int size = list.size();
            ++step;
            // 复杂度
            for (int i = 0; i < size; i++) {
                String start = list.poll();
                for (String s : wordList) {
                    // 已经遍历的不再重复遍历
                    if (visited.contains(s)) {
                        continue;
                    }
                    // 不能转换的直接跳过
                    if (!canConvert(start, s)) {
                        continue;
                    }
                    // 用于调试
                    // System.out.println(count + ": " + start + "->" + s);
                    // 可以转换，并且能转换成 endWord，则返回 count
                    if (s.equals(endWord)) {
                        return step + 1;
                    }
                    // 说明S和start中间是有一条边的。保存访问过的单词，同时把单词放进队列，用于下一层的访问
                    visited.add(s);
                    list.offer(s);
                }
            }
        }
        return 0;
    }

    /**
     * 把ladderLength1中的hashset换成数组，减少存储，直接使用下标访问会更快
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        // visited 修改为 boolean 数组
        // visited 修改为 boolean 数组
        boolean[] visited = new boolean[wordList.size()];
        int idx = wordList.indexOf(beginWord);
        if (idx != -1) {
            visited[idx] = true;
        }
        // list中维护路径
        Queue<String> list = new LinkedList<>();
        list.offer(beginWord);


        int step = 0;
        while (!list.isEmpty()) {
            int size = list.size();
            ++step;
            while (size-- > 0) {
                String start = list.poll();
                for (int i = 0; i < wordList.size(); ++i) {
                    // 通过 index 判断是否已经访问
                    if (visited[i]) {
                        continue;
                    }
                    String s = wordList.get(i);
                    if (!canConvert(start, s)) {
                        continue;
                    }
                    if (s.equals(endWord)) {
                        return step + 1;
                    }
                    visited[i] = true;
                    list.offer(s);
                }
            }
        }
        return 0;
    }

    /**
     * 双端队列
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength03(String beginWord, String endWord, List<String> wordList) {
        int end = wordList.indexOf(endWord);
        if (end == -1) {
            return 0;
        }
        wordList.add(beginWord);
        int start = wordList.size() - 1;
        // 用于 BFS 遍历的队列
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        // 用于保存已访问的单词
        Set<Integer> visited1 = new HashSet<>();
        Set<Integer> visited2 = new HashSet<>();
        queue1.offer(start);
        queue2.offer(end);
        visited1.add(start);
        visited2.add(end);
        int count1 = 0;
        int count2 = 0;
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            count1++;
            int size1 = queue1.size();
            while (size1-- > 0) {
                String s = wordList.get(queue1.poll());
                for (int i = 0; i < wordList.size(); ++i) {
                    if (visited1.contains(i)) {
                        continue;
                    }
                    if (!canConvert(s, wordList.get(i))) {
                        continue;
                    }
                    if (visited2.contains(i)) {
                        return count1 + count2 + 1;
                    }
                    visited1.add(i);
                    queue1.offer(i);
                }
            }
            count2++;
            int size2 = queue2.size();
            while (size2-- > 0) {
                String s = wordList.get(queue2.poll());
                for (int i = 0; i < wordList.size(); ++i) {
                    if (visited2.contains(i)) {
                        continue;
                    }
                    if (!canConvert(s, wordList.get(i))) {
                        continue;
                    }
                    if (visited1.contains(i)) {
                        return count1 + count2 + 1;
                    }
                    visited2.add(i);
                    queue2.offer(i);
                }
            }
        }
        return 0;
    }

    /**
     * 主要的优化点就是每次遍历一层时，从节点更少的一端遍历
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int end = wordList.indexOf(endWord);
        if (end == -1) {
            return 0;
        }
        wordList.add(beginWord);
        int start = wordList.size() - 1;
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        Set<Integer> visited1 = new HashSet<>();
        Set<Integer> visited2 = new HashSet<>();
        queue1.offer(start);
        queue2.offer(end);
        visited1.add(start);
        visited2.add(end);
        int count = 0;
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            count++;
            if (queue1.size() > queue2.size()) {
                Queue<Integer> tmp = queue1;
                queue1 = queue2;
                queue2 = tmp;
                Set<Integer> t = visited1;
                visited1 = visited2;
                visited2 = t;
            }
            int size1 = queue1.size();
            while (size1-- > 0) {
                String s = wordList.get(queue1.poll());
                for (int i = 0; i < wordList.size(); ++i) {
                    if (visited1.contains(i)) {
                        continue;
                    }
                    if (!canConvert(s, wordList.get(i))) {
                        continue;
                    }
                    if (visited2.contains(i)) {
                        return count + 1;
                    }
                    visited1.add(i);
                    queue1.offer(i);
                }
            }
        }
        return 0;
    }


    private static boolean canConvert(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < s1.length(); ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                ++count;
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }

    public int ladderLength_final(String beginWord, String endWord, List<String> wordList) {
        int end = wordList.indexOf(endWord);
        if (end == -1) {
            return 0;
        }
        wordList.add(beginWord);

        // 从两端 BFS 遍历要用的队列
        Queue<String> queue1 = new LinkedList<>();
        Queue<String> queue2 = new LinkedList<>();
        // 两端已经遍历过的节点
        Set<String> visited1 = new HashSet<>();
        Set<String> visited2 = new HashSet<>();
        queue1.offer(beginWord);
        queue2.offer(endWord);
        visited1.add(beginWord);
        visited2.add(endWord);

        int count = 0;
        Set<String> allWordSet = new HashSet<>(wordList);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            count++;
            //每次遍历一层时，从节点更少的一端遍历
            if (queue1.size() > queue2.size()) {
                Queue<String> tmp = queue1;
                queue1 = queue2;
                queue2 = tmp;
                Set<String> t = visited1;
                visited1 = visited2;
                visited2 = t;
            }
            int size1 = queue1.size();
            while (size1-- > 0) {
                String s = queue1.poll();
                char[] chars = s.toCharArray();
                for (int j = 0; j < s.length(); ++j) {
                    // 保存第j位的原始字符
                    char c0 = chars[j];
                    for (char c = 'a'; c <= 'z'; ++c) {
                        chars[j] = c;
                        String newString = new String(chars);
                        // 已经访问过了，跳过
                        if (visited1.contains(newString)) {
                            continue;
                        }
                        // 两端遍历相遇，结束遍历，返回 count
                        if (visited2.contains(newString)) {
                            return count + 1;
                        }
                        // 如果单词在列表中存在，将其添加到队列，并标记为已访问
                        if (allWordSet.contains(newString)) {
                            queue1.offer(newString);
                            visited1.add(newString);
                        }
                    }
                    // 恢复第j位的原始字符
                    chars[j] = c0;
                }
            }
        }
        return 0;
    }


}

