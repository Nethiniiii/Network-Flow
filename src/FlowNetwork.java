/**
 * Name : S. A. Nethini Pabodhya Perera
 * UOW ID : w2051616
 * IIT ID : 20230282
 */


import java.util.*;

public class FlowNetwork {
    int n; // Number of nodes in the network
    Map<Integer, List<Edge>> adj; // Adjacency map: node ID → list of outgoing edges

    /**
     * Constructor to initialize the flow network.
     * @param n the total number of nodes (numbered 0 to n-1)
     */
    public FlowNetwork(int n) {
        this.n = n;
        adj = new HashMap<>(); // Start with an empty map of adjacency lists
    }

    /**
     * Adds a directed edge with the given capacity from 'from' to 'to',
     * and also adds the corresponding reverse edge with 0 capacity
     * for residual graph operations.
     *
     * @param from     the source node ID
     * @param to       the target node ID
     * @param capacity the capacity of the forward edge
     */
    public void addEdge(int from, int to, int capacity) {
        // Ensure both nodes have an adjacency list initialized
        adj.putIfAbsent(from, new ArrayList<>());
        adj.putIfAbsent(to, new ArrayList<>());

        // Create forward and reverse edges
        Edge forward = new Edge(to, capacity);
        Edge backward = new Edge(from, 0);

        // Link each edge to its reverse
        forward.reverse = backward;
        backward.reverse = forward;

        // Add edges to the adjacency lists
        adj.get(from).add(forward);
        adj.get(to).add(backward);
    }

    /**
     * Counts the number of real edges in the network.
     * Since we store both forward and reverse edges,
     * we only count those with positive capacity and divide by two.
     *
     * @return the total number of forward edges in the network
     */
    public int countEdges() {
        int count = 0;
        // Iterate over all adjacency lists
        for (List<Edge> edges : adj.values()) {
            // Count edges that represent actual capacity (forward edges)
            for (Edge e : edges) {
                if (e.capacity > 0) count++;
            }
        }
        return count / 2; // Each forward edge has a corresponding reverse edge
    }
}



