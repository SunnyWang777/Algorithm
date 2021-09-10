package DFS.Graph;

/**
 * 任意两点的shorestpath
 * https://blog.csdn.net/qq_35644234/article/details/60875818
 * <p>
 * https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/
 * The Floyd Warshall Algorithm is for solving the All Pairs Shortest Path problem. The problem is to
 * find shortest distances between every pair of vertices in a given edge weighted directed Graph.
 */
public class FloydShortestpath {
    final static int INF = 99999, V = 4;

    public void floydWarshall(int graph[][]) {
        int dist[][] = new int[V][V];
        int[][] path = new int[V][V];

        int i, j, k;
        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                path[i][j] = j;
            }
        }

        //i, j之间通过k 点来松弛
        for (k = 0; k < V; k++) {
            // Pick all vertices as source one by one
            for (i = 0; i < V; i++) {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < V; j++) {
                    if(dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = path[i][k];
                    }
                }
            }
        }
        // Print the shortest distance matrix
        printSolution(dist);
        printPath(path, dist);
    }

    void printPath(int[][] path, int[][] dist) {
        System.out.println("\"各个顶点对的最短路径：" + " \t\t ");
        int row = 0;
        int col = 0;
        int temp = 0;

        for (row = 0; row < path.length; row++){
            for (col = row + 1; col < path.length; col++){
                System.out.print("v" + row + " --- " + " v " + col + " weight: " + dist[row][col] + " path: " + " v " + row);
                temp = path[row][col];
                while (col != temp) {
                    System.out.print(" ---> " + " v "+ temp);
                    temp = path[temp][col];
                }
                System.out.print(" ---> " + " v "+ temp);
                System.out.println();
                //System.out.println("-----------------");
            }
        }
    }

    void printSolution(int dist[][])
    {
        System.out.println("The following matrix shows the shortest "+
                "distances between every pair of vertices");
        for (int i=0; i<V; ++i)
        {
            for (int j=0; j<V; ++j)
            {
                if (dist[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j]+"   ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int graph[][] =
                {{0,   5,  INF, 10},
                {INF, 0,   3, INF},
                {INF, INF, 0,   1},
                {INF, INF, INF, 0}
        };
        FloydShortestpath a = new FloydShortestpath();

        // Print the solution
        a.floydWarshall(graph);
    }
}
