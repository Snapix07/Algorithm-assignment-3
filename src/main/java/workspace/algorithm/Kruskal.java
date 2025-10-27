package workspace.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Kruskal {
    private static int operations = 0;
    private static int totalCost = 0;

    public static Graph kruskal(Graph graph) {
        operations = 0;
        totalCost = 0;

        Graph mst = new Graph();
        mst.nodes.addAll(graph.nodes);

        DSU dsu = new DSU();
        for (Node node : graph.nodes) {
            dsu.makeSet(node);
            operations++;
        }

        List<Edge> sortedEdges = new ArrayList<>(graph.edges);
        Collections.sort(sortedEdges, Comparator.comparingInt(edge -> edge.weight));
        operations += sortedEdges.size();

        for (Edge edge : sortedEdges) {
            operations++;
            Node u = edge.start;
            Node v = edge.end;

            if (dsu.find(u) != dsu.find(v)) {
                mst.edges.add(edge);
                dsu.union(u, v);
                totalCost += edge.weight;
                operations += 3;
            }

            if (mst.edges.size() == graph.nodes.size() - 1) {
                break;
            }
        }

        return mst;
    }

    public static int getOperationCount() {
        return operations;
    }

    public static int getTotalCost() {
        return totalCost;
    }
}
