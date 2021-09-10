package DFS.Graph;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
 * single point shortest path，可以负weight
 */
public class BellmanFordShorestPath {
    static class Graph {
        class Edge {
            int src, dest, weight;

            Edge() {
                src = dest = weight = 0;
            }
        }

        int V, E;
        Edge edge[];

        // Creates a graph with V vertices and E edges
        Graph(int v, int e) {
            V = v;
            E = e;
            edge = new Edge[e];
            for (int i = 0; i < e; ++i)
                edge[i] = new Edge();
        }

        public void BellmanFord(Graph graph, int src) {
            boolean[] visited = new boolean[graph.V];
            int[] dist = new int[graph.V];
            Arrays.fill(visited, false);
            Arrays.fill(dist, Integer.MAX_VALUE);
            visited[src] = true;
            dist[src] = 0;
            int[] path = new int[graph.V];
            path[src] = -1;

            // Step 2: Relax all edges |V| - 1 times. A simple
            // shortest path from src to any other vertex can
            // have at-most |V| - 1 edges
            for(int i = 1 ;i < V; i++) {
                for(int j = 0; j < graph.E; j++) {
                    int u = graph.edge[j].src;
                    int v = graph.edge[j].dest;
                    int weight = graph.edge[j].weight;
                    if(dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + graph.edge[j].weight) {
                        dist[v] = dist[u] + weight;
                        path[v] = u;
                    }
                }
            }

            // Step 3: check for negative-weight cycles. The above
            // step guarantees shortest distances if graph doesn't
            // contain negative weight cycle. If we get a shorter
            // path, then there is a cycle.
            for (int j = 0; j < E; ++j) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    System.out.println("Graph contains negative weight cycle");
                    return;
                }
            }

            printArr(dist, src);
            for(int i = 0 ;i < V; i++) {
                System.out.print("\n" + src + " -> ");
                System.out.print(i + " \t\t ");
                System.out.print(path[i] + "\t\t");
                printPath(path, i);
            }
        }

        void printPath(int[] path, int cur) {
            if(cur == -1)
                return;
            printPath(path, path[cur]);
            System.out.print(cur + " ");
        }

        // A utility function used to print the solution
        void printArr(int dist[], int V)
        {
            System.out.println("Vertex Distance from Source");
            for (int i = 0; i < V; ++i)
                System.out.println(i + "\t\t" + dist[i]);
        }
    }

    public static void main(String[] args) {
        int V = 5; // Number of vertices in graph
        int E = 8; // Number of edges in graph
        Graph graph = new Graph(V, E);

        // add edge 0-1 (or A-B in above figure)
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = -1;

        // add edge 0-2 (or A-C in above figure)
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 4;

        // add edge 1-2 (or B-C in above figure)
        graph.edge[2].src = 1;
        graph.edge[2].dest = 2;
        graph.edge[2].weight = 3;

        // add edge 1-3 (or B-D in above figure)
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 2;

        // add edge 1-4 (or A-E in above figure)
        graph.edge[4].src = 1;
        graph.edge[4].dest = 4;
        graph.edge[4].weight = 2;

        // add edge 3-2 (or D-C in above figure)
        graph.edge[5].src = 3;
        graph.edge[5].dest = 2;
        graph.edge[5].weight = 5;

        // add edge 3-1 (or D-B in above figure)
        graph.edge[6].src = 3;
        graph.edge[6].dest = 1;
        graph.edge[6].weight = 1;

        // add edge 4-3 (or E-D in above figure)
        graph.edge[7].src = 4;
        graph.edge[7].dest = 3;
        graph.edge[7].weight = -3;

        graph.BellmanFord(graph, 0);
    }
}
