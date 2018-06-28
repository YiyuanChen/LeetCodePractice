class LC474 {
    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        if (l == 0) return 0;
        int[][][] dp = new int[l + 1][m + 1][n + 1];
        for (int i = 0; i <= l; i++) {
            int[] group = i > 0 ? getNum(strs[i - 1]) : new int[]{};
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (i == 0) dp[i][j][k] = 0;
                    else if (j >= group[0] && k >= group[1]) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], 1 + dp[i - 1][j - group[0]][k - group[1]]);
                    }
                    else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }
        return dp[l][m][n];
    }
    
    public int[] getNum(String str) {
        int zeros = 0, ones = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') zeros++;
            else ones++;
        }
        return new int[]{zeros, ones};
    }
}