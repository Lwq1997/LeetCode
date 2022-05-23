package com.lwq.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TopologySort {
    /**
     * 拓扑排序, 对于有向图, 可以求出入度为0的点的拓扑排序
     *
     * @param graph
     * @return
     */
    public List<Node> sortedTopology(Graph graph) {
        LinkedList<Node> inZeroQueue = new LinkedList<>();
        //key: node, value: in degree
        HashMap<Node, Integer> inMap = new HashMap<>();
        HashMap<Integer, Node> nodes = graph.nodes;
        for (Node node : nodes.values()) {
            //遍历整个图，填充inMap和inZeroQueue
            inMap.put(node, node.in);
            if (node.in == 0) {
                inZeroQueue.add(node);
            }
        }
        List<Node> res = new ArrayList<>();
        while (!inZeroQueue.isEmpty()) {
            Node cur = inZeroQueue.poll();
            res.add(cur);
            ArrayList<Node> nexts = cur.nexts;
            for (Node next : nexts) {
                //从图中删除cur的节点，此时和cur响铃的所有节点的入度-1
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    inZeroQueue.add(next);
                }
            }
        }
        return res;
    }
}
