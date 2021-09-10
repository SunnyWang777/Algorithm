package DFS.DFSPath;

/**
 * https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
 * */
public class GraphDFSPath {
//    class Graph {
//        int vertex();
//        int edge();
//        void addEdge(int v, int w);
//        Iterable<Integer> adj(int v);
//        int degree(int v);
//        String toString();
//    }
//
//    private final Graph graph;
//    private final boolean[] marked;
//    private final int[] edgeTo; // 用于记录路径
//    private final int originPoint;
//
//    public GraphDFSPath(Graph graph, int originPoint) {
//        int vertex = graph.vertex();
//        this.graph = graph;
//        this.originPoint = originPoint;
//        this.marked = new boolean[vertex];
//        this.edgeTo = new int[vertex];
//       // validateVertex(originPoint);
//        dfs(originPoint);
//    }
//
//    private void dfs(int vertex) {
//        marked[vertex] = true;
//
//        for(int nei: graph.adj(vertex)) {
//            if(!marked[nei]) {
//                marked[nei] = true;
//                edgeTo[nei] = vertex;
//                dfs(nei);
//            }
//        }
//    }
//
//    public Iterable<Integer> pathTo(int vertex) {
//        Stack<Integer> stack = new Stack<>();
//// 从指定顶点处向上遍历路径(直到起点)
//        for(int x = vertex; x!= originPoint ; x = edgeTo[x]) {
//            stack.push(x);
//        }
//        stack.push(originPoint);
//        return stack;
//    }
}
