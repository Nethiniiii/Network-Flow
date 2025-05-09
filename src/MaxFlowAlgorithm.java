/**
 * Name : S. A. Nethini Pabodhya Perera
 * UOW ID : w2051616
 * IIT ID : 20230282
 */


import java.util.*;

public class MaxFlowAlgorithm {
    private FlowNetwork network;  // The flow network on which we will compute max flow

    // Constructor: store the reference to the flow network
    public MaxFlowAlgorithm(FlowNetwork network) {
        this.network = network;
    }

    /**
     * Computes the maximum flow from `source` to `sink` using the
     * Edmonds–Karp algorithm (BFS-based Ford–Fulkerson) and prints
     * each augmenting path and flow added per iteration.
     *
     * @param source the source node index
     * @param sink   the sink node index
     * @return the value of the maximum flow
     */
    public int edmondsKarpWithAugmentingPaths(int source, int sink) {
        int maxFlow = 0;     // Accumulates total flow sent from source to sink
        int iteration = 0;   // Counts the number of augmenting-path iterations

        // Repeat until no augmenting path is found
        while (true) {
            // parent map: for each reachable node, store the edge used to reach it
            Map<Integer, Edge> parent = new HashMap<>();
            Queue<Integer> queue = new LinkedList<>();

            // Start BFS from the source
            queue.add(source);
            parent.put(source, new Edge(-1, 0)); // Mark source as visited with a dummy edge

            // Perform BFS to find a path to the sink in the residual graph
            while (!queue.isEmpty() && !parent.containsKey(sink)) {
                int u = queue.poll();
                List<Edge> edges = network.adj.get(u);
                if (edges == null) continue;  // No outgoing edges from this node

                // Explore each edge with positive residual capacity
                for (Edge e : edges) {
                    if (!parent.containsKey(e.to) && e.capacity > 0) {
                        parent.put(e.to, e);  // Record the edge used to reach e.to
                        queue.add(e.to);      // Enqueue for further exploration
                    }
                }
            }

            // If sink was not reached, no more augmenting paths exist
            if (!parent.containsKey(sink)) {
                break;
            }

            // Determine the bottleneck capacity along the found path
            int flow = Integer.MAX_VALUE;
            List<Integer> path = new ArrayList<>();
            for (int v = sink; v != source; ) {
                Edge e = parent.get(v);
                flow = Math.min(flow, e.capacity); // Track minimum capacity
                path.add(v);                       // Build the path backwards
                v = e.reverse.to;                  // Move to the previous node
            }
            path.add(source);
            Collections.reverse(path); // Reverse to get path from source→sink

            // Apply the flow: subtract from forward edges, add to reverse edges
            for (int v = sink; v != source; ) {
                Edge e = parent.get(v);
                e.capacity -= flow;         // Reduce residual capacity
                e.reverse.capacity += flow; // Increase capacity of reverse edge
                v = e.reverse.to;           // Step backwards along the path
            }

            // Update total flow and iteration count
            maxFlow += flow;
            iteration++;

            // Print details of this augmenting-path iteration
            System.out.println();
            System.out.println("\n    Iteration " + iteration + "  " + "; Flow added this iteration: " + flow);
            //System.out.println("Augmenting Path: " + path);
        }

        // After all iterations, print the final summary
        System.out.println("\n              ------------------- Final Result ------------------");
        System.out.println("                ** Total iterations (augmenting paths found): " + iteration);
        System.out.println("                ** Final maximum flow from source to sink: " + maxFlow);

        return maxFlow;
    }
}



