package com.lwq;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

/**
 * 二叉树的所有判断
 */
public class IsBinaryTree {
    public static void main(String[] args) {
        //创建二叉树
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        System.out.println("是否是搜索二叉树：" + isBalancedTree(root));
        System.out.println("是否是完全二叉树：" + isCompleteTree(root));
        System.out.println("完全二叉树节点个数：" + countNodes1(root));
        System.out.println("完全二叉树节点个数：" + countNodes(root));
        ReturnType process = process(root);
        System.out.println(process);
        System.out.println("===========");
        TreeNode node = new TreeNode(2);
        node.left = new TreeNode(1);
        node.right = new TreeNode(3);
        System.out.println("是否是搜索二叉树：" + isBalancedTree(node));
        System.out.println("是否是完全二叉树：" + isCompleteTree(node));
        System.out.println("完全二叉树节点个数：" + countNodes1(node));
        System.out.println("完全二叉树节点个数：" + countNodes(node));
        process = process(node);
        System.out.println(process);
    }

    /**
     * 判断是否是二叉树
     *
     * @param root
     * @return
     */
    public static boolean isBinaryTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left != null && root.right != null) {
            return isBinaryTree(root.left) && isBinaryTree(root.right);
        }
        return false;
    }

    /**
     * 判断是否是搜索二叉树，左子树小于当前节点小于右子树
     *
     * @param root
     * @return
     */
    public static boolean isBalancedTree(TreeNode root) {
        if (root != null) {
            long prevalue = Long.MIN_VALUE;
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if (prevalue >= root.val) {
                    return false;
                } else {
                    prevalue = root.val;
                }
//                System.out.print(root.val + " ");
                root = root.right;
            }
        }
        return true;
    }

    /**
     * 判断是否是完全二叉树
     */
    public static boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            boolean flag = false;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                TreeNode left = node.left;
                TreeNode right = node.right;
                if (flag && (left != null || right != null)) {
                    //如果已经是叶子节点，但是左右节点不为空，则不是完全二叉树
                    return false;
                }
                if (left == null && right != null) {
                    //有右节点，没有左节点，则不是完全二叉树
                    return false;
                }
                if (left != null) {
                    queue.add(left);
                }
                if (right != null) {
                    queue.add(right);
                }
                if (left != null && right == null) {
                    //碰到某一个节点他有左节点，但是没有右节点，则这个几点之后的所有节点都是叶子节点
                    flag = true;
                }
            }
        }
        return true;
    }

    /**
     * 完全二叉树节点个数
     */
    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left + right + 1;
    }

    public static int countNodes1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if (left == right) {
            return (1 << left) + countNodes1(root.right);
        } else {
            return (1 << right) + countNodes1(root.left);
        }
    }

    private static int countLevel(TreeNode left) {
        int count = 0;
        while (left != null) {
            count++;
            left = left.left;
        }
        return count;
    }

    /**
     * 是否满二叉树
     *
     * @param node
     * @return
     */
    public static boolean isFullTree(TreeNode node) {
        ReturnType process = process(node);
        return process.nodes == 1 >> process.height - 1;
    }

    private static ReturnType process(TreeNode node) {
        if (node == null) {
            return new ReturnType(true, true, 0, 0, Long.MIN_VALUE, Long.MAX_VALUE);
        }
        ReturnType left = process(node.left);
        ReturnType right = process(node.right);

        int height = Math.max(left.height, right.height) + 1;
        int nodes = left.nodes + right.nodes + 1;
        boolean isComplete = left.isComplete && right.isComplete && Math.abs(left.height - right.height) <= 1;

        boolean isBalanced = true;
        if (left != null && (!left.isBalanced || left.max >= node.val)) {
            isBalanced = false;
        }
        if (right != null && (!right.isBalanced || right.min <= node.val)) {
            isBalanced = false;
        }
        long max = Math.max(right.max, Math.max(node.val, left.max));
        long min = Math.min(right.min, Math.min(node.val, left.min));
        return new ReturnType(isBalanced, isComplete, nodes, height, max, min);
    }

    public static class ReturnType {
        public boolean isBalanced;
        public boolean isComplete;
        public int nodes;
        public int height;
        public long max;
        public long min;

        public boolean isFullTree() {
            System.out.println("node:" + nodes + " height:" + height + " max：" + max + " min:" + min);
            return nodes == ((1 << height) - 1);
        }

        public ReturnType(boolean isBalanced, boolean isComplete, int nodes, int height, long max, long min) {
            this.isBalanced = isBalanced;
            this.isComplete = isComplete;
            this.nodes = nodes;
            this.height = height;
            this.max = max;
            this.min = min;
        }

        @Override
        public String toString() {
            return "ReturnType{" +
                    "isBalanced=" + isBalanced +
                    ", isComplete=" + isComplete +
                    ", nodes=" + nodes +
                    ", height=" + height +
                    ", max=" + max +
                    ", min=" + min +
                    ", isFullTree=" + isFullTree() +
                    '}';
        }
    }
}