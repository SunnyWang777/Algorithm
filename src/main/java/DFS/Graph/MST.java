package DFS.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//https://www.cnblogs.com/hi3254014978/p/12672367.html
//https://www.jianshu.com/p/40e6c83df608
public class MST {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<int[]> weights = new ArrayList<>();
        for (int[] edge : edges) {
            weights.add(edge);
        }
        Collections.sort(weights, (w1, w2) -> Integer.compare(w1[2], w2[2]));
        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();
        // Min cost of MST.
        int minCost = costOfMST(n, weights, null, null);
        for (int i = 0; i < edges.length; ++i) {
            int[] edge = edges[i];
            if(costOfMST(n, weights, null, edge) > minCost) {
                // Without critical edge, cost increases.
                critical.add(i);
            } else  if(costOfMST(n, weights, edge, null) == minCost) {
                // Since edge is not critical, as long as it can be part of MST, it's pseudo.
                pseudo.add(i);
            }
        }
        return Arrays.asList(critical, pseudo);
    }

    private int costOfMST(int n, List<int[]> edges, int[] required, int[] avoid) {
        int cost = 0;
        DSU dsu = new DSU(n);
        if(required != null) {
            dsu.union(required[0], required[1]);
            cost += required[2];
        }

        for(int[] edge: edges) {
            if(edge != avoid && dsu.union(edge[0], edge[1])) {
                cost += edge[2];
            }
            if(dsu.getGroups() == 1)
                return cost;
        }
        return Integer.MAX_VALUE;
    }

    static class DSU {
        private int[] parents;
        private int size;

        public DSU(int size) {
            this.size = size;
            parents = new int[size];
            for (int i = 0; i < size; ++i) {
                parents[i] = i;
            }
        }

        public boolean union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if(parentX == parentY)
                return false;
            if(parentX < parentY) {
                parents[parentY] = parentX;
            } else {
                parents[parentY] = parentX;
            }
            --size;
            return true;
        }

        public int getGroups() {
            return size;
        }

        public int find(int x) {
            while (parents[x] != x) {
                parents[x] = parents[parents[x]];
                x = parents[x];
            }

            return parents[x];
        }
    }

}
