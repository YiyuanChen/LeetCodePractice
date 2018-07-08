class LC868 {
    public int[][] transpose(int[][] A) {
        if (A.length == 0 || A[0].length == 0) return new int[][]{};
        int m = A.length, n = A[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[j][i] = A[i][j];
            }
        }
        return res;
    }
}