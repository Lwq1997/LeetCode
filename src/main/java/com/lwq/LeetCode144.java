package com.lwq;

import java.util.*;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 *  示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 *
 * 中序遍历：94
 * 后序遍历：145
 */
public class LeetCode144 {
    public static void main(String[] args) {
        //测试用例
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);
        List<Integer> integers = new LeetCode144().preOrderIteration_01(head);
        System.out.println(integers);
    }

    /**
     * 前序遍历，递归模式
     * @param root
     * @return
     */
    public List<Integer> preOrderIteration_01(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        dfs(res,root);
        return res;
    }

    void dfs(List<Integer> res, TreeNode root) {
        if(root==null) {
            return;
        }
        //按照 打印—左-右的方式遍历
        res.add(root.val);
        dfs(res,root.left);
        dfs(res,root.right);
    }

    /**
     *
     我们使用栈来进行迭代，过程如下：

     初始化栈，并将根节点入栈；
     当栈不为空时：
     弹出栈顶元素 node，并将值添加到结果中；
     如果 node 的右子树非空，将右子树入栈；
     如果 node 的左子树非空，将左子树入栈；
     由于栈是“先进后出”的顺序，所以入栈时先将右子树入栈，这样使得前序遍历结果为 “根->左->右”的顺序。

     作者：z1m
     链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/tu-jie-er-cha-shu-de-si-chong-bian-li-by-z1m/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public List<Integer> preOrderIteration_02(TreeNode head) {
        ArrayList<Integer> res = new ArrayList<>();
        if (head == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            // 	先右再左
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }
}
