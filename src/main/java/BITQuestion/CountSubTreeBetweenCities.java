package BITQuestion;

import java.util.*;

public class CountSubTreeBetweenCities {
    List<Integer>[] adj;
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        adj = new List[n];
        for(int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        // step1: build adjw
        for(int[] e: edges) {
            adj[e[0]-1].add(e[1]-1);
            adj[e[1]-1].add(e[0]-1);
        }

        // step2: try 2^n possible subtrees
        int[] rets = new int[n-1];
        int[] allow = new int[n];
        int[] dist = new int[n];
        for (int state=1; state<(1<<n); state++)
        {
            int start = 0;
            int count = 0;
            for (int i=0; i<n; i++)
            {
                if (((state>>i)&1)==1)
                {
                    allow[i]=1;
                    start = i;
                    count++;
                }
                else
                    allow[i]=0;
            }

            for (int i=0; i<n; i++)
                dist[i] = -1;
            int v1 = bfs(start, dist, allow);

            int countVisited = 0;
            for (int i=0; i<n; i++)
                countVisited += (dist[i]!=-1?1:0);
            if (countVisited!=count)
                continue;

            for (int i=0; i<n; i++)
                dist[i] = -1;
            int v2 = bfs(v1, dist, allow);
            int maxDist = 0;
            for(int d: dist)
                maxDist = Math.max(d, maxDist);

            rets[maxDist]++;
        }

        //rets.erase(rets.begin());
        return rets;
    }

    int bfs(int start, int[] dis, int[] allow)
    {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        dis[start] = 0;
        int maxDis = 0;
        int maxId = start;

        while (!q.isEmpty())
        {
            int cur = q.poll();

            for (int next: adj[cur])
            {
                if (allow[next]==0) continue;
                if (dis[next] == -1)
                {
                    q.offer(next);
                    dis[next] = dis[cur] + 1;
                    if (dis[next] > maxDis)
                    {
                        maxDis = dis[next];
                        maxId = next;
                    }
                }
            }
        }
        return maxId;
    }

    public static void main(String[] args) {
        CountSubTreeBetweenCities test = new CountSubTreeBetweenCities();
        int[][] matrix = {{1,2},{2,3},{2,4}};
        test.countSubgraphsForEachDiameter(4, matrix);
    }
}
