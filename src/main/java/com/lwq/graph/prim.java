package com.lwq.graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class prim {
    /**
     * 最小生成树：一个连通图可能有多个生成树。当图中的边具有权值时，总会有一个生成树的边的权值之和小于或者等于其它生成树的边的权值之和
     * 我们确定根节点，从这个结点出发。普里姆算法按照以下步骤逐步扩大树中所含顶点的数目，直到遍及连通图的所有顶点。
     *
     * @param graph
     * @return
     */
    public Set<Edge> primMST(Graph graph) {
        HashSet<Edge> res = new HashSet<>();
        //保存所有遍历到的点
        HashSet<Node> set = new HashSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue<Edge>(new EdgeComparator());

        for (Node node : graph.nodes.values()) {
            if (!set.contains(node)) {
                //如果没有包含node，则将node加入set，并且拿到这个node的所有边，放到优先队列中
                set.add(node);
                for (Edge edge : node.edges) {
                    queue.add(edge);
                }
                //此时还不确定需要用那条边作为最短的一条边，所以先弹出一条最小边，看看两边的节点之前是不是已经遍历到了
                while (!queue.isEmpty()) {
                    Edge poll = queue.poll();
                    Node to = poll.to;
                    if (!set.contains(to)) {
                        set.add(to);
                        res.add(poll);
                        for (Edge edge : to.edges) {
                            queue.add(edge);
                        }
                    }
                }
            } else {
                //如果包含node,则不处理，继续
                continue;
            }
        }
        return res;
    }
}
