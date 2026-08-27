class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        char[] ans = new char[n];

        for (int i = 0; i < n; i++) {
            int t = target.charAt(i) - 'a';

            // Try to keep current character equal to target
            if (freq[t] > 0) {
                ans[i] = target.charAt(i);
                freq[t]--;
            } else {
                // Make current position greater
                for (int c = t + 1; c < 26; c++) {
                    if (freq[c] > 0) {
                        ans[i] = (char) ('a' + c);
                        freq[c]--;

                        return finish(ans, i, freq);
                    }
                }

                // Can't make current position greater,
                // so backtrack.
                return backtrack(ans, i - 1, freq, target);
            }
        }

        // s can form target exactly.
        // Need a strictly greater permutation.
        return backtrack(ans, n - 1, freq, target);
    }

    private String backtrack(
            char[] ans,
            int pos,
            int[] freq,
            String target) {

        for (int i = pos; i >= 0; i--) {

            // Restore character used at position i
            freq[ans[i] - 'a']++;

            int t = target.charAt(i) - 'a';

            // Find smallest character > target[i]
            for (int c = t + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    ans[i] = (char) ('a' + c);
                    freq[c]--;

                    return finish(ans, i, freq);
                }
            }
        }

        return "";
    }

    private String finish(char[] ans, int pos, int[] freq) {
        StringBuilder sb = new StringBuilder();

        // Keep prefix unchanged
        for (int i = 0; i <= pos; i++) {
            sb.append(ans[i]);
        }

        // Put remaining characters in smallest order
        for (int c = 0; c < 26; c++) {
            while (freq[c] > 0) {
                sb.append((char) ('a' + c));
                freq[c]--;
            }
        }

        return sb.toString();
    }
}