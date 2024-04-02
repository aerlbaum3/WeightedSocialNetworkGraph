import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class TestingTheGraph {

    @Test
    public void testAddVertex() {
        WeightedSocialNetworkGraph<String> graph = new WeightedSocialNetworkGraph<>();
        graph.addVertex("Alice");
        assertTrue(graph.getNeighbors("Alice").isEmpty());
    }

    @Test
    public void testAddEdge() {
        WeightedSocialNetworkGraph<String> graph = new WeightedSocialNetworkGraph<>();
        graph.addVertex("Alice");
        graph.addVertex("Bob");
        graph.addEdge("Alice", "Bob", 3);
        assertEquals(1, graph.getNeighbors("Alice").size());
        assertEquals(1, graph.getNeighbors("Bob").size());
    }

    @Test
    public void testFindPath() {
        WeightedSocialNetworkGraph<String> graph = new WeightedSocialNetworkGraph<>();
        graph.addVertex("Alice");
        graph.addVertex("Bob");
        graph.addVertex("Charlie");
        graph.addVertex("David");
        graph.addEdge("Alice", "Bob", 3);
        graph.addEdge("Bob", "Charlie", 4);
        graph.addEdge("Charlie", "David", 2);
        List<String> path = graph.findPath("Alice", "David");
        assertNotNull(path);
        assertEquals(4, path.size()); // The path size should include both start and end nodes
        assertEquals("Alice", path.get(0));
        assertEquals("Bob", path.get(1));
        assertEquals("Charlie", path.get(2));
        assertEquals("David", path.get(3));
    }


    @Test
    public void testGetEdgeWeight() {
        WeightedSocialNetworkGraph<String> graph = new WeightedSocialNetworkGraph<>();
        graph.addVertex("Alice");
        graph.addVertex("Bob");
        graph.addEdge("Alice", "Bob", 3);
        assertEquals(3, graph.getEdgeWeight("Alice", "Bob"));
    }
    public void testDirectEdge() {
        WeightedSocialNetworkGraph<String> graph = new WeightedSocialNetworkGraph<>();
        graph.addVertex("Alice");
        graph.addVertex("Bob");
        graph.addEdge("Alice", "Bob", 5);
        List<String> path = graph.findPath("Alice", "Bob");
        assertNotNull(path);
        assertEquals(2, path.size());
        assertEquals("Alice", path.get(0));
        assertEquals("Bob", path.get(1));
        assertEquals(5, graph.getEdgeWeight("Alice", "Bob"));
    }
    public void testNoPath() {
        WeightedSocialNetworkGraph<String> graph = new WeightedSocialNetworkGraph<>();
        graph.addVertex("Alice");
        graph.addVertex("Bob");
        graph.addVertex("Charlie");
        assertNull(graph.findPath("Alice", "Charlie"));
    }
   


}
