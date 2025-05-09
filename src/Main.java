/**
 * Name : S. A. Nethini Pabodhya Perera
 * UOW ID : w2051616
 * IIT ID : 20230282
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Display the current working directory for user's reference
        System.out.println("Working dir: " + System.getProperty("user.dir"));

        // Main loop to allow multiple file attempts
        while (true) {
            // Ask the user for the benchmark filename (without '.txt')
            System.out.print("\nEnter benchmark filename without '.txt' (e.g. bridge_1 to 19 or ladder_1 to 20): ");
            String filename = scanner.nextLine().trim();

            // Build the complete file path assuming the files are inside 'src/benchmarks/' folder
            String path = "src/benchmarks/" + filename + ".txt";

            // Create a File object to check if the file exists
            File file = new File(path);

            // Validation: Check if the file exists
            if (!file.exists()) {
                System.out.println("\n⚠️  Invalid file name. File not found: " + path);
                // Ask if the user wants to exit
                if (!askToContinue(scanner, "Do you want to exit the program? (yes/no): ")) {
                    continue; // If not exiting, restart loop to enter a filename again
                } else {
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
            }

            // If file exists, proceed to parse and solve
            try {
                // Parse the network
                FlowNetwork network = FlowNetworkManager.parseNetworkFile(path);

                // Display benchmark information
                System.out.println("\n               ------------- Benchmark Info --------------");
                System.out.println("                 ** Benchmark File Loaded: " + filename + ".txt");
                System.out.println("                 ** Number of nodes: " + network.n);
                System.out.println("                 ** Number of edges: " + network.countEdges());
                System.out.println("                 ** Source node: 0");
                System.out.println("                 ** Target node: " + (network.n - 1));
                System.out.println("               ------------------------------------------");

                // Start measuring execution time
                long startTime = System.currentTimeMillis();

                // Create the solver and compute maximum flow
                MaxFlowAlgorithm solver = new MaxFlowAlgorithm(network);
                int maxFlow = solver.edmondsKarpWithAugmentingPaths(0, network.n - 1);

                // End measuring execution time
                long endTime = System.currentTimeMillis();
                double totalTimeSeconds = (endTime - startTime) / 1000.0;

                // Display execution time
                System.out.println("\n                           Execution Time ");
                System.out.println("               Total execution time: " + (endTime - startTime) + " milliseconds");
                System.out.println("               Or roughly: " + totalTimeSeconds + " seconds");
                System.out.println("               ______________________________________");

                System.out.println("Program finished successfully for this file!");

                // Ask the user if they want to try another file
                if (!askToContinue(scanner, "\nDo you want to try another file? (yes/no): ")) {
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                } else {
                    System.out.println("Let's try another file...");
                    // Continue looping
                }

            } catch (IOException e) {
                // Handle file reading errors
                System.err.println("Error reading file: " + e.getMessage());
                System.out.println("Something went wrong while reading the file.");
                // Ask if user wants to exit
                if (!askToContinue(scanner, "Do you want to exit the program? (yes/no): ")) {
                    continue;
                } else {
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
            }
        }
    }

    /**
     * Helper method to ask user if they want to continue or exit.
     * Ensures only 'yes' or 'no' responses are accepted.
     *
     * @param scanner Scanner object to read input
     * @param message Message to display
     * @return true if user wants to exit, false otherwise
     */
    private static boolean askToContinue(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("yes")) {
                return true; // User chose to exit
            } else if (response.equals("no")) {
                return false; // User chose to continue
            } else {
                System.out.println("Invalid input. Please type 'yes' or 'no'.");
            }
        }
    }
}



