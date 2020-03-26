package com.lwq;

import com.lwq.primary_algorithm.Solution;

import java.util.*;

/**
 * 给定一个二维网格 grid。 "." 代表一个空房间， "#" 代表一堵墙， "@" 是起点，（"a", "b", ...）代表钥匙，（"A", "B", ...）代表锁。
 *
 * 我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。
 *
 * 假设 K 为钥匙/锁的个数，且满足 1 <= K <= 6，字母表中的前 K 个字母在网格中都有自己对应的一个小写和一个大写字母。换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。
 *
 * 返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：["@.a.#","###.#","b.A.B"]
 * 输出：8
 * 示例 2：
 *
 * 输入：["@..aA","..B#.","....b"]
 * 输出：6
 *  
 *
 * 提示：
 *
 * 1 <= grid.length <= 30
 * 1 <= grid[0].length <= 30
 * grid[i][j] 只含有 '.', '#', '@', 'a'-'f' 以及 'A'-'F'
 * 钥匙的数目范围是 [1, 6]，每个钥匙都对应一个不同的字母，正好打开一个对应的锁。
 *
 *
 */
public class shortestpathallkeys {

    public static void main(String[] args) {
        String[] arr ={"@.a.#","###.#","b.A.B"};
        int res = new Solution().shortestPathAllKeys(arr);
        System.out.println(res);
    }

    private static int shortestPathAllKeys(String[] arr) {
        int[] deptx ={0,0,1,-1};
        int[] depty ={1,-1,0,0};

        if(arr == null || arr.length == 0 || arr[0].equals("")){
            return -1;
        }

        int m = arr.length;
        int n = arr[0].length();
        int totalKeysNum = 0;
        Queue<Pair> queue = new LinkedList<>();
        Set<Pair> set = new HashSet<>();

        for(int i = 0 ;i < m ; i++){
            for(int j = 0; j < n ; j++){
                char c = arr[i].charAt(j);
                if(c >= 'a' && c <= 'f'){
                    totalKeysNum+=1;
                }
                if(c == '@'){
                    Pair pair = new Pair(i,j,0,0);
                    queue.offer(pair);
                    set.add(pair);
                }
            }
        }

        while (queue.size()!=0){
            Pair cur = queue.poll();
            if(cur.keys == (1<<totalKeysNum) -1){
                return cur.steps;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nextX = cur.x + deptx[i];
                int nextY = cur.y + depty[i];

                if(nextX < 0 || nextY < 0 || nextX >= m || nextY >= n){
                    continue;
                }
                char c = arr[nextX].charAt(nextY);

                Pair nextPair = new Pair(nextX,nextY,cur.steps+1,cur.keys);

                if(c == '#' || (c >= 'A' && c <= 'F' && ((cur.keys >> c - 'A')&1)==0)){
                    continue;
                }

                if (set.contains(nextPair)) {
                    continue;
                }

                if (c >= 'a' && c <= 'f') {
                    nextPair.keys |= (1 << c - 'a');
                }

                queue.offer(nextPair);
                set.add(nextPair);

            }
        }

        return -1;
    }


}


class Pair {
    public int x;
    public int y;
    public int steps;
    public int keys;

    public Pair(int x, int y, int steps, int keys) {
        this.x = x;
        this.y = y;
        this.steps = steps;
        this.keys = keys;
    }

    @Override
    public boolean equals(Object obj) {
        Pair o = (Pair)obj;
        if (this == o) {
            return true;
        }

        return (this.x == o.x && this.y == o.y && this.keys == o.keys);
    }

    @Override
    public int hashCode () {
        return x * 100 + y * 10 + keys;
    }
}
