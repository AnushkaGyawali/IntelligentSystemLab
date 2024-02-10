/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mainlab2;

/**
 *
 * @author Dell
 */
public class MainLab2 {

    public static void main(String[] args) {
        Graph graph = new Graph(false);

        // Create nodes
        Node n1 = new Node(1, "Arad", 366);
        Node n2 = new Node(2, "Zerind", 374);
        Node n3 = new Node(3, "Timisoara", 329);
        Node n4 = new Node(4, "oradea", 380);
        // Create edges
        graph.insertEdge(n1, n2);
        graph.insertEdge(n1, n3);
        graph.insertEdge(n2, n4);
        // Print edges for a node
        graph.printEdges(n1);
        System.out.println();

        // Perform DFS
        System.out.println("DFS:");
//        graph.dfs(n1);
        System.out.println();

        // Perform BFS
        System.out.println("BFS:");
        graph.bfs(n1);
         System.out.println();
    }
}




