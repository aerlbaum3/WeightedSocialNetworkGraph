import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create a weighted social network graph
        WeightedSocialNetworkGraph<String> graph = createGraph();

        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);
        char choice = ' ';

        // Display menu and process user choice until 'D' (exit) is selected
        while (choice != 'D') {
            // Display menu options
            System.out.println("\nMenu:");
            System.out.println("A: Demonstrate three distinct paths through the graph");
            System.out.println("B: Perform breadth-first search traversal");
            System.out.println("C: Display the adjacency table");
            System.out.println("D: Perform Dijktras algorithm");
            System.out.println("E: Exit");
            System.out.print("Enter your choice: ");

            // Read user choice
            choice = Character.toUpperCase(scanner.nextLine().charAt(0));

            // Process user choice
            switch (choice) {
                case 'A':
                    // Demonstrate three distinct paths through the graph
                    demonstratePaths(graph);
                    break;
                case 'B':
                    // Perform breadth-first search traversal
                    performBFS(graph, scanner);
                    break;
                case 'C':
                    // Display the adjacency table
                    graph.displayAdjacencyList();
                    break;
                case 'D':
                	//Peform dijktras algorithm
                	performDijkstra(graph,scanner);
                case 'E':
                    // Exit the program
                    System.out.println("Exiting...");
                    break;
                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Please enter A, B, C, or D.");
            }
        }
        // Close the scanner
        scanner.close();
    }

    // Method to create the weighted social network graph
    private static WeightedSocialNetworkGraph<String> createGraph() {
        WeightedSocialNetworkGraph<String> graph = new WeightedSocialNetworkGraph<>();
        // Add vertices
        graph.addVertex("Alice");
        graph.addVertex("Bob");
        graph.addVertex("Charlie");
        graph.addVertex("David");
        graph.addVertex("Emily");
        graph.addVertex("Frank");

        // Add edges
        graph.addEdge("Alice", "Bob", 3);
        graph.addEdge("Alice", "Charlie", 2);
        graph.addEdge("Bob", "David", 5);
        graph.addEdge("Charlie", "Emily", 4);
        graph.addEdge("David", "Emily", 1);
        graph.addEdge("Emily", "Frank", 2);

        return graph;
    }

    // Method to demonstrate three distinct paths through the graph
    private static void demonstratePaths(WeightedSocialNetworkGraph<String> graph) {
        System.out.println("Distinct Paths in the Weighted Social Network Graph:");
        printPath(graph, "Alice", "Emily");
        printPath(graph, "Bob", "Frank");
        printPath(graph, "Charlie", "David");
    }

    // Method to print a path between two vertices in the graph
    private static void printPath(WeightedSocialNetworkGraph<String> graph, String start, String end) {
        List<String> path = graph.findPath(start, end);
        if (path != null) {
            System.out.print("Path from " + start + " to " + end + ": ");
            int totalWeight = 0;
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i < path.size() - 1) {
                    int edgeWeight = graph.getEdgeWeight(path.get(i), path.get(i + 1));
                    totalWeight += edgeWeight;
                    System.out.print(" (" + edgeWeight + ") -> ");
                }
            }
            System.out.println(" (Total Weight: " + totalWeight + ")");
        } else {
            System.out.println("No path found between " + start + " and " + end);
        }
    }

    // Method to perform breadth-first search traversal
    private static void performBFS(WeightedSocialNetworkGraph<String> graph, Scanner scanner) {
        System.out.println("Breadth-First Search Traversal:");
        System.out.println("Enter the point you want to start by: ");
        String startPoint = scanner.nextLine();
        BFS.breadthFirstSearch(graph, startPoint);
    }
    private static void performDijkstra(WeightedSocialNetworkGraph<String> graph, Scanner scanner) {
        System.out.println("Dijkstra's Shortest Path Algorithm:");
        System.out.println("Enter the starting point: ");
        String startPoint = scanner.nextLine();
        System.out.println("Enter the destination point: ");
        String destinationPoint = scanner.nextLine();

        List<String> shortestPath = graph.dijkstraShortestPath(startPoint, destinationPoint);

        if (shortestPath != null) {
            System.out.print("Shortest path from " + startPoint + " to " + destinationPoint + ": ");
            for (int i = 0; i < shortestPath.size(); i++) {
                System.out.print(shortestPath.get(i));
                if (i < shortestPath.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        } else {
            System.out.println("No path found between " + startPoint + " and " + destinationPoint);
        }
    }
}
