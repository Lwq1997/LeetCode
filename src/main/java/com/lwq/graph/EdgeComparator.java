package com.lwq.graph;

import java.util.Comparator;

/**
 * 边权重比较器,权重小的排在前面
 */
public class EdgeComparator implements Comparator<Edge> {
    @Override
    public int compare(Edge o1, Edge o2) {
        return o1.weight - o2.weight;
    }
}
