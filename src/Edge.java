/**
 * Name : S. A. Nethini Pabodhya Perera
 * UOW ID : w2051616
 * IIT ID : 20230282
 */


// Class representing an edge in the flow network
public class Edge {
    int to;         // Target node (the node this edge points to)
    int capacity;   // Remaining capacity on this edge (how much more flow can be sent)
    Edge reverse;   // Reference to the reverse edge (for residual graph operations)

    // Constructor to initialize an edge with target node and initial capacity
    public Edge(int to, int capacity) {
        this.to = to;           // Set target node
        this.capacity = capacity; // Set initial capacity
    }
}

