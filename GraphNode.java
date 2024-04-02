import java.util.ArrayList;
import java.util.List;

public class GraphNode<T> {

	T data; // Data associated with the graph node
    List<GraphNode> neighbors; // List to store neighboring graph nodes

    // Constructor to initialize a graph node with given data
    public GraphNode(T data) {
        this.data = data; // Initialize the data of the graph node
        this.neighbors = new ArrayList<>(); // Initialize the list of neighbors
    }

    // Method to add a neighbor (another graph node) to the list of neighbors
    public void addNeighbor(GraphNode neighbor) {
        this.neighbors.add(neighbor); // Add the neighbor to the list of neighbors
    }
}
