package com.mycompany.lab4;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        // Create nodes to represent the cities
        Node n1 = new Node(1, "Arad");
        Node n2 = new Node(2, "Zerind");
        Node n3 = new Node(3, "Timisoara");
        Node n4 = new Node(4, "Oradea");
        Node n5 = new Node(5, "Lugoj");
        Node n6 = new Node(6, "Mehadia");
        Node n7 = new Node(7, "Drobeta");
        Node n8 = new Node(8, "Sibiu");
        Node n9 = new Node(9, "Rimnicu Vilcea");
        Node n10 = new Node(10, "Craiova");
        Node n11 = new Node(11, "Fagaras");
        Node n12 = new Node(12, "Pitesti");
        Node n13 = new Node(13, "Bucharest");
        Node n14 = new Node(14, "Giurgiu");
        Node n15 = new Node(15, "Urziceni");
        Node n16 = new Node(16, "Hirsova");
        Node n17 = new Node(17, "Eforie");
        
        // Create a graph based on the Romania map
        Graph graph = new Graph(false); // False signifying the graph is undirected
        
        // Add edges between cities if there is a road connecting them
        graph.insertEdge(n1, n2);
        graph.insertEdge(n1, n3);
        graph.insertEdge(n2, n4);
        graph.insertEdge(n3, n5);
        graph.insertEdge(n5, n6);
        graph.insertEdge(n6, n7);
        graph.insertEdge(n7, n10);
        graph.insertEdge(n8, n1);
        graph.insertEdge(n8, n9);
        graph.insertEdge(n8, n11);
        graph.insertEdge(n9, n10);
        graph.insertEdge(n10, n12);
        graph.insertEdge(n11, n13);
        graph.insertEdge(n12, n13);
        graph.insertEdge(n13, n14);
        graph.insertEdge(n13, n15);
        graph.insertEdge(n15, n16);
        graph.insertEdge(n16, n17);
        
        // Run BFS and DFS using three different start nodes and present the results
        System.out.println("BFS starting from Arad:");
        bfs(graph, n1);
        System.out.println("\nDFS starting from Arad:");
        dfs(graph, n1);
        
        // Perform Best-First Search for different start nodes
        System.out.println("\nBest-First Search starting from Arad:");
        bestFirstSearch(graph, n1, n13); // Pass different start and destination nodes
    }

    // BFS implementation
    public static void bfs(Graph graph, Node start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        start.setVisited(true);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.getName() + " -> ");

            for (Node neighbor : graph.getAdjacencyMap().getOrDefault(current, new LinkedList<>())) {
                if (!neighbor.isVisited()) {
                    queue.add(neighbor);
                    neighbor.setVisited(true);
                }
            }
        }
    }

    // DFS implementation
    public static void dfs(Graph graph, Node start) {
        System.out.print(start.getName() + " -> ");
        start.setVisited(true);

        for (Node neighbor : graph.getAdjacencyMap().getOrDefault(start, new LinkedList<>())) {
            if (!neighbor.isVisited()) {
                dfs(graph, neighbor);
            }
        }
    }

    // Best-First Search implementation
    public static void bestFirstSearch(Graph graph, Node start, Node destination) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();
            System.out.print(current.getName() + " -> ");

            if (current == destination) {
                printPath(destination);
                break;
            }

            current.setVisited(true);

            for (Node neighbor : graph.getAdjacencyMap().getOrDefault(current, new LinkedList<>())) {
                if (!neighbor.isVisited()) {
                    neighbor.setParent(current);
                    priorityQueue.add(neighbor);
                }
            }
        }
    }

    // Method to print the best path by following the parent chain
    public static void printPath(Node node) {
        if (node != null) {
            printPath(node.getParent());
            System.out.print(node.getName() + " -> ");
        }
    }
}
