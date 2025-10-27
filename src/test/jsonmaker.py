import json
import random

def create_graph(graph_id, num_nodes, min_weight=1, max_weight=10, density=0.6):

    nodes = [chr(ord('A') + i) for i in range(num_nodes)]
    edges = []

    max_possible_edges = num_nodes * (num_nodes - 1) // 2
    target_edges_count = int(max_possible_edges * density)

    potential_edges = []
    for i in range(num_nodes):
        for j in range(i + 1, num_nodes):
            potential_edges.append((nodes[i], nodes[j]))

    if len(potential_edges) > target_edges_count:
        selected_edges = random.sample(potential_edges, target_edges_count)
    else:
        selected_edges = potential_edges

    for u, v in selected_edges:
        weight = random.randint(min_weight, max_weight)
        edges.append({
            "from": u,
            "to": v,
            "weight": weight
        })

    return {
        "id": graph_id,
        "nodes": nodes,
        "edges": edges
    }

def generate_input_json(filename="generated_input.json"):

    all_graphs = []


    graph1 = create_graph(
        graph_id=1,
        num_nodes=5,
        min_weight=1,
        max_weight=10,
        density=0.8
    )
    all_graphs.append(graph1)

    graph2 = create_graph(
        graph_id=2,
        num_nodes=7,
        min_weight=5,
        max_weight=20,
        density=0.4
    )
    all_graphs.append(graph2)

    graph3 = create_graph(
        graph_id=3,
        num_nodes=10,
        min_weight=1,
        max_weight=5,
        density=0.9
    )
    all_graphs.append(graph3)

    final_data = {
        "graphs": all_graphs
    }

    with open(filename, 'w', encoding='utf-8') as f:
        json.dump(final_data, f, ensure_ascii=False, indent=2)

    print(f"Сгенерированные данные сохранены в файл: {filename}")

if __name__ == "__main__":
    generate_input_json()