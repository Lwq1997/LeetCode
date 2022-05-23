package com.lwq.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DFS {
    public void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            ArrayList<Node> nexts = cur.nexts;
            for (Node next : nexts) {
                if (!set.contains(next)) {
                    //这里需要把cur压回去，并且最后break了，表示是要从这条路一直往下走
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
