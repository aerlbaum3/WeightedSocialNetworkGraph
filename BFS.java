import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {
    // Method to perform breadth-first search traversal on a weighted social network graph
    public static <T> void breadthFirstSearch(WeightedSocialNetworkGraph<T> graph, T start) {
        // Create a queue to store nodes to be visited
        Queue<T> queue = new LinkedList<>();
        // Create a set to keep track of visited nodes
        Set<T> visited = new HashSet<>();

        // Enqueue the start node and mark it as visited
        queue.offer(start);
        visited.add(start);

        // Continue traversal until the queue is empty
        while (!queue.isEmpty()) {
            // Dequeue a node from the queue
            T currentNode = queue.poll();
            // Visit the dequeued node
            System.out.print(currentNode + " ");

            // Enqueue all adjacent nodes of the dequeued node that have not been visited
            for (T neighbor : graph.getNeighbors(currentNode)) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor); // Add the neighbor to the queue
                    visited.add(neighbor); // Mark the neighbor as visited
                }
            }
        }
    }
}
