package com.lwq.trietree;

public class TrieTree {
    class TrieNode {
        public int pass;
        public int end;
        //next[26]，从这个节点出发，可以走到的节点，如果为null，则表示该节点不能走到
        public TrieNode[] next;

        public TrieNode() {
            pass = 0;
            end = 0;
            next = new TrieNode[26];
        }
    }

    public class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         *
         * @param word
         */
        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            TrieNode node = root;
            node.pass++;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new TrieNode();
                }
                node.next[index].pass++;
            }
            node.end++;
        }

        /**
         * 删除word这个单词
         * 沿途pass--
         * 最终end--
         * @param word
         */
        public void delete(String word){
            if(search(word)!=0){
                char[] chars = word.toCharArray();
                TrieNode node = root;
                node.pass--;
                for (int i = 0; i < chars.length; i++) {
                    int index = chars[i] - 'a';
                    if (--node.next[index].pass == 0) {
                        node.next[index] = null;
                        return;
                    }
                    node = node.next[index];
                }
                node.end--;
            }
        }

        /**
         * word这个单词在trie中出现了几次
         *
         * @param word
         * @return
         */
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }
            return node.end;
        }

        /**
         * 有几个是以pre为前缀的单词
         *
         * @param word
         * @return
         */
        public int prefixNumber(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }
            return node.pass;
        }
    }
}
