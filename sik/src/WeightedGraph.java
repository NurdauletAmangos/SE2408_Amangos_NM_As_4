import java.util.*;

public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Vertex<V>>> map;

    public WeightedGraph() {
        this.map = new HashMap<>();
    }

    public void addVertex(Vertex<V> vertex) {
        map.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex<V> source, Vertex<V> dest, double weight) {
        if (!map.containsKey(source)) addVertex(source);
        if (!map.containsKey(dest)) addVertex(dest);

        source.addAdjacentVertex(dest, weight);
        map.get(source).add(dest);
    }

    public List<Vertex<V>> getAdjacentVertices(Vertex<V> vertex) {
        return map.getOrDefault(vertex, Collections.emptyList());
    }

    public Set<Vertex<V>> getVertices() {
        return map.keySet();
    }

    public double getWeight(Vertex<V> source, Vertex<V> dest) {
        return source.getAdjacentVertices().getOrDefault(dest, Double.POSITIVE_INFINITY);
    }
}