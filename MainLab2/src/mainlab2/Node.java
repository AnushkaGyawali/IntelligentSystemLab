package mainlab2;
public class Node implements Comparable<Node> {
    private int nodeID;
    private String name;
    private boolean visited;
    private int hscore;
    private Node parent;

    public Node(int nodeID, String name, int hscore) {
        this.nodeID = nodeID;
        this.name = name;
        this.visited = false;
        this.hscore = hscore;
        this.parent = null;
    }

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getHscore() {
        return hscore;
    }

    public void setHscore(int hscore) {
        this.hscore = hscore;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.hscore, other.hscore);
    }
}
