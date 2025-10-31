package workspace.algorithm;

import java.util.*;

public class Kruskal {
    public static class Result {
        public List<Edge> mstEdges = new ArrayList<>();
        public int totalCost = 0;
        public int operations = 0;
        public double execTimeMs = 0.0;
    }

    private static class UnionFind {
        private Map<Node, Node> parent = new HashMap<>();

        public Node find(Node x) {
            if (parent.get(x) == x) return x;
            parent.put(x, find(parent.get(x)));
            return parent.get(x);
        }

        public void union(Node a, Node b) {
            Node pa = find(a);
            Node pb = find(b);
            if (pa != pb) parent.put(pa, pb);
        }

        public void makeSet(Collection<Node> nodes) {
            for (Node n : nodes) parent.put(n, n);
        }
    }

    public static Result run(Graph graph) {
        long start = System.nanoTime();
        Result result = new Result();

        List<Edge> edges = new ArrayList<>(graph.edges);
        edges.sort(Comparator.comparingInt(e -> e.weight));

        UnionFind uf = new UnionFind();
        uf.makeSet(graph.nodes);

        for (Edge e : edges) {
            result.operations++;
            Node u = e.start;
            Node v = e.end;
            if (uf.find(u) != uf.find(v)) {
                uf.union(u, v);
                result.mstEdges.add(e);
                result.totalCost += e.weight;
            }
        }

        long end = System.nanoTime();
        result.execTimeMs = (end - start) / 1_000_000.0;
        return result;
    }
}
