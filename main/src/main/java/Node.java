public class Node implements Comparable<Node> {
    private int nodeID;
    private String name;
    private boolean visited;
    private int hscore;
    private Node parent;

    public Node(int nodeID, String name) {
        this.nodeID = nodeID;
        this.name = name;
        this.visited = false;
        this.hscore = 0;
        this.parent = null;
    }

    public int getNodeID() {
        return nodeID;
    }

    public String getName() {
        return name;
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
    public int compareTo(Node otherNode) {
        return Integer.compare(this.hscore, otherNode.hscore);
    }
}
