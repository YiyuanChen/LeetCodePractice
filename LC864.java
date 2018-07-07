class LC864 {
    int size;
    Map<Integer, Integer> map;
    Random rand;
    
    public Solution(int N, int[] blacklist) {
        rand = new Random();
        size = N - blacklist.length;
        map = new HashMap<>();
        Set<Integer> blackSet = new HashSet<>();
        for (int i = size; i < N; i++) blackSet.add(i);
        for (int num : blacklist) {
            blackSet.remove(num);
        }
        Iterator<Integer> it = blackSet.iterator();
        for (int i = 0; i < blacklist.length; i++) {
            if (blacklist[i] < size) {
                map.put(blacklist[i], it.next());
            }
        }
        
    }
    
    public int pick() {
        int val = rand.nextInt(size);
        return map.getOrDefault(val, val);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(N, blacklist);
 * int param_1 = obj.pick();
 */