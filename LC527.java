class 527 {
    class TrieNode {
        int count;
        TrieNode[] children;
        int[] tails;
        public TrieNode() {
            count = 0;
            children = new TrieNode[26];
            // tails = new int[26];
        }
    }
    
    class Trie {
        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        
        public void insert(String s) {
            TrieNode cur = root;
            cur.count++;
            char tail = s.charAt(s.length() - 1);
            for (int i = 1; i < s.length() - 2; i++) {
                char tmp = s.charAt(i);
                if (cur.children[tmp - 'a'] == null) cur.children[tmp - 'a'] = new TrieNode();
                cur = cur.children[tmp - 'a'];
                cur.count++;
                // cur.tails[tail - 'a']++;
            }
            /*
            TrieNode cur = root;
            char tail = s.charAt(s.length() - 1);
            for (int i = 0; i < s.length() - 2; i++) {
                char tmp = s.charAt(i);
                if (cur.children[tmp - 'a'] == null) cur.children[tmp - 'a'] = new TrieNode();
                cur = cur.children[tmp - 'a'];
                cur.count++;
                cur.tails[tail - 'a']++;
            }
            */
        }
        
        public String get(String s) {
            char tail = s.charAt(s.length() - 1), head = s.charAt(0);
            TrieNode cur = root;//.children[head - 'a'];
            if (cur.count == 1) return "" + head + (s.length() - 2) + tail;
            StringBuilder sb = new StringBuilder();
            sb.append(head);
            for (int i = 1; i < s.length() - 3; i++) {
                char tmp = s.charAt(i);
                cur = cur.children[tmp - 'a'];
                sb.append(tmp);
                if (cur.count == 1) {
                    // System.out.println(s + tmp + cur.count + cur.tails[tail - 'a']);
                    sb.append(s.length() - i - 2);
                    sb.append(tail);
                    return sb.toString();
                }
            }
            return s;
            
            /*
            char tail = s.charAt(s.length() - 1), head = s.charAt(0);
            TrieNode cur = root;//.children[head - 'a'];
            StringBuilder sb = new StringBuilder();
            // sb.append(head);
            for (int i = 0; i < s.length() - 3; i++) {
                char tmp = s.charAt(i);
                cur = cur.children[tmp - 'a'];
                sb.append(tmp);
                if (cur.count == 1 || cur.tails[tail - 'a'] == 1) {
                    // System.out.println(s + tmp + cur.count + cur.tails[tail - 'a']);
                    sb.append(s.length() - i - 2);
                    sb.append(tail);
                    return sb.toString();
                }
            }
            return s;
            **/
        }
    }
    
    public String getAbbr(String s) {
        if (s.length() < 4) return s;
        return s.substring(0, 1) + (s.length() - 2) + s.charAt(s.length() - 1);
    }
    
    public List<String> wordsAbbreviation(List<String> dict) {
        List<String> res = new ArrayList<>();
        if (dict.size() == 0) return res;
        Map<String, Trie> map = new HashMap<>();
        for (String word : dict) {
            if (word.length() < 4) continue;
            String abbr = getAbbr(word);
            if (!map.containsKey(abbr)) map.put(abbr, new Trie());
            map.get(abbr).insert(word);
        }
        for (String word : dict) {
            if (word.length() < 4) res.add(word);
            else {
                res.add(map.get(getAbbr(word)).get(word));
            }
        }
        // Map<Integer, Trie> map = new HashMap<>();
        // for (String word : dict) {
        //     int l = word.length();
        //     if (l < 4) continue;
        //     // map.putIfAbsent(l, new Trie());
        //     if (!map.containsKey(l)) map.put(l, new Trie());
        //     map.get(l).insert(word);
        // }
        // for (String word : dict) {
        //     if (word.length() < 4) res.add(word);
        //     else {
        //         res.add(map.get(word.length()).get(word));
        //     }
        // }
        return res;
    }
    /*
    public List<String> wordsAbbreviation(List<String> dict) {
        List<String> res = new ArrayList<>();
        if (dict.size() == 0) return res;
        Map<String, Integer> map = new HashMap<>();
        for (String str : dict) {
            getAbbr(map, str);
        }
        for (String str : dict) {
            res.add(getStr(map, str));
        }
        return res;
    }
    
    public void getAbbr(Map<String, Integer> map, String str) {
        int l = str.length();
        if (l < 4) {
            map.put(str, map.getOrDefault(str, 0) + 1);
            return;
        }
        for (int i = 1; i < l - 2; i++) {
            String abbr = str.substring(0, i) + (l - 1 - i) + str.charAt(l - 1);
            map.put(abbr, map.getOrDefault(abbr, 0) + 1);
        }
    }
    
    public String getStr(Map<String, Integer> map, String str) {
        int l = str.length();
        if (l < 4) {
            return str;
        }
        for (int i = 1; i < l - 2; i++) {
            String abbr = str.substring(0, i) + (l - 1 - i) + str.charAt(l - 1);
            if (map.get(abbr) == 1) return abbr;
        }
        return str;
    }
    */
}