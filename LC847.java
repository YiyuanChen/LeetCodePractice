class LC847 {
    public int shortestPathLength(int[][] graph) {
        /*if (graph.length == 0 || graph[0].length == 0) return 0;
        int n = graph.length;
        int[][] minDist = new int[n][n];
        for (int i = 0; i < n; i++) {
            if (graph[i].length == 0) return -1;
            bfs(graph, minDist[i], i);
            // System.out.println(Arrays.toString(minDist[i]));
        }
        */
        
        // int[][] dp = new int[n][n]; //dp(i, j) represents minPath of visited jth Node at ith
        // for (int i = 1; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         dp[i][j] = Integer.MAX_VALUE;
        //         for (int k = 0; k < n; k++) {
        //             if (j == k) continue;
        //             dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + minDist[k][j]);
        //         }
        //     }
        // }
        // for (int i = 0; i < n; i++) {
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        // int minPath = Integer.MAX_VALUE;
        // for (int num : dp[n - 1]) minPath = Math.min(minPath, num);
        
        /* absolute TLE
        int minPath = Integer.MAX_VALUE;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(i);
            minPath = Math.min(minPath, backtrack(set, i, minDist, n, 0));
            set.remove(i);
        }
        return minPath;
        */
        
        int[][] dp = new int[graph.length][1 << graph.length];
        Queue<State> queue =  new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][1 << i]=0;
            queue.offer(new State(1 << i, i));
        }
        
        while (!queue.isEmpty()) {
            State state = queue.poll();
            
            for (int next : graph[state.source]) {
                int nextMask = state.mask | 1 << next;
                if (dp[next][nextMask] > dp[state.source][state.mask] + 1) {
                    dp[next][nextMask] = dp[state.source][state.mask] + 1;
                    queue.offer(new State(nextMask, next));
                }
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < graph.length; i++) {
            res = Math.min(res, dp[i][(1 << graph.length) - 1]);
        }
        return res;
	}

	class State {
		public int mask, source;
		public State(int m, int s) {
			mask=m;
			source=s;
		}
	}
    
    public int backtrack(Set<Integer> set, int prevNode, int[][] minDist, int n, int len) {
        if (set.size() == n) {
            return len;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (i == prevNode || set.contains(i)) continue;
            set.add(i);
            res = Math.min(res, backtrack(set, i, minDist, n, len + minDist[prevNode][i]));
            // backtrack(set, index + 1, i, minDist, n, len + minDist[prevNode][i], list);
            set.remove(i);
        }
        return res;
    }
    
    public void bfs(int[][] graph, int[] rows, int start) {
        Arrays.fill(rows, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int point = queue.poll();
                if (rows[point] >= 0) continue;
                rows[point] = step;
                for (int nextPoint : graph[point]) {
                    if (rows[nextPoint] == -1) queue.offer(nextPoint);
                }
            }
            // System.out.println(Arrays.toString(rows));
            step++;
        }
        // System.out.println("------------");
    }
}