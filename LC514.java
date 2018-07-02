class LC514 {
    public int findRotateSteps(String ring, String key) {
        int n = ring.length(), m = key.length();
        /*
        int[][] dp = new int[m + 1][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (ring.charAt(k) == key.charAt(i)) {
                        int step = Math.abs(j - k);
                        step = Math.min(step, n - step);
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][k] + step);
                    }
                }
            }
        }
        return dp[0][0] + m;
        */
        Map<Integer, Integer> mem = new HashMap<>();
        return dfs(0, 0, mem, ring, key) + key.length();
    }
    
    public int dfs(int ringPos, int keyPos, Map<Integer, Integer> mem, String ring, String key) {
        if (keyPos == key.length()) return 0;
        int index = ringPos * key.length() + keyPos;
        if (mem.containsKey(index)) return mem.get(index);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < ring.length(); i++) {
            if (ring.charAt(i) == key.charAt(keyPos)) {
                int minStep = Math.abs(i - ringPos);
                minStep = Math.min(minStep, ring.length() - minStep);
                res = Math.min(res, minStep + dfs(i, keyPos + 1, mem, ring, key));
            }
        }
        mem.put(index, res);
        return res;
    }
    
}