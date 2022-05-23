package com.lwq.graph;

public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
