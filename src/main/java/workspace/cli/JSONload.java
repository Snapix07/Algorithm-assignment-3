package workspace.cli;

import com.google.gson.*;
import workspace.algorithm.*;

import java.lang.reflect.Type;
import java.util.*;

public class JSONload implements JsonDeserializer<List<Graph>> {

    @Override
    public List<Graph> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        List<Graph> graphs = new ArrayList<>();
        JsonArray graphsArray = json.getAsJsonObject().getAsJsonArray("graphs");

        for (JsonElement gEl : graphsArray) {
            JsonObject gObj = gEl.getAsJsonObject();
            Graph graph = new Graph();
            graph.id = gObj.get("id").getAsInt();

            Map<String, Node> nodeMap = new HashMap<>();
            for (JsonElement nodeNameEl : gObj.getAsJsonArray("nodes")) {
                String name = nodeNameEl.getAsString();
                Node node = new Node(name);
                nodeMap.put(name, node);
                graph.nodes.add(node);
            }

            for (JsonElement edgeEl : gObj.getAsJsonArray("edges")) {
                JsonObject eObj = edgeEl.getAsJsonObject();
                String from = eObj.get("from").getAsString();
                String to = eObj.get("to").getAsString();
                int weight = eObj.get("weight").getAsInt();

                Node start = nodeMap.get(from);
                Node end = nodeMap.get(to);

                Edge edge = new Edge(start, end, weight);
                start.Add(edge);
                graph.edges.add(edge);
            }

            graphs.add(graph);
        }

        return graphs;
    }
}