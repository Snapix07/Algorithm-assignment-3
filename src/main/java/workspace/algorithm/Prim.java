package workspace.algorithm;

import java.util.*;

public class Prim {
    public static class Result {
        public List<Edge> mstEdges = new ArrayList<>();
        public int totalCost = 0;
        public int operations = 0;
        public double execTimeMs = 0.0;
    }

    public static Result run(Graph graph) {
        long start = System.nanoTime();
        Result result = new Result();

        if (graph.nodes.isEmpty()) return result;

        Set<Node> inMST = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        Node startNode = graph.nodes.get(0);
        inMST.add(startNode);
        pq.addAll(startNode.edges);

        while (!pq.isEmpty() && result.mstEdges.size() < graph.nodes.size() - 1) {
            Edge e = pq.poll();
            result.operations++;
            Node u = e.start;
            Node v = e.end;

            Node outside = null;
            if (inMST.contains(u) && !inMST.contains(v)) outside = v;
            else if (inMST.contains(v) && !inMST.contains(u)) outside = u;
            else continue;

            inMST.add(outside);
            result.mstEdges.add(e);
            result.totalCost += e.weight;

            for (Edge ne : outside.edges) {
                result.operations++;
                Node a = ne.start;
                Node b = ne.end;
                if (!(inMST.contains(a) && inMST.contains(b))) pq.add(ne);
            }
        }

        long end = System.nanoTime();
        result.execTimeMs = (end - start) / 1_000_000.0;
        return result;
    }
}
