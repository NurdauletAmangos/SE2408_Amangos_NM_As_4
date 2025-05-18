import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private Map<Vertex<V>, Double> distTo;
    private Map<Vertex<V>, Vertex<V>> edgeTo;
    private PriorityQueue<VertexDist<V>> pq;

    private static class VertexDist<V> implements Comparable<VertexDist<V>> {
        Vertex<V> vertex;
        double dist;

        public VertexDist(Vertex<V> vertex, double dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public int compareTo(VertexDist<V> other) {
            return Double.compare(this.dist, other.dist);
        }
    }

    public DijkstraSearch(WeightedGraph<V> graph, Vertex<V> start) {
        super(graph, start);
        this.distTo = new HashMap<>();
        this.edgeTo = new HashMap<>();
        pq = new PriorityQueue<>();

        for (Vertex<V> v : graph.getVertices()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        distTo.put(start, 0.0);

        pq.add(new VertexDist<>(start, 0.0));
        while (!pq.isEmpty()) {
            relax(pq.remove().vertex);
        }
    }

    private void relax(Vertex<V> v) {
        for (Vertex<V> w : graph.getAdjacentVertices(v)) {
            double weight = graph.getWeight(v, w);
            if (distTo.get(w) > distTo.get(v) + weight) {
                distTo.put(w, distTo.get(v) + weight);
                edgeTo.put(w, v);
                pq.add(new VertexDist<>(w, distTo.get(w)));
            }
        }
    }

    @Override
    public List<Vertex<V>> pathTo(Vertex<V> vertex) {
        if (!edgeTo.containsKey(vertex) && !vertex.equals(start))
            return Collections.emptyList();

        List<Vertex<V>> path = new LinkedList<>();
        for (Vertex<V> x = vertex; x != null && !x.equals(start); x = edgeTo.get(x)) {
            path.add(0, x);
        }
        path.add(0, start);
        return path;
    }
}