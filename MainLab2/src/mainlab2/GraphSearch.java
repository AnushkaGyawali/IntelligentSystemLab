package mainlab2;
import java.util.*;

public class GraphSearch {
    public static void DFS(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        node.setVisited(true);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.getName() + " ");

            LinkedList<Node> neighbors = current.getNeighbors();
            for (Node neighbor : neighbors) {
                if (!neighbor.isVisited()) {
                    stack.push(neighbor);
                    neighbor.setVisited(true);
                }
            }
        }
    }

    public static void BFS(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        node.setVisited(true);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.getName() + " ");

            LinkedList<Node> neighbors = current.getNeighbors();
            for (Node neighbor : neighbors) {
                if (!neighbor.isVisited()) {
                    queue.add(neighbor);
                    neighbor.setVisited(true);
                }
            }
        }
    }

    public static void bestFirstSearch(Node start, Node destination) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.getName() + " ");

            if (current == destination) {
                break;
            }

            LinkedList<Node> neighbors = current.getNeighbors();
            for (Node neighbor : neighbors) {
                if (!neighbor.isVisited() && !queue.contains(neighbor)) {
                    neighbor.setParent(current);
                    queue.add(neighbor);
                }
            }

            current.setVisited(true);
        }
    }
}
