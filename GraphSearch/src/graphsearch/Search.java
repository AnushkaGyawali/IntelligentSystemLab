/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphsearch;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.NullPointerException.*;
/**
 *
 * @author Dell
 */
import java.util.LinkedList;

public class Search {
      private static HashMap<Node, LinkedList<Node>> adjacencyMap;
    public static void DFS(Node node) {
        if (node == null)
            return;

        node.setVisited(true);
        System.out.println("Visited: " + node.getName());

        LinkedList<Node> neighbors = adjacencyMap.get(node);
        if (neighbors == null)
            return;

        for (Node neighbor : neighbors) {
            if (!neighbor.isVisited()) {
                DFS(neighbor);
            }
        }
       
    }
     public static void BFS(Node node) {
        if (node == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            current.setVisited(true);
            System.out.println("Visited: " + current.getName());

            LinkedList<Node> neighbors = adjacencyMap.get(current);
            if (neighbors == null)
                continue;

            for (Node neighbor : neighbors) {
                if (!neighbor.isVisited()) {
                    queue.add(neighbor);
                }
            }
        }
    }
}

