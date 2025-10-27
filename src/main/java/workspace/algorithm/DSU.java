package workspace.algorithm;

import java.util.HashMap;
import java.util.Map;

public class DSU {
    private Map<Node, Node> parent = new HashMap<>();
    private Map<Node, Integer> rank = new HashMap<>();

    public void makeSet(Node node) {
        parent.put(node, node);
        rank.put(node, 0);
    }

    public Node find(Node node) {
        if (parent.get(node) != node) {
            parent.put(node, find(parent.get(node)));
        }
        return parent.get(node);
    }

    public void union(Node x, Node y) {
        Node rootX = find(x);
        Node rootY = find(y);

        if (rootX != rootY) {

            if (rank.get(rootX) < rank.get(rootY)) {
                parent.put(rootX, rootY);
            } else if (rank.get(rootX) > rank.get(rootY)) {
                parent.put(rootY, rootX);
            } else {
                parent.put(rootY, rootX);
                rank.put(rootX, rank.get(rootX) + 1);
            }
        }
    }
}
