/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package graphsearch;

/**
 *
 * @author Dell
 */
public class GraphSearch {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // Create nodes to represent the cities
        Node arad = new Node(1, "Arad");
        Node zerind = new Node(2, "Zerind");
        Node timisoara = new Node(3, "Timisoara");
        Node sibiu = new Node(4, "Sibiu");
        Node fagaras = new Node(5, "Fagaras");
        Node rimnicuVilcea = new Node(6, "Rimnicu Vilcea");
        Node pitesti = new Node(7, "Pitesti");
        Node craiova = new Node(8, "Craiova");
        Node bucharest = new Node(9, "Bucharest");

        // Create a graph object
        Graph graph = new Graph(false); // false signifying the graph is undirected

        // Create edges between cities if there is a road connecting them
        graph.insertEdge(arad, zerind);
        graph.insertEdge(arad, sibiu);
        graph.insertEdge(arad, timisoara);
        graph.insertEdge(zerind, sibiu);
        graph.insertEdge(sibiu, fagaras);
        graph.insertEdge(sibiu, rimnicuVilcea);
        graph.insertEdge(rimnicuVilcea, pitesti);
        graph.insertEdge(pitesti, craiova);
        graph.insertEdge(pitesti, bucharest);
        graph.insertEdge(fagaras, bucharest);
        graph.insertEdge(bucharest, craiova);

        // Print the edges for a node
        graph.printEdges(arad);
        graph.printEdges(sibiu);
        
        
        // Perform DFS with different start nodes
        Search.DFS(arad);
        System.out.println("------------------------------");
        Search.DFS(zerind);
        System.out.println("------------------------------");
        Search.DFS(bucharest);
        System.out.println("------------------------------");

        // Perform BFS with different start nodes
        Search.BFS(arad);
        System.out.println("------------------------------");
        Search.BFS(zerind);
        System.out.println("------------------------------");
        Search.BFS(bucharest);
        System.out.println("------------------------------");
    }
}

