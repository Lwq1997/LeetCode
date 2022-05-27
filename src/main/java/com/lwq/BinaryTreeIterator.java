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
        System.out.println("\n");
        System.out.println("morris：");
        BinaryTreeIterator.morris(root);
        System.out.println("\n");
        System.out.println("morris先序：");
        BinaryTreeIterator.morrisPre(root);
        System.out.println("\n");
        System.out.println("morris中序：");
        BinaryTreeIterator.morrisIn(root);
        System.out.println("\n");
        System.out.println("morris中序：");
        BinaryTreeIterator.morrisIn2(root);
        System.out.println("\n");
        System.out.println("morris后序：");
        BinaryTreeIterator.morrisPost(root);
    }

    /**
     * morris后续遍历
     * <p>
     * 到达节点的第二次的时候：逆序打印自己左树的右边界
     * 然后逆序打印整个树的右边界
     *
     * @param root
     */
    private static void morrisPost(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(root);
    }

    //以X为头的树，逆序打印这棵树的右边界
    public static void printEdge(TreeNode root) {
        TreeNode tail = reverseEdge(root);
        TreeNode cur = tail;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    private static TreeNode reverseEdge(TreeNode root) {
        TreeNode pre = null;
        TreeNode next = null;
        while (root != null) {
            next = root.right;
            root.right = pre;
            pre = root;
            root = next;
        }
        return pre;
    }

    /**
     * morris中序遍历
     * <p>
     * <p>
     * 只到达一次，则打印
     * 达到两次，打印第二次
     *
     * @param root
     */
    private static void morrisIn2(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    System.out.print(cur.val + " ");
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.val + " ");
            }
            cur = cur.right;
        }
    }

    /**
     * morris中序遍历
     * <p>
     * <p>
     * 只到达一次，则打印
     * 达到两次，打印第二次
     *
     * @param root
     */
    private static void morrisIn(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            //走到这里，可能说明cur只会到达一次，因为有左子树才会重复到达
            //走到这里，可能说明cur到达了两次，但是这里肯定不是第一次，因为第一次在上面，并且continue了
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
    }

    /**
     * morris先序遍历
     * <p>
     * 只到达一次，则打印
     * 达到两次，打印第一次
     *
     * @param root
     */
    private static void morrisPre(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    //走到这里，说明cur会到达两次，这里是第一次
                    System.out.print(cur.val + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                //走到这里，说明cur只会到达一次，因为有左子树才会重复到达
                System.out.print(cur.val + " ");
            }
            cur = cur.right;
        }
    }

    /**
     * morris遍历
     * cur为头结点
     * 如果cur没有左孩子，cur向右移动（cur = cur.right）
     * 如果cur有左孩子，找到cur的左子树的最右节点mostright
     * 如果mostright的右孩子为空，将mostright的右孩子设置为cur，cur向左移动（cur = cur.left）
     * 如果mostright的右孩子不为空，mostright的右孩子指向空，cur向右移动（cur = cur.right）
     * cur为空，遍历停止
     *
     * @param root
     */
    private static void morris(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            System.out.print(cur.val + " ");
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }

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