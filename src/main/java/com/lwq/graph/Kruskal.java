package com.lwq.graph;

import java.util.*;

public class Kruskal {
    public class MySet {
        //表示当前节点属于哪个集合
        public HashMap<Node, List<Node>> setMap = new HashMap<>();

        public MySet(List<Node> nodes) {
            for (Node node : nodes) {
                //每个节点自成一个集合
                ArrayList<Node> list = new ArrayList<>();
                list.add(node);
                setMap.put(node, list);
            }
        }

        public boolean isSameList(Node from, Node to) {
            return setMap.get(from) == setMap.get(to);
        }

        public void union(Node from, Node to) {
            //把to节点对应集合中的所有节点，挪到from中
            List<Node> fromNodes = setMap.get(from);
            List<Node> toNodes = setMap.get(to);
            for (Node toNode : toNodes) {
                fromNodes.add(toNode);
                setMap.put(toNode, fromNodes);
            }
        }
    }

    /**
     * 构造一个边集合，这些变量都是为了构造最小生成树
     * 从一个图中删除某些边，导致最后的图还是是一个连通图，并且边的权重之和最小
     * <p>
     * 先构造一个只含 n 个顶点、而边集为空的子图，把子图中各个顶点看成各棵树上的根结点，
     * 之后从网的边集 E 中选取一条权值最小的边，若该条边的两个顶点分属不同的树，则将其加入子图，即把两棵树合成一棵树，
     * 反之，若该条边的两个顶点已落在同一棵树上，则不可取，而应该取下一条权值最小的边再试之。
     * 依次类推，直到森林中只有一棵树，也即子图中含有 n-1 条边为止。
     *
     * @param graph
     * @return
     */
    public Set<Edge> kruskalMST(Graph graph) {
        HashMap<Integer, Node> nodes = graph.nodes;
        Collection<Node> values = nodes.values();
        MySet mySet = new MySet((List<Node>) graph.nodes.values());
        //把边按照从小到大，塞入一个最小堆中
        PriorityQueue<Edge> queue = new PriorityQueue<Edge>(new EdgeComparator());
        graph.edges.forEach(edge -> queue.add(edge));
        Set<Edge> edges = new HashSet<>();
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            Node from = edge.from;
            Node to = edge.to;
            if (!mySet.isSameList(from, to)) {
                //如果这条边的两边节点，不是一个集合，则union下，如果是一个集合，这条边则不能进行任何操作，否则会让图循环
                mySet.union(from, to);
                edges.add(edge);
            }
        }
        return edges;
    }
}
