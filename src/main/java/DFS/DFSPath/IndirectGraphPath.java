package DFS.DFSPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IndirectGraphPath {
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> ls = new ArrayList<>();
        for(int i = 0;i<n;i++){
            ls.add(new ArrayList<>());
        }

        for(int i = 0;i<edges.length;i++){
            int[] edge = edges[i];
            ls.get(edge[0]).add(edge[1]);
            ls.get(edge[1]).add(edge[0]);
        }

        int count = 0;
        int[] path = new int[n];
        boolean[] v = new boolean[n];
        for(int i = 0;i<n;i++){
            if(!v[i]){
                path[i] = -1;
                dfs(ls,i,v, path);
                count++;
            }
        }

        //pri
        return count++;
    }

    private void dfs(List<List<Integer>> gr, int start, boolean[] v, int[] path){
        if(v[start])
            return;
        Stack<Integer> st = new Stack<>();
        st.push(start);
        while(!st.isEmpty()) {
            int cur = st.pop();
            for(int nei: gr.get(cur)) {
                if(v[nei])
                    continue;
                path[nei] = cur;
                st.push(nei);
            }
            v[cur] = true;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{0,1},{1,2},{3,4}};
        IndirectGraphPath test = new IndirectGraphPath();
        test.countComponents(5, matrix);
    }
}
