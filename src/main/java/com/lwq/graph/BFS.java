package com.lwq.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class BFS {
    public void bfs(Node node) {
        if (node == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            ArrayList<Node> nexts = cur.nexts;
            for (Node next : nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }
}
