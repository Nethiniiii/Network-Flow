# Max Flow Network Solver

This project implements the **Edmonds-Karp algorithm** in Java to compute the **maximum flow** in a directed flow network.

## Features
- Reads benchmark graph data from `.txt` files.
- Finds augmenting paths using BFS.
- Displays each augmenting path and the flow added.
- Shows total maximum flow and execution time.
- Interactive console interface with input validation.

## How It Works
1. User inputs a benchmark file name (e.g. `bridge_1`, `ladder_5`).
2. The program loads the graph and runs the Edmonds-Karp algorithm.
3. Augmenting paths and flow details are printed per iteration.
4. Final maximum flow and execution time are displayed.
5. User can choose to test another file or exit.

## File Structure
```
├── src
│   ├── Main.java                // Handles user interaction and program control
│   ├── FlowNetwork.java         // Network structure using adjacency list
│   ├── Edge.java                // Edge class with capacity and reverse link
│   ├── MaxFlowAlgorithm.java    // Edmonds-Karp algorithm logic
│   ├── FlowNetworkManager.java  // Parses benchmark files
│
├── benchmarks                  // Folder containing .txt benchmark graph files
```

## Example Benchmark Format
```
6
0 1 4
0 4 1
1 2 2
1 3 1
2 3 1
...
```
- First line: number of nodes
- Remaining lines: edges in format `from to capacity`

## How to Run (IntelliJ or Terminal)
1. Place `.txt` files in `src/benchmarks/`
2. Compile all files:
```bash
javac *.java
```
3. Run the main class:
```bash
java Main
```

## Author
S. A. Nethini Pabodhya Perera  
UOW ID: w2051616 | IIT ID: 20230282


