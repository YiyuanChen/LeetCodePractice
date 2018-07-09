class LC656 {
    public List<Integer> cheapestJump(int[] A, int B) {
        List<Integer> res = new ArrayList<>();
        int l = A.length;
        if (l == 0 || A[l - 1] == -1 || A[0] == -1) return res;
        int[] dp = new int[l];
        dp[l - 1] = A[l - 1];
        for (int i = l - 2; i >= 0; i--) {
            if (A[i] == -1) {
                dp[i] = -1;
                continue;
            }
            int curMin = Integer.MAX_VALUE;
            for (int j = i + 1; j <= Math.min(i + B, l - 1); j++) {
                if (j < l && dp[j] >= 0) {
                    curMin = Math.min(dp[j], curMin);
                }
            }
            if (curMin == Integer.MAX_VALUE) dp[i] = -1;
            else dp[i] = curMin + A[i];
        }
        if (dp[0] == -1) return res;
        // System.out.println(Arrays.toString(dp));
        int next = dp[0];
        for (int i = 0; i < l; i++) {
            if (dp[i] == next) {
                res.add(i + 1);
                next -= A[i];
            }
        }
        return res;
    }
}