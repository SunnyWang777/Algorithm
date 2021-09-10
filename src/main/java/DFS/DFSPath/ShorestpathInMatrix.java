package DFS.DFSPath;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1091. Shortest Path in Binary Matrix
 * how to restore the path
 */
public class ShorestpathInMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int nr = grid.length, nc = grid[0].length;
        int[][] path = new int[nr][nc];

        if (grid[0][0] != 0 || grid[nr - 1][nc - 1] != 0)
            return -1;

        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        grid[0][0] = 1;
        path[0][0] = -1;

        int len = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            while (levelSize-- > 0) {
                int[] s = queue.poll();
                if (s[0] == nr - 1 && s[1] == nc - 1) {
                    return len + 1;
                }
                for (int[] dir : dirs) {
                    int next_r = s[0] + dir[0];
                    int next_c = s[1] + dir[1];
                    if (next_r < 0 || next_r >= nr || next_c < 0 || next_c >= nc || grid[next_r][next_c] == 1) {
                        continue;
                    }

                    queue.offer(new int[]{next_r, next_c});
                    grid[next_r][next_c] = 1; // Mark visited
                    path[next_r][next_c] = s[0] * nc + s[1];
                }
            }
            len++;
        }

        printPath(path, nr - 1, nc - 1, nc);

        return -1;
    }

    private void printPath(int[][] path, int dr, int dc, int colLen) {
        if (path[dr][dc] == -1)
            return;
        printPath(path, path[dr][dc] / colLen, path[dr][dc] % colLen, colLen);
        System.out.printf("cur position " + dr + " , " + dc + " ->" + path[dr][dc] / colLen + " , " + path[dr][dc] % colLen);
    }
}
