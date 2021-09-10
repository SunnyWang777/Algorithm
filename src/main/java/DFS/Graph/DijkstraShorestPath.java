package DFS.Graph;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/printing-paths-dijkstras-shortest-path-algorithm/
 *
 * */
public class DijkstraShorestPath {
    private static final int NO_PARENT = -1;

    public static void dijkstra(int[][] adjacencyMatrix, int startVertex){
        int V = adjacencyMatrix[0].length;
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        dist[startVertex] = 0;
        int[] path = new int[V];
        path[startVertex] = NO_PARENT;
        //visited[startVertex] = true;

        for(int i = 1 ; i < V; i++) {
            int min = Integer.MAX_VALUE;
            int u = -1;
            for(int j = 0; j < V; j++) {
                if(!visited[j] && dist[j] < min) {
                    min = dist[j];
                    u = j;
                }
            }
            if(u == -1)
                break;
            visited[u] = true;
            for(int v = 0 ; v< V; v++) {
                if(!visited[v] && adjacencyMatrix[u][v] != 0 && dist[u] + adjacencyMatrix[u][v] < dist[v]) {
                    dist[v] = dist[u] + adjacencyMatrix[u][v];
                    path[v] = u;
                }
            }
        }

        printPath(path, dist, startVertex);
   }

   public static void printPath(int[] path, int[] dist, int src) {
        for(int i =0; i < path.length; i++) {
            System.out.print("\n" + src + " -> ");
            System.out.print(i + " \t\t ");
            System.out.print(dist[i] + "\t\t");
            printShorestPath(path, i);
        }
   }

   public static void printShorestPath(int[] path, int cur) {
        if(cur == NO_PARENT)
            return;
       printShorestPath(path, path[cur]);
       System.out.print(cur + " ");
   }

    public static void main(String[] args) {
        int[][] adjacencyMatrix = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 0, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 14, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        dijkstra(adjacencyMatrix, 0);
    }
}
