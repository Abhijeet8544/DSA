class Solution {
    public int[] validSequence(String word1, String word2) {

        char[] a = word1.toCharArray();
        char[] b = word2.toCharArray();

        int n = a.length;
        int m = b.length;

        // dp[i] = word1[i...] se word2 ke suffix
        // ke kitne characters match ho sakte hain
        int[] dp = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {

            if (j >= 0 && a[i] == b[j]) {
                dp[i] = dp[i + 1] + 1;
                j--;
            } else {
                dp[i] = dp[i + 1];
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        // Greedy
        while (i < n && j < m) {

            // Character already matches
            if (a[i] == b[j]) {

                ans[j] = i;
                j++;

            } else {

                // We can use our ONE mismatch here
                if (dp[i + 1] >= m - 1 - j) {

                    ans[j] = i;
                    j++;

                    // mismatch used
                    i++;

                    break;
                }
            }

            i++;
        }

        // Not enough characters
        if (j < m && i == n) {
            return new int[0];
        }

        // After mismatch, remaining characters
        // must match exactly
        while (j < m && i < n) {

            if (a[i] == b[j]) {
                ans[j] = i;
                j++;
            }

            i++;
        }

        // Could not form complete sequence
        if (j < m) {
            return new int[0];
        }

        return ans;
    }
}