import java.util.*;

public class SocialNetwork {
    // Adjacency list representation of the graph
    private Map<String, List<String>> adjList;

    // Constructor to initialize the graph
    public SocialNetwork() {
        adjList = new HashMap<>();
    }

    // Add a user to the social network (vertex)
    public void addUser(String user) {
        adjList.putIfAbsent(user, new ArrayList<>());
    }

    // Add a friendship between two users (undirected edge)
    public void addFriendship(String user1, String user2) {
        adjList.putIfAbsent(user1, new ArrayList<>());
        adjList.putIfAbsent(user2, new ArrayList<>());
        adjList.get(user1).add(user2);
        adjList.get(user2).add(user1);
    }

    // Display all users and their friends (adjacency list)
    public void displayNetwork() {
        System.out.println("Social Network (Users and their friends):");
        for (Map.Entry<String, List<String>> entry : adjList.entrySet()) {
            String user = entry.getKey();
            List<String> friends = entry.getValue();
            System.out.println(user + " -> " + friends);
        }
    }

    // Perform BFS to find all friends (connected users) starting from a given user
    public void breadthFirstSearch(String startUser) {
        if (!adjList.containsKey(startUser)) {
            System.out.println("User not found in the network.");
            return;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(startUser);
        visited.add(startUser);

        System.out.println("Breadth-First Search starting from " + startUser + ":");

        while (!queue.isEmpty()) {
            String user = queue.poll();
            System.out.print(user + " ");

            // Explore each friend (neighbor)
            for (String friend : adjList.get(user)) {
                if (!visited.contains(friend)) {
                    visited.add(friend);
                    queue.add(friend);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();

        // Adding users to the social network
        network.addUser("Alice");
        network.addUser("Bob");
        network.addUser("Charlie");
        network.addUser("David");
        network.addUser("Eve");

        // Adding friendships (edges between users)
        network.addFriendship("Alice", "Bob");
        network.addFriendship("Alice", "Charlie");
        network.addFriendship("Bob", "David");
        network.addFriendship("Charlie", "Eve");

        // Display the network
        network.displayNetwork();

        // Perform BFS starting from Alice
        network.breadthFirstSearch("Alice");

        // Perform BFS starting from Bob
        network.breadthFirstSearch("Bob");
    }
}        