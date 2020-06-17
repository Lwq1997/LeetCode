package com.lwq;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 */
public class LeetCode36_1 {
    Node pre,head;
    public Node treeToDoublyList(Node root) {
        if(root == null){
            return null;
        }
        // 双向+排序
        dfs(root);
        // 循环
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void dfs(Node cur) {
        if(cur == null){
            return;
        }
        dfs(cur.left);
        if(pre != null){
            //修改双向节点引用
            pre.right = cur;
        }else {
            //代表正在访问链表头节点，记为 head
            head = cur;
        }
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }
}
