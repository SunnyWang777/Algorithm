package DFS.DFSPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AllPathsFromSourceToTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        if(graph == null || graph.length == 0)
            return res;
        int n = graph.length;
        dfs(graph,0, n-1, res, new ArrayList<>());
        return res;
    }

    private void dfs(int[][] graph, int start, int end, List<List<Integer>> res, List<Integer> path) {
        if(start == end) {
            path.add(start);
            res.add(new ArrayList<>(path));
            path.remove(path.size() -1);
            return;
        }

        for(int nei: graph[start]) {
            path.add(start);
            dfs(graph, nei, end, res, path);
            path.remove(path.size() -1);
        }
    }

    public static void main(String[] args) {
        int a = 6;
        int b = 6/2;
        int[][] graph = {{1,2},{3},{3},{}};
        AllPathsFromSourceToTarget test = new AllPathsFromSourceToTarget();
        //test.allPathsSourceTarget(graph);

        test.leadsToDestination(2, new int[][]{{0,1},{1,1}}, 0, 1);
    }

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(graph, edges);
        HashSet<Integer> visited = new HashSet<>();
        return dfs(graph, source, destination, visited);
    }

    private boolean dfs(HashMap<Integer, List<Integer>> graph, int source, int dest, HashSet<Integer> visited) {
        if(source == dest) {
            if(!graph.containsKey(dest) || graph.get(dest).size() == 0)
                return true;
            return false;
        }

        if(!graph.containsKey(source))
            return false;
        for(int nei: graph.get(source)) {
            if(visited.contains(nei))
                return false;
            visited.add(nei);
            if(!dfs(graph, nei, dest, visited)) {
                return false;
            }
            visited.remove(nei);
        }

        return true;
    }

    private void buildGraph(HashMap<Integer, List<Integer>> graph, int[][] edges) {
        for(int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            if(!graph.containsKey(from)) {
                graph.put(from, new ArrayList<>());
            }
            graph.get(from).add(to);
        }
    }
}


