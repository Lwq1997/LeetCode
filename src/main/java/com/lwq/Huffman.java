package com.lwq;

import java.util.PriorityQueue;

/**
 * 给定一个权重数组，通过哈夫曼编码，返回带权路径长度最小的树
 *
 * 结点的带权路径长度=是指树的根结点到该结点的路径长度*该结点权重
 */
public class Huffman {
    private static void printTree(Node root) {
        if(root == null){
            return;
        }
        System.out.println(root.getWeight());
        printTree(root.getLeftNode());
        printTree(root.getRightNode());
    }

    private Node createHuffman(int[] weights) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Node[] nodes = new Node[weights.length];

        for (int i = 0 ; i < weights.length ; i++) {
            nodes[i] = new Node(weights[i]);
            queue.add(nodes[i]);
        }

        while (queue.size()>1){
            Node left = queue.poll();
            Node right = queue.poll();

            Node parent = new Node(left.getWeight()+right.getWeight(),left,right);
            queue.add(parent);
        }
        return queue.poll();
    }

    class Node implements Comparable<Node>{
        private int weight;
        private Node leftNode;
        private Node rightNode;

        public Node(int weight) {
            this.weight = weight;
        }

        public Node(int weight, Node leftNode, Node rightNode) {
            this.weight = weight;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        @Override
        public int compareTo(Node o) {
            return new Integer(this.weight).compareTo(new Integer(o.weight));
        }
    }
}

