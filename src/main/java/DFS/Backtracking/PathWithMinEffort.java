package DFS.Backtracking;

import java.util.*;

public class PathWithMinEffort {

    //region Uion Find
    class Edge {
        int x;
        int y;
        int difference;

        Edge(int x, int y, int difference) {
            this.x = x;
            this.y = y;
            this.difference = difference;
        }
    }

    class UnionFind {
        int[] parent;
        int[] rank;
        List<Edge> edgeList;

        public UnionFind(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;
            parent = new int[row * col];
            edgeList = new ArrayList<>();
            rank = new int[row * col];
            for (int currentRow = 0; currentRow < row; currentRow++) {
                for (int currentCol = 0; currentCol < col; currentCol++) {
                    if (currentRow > 0) {
                        edgeList.add(new Edge(currentRow * col + currentCol,
                                (currentRow - 1) * col + currentCol,
                                Math.abs(heights[currentRow][currentCol] - heights[currentRow - 1][currentCol]))
                        );
                    }
                    if (currentCol > 0) {
                        edgeList.add(new Edge(currentRow * col + currentCol,
                                currentRow * col + currentCol - 1,
                                Math.abs(heights[currentRow][currentCol] - heights[currentRow][currentCol - 1]))
                        );
                    }
                    parent[currentRow * col + currentCol] = currentRow * col + currentCol;
                }
            }
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX != parentY) {
                if (rank[parentX] > rank[parentY]) parent[parentY] = parentX;
                else if (rank[parentX] < rank[parentY]) parent[parentX] = parentY;
                else {
                    parent[parentY] = parentX;
                    rank[parentX] += 1;
                }
            }
        }
    }

    public int minimumEffortPathIII(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        if (row == 1 && col == 1) return 0;
        UnionFind unionFind = new UnionFind(heights);
        List<Edge> edgeList = unionFind.edgeList;
        Collections.sort(edgeList, (e1, e2) -> e1.difference - e2.difference);

        for (int i = 0; i < edgeList.size(); i++) {
            int x = edgeList.get(i).x;
            int y = edgeList.get(i).y;
            unionFind.union(x, y);
            if (unionFind.find(0) == unionFind.find(row * col - 1)) return edgeList.get(i).difference;
        }
        return -1;
    }
    //endregion

    //region Dijkistra O(m⋅nlog(m⋅n))
    int directions[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int minimumEffortPathII(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        int[][] differenceMatrix = new int[row][col];
        for (int[] eachRow : differenceMatrix)
            Arrays.fill(eachRow, Integer.MAX_VALUE);
        differenceMatrix[0][0] = 0;
        PriorityQueue<Cell> queue = new PriorityQueue<Cell>((a, b) -> (a.difference.compareTo(b.difference)));
        boolean[][] visited = new boolean[row][col];
        queue.add(new Cell(0, 0, differenceMatrix[0][0]));

        while (!queue.isEmpty()) {
            Cell curr = queue.poll();
            visited[curr.x][curr.y] = true;
            if (curr.x == row - 1 && curr.y == col - 1)
                return curr.difference;
            for (int[] direction : directions) {
                int adjacentX = curr.x + direction[0];
                int adjacentY = curr.y + direction[1];
                if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                    int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[curr.x][curr.y]);
                    int maxDifference = Math.max(currentDifference, differenceMatrix[curr.x][curr.y]);
                    if (differenceMatrix[adjacentX][adjacentY] > maxDifference) {
                        differenceMatrix[adjacentX][adjacentY] = maxDifference;
                        queue.add(new Cell(adjacentX, adjacentY, maxDifference));
                    }
                }
            }
        }
        return differenceMatrix[row - 1][col - 1];
    }

    class Cell {
        int x;
        int y;
        Integer difference;

        Cell(int x, int y, Integer difference) {
            this.x = x;
            this.y = y;
            this.difference = difference;
        }
    }
    //endregion

    //region DFS
    public int minimumEffortPath(int[][] heights) {
        return dfs(heights, 0, 0, heights.length, heights[0].length, 0);
    }

    int maxSoFar = Integer.MAX_VALUE;
    int dirs[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int dfs(int[][] heights, int x, int y, int m, int n, int maxDifference) {
        if(x == m-1 && y == n-1)
            return maxDifference;

        int res = 0;
        int minEffort = Integer.MAX_VALUE;
        int currentHeight = heights[x][y];
        heights[x][y] = 0;

        for(int d = 0; d < 4; d++) {
            int r = x + dirs[d][0];
            int c = y + dirs[d][1];
            if(isValidCell(r, c, m, n) && heights[r][c] != 0) {
                int curDiff = Math.abs(heights[r][c] - currentHeight);
                curDiff = Math.max(maxDifference, curDiff);
                if (curDiff < maxSoFar) {
                    int ans = dfs(heights, r, c, m, n, curDiff);
                    minEffort = Math.min(minEffort, ans);
                }
            }
        }

        heights[x][y] = currentHeight;
        return minEffort;
    }

    boolean isValidCell(int x, int y, int row, int col) {
        return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
    }
    //endregion


    public static void main(String[] args) {
        int[][] heights = {{1,2,2},{3,8,2},{5,3,5}};
        PathWithMinEffort test = new PathWithMinEffort();
        test.minimumEffortPath(heights);
    }
}
