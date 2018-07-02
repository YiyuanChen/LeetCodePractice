class LC861 {
    public int matrixScore(int[][] A) {
        int m = A.length, n = A[0].length;
        for (int i = 0; i < m; i++) {
            if (A[i][0] == 0) flip(A[i]);
        }
        for (int i = 1; i < n; i++) {
            if (needFlip(A, i)) flipCol(A, i);
        }
        int sum = 0;
        for (int i = 0; i < m; i++) {
            int curSum = 0;
            for (int j = 0; j < n; j++) {
                curSum *= 2;
                curSum += A[i][j];
            }
            sum += curSum;
        }
        return sum;
    }
    
    public boolean needFlip(int[][] A, int col) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i][col] == 1) count++;
            else count--;
        }
        return count < 0;
    }
    
    public void flip(int[] row) {
        for (int i = 0; i < row.length; i++) {
            row[i] ^= 1;
        }
    }
    
    public void flipCol(int[][] A, int col) {
        for (int i = 0; i < A.length; i++) {
            A[i][col] ^= 1;
        }
    }
}