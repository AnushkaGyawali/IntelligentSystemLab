/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainlab2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private HashMap<Node, LinkedList<Node>> adjacencyMap;
    private boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        adjacencyMap = new HashMap<>();
    }

    public void insertEdge(Node source, Node target) {
        
        LinkedList<Node> neighbors = adjacencyMap.getOrDefault(source, new LinkedList<>());
        neighbors.add(target);
        adjacencyMap.put(source, neighbors);

        if (!directed) {
            LinkedList<Node> targetNeighbors = adjacencyMap.getOrDefault(target, new LinkedList<>());
            targetNeighbors.add(source);
            adjacencyMap.put(target, targetNeighbors);
        }
    }

    public void printEdges(Node node) {
        LinkedList<Node> neighbors = adjacencyMap.get(node);
        if (neighbors != null) {
            for (Node neighbor : neighbors) {
                System.out.println(node.getName() + " -> " + neighbor.getName());
            }
        }
    }

    public void bfs(Node start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        start.setVisited(true);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.getName() + " ");

            LinkedList<Node> neighbors = adjacencyMap.getOrDefault(current, new LinkedList<>());
            for (Node neighbor : neighbors) {
                if (!neighbor.isVisited()) {
                    queue.add(neighbor);
                    neighbor.setVisited(true);
                   
                }
            }
        }
    }

    public void dfs(Node start) {
        start.setVisited(true);
        System.out.print(start.getName() + " ");

        LinkedList<Node> neighbors = adjacencyMap.getOrDefault(start, new LinkedList<>());
        for (Node neighbor : neighbors) {
            if (!neighbor.isVisited()) {
                dfs(neighbor);
            }
        }
    }
}
