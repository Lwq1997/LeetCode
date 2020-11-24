package com.lwq;

/**
 * 给出一个完全二叉树，求出该树的节点个数。
 *
 * 说明：
 *
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 *
 * 示例:
 *
 * 输入:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * 输出: 6
 */
public class LeetCode222 {
    /**
     * 暴力递归
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     完全二叉树的高度可以直接通过不断地访问左子树就可以获取
     判断左右子树的高度:
         如果相等说明左子树是满二叉树, 然后进一步判断右子树的节点数(最后一层最后出现的节点必然在右子树中)
         如果不等说明右子树是深度小于左子树的满二叉树, 然后进一步判断左子树的节点数(最后一层最后出现的节点必然在左子树中)
     **/
    public int countNodes1(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if(left == right){
            return countNodes1(root.right) + (1<<left);
        }else{
            return countNodes1(root.left) + (1<<right);
        }
    }

    private int countLevel(TreeNode root) {
        int level = 0;
        while(root != null){
            level++;
            root = root.left;
        }
        return level;
    }
}
