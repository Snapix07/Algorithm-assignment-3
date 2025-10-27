package workspace.cli;

import com.google.gson.*;
import workspace.algorithm.*;

import java.io.FileReader;
import java.util.List;

public class KruskalLaunch {
    public static void main(final String[] args) throws Exception {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(List.class, new JSONload())
                .create();

        FileReader reader = new FileReader("src/test/input.json");
        List<Graph> graphs = gson.fromJson(reader, List.class);
        reader.close();

        StringBuilder json = new StringBuilder();
        json.append("{\n  \"results\": [\n");

        for (int i = 0; i < graphs.size(); i++) {
            Graph graph = graphs.get(i);

            long start = System.nanoTime();
            Graph mst = Kruskal.kruskal(graph);
            long end = System.nanoTime();

            double execTime = (end - start) / 1_000_000.0;
            int totalCost = Kruskal.getTotalCost();
            int ops = Kruskal.getOperationCount();

            json.append("    {\n");
            json.append("      \"graph_id\": ").append(graph.id).append(",\n");
            json.append("      \"input_stats\": {\n");
            json.append("        \"vertices\": ").append(graph.nodes.size()).append(",\n");
            json.append("        \"edges\": ").append(graph.edges.size()).append("\n");
            json.append("      },\n");

            json.append("      \"kruskal\": {\n");
            json.append("        \"mst_edges\": [\n");

            for (int j = 0; j < mst.edges.size(); j++) {
                Edge e = mst.edges.get(j);
                json.append("          { \"from\": \"").append(e.start)
                        .append("\", \"to\": \"").append(e.end)
                        .append("\", \"weight\": ").append(e.weight).append(" }");
                if (j < mst.edges.size() - 1) json.append(",");
                json.append("\n");
            }

            json.append("        ],\n");
            json.append("        \"total_cost\": ").append(totalCost).append(",\n");
            json.append("        \"operations_count\": ").append(ops).append(",\n");
            json.append("        \"execution_time_ms\": ")
                    .append(String.format("%.2f", execTime)).append("\n");
            json.append("      }\n");
            json.append("    }");

            if (i < graphs.size() - 1) json.append(",");
            json.append("\n");
        }

        json.append("  ]\n}");
        System.out.println(json.toString());
    }
}
