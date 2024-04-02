import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {

    List<Person> neighbors; // List to store neighboring persons
    String name; // Name of the person
    Map<Person, Integer> friends; // Store friends along with the edge weights

    // Constructor to initialize a person with a given name
    public Person(String name) {
        this.name = name;
        this.friends = new HashMap<>(); // Initialize the map to store friends
        this.neighbors = new ArrayList<>(); // Initialize the list of neighbors
    }

    // Method to add a friend with a given edge weight
    public void addFriend(Person friend, int weight) {
        this.friends.put(friend, weight); // Add the friend to the map with the associated weight
        // For a social network, friendships are usually bidirectional
        friend.friends.put(this, weight); // Add this person as a friend to the friend's map
    }

    // Method to add a neighbor (another person) to the list of neighbors
    public void addNeighbor(Person neighbor) {
        this.neighbors.add(neighbor); // Add the neighbor to the list of neighbors
    }
}
