class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int diff = 0;
        int qDiff = 0;

        for (int i = 0; i < half; i++) {
            if (num.charAt(i) == '?') {
                qDiff++;
            } else {
                diff += num.charAt(i) - '0';
            }
        }

        for (int i = half; i < n; i++) {
            if (num.charAt(i) == '?') {
                qDiff--;
            } else {
                diff -= num.charAt(i) - '0';
            }
        }

        return diff * 2 != -9 * qDiff;
    }
}