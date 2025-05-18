import java.util.*;

public class BreadthFirstSearch<V> extends Search<V> {
    private Map<Vertex<V>, Vertex<V>> edgeTo;
    private Set<Vertex<V>> marked;

    public BreadthFirstSearch(WeightedGraph<V> graph, Vertex<V> start) {
        super(graph, start);
        this.edgeTo = new HashMap<>();
        this.marked = new HashSet<>();
        bfs(start);
    }

    private void bfs(Vertex<V> source) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        queue.add(source);
        marked.add(source);

        while (!queue.isEmpty()) {
            Vertex<V> v = queue.remove();
            for (Vertex<V> w : graph.getAdjacentVertices(v)) {
                if (!marked.contains(w)) {
                    edgeTo.put(w, v);
                    marked.add(w);
                    queue.add(w);
                }
            }
        }
    }

    @Override
    public List<Vertex<V>> pathTo(Vertex<V> vertex) {
        if (!marked.contains(vertex)) return Collections.emptyList();

        List<Vertex<V>> path = new LinkedList<>();
        for (Vertex<V> x = vertex; x != start; x = edgeTo.get(x)) {
            path.add(0, x);
        }
        path.add(0, start);
        return path;
    }
}