package workspace.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Kruskal {
    public static Graph kruskal(Graph graph) {
        // Create result MST graph
        Graph mst = new Graph();
        mst.nodes.addAll(graph.nodes);

        // Initialize DSU
        DSU dsu = new DSU();
        for (Node node : graph.nodes) {
            dsu.makeSet(node);
        }

        // Sort edges by weight
        List<Edge> sortedEdges = new ArrayList<>(graph.edges);
        Collections.sort(sortedEdges, Comparator.comparingInt(edge -> edge.weight));

        // Process edges in increasing order of weight
        for (Edge edge : sortedEdges) {
            Node u = edge.start;
            Node v = edge.end;

            // Check if including this edge creates a cycle
            if (dsu.find(u) != dsu.find(v)) {
                mst.edges.add(edge);
                dsu.union(u, v);
            }

            // Stop when we have n-1 edges (for n nodes)
            if (mst.edges.size() == graph.nodes.size() - 1) {
                break;
            }
        }

        return mst;
    }
}
