package workspace.cli;

import workspace.algorithm.Edge;
import workspace.algorithm.Graph;
import workspace.algorithm.Kruskal;
import workspace.algorithm.Node;

public class KruskalLaunch {
    public static void main(final String[] args) {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");

        // Create edges
        Edge ab = new Edge(a, b, 4);
        Edge ac = new Edge(a, c, 1);
        Edge bc = new Edge(b, c, 2);
        Edge bd = new Edge(b, d, 5);
        Edge cd = new Edge(c, d, 8);
        Edge ce = new Edge(c, e, 10);
        Edge de = new Edge(d, e, 2);

        // Add edges to nodes
        a.Add(ab); a.Add(ac);
        b.Add(ab); b.Add(bc); b.Add(bd);
        c.Add(ac); c.Add(bc); c.Add(cd); c.Add(ce);
        d.Add(bd); d.Add(cd); d.Add(de);
        e.Add(ce); e.Add(de);

        // Create graph
        Graph graph = new Graph();
        graph.nodes.add(a); graph.nodes.add(b); graph.nodes.add(c);
        graph.nodes.add(d); graph.nodes.add(e);
        graph.edges.add(ab); graph.edges.add(ac); graph.edges.add(bc);
        graph.edges.add(bd); graph.edges.add(cd); graph.edges.add(ce);
        graph.edges.add(de);

        // Run Kruskal's algorithm
        Graph mst = Kruskal.kruskal(graph);

        // Print results
        System.out.println("Minimum Spanning Tree Edges:");
        int totalWeight = 0;
        for (Edge edge : mst.edges) {
            System.out.println(edge);
            totalWeight += edge.weight;
        }
        System.out.println("Total weight: " + totalWeight);
    }
}
