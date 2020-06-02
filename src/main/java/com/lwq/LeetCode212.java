package com.lwq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LeetCode212 {
    // 定义方向
    int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if(exist(board,word)){
                res.add(word);
            }
        }
        return res;
    }

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col ; j++){
                if(board[i][j] == word.charAt(0)){
                    if(dfs(board,visited,i,j,word,0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, boolean[][] visited, int x, int y, String word, int index) {
        if(index == word.length() -1 ){
            return board[x][y] == word.charAt(index);
        }
        if(board[x][y] == word.charAt(index)){
            visited[x][y] = true;
            for(int i = 0; i < 4 ; i++){
                int nx = x + direction[i][0];
                int ny = y + direction[i][1];
                if (inArea(nx, ny,board) && !visited[nx][ny]) {
                    if(dfs(board,visited,nx,ny,word,index+1)){
                        return true;
                    }
                }
            }
            visited[x][y] = false;
        }
        return false;
    }

    private boolean inArea(int nx, int ny, char[][] board) {
        return nx >= 0 && nx < board.length && ny >=0 && ny < board[0].length;
    }

    public List<String> findWords1(char[][] board, String[] words) {
        //将单词的长度从大到小排序
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }

        });
        Trie trie = new Trie();
        List<String> res = new ArrayList<>();
        for (String word : words) {
            //判断当前单词是否是已经完成的单词的前缀
            if (trie.startsWith(word)) {
                res.add(word);
                continue;
            }
            if (exist(board, word)) {
                res.add(word);
                //加入到前缀树中
                trie.insert(word);
            }
        }
        return res;
    }
    
    class Trie {

        public TrieNode root ;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if(node.next[c-'a'] == null){
                    node.next[c-'a'] = new TrieNode();
                }
                node = node.next[c-'a'];
            }
            node.isEnd = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node = node.next[c-'a'];
                if(node == null){
                    return false;
                }
            }
            return node.isEnd;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                node = node.next[c-'a'];
                if(node == null){
                    return false;
                }
            }
            return true;
        }

        class TrieNode{
            public boolean isEnd;
            public TrieNode[] next;

            public TrieNode() {
                this.isEnd = false;
                next = new TrieNode[26];
            }
        }
    }






}
