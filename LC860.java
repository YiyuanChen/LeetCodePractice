class LC860 {
    public boolean lemonadeChange(int[] bills) {
        int[] changes = new int[2];
        for (int bill : bills) {
            // System.out.println(bill);
            if (bill == 5) {
                changes[0]++;
            }
            else if (bill == 10) {
                if (--changes[0] < 0) return false;
                changes[1]++;
            }
            else {
                int left = 15;
                if (changes[1] > 0) {
                    changes[1]--;
                    left -= 10;
                }
                // System.out.println(changes[0]);
                if (changes[0] < left / 5) return false;
                changes[0] -= left / 5;
            }
        }
        return true;
    }
}