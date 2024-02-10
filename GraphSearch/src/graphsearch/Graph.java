/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphsearch;

import java.util.HashMap;
import java.util.LinkedList;

import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
    private HashMap<Node, LinkedList<Node>> adjacencyMap;
    private boolean directed;

    public Graph(boolean directed) {
        this.adjacencyMap = new HashMap<>();
        this.directed = directed;
    }

    public void insertEdge(Node source, Node target) {
        LinkedList<Node> neighbors = adjacencyMap.getOrDefault(source, new LinkedList<>());
        neighbors.add(target);
        adjacencyMap.put(source, neighbors);

        if (!directed) {
            neighbors = adjacencyMap.getOrDefault(target, new LinkedList<>());
            neighbors.add(source);
            adjacencyMap.put(target, neighbors);
        }
    }

    public HashMap<Node, LinkedList<Node>> getAdjacencyMap() {
        return adjacencyMap;
    }

    public boolean isDirected() {
        return directed;
    }

    public void printEdges(Node node) {
        LinkedList<Node> neighbors = adjacencyMap.get(node);
        if (neighbors != null) {
            System.out.print("Edges for " + node.getName() + ": ");
            for (Node neighbor : neighbors) {
                System.out.print(neighbor.getName() + " ");
            }
            System.out.println();
        }
    }
}

