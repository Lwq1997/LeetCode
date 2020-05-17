package com.lwq;

public class LeetCode236 {
    public TreeNode res;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root,p,q);
        return res;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return false;
        }
        boolean left_son = dfs(root.left,p,q);
        boolean right_son = dfs(root.right,p,q);

        // 如果当前节点的左右子树各包含p，q中的某一个，当前节点就是最近的祖先
        // 如果当前节点是p，切左右子树任意一个包含q，当前节点就是最近的祖先
        // 如果当前节点是q，切左右子树任意一个包含p，当前节点就是最近的祖先
        if((left_son && right_son) || ((root.val == p.val || root.val == q.val) && (left_son || right_son))){
            res = root;
        }

        // 只要当前节点的左子树中包含过p，q的任意一个，就是true
        // 只要当前节点的右子树中包含过p，q的任意一个，就是true
        // 只要当前节点为p，q的任意一个，就是true
        return left_son || right_son || (root.val == p.val || root.val == q.val);
    }
}
