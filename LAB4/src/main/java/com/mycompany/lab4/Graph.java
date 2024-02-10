package com.mycompany.lab4;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    private HashMap<Node, List<Node>> adjacencyMap;
    private boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        this.adjacencyMap = new HashMap<>();
    }

    public void insertEdge(Node source, Node target) {
        adjacencyMap.computeIfAbsent(source, k -> new ArrayList<>()).add(target);
        if (!directed) {
            adjacencyMap.computeIfAbsent(target, k -> new ArrayList<>()).add(source);
        }
    }

    public HashMap<Node, List<Node>> getAdjacencyMap() {
        return adjacencyMap;
    }

    public boolean isDirected() {
        return directed;
    }
}
