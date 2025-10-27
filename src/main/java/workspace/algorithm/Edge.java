package workspace.algorithm;

public class Edge {

    public Node start;
    public Node end;
    public int weight;

    public Edge(Node start, Node end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "{ \"from\": \"" + start + "\", \"to\": " + end + "\", \"weight\": " + weight + " }";
    }
}
