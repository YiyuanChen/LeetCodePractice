class LC593 {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] point = new int[][] {p1, p2, p3, p4};
        Arrays.sort(point, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        Set<Integer> set = new HashSet<>();
        for (int[] p : point) {
            if (!set.add(p[0] * 50000 + p[1])) return false;
        }
        if (isParallelEqual(point[0], point[1], point[2], point[3]) && isSameLen(point[0], point[1], point[0], point[2]) && has90(point[0], point[1], point[2])) return true;
        if (isParallelEqual(point[0], point[2], point[1], point[3]) && isSameLen(point[0], point[1], point[0], point[2]) && has90(point[0], point[1], point[2])) return true;
        return false;
    }
    
    public boolean isParallelEqual(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (p1[0] - p2[0] != p3[0] - p4[0]) return false;
        if (p1[1] - p2[1] != p3[1] - p4[1]) return false;
        return true;
    }
    
    public boolean isSameLen(int[] p1, int[] p2, int[] p3, int[] p4) {
        return Math.pow(p1[0] - p2[0], 2) + Math.pow(p3[0] - p4[0], 2) == Math.pow(p1[1] - p2[1], 2) + Math.pow(p3[1] - p4[1], 2);
    }
    
    public boolean has90(int[] p1, int[] p2, int[] p3) {
        return (p1[0] - p2[0]) * (p1[0] - p3[0]) + (p1[1] - p2[1]) * (p1[1] - p3[1]) == 0;
    }
}