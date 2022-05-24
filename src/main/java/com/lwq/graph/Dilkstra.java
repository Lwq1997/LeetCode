package com.lwq.graph;

import java.util.HashMap;
import java.util.HashSet;

public class Dilkstra {
    public HashMap<Node, Integer> dilkstra(Graph graph, Node start) {
        //key：节点，value：节点到起点的距离
        HashMap<Node, Integer> res = new HashMap<>();
        //已经求过距离过的节点,不再求距离
        HashSet<Node> selectSet = new HashSet<>();
        res.put(start, 0);
        Node minNode = getMinDisAndNoSelect(graph, res, selectSet);
        while (minNode != null) {
            Integer distance = res.get(minNode);
            for (Edge edge : minNode.edges) {
                Node to = edge.to;
                if (!res.containsKey(to)) {
                    res.put(to, distance + edge.weight);
                } else {
                    res.put(to, Math.min(res.get(to), distance + edge.weight));
                }
            }
            selectSet.add(minNode);
            minNode = getMinDisAndNoSelect(graph, res, selectSet);
        }
        return res;
    }

    /**
     * 获取最小距离的节点，并且不在selectSet中
     *
     * @param graph
     * @param res
     * @param selectSet
     * @return
     */
    private Node getMinDisAndNoSelect(Graph graph, HashMap<Node, Integer> res, HashSet<Node> selectSet) {
        Node minNode = null;
        int minDis = Integer.MAX_VALUE;
        for (Node node : res.keySet()) {
            if (!selectSet.contains(node) && minDis > res.get(node)) {
                minDis = res.get(node);
                minNode = node;
            }
        }
        return minNode;
    }
}
