class LC865 {
    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int m, n, minStep;
    class Point {
        int x;
        int y;
        public Point(int i, int j) {
            x = i;
            y = j;
        }
    }
    
    public int shortestPathAllKeys(String[] grid) {
        int[] start = new int[2];
        List<Character> keys = new ArrayList<>();
        m = grid.length;
        n = grid[0].length();
        char[][] grids = new char[m][n];
        minStep = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            // grids[i] = new char[grid[i].length()];
            for (int j = 0; j < grid[i].length(); j++) {
                grids[i][j] = grid[i].charAt(j);
                if (grid[i].charAt(j) == '@') {
                    start[0] = i;
                    start[1] = j;
                }
                else if (grid[i].charAt(j) <= 'f' && grid[i].charAt(j) >= 'a') {
                    keys.add(grid[i].charAt(j));
                }
            }
        }
        // for (char[] row : grids) System.out.println(Arrays.toString(row));
        // System.out.println(keys);
        int keyNum = keys.size();
        backtrack(grids, start[0], start[1], new HashSet<>(), keys, 0);
        return minStep == Integer.MAX_VALUE ? -1 : minStep;
    }
    
    public void backtrack(char[][] grid, int x, int y, Set<Character> own, List<Character> keys, int total) {
        if (own.size() == keys.size()) {
            minStep = Math.min(minStep, total);
            return;
        }
        for (int i = 0; i < keys.size(); i++) {
            if (own.contains(keys.get(i))) continue;
            int[] stepCost = getKey(grid, x, y, own, keys.get(i));
            // System.out.println(stepCost[0] + "  " + x + " " + y + "  " + keys.get(i));
            if (stepCost[0] == -1) continue;
            own.add(keys.get(i));
            backtrack(grid, stepCost[1], stepCost[2], own, keys, total + stepCost[0]);
            own.remove(keys.get(i));
        }
    }
    
    public int[] getKey(char[][] grid, int a, int b, Set<Character> own, char target) {
        boolean[][] visited = new boolean[m][n];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(a, b));
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                if (visited[p.x][p.y]) continue;
                visited[p.x][p.y] = true;
                if (grid[p.x][p.y] == target) return new int[]{step, p.x, p.y};
                for (int[] dir : dirs) {
                    int x = p.x + dir[0], y = p.y + dir[1];
                    if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == '#') continue;
                    if (grid[x][y] <= 'F' && grid[x][y] >= 'A' && !own.contains((char)(grid[x][y] - 'A' + 'a'))) continue;
                    queue.offer(new Point(x, y));
                }
            }
            step++;
        }
        return new int[]{-1, -1, -1};
    }
}