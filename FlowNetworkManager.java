/**
 * Name : S. A. Nethini Pabodhya Perera
 * UOW ID : w2051616
 * IIT ID : 20230282
 */


// Class containing helper methods for network flow application
public class FlowNetworkManager {

    // Parse the benchmark file and build the flow network
    public static FlowNetwork parseNetworkFile(String filename) throws java.io.IOException {
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filename));
        String line = reader.readLine();
        if (line == null) throw new java.io.IOException("Empty file"); // If file is empty, throw error

        int n = Integer.parseInt(line.trim()); // First line contains number of nodes
        FlowNetwork network = new FlowNetwork(n); // Create a new flow network with n nodes

        // Read the remaining lines to add edges
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue; // Skip empty lines
            String[] parts = line.split("\\s+"); // Split the line by whitespace
            int from = Integer.parseInt(parts[0]); // From node
            int to = Integer.parseInt(parts[1]);   // To node
            int capacity = Integer.parseInt(parts[2]); // Edge capacity
            network.addEdge(from, to, capacity); // Add edge to the network
        }

        reader.close(); // Close the file reader
        return network; // Return the constructed flow network
    }

    // Solve the maximum flow for the given network
    public static int solveMaxFlow(FlowNetwork network) {
        MaxFlowAlgorithm solver = new MaxFlowAlgorithm(network); // Create a solver for the network
        // Call the method to solve using Edmonds-Karp with augmenting paths printed
        return solver.edmondsKarpWithAugmentingPaths(0, network.n - 1);
    }
}


