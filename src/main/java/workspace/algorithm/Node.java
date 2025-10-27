package workspace.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    public String name;
    public List<Edge> edges = new ArrayList<Edge>();

    public Node(String name) {
        this.name = name;
    }

    public void Add(Edge edge){
        edges.add(edge);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
