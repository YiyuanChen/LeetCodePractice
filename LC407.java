class LC407 {
    class Point {
        int x;
        int y;
        int height;
        public Point(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
    
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length == 0 || heightMap[0].length == 0) return 0;
        int m = heightMap.length, n = heightMap[0].length;
        if (m < 2 || n < 2) return 0;
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> a.height - b.height);
        for (int i = 0; i < n; i++) {
            pq.offer(new Point(0, i, heightMap[0][i]));
            pq.offer(new Point(m - 1, i, heightMap[m - 1][i]));
        }
        for (int i = 1; i < m - 1; i++) {
            pq.offer(new Point(i, 0, heightMap[i][0]));
            pq.offer(new Point(i, n - 1, heightMap[i][n - 1]));
        }
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            if (visited[cur.x][cur.y]) continue;
            visited[cur.x][cur.y] = true;
            for (int[] dir : dirs) {
                int i = cur.x + dir[0], j = cur.y + dir[1];
                if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j]) continue;
                if (heightMap[i][j] < cur.height) {
                    res += cur.height - heightMap[i][j];
                    heightMap[i][j] = cur.height;
                }
                pq.offer(new Point(i, j, heightMap[i][j]));
            }
        }
        return res;
    }
}