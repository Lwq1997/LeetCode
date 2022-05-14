package com.lwq;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 *
 * 假设二叉树中至少有一个节点。
 *
 *  
 *
 * 示例 1:
 *
 *
 *
 * 输入: root = [2,1,3]
 * 输出: 1
 * 示例 2:
 *
 *
 *
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 * 输出: 7
 *  
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [1,104]
 * -231 <= Node.val <= 231 - 1 
 */
public class LeetCode513 {
    public static void main(String[] args) {
        LeetCode513 leetCode513 = new LeetCode513();
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        System.out.println(leetCode513.findBottomLeftValue(root));
    }
    public int findBottomLeftValue(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<>();
        TreeNode res = root;
        list.add(root);
        while (!list.isEmpty()){
            res = list.remove(0);
            if(res.right!=null){
                list.add(res.right);
            } if(res.left!=null){
                list.add(res.left);
            }
        }
        return res.val;
    }
}
