import java.util.List;

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        Vertex<String> v4 = new Vertex<>("D");
        Vertex<String> v5 = new Vertex<>("E");

        graph.addEdge(v1, v2, 1.0);
        graph.addEdge(v1, v3, 2.0);
        graph.addEdge(v2, v4, 3.0);
        graph.addEdge(v3, v4, 1.5);
        graph.addEdge(v4, v5, 2.5);

        System.out.println("BFS path from A to E:");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph, v1);
        printPath(bfs.pathTo(v5));

        System.out.println("\nDijkstra path from A to E:");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph, v1);
        printPath(dijkstra.pathTo(v5));
    }

    private static void printPath(List<Vertex<String>> path) {
        if (path.isEmpty()) {
            System.out.println("No path found");
            return;
        }

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).getData());
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}