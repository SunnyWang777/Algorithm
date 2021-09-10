package DFS.DFSPath;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PathWithMaximumMinimumValue {
    int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public List<String> maximumMinimumPath(int[][] A) {
        int m = A.length, n = A[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[0] - a[0]));
        pq.offer(new int[]{A[0][0], 0, 0});
        int res = A[0][0];
        visited[0][0] = true;

        List<String> path = new ArrayList<>();

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            // System.out.println(top[0] + " " + top[1] + " " + top[2]);
            res = Math.min(res, top[0]);
            path.add("val " + top[0] + " x : " + top[1] + " y: " + top[2]);
            if (top[1] == m - 1 && top[2] == n - 1) {
                break;
            }
            for (int[] dir : dirs) {
                int x = dir[0] + top[1];
                int y = dir[1] + top[2];
                // System.out.println("can" + " " + x + " " + y);
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) continue;
                // System.out.println("add" + A[x][y] + " " + x + " " + y);
                pq.offer(new int[]{A[x][y], x, y});
                visited[x][y] = true;
            }
        }
        return path;
    }

    public static void main(String[] args) {
        int[][] matrix = {{5, 4, 5}, {1, 2, 6}, {7, 4, 6}};
        PathWithMaximumMinimumValue test = new PathWithMaximumMinimumValue();
        List<String> path = test.maximumMinimumPath(matrix);

        for(String p: path)
            System.out.println(p);
    }
}
