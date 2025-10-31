package workspace;

import com.google.gson.*;
import workspace.algorithm.*;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Read input.json
        JsonObject input = gson.fromJson(new FileReader("input.json"), JsonObject.class);
        JsonArray graphsArray = input.getAsJsonArray("graphs");
        JsonArray resultsArray = new JsonArray();

        for (JsonElement gElem : graphsArray) {
            JsonObject gObj = gElem.getAsJsonObject();
            int id = gObj.get("id").getAsInt();

            // Build Graph
            Graph graph = new Graph();
            Map<String, Node> map = new HashMap<>();
            for (JsonElement nodeName : gObj.getAsJsonArray("nodes")) {
                Node n = new Node(nodeName.getAsString());
                graph.nodes.add(n);
                map.put(n.name, n);
            }

            for (JsonElement eElem : gObj.getAsJsonArray("edges")) {
                JsonObject eo = eElem.getAsJsonObject();
                Node from = map.get(eo.get("from").getAsString());
                Node to = map.get(eo.get("to").getAsString());
                int w = eo.get("weight").getAsInt();
                Edge edge1 = new Edge(from, to, w);
                Edge edge2 = new Edge(to, from, w);
                from.Add(edge1);
                to.Add(edge2);
                graph.edges.add(edge1);
            }

            // Run Prim
            Prim.Result primRes = Prim.run(graph);
            // Run Kruskal
            Kruskal.Result kruskalRes = Kruskal.run(graph);

            // Build output JSON
            JsonObject resultObj = new JsonObject();
            resultObj.addProperty("graph_id", id);

            JsonObject inputStats = new JsonObject();
            inputStats.addProperty("vertices", graph.nodes.size());
            inputStats.addProperty("edges", graph.edges.size());
            resultObj.add("input_stats", inputStats);

            resultObj.add("prim", mstToJson(primRes, gson));
            resultObj.add("kruskal", mstToJson(kruskalRes, gson));

            resultsArray.add(resultObj);
        }

        JsonObject output = new JsonObject();
        output.add("results", resultsArray);

        try (FileWriter writer = new FileWriter("output.json")) {
            gson.toJson(output, writer);
        }

        System.out.println("✅ Output saved to output.json");
    }

    private static JsonObject mstToJson(Prim.Result res, Gson gson) {
        JsonObject obj = new JsonObject();
        JsonArray arr = new JsonArray();
        for (Edge e : res.mstEdges) {
            JsonObject eo = new JsonObject();
            eo.addProperty("from", e.start.name);
            eo.addProperty("to", e.end.name);
            eo.addProperty("weight", e.weight);
            arr.add(eo);
        }
        obj.add("mst_edges", arr);
        obj.addProperty("total_cost", res.totalCost);
        obj.addProperty("operations_count", res.operations);
        obj.addProperty("execution_time_ms", res.execTimeMs);
        return obj;
    }

    private static JsonObject mstToJson(Kruskal.Result res, Gson gson) {
        JsonObject obj = new JsonObject();
        JsonArray arr = new JsonArray();
        for (Edge e : res.mstEdges) {
            JsonObject eo = new JsonObject();
            eo.addProperty("from", e.start.name);
            eo.addProperty("to", e.end.name);
            eo.addProperty("weight", e.weight);
            arr.add(eo);
        }
        obj.add("mst_edges", arr);
        obj.addProperty("total_cost", res.totalCost);
        obj.addProperty("operations_count", res.operations);
        obj.addProperty("execution_time_ms", res.execTimeMs);
        return obj;
    }
}
