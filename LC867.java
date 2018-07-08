class LC867 {
    public int primePalindrome(int N) {
        String str = String.valueOf(N);
        int l = str.length();
        String half = str.substring(0, l / 2);
        if (half.length() == 0) {
            for (int i = 2; i < 12; i++) {
                if (i >= N && isPrime(i)) return i;
            }
        }
        int start = Integer.parseInt(half);
        int minOdd = Integer.MAX_VALUE, minEven = Integer.MAX_VALUE;
        boolean flag = false;
        for (int i = start; i < (int)Math.pow(10, l / 2 + 1); i++) {
            String cur = String.valueOf(i);
            if (flag) break;
            if ((cur.charAt(0) - '0') % 2 == 0) continue;
            StringBuilder sb = new StringBuilder(cur);
            String res = cur + sb.reverse().toString();
            try {
                int val = Integer.parseInt(res);
                if (val < minEven && val >= N && isPalin(res) && isPrime(val)) {
                    minEven = val;
                    flag = true;
                }
            }
            catch (Exception e) {}
        }
        flag = false;
        for (int i = (int)Math.pow(10, l / 2 - 1); i < (int)Math.pow(10, l / 2 + 1); i++) {
            String cur = String.valueOf(i);
            if (flag) break;
            if ((cur.charAt(0) - '0') % 2 == 0) continue;
            String reverse = new StringBuilder(cur).reverse().toString();
            for (int j = 0; j < 10; j++) {
                String res = cur + j + reverse;
                try {
                    int val = Integer.parseInt(res);
                    if (val < minOdd && val >= N && isPalin(res) && isPrime(val)) {
                        minOdd = val;
                        flag = true;
                    }
                }
                catch (Exception e) {}
            }
        }
        // System.out.println(minEven + "   " + minOdd);
        // for (int i = start; i < (int)Math.pow(10, l / 2 + 2); i++) {
        //     String cur = String.valueOf(i);
        //     if ((cur.charAt(0) - '0') % 2 == 0) continue;
        //     if (cur.length() * 2 == l) {
        //         StringBuilder sb = new StringBuilder(cur);
        //         String res = cur + sb.reverse().toString();
        //         int val = Integer.parseInt(res);
        //         if (val >= N && isPalin(res) && isPrime(val)) return val;
        //     }
        //     else if (cur.length() * 2 == l - 1) {
        //         String reverse = new StringBuilder(cur).reverse().toString();
        //         for (int j = 0; j < 10; j++) {
        //             String res = cur + j + reverse;
        //             int val = Integer.parseInt(res);
        //             if (val >= N && isPalin(res) && isPrime(val)) return val;
        //         }
        //     }
        //     else if (cur.length() * 2 == l + 1) {
        //         StringBuilder sb = new StringBuilder(cur);
        //         String res = cur + sb.reverse().toString();
        //         int val = Integer.parseInt(res);
        //         if (val >= N && isPalin(res) && isPrime(val)) return val;
        //     }
        //     else if (cur.length() * 2 == l + 2) {
        //         String reverse = new StringBuilder(cur).reverse().toString().substring(1);
        //         String res = cur + reverse;
        //         int val = Integer.parseInt(res);
        //         if (val >= N && isPalin(res) && isPrime(val)) return val;
        //     }
        // }
        return Math.min(minOdd, minEven);
    }
    
    public boolean isPalin(String n) {
        String val = n;
        int l = 0, r = val.length() - 1;
        while (l < r) {
            if (val.charAt(l++) != val.charAt(r--)) return false;
        }
        return true;
    }
    
    public boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2 || n == 3) return true;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}