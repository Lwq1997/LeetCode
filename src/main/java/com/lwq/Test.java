package com.lwq;

import java.util.List;

public class Test {
    public TreeNode res ;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root,p,q);
        return res;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return false;
        }
        Boolean left_son = dfs(root.left,p,q);
        Boolean right_son = dfs(root.right,p,q);

        if((left_son && right_son) || ((left_son||right_son) && (root.val==p.val || root.val==q.val)) ){
            res = root;
        }

        // 只要当前节点的左子树中包含过p，q的任意一个，就是true
        // 只要当前节点的右子树中包含过p，q的任意一个，就是true
        // 只要当前节点为p，q的任意一个，就是true
        return left_son || right_son || root.val==p.val || root.val == q.val;
    }
}
