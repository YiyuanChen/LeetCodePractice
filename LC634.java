class LC634 {
    public int findDerangement(int n) {
        if (n <= 1) return 0;
        if (n == 2) return 1;
        if (n == 3) return 2;
        long prev = 1, cur = 2, res = 0;
        int mod = 1_000_000_007;
        for (int i = 4; i <= n; i++) {
            res = (prev + cur) * (i - 1);
            prev = cur % mod;
            cur = res % mod;
        }
        return (int) (res % mod);
    }
}