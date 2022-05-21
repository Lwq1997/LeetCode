package com.lwq;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 二叉树的所有遍历
 */
public class BinaryTreeIterator {
    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        System.out.print("递归：");
        BinaryTreeIterator.Order(root);
        System.out.println("\n");
        System.out.print("前序递归：");
        BinaryTreeIterator.preOrder(root);
        System.out.println("\n");
        System.out.print("前序非递归：");
        BinaryTreeIterator.preOrderUnRecur(root);
        System.out.println("\n");
        System.out.print("中序递归：");
        BinaryTreeIterator.inOrder(root);
        System.out.println("\n");
        System.out.print("中序非递归：");
        BinaryTreeIterator.inOrderUnRecur(root);
        System.out.println("\n");
        System.out.print("后序递归：");
        BinaryTreeIterator.postOrder(root);
        System.out.println("\n");
        System.out.print("后序非递归：");
        BinaryTreeIterator.postOrderUnRecur(root);
        System.out.println("\n");
        System.out.println("层序遍历：");
        BinaryTreeIterator.level(root);
    }

    //递归序列
    public static void Order(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            Order(root.left);
            System.out.print(root.val + " ");
            Order(root.right);
            System.out.print(root.val + " ");
        }
    }

    //递归前序
    public static void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    //前序遍历
    public static void preOrderUnRecur(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            //前序：头左右：所以进栈的顺序是先右子树再左子树
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                System.out.print(pop.val + " ");
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                if (pop.left != null) {
                    stack.push(pop.left);
                }
            }
        }
    }

    //递归中序
    public static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val + " ");
            inOrder(root.right);
        }
    }

    //中序遍历
    public static void inOrderUnRecur(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                System.out.print(root.val + " ");
                root = root.right;
            }
        }
    }

    //递归后续
    public static void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.val + " ");
        }
    }

    //后序遍历
    public static void postOrderUnRecur(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();
            //后序：左右头，所以进栈1的顺序：头左右，然后使用Stack2来逆序
            stack1.push(root);
            while (!stack1.isEmpty()) {
                TreeNode pop = stack1.pop();
                stack2.push(pop);
                if (pop.left != null) {
                    stack1.push(pop.left);
                }
                if (pop.right != null) {
                    stack1.push(pop.right);
                }
            }
            while (stack2.size() > 0) {
                System.out.print(stack2.pop().val + " ");
            }
        }
    }

    //层序遍历
    public static void level(TreeNode root) {
        int max = Integer.MIN_VALUE;
        if (root != null) {
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int i = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                System.out.print("第" + i + "层为：");
                max = Math.max(max, size);
                for (int j = 0; j < size; j++) {
                    TreeNode pop = queue.removeFirst();
                    System.out.print(pop.val + " ");
                    if (pop.left != null) {
                        queue.add(pop.left);
                    }
                    if (pop.right != null) {
                        queue.add(pop.right);
                    }
                }
                System.out.print("\n");
                i++;
            }
        }
        System.out.println("最大宽度为：" + max);
    }
}