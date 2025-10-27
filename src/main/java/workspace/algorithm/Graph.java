package workspace.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public int id;
    public List<Node> nodes = new ArrayList<Node>();
    public List<Edge> edges = new ArrayList<>();

    public float exec_time = 0;
    public int operation_count = 0;
    public int total_cost = 0;
}
