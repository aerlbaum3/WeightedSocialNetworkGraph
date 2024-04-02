import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class WeightedSocialNetworkGraph<T> {
    private Map<T, List<Edge<T>>> adjacencyList;

    // Constructor to initialize the adjacency list
    public WeightedSocialNetworkGraph() {
        adjacencyList = new HashMap<>();
    }

    // Inner class to represent edges in the graph
    public static class Edge<T> {
        private T destination;
        private int weight;

        // Constructor for creating an edge with destination and weight
        public Edge(T destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        // Getter method for destination vertex of the edge
        public T getDestination() {
            return destination;
        }

        // Getter method for weight of the edge
        public int getWeight() {
            return weight;
        }
    }

    // Method to add a vertex to the graph
    public void addVertex(T vertex) {
        // If the vertex is not already in the adjacency list, add it with an empty list of edges
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Method to add an edge between two vertices with a given weight
    public void addEdge(T source, T destination, int weight) {
        // Check if source and destination vertices are not null and exist in the graph
        if (source == null || destination == null) {
            throw new IllegalArgumentException("Source and destination vertices cannot be null.");
        }
        if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination)) {
            throw new IllegalArgumentException("One or both of the vertices do not exist in the graph.");
        }

        // Add the edge from source to destination and vice versa for an undirected graph
        adjacencyList.get(source).add(new Edge<>(destination, weight));
        adjacencyList.get(destination).add(new Edge<>(source, weight)); // For undirected graph
    }

    // Method to get the neighbors of a given vertex
    public List<T> getNeighbors(T vertex) {
        List<T> neighbors = new ArrayList<>();
        // Get the list of edges associated with the vertex
        List<Edge<T>> edges = adjacencyList.getOrDefault(vertex, new ArrayList<>());
        // Extract the destination vertex from each edge and add it to the list of neighbors
        for (Edge<T> edge : edges) {
            neighbors.add(edge.getDestination());
        }
        return neighbors;
    }

    // Method to find a path between two vertices using BFS algorithm
    public List<T> findPath(T start, T end) {
        Queue<T> queue = new LinkedList<>();
        Map<T, T> parentMap = new HashMap<>();
        queue.offer(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            if (current.equals(end)) {
                // Reconstruct the path if the end vertex is reached
                List<T> path = new ArrayList<>();
                while (current != null) {
                    path.add(current);
                    current = parentMap.get(current);
                }
                Collections.reverse(path); // Reverse the path to get it in the correct order
                return path;
            }
            // Explore neighbors of the current vertex
            for (T neighbor : getNeighbors(current)) {
                if (!parentMap.containsKey(neighbor)) {
                    queue.offer(neighbor);
                    parentMap.put(neighbor, current); // Keep track of the parent vertex
                }
            }
        }
        // No path found
        return null;
    }

    // Method to get the weight of the edge between two vertices
    public int getEdgeWeight(T source, T destination) {
        List<Edge<T>> edges = adjacencyList.get(source);
        // Find the edge with the specified destination vertex and return its weight
        for (Edge<T> edge : edges) {
            if (edge.getDestination().equals(destination)) {
                return edge.getWeight();
            }
        }
        // Edge not found
        return -1;
    }

    // Method to display the adjacency list of the graph
    public void displayAdjacencyList() {
        for (Map.Entry<T, List<Edge<T>>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            // Display the destination vertex and weight of each edge associated with the current vertex
            for (Edge<T> neighbor : entry.getValue()) {
                System.out.print(neighbor.getDestination() + "(" + neighbor.getWeight() + " miles) ");
            }
            System.out.println(); // Move to the next line for the next vertex
        }
    }
    public List<T> dijkstraShortestPath(T start, T end) {
        Map<T, Integer> distances = new HashMap<>();
        Map<T, T> parentMap = new HashMap<>();
        PriorityQueue<Node<T>> minHeap = new PriorityQueue<>(Comparator.comparingInt(Node::getDistance));

        // Initialize distances
        for (T vertex : adjacencyList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
            parentMap.put(vertex, null);
        }

        // Set distance to start vertex as 0 and add it to the priority queue
        distances.put(start, 0);
        minHeap.offer(new Node<>(start, 0));

        while (!minHeap.isEmpty()) {
            T current = minHeap.poll().getVertex();

            if (current.equals(end)) {
                // Reconstruct the shortest path
                List<T> shortestPath = new ArrayList<>();
                while (current != null) {
                    shortestPath.add(current);
                    current = parentMap.get(current);
                }
                Collections.reverse(shortestPath);
                return shortestPath;
            }

            for (Edge<T> edge : adjacencyList.get(current)) {
                T neighbor = edge.getDestination();
                int newDistance = distances.get(current) + edge.getWeight();
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    parentMap.put(neighbor, current);
                    minHeap.offer(new Node<>(neighbor, newDistance));
                }
            }
        }
        // No path found
        return null;
    }private static class Node<T> {
        private T vertex;
        private int distance;

        public Node(T vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public T getVertex() {
            return vertex;
        }

        public int getDistance() {
            return distance;
        }
    }
}




