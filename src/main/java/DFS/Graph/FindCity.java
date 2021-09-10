package DFS.Graph;

import java.awt.image.VolatileImage;
import java.lang.reflect.Array;
import java.util.*;

public class FindCity {
    //1334.Find-the-City-With-the-Smallest-Number-of-Neighbors-at-a-Threshold-Distance (TBD)
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        List<int[]>[] graph = new List[n];
        initGraph(graph, edges, n);

        int maxCities = Integer.MAX_VALUE;
        int ans = 0;
        for(int i = 0; i < n; i++) {
            int cityCount = countCities(graph, i, distanceThreshold, n);
            if(cityCount <= maxCities) {
                maxCities = cityCount;
                ans = i;
            }
        }
        return ans;
    }

    public int countCities(List<int[]>[] graph, int city, int dist, int n) {
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        q.add(new int[]{ city, 0 });
        int cityCount = 0;

        while(!q.isEmpty()) {
            int[] vertex = q.poll();
            int currCity = vertex[0];
            int costToReachCity = vertex[1];
            if(!visited[currCity]) {
                visited[currCity] = true;
                cityCount++;
                for(int[] adj: graph[currCity]) {
                    int v = adj[0];
                    int cost = adj[1];
                    if(!visited[v] &&  costToReachCity + cost <= dist) {
                        q.offer(new int[]{v, costToReachCity + cost});
                    }
                }
            }
        }

        return cityCount -1;
    }

    public void initGraph(List<int[]>[] graph, int[][] edges, int n) {
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : edges) {
            graph[edge[0]].add(new int[]{ edge[1], edge[2] });
            graph[edge[1]].add(new int[]{ edge[0], edge[2] });
        }
    }


//    private int dfs(HashMap<Integer, List<Edge>> graph, int node, int parent, int dist, int cnt, int distanceThreshold, boolean[] visited) {
//
//        int levelCnt = 0;
//        for(Edge e: graph.get(node)) {
//            if(e.to == parent || visited[e.to])
//                continue;
//            visited[e.to] = true;
//            dist += e.dist;
//            if(dist <= distanceThreshold) {
//                levelCnt += dfs(graph, e.to, node, dist, cnt+1, distanceThreshold, visited);
//            }
//            dist -= e.dist;
//            visited[e.to] = false;
//        }
//
//        return levelCnt;
//    }

    public static void main(String[] args) {
        int[][] matrix = {{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        FindCity test = new FindCity();
        test.findTheCity(4, matrix, 4);

    }
}
