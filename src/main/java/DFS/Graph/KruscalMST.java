package DFS.Graph;

import java.util.Arrays;
import java.util.Comparator;

//https://www.cnblogs.com/hi3254014978/p/12672367.html
public class KruscalMST {

    static class Edge {
        int u, v;
        int cost;
    }

    public static Edge[] edges = new Edge[10010];   // 存储所有的边的数据
    public static int[] root = new int[110];      // 存储每个结点所在集合
    public static int e, v;     // 分别表示边数和顶点数

    public int Krusal() {
        Arrays.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge a, Edge b) {
                return a.cost - b.cost;
            }
        });

        int cost = 0;   // 总花费
        int edgeCount = 0;  // 当前已归纳的边数
        for (int i = 0; i < e; i++) {
            // 判断 边的链各个顶点是否属于同一个集合
            int uRoot = findRoot(edges[i].u);
            int vRoot = findRoot(edges[i].v);
            if (uRoot != vRoot) {
                root[vRoot] = uRoot;
                cost += edges[i].cost;
                edgeCount++;
                if (edgeCount == v - 1)
                    return cost;
            }
        }

        return -1;
    }

    private int findRoot(int idx) {
        while(root[idx] != idx) {
            idx = root[idx];
        }

        return idx;
    }

    public static void main(String[] args) {

    }
}


