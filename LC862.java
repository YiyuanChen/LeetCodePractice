class LC862 {
    public int shortestSubarray(int[] A, int K) {
        // int curSum = A[0];
        int curSum = 0;
        int minSize = A.length + 1;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        // map.put(0, -1);
        for (int i = 0; i < A.length; i++) {
            // dp[i] = Math.max(dp[i - 1] + A[i], A[i]);
            // dp[i] = dp[i - 1] + A[i];
            if (A[i] >= K) return 1;
            curSum += A[i];
            int need = curSum - K;
            // Map<Integer, Integer> subMap = map.headMap(need, true);
            if (curSum >= K) {
                minSize = Math.min(minSize, i + 1);
            }
            // if (!subMap.isEmpty()) {
            //     int maxIndex = -1;
            //     for (int val : subMap.values()) {
            //         maxIndex = Math.max(maxIndex, val);
            //     }
            //     minSize = Math.min(i - maxIndex, minSize);
            // }
            // map.put(curSum, i);
            // Set<Integer> tmp = new HashSet<>();
            // for (int key : subMap.keySet()) tmp.add(key);
            // for (int key : tmp) {
            //     map.remove(key);
            // }
            
            
            Integer num = map.floorKey(need);
            while (num != null) {
                if (i - map.get(num) < minSize) {
                    minSize = i - map.get(num);
                }
                map.remove(num);
                num = map.lowerKey(num);
            }
            map.put(curSum, i);
        }
        return minSize == A.length + 1 ? -1 : minSize;
    }
}