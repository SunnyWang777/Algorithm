package DFS.Graph;

import java.util.Arrays;

public class PrimMST {

    // 构建最小生成树
    public static int[][] G = new int[110][110];    // 村庄的图
    public static boolean[] vis = new boolean[110]; // 判断某个村庄是否已经被访问过
    public static int[] dis = new int[110];     // 存放每个村庄到当前生成树集合的距离
    public static int e;
    public static final int INF = 1 << 30;
    public static int v;

    public int Prim(int u) {
        int price = 0;
        // 初始化距离数组
        Arrays.fill(dis, INF);
        dis[u] = 0;
        // 对每个村庄进行循环判断，每次选出其中一个点
        for (int i = 0; i < v; i++) {
            int min = Integer.MAX_VALUE, node = -1;
            for (int candidate = 0; candidate < v; candidate++) {
                if (dis[candidate] < min) {
                    min = dis[candidate];
                    node = candidate;
                }
            }

            if (node == -1)
                break;
            vis[node] = true;
            price += dis[node];
            for (int nextNode = 0; nextNode < G.length; nextNode++) {
                if (dis[nextNode] == INF && dis[node] + G[node][nextNode] < dis[nextNode]) {
                    dis[nextNode] = dis[node] + G[node][nextNode];
                }
            }
        }
        return price;
    }


    public static void main(String[] args) {

    }
}
