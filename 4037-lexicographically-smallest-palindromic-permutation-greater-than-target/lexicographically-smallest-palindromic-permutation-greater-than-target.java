class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Check palindrome possibility
        int odd = 0;
        int mid = -1;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                mid = i;
            }
        }

        if (odd > 1) return "";

        int halfLen = n / 2;

        // Counts available for the first half
        int[] half = new int[26];
        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
        }

        char[] t = target.toCharArray();

        /*
         * The palindrome comparison is decided by:
         *
         * first half
         * then middle (if odd)
         *
         * So first find the smallest possible left half.
         */

        char[] leftTarget = new char[halfLen];

        for (int i = 0; i < halfLen; i++) {
            leftTarget[i] = t[i];
        }

        // Try to find smallest left half >= target's left half.
        String left = smallestGreaterOrEqual(leftTarget, half);

        if (left == null) return "";

        String ans = build(left, mid);

        if (ans.compareTo(target) > 0) {
            return ans;
        }

        // Left half was equal and middle was not enough.
        // Find the next possible left half.
        left = nextPermutation(left, half);

        if (left == null) return "";

        return build(left, mid);
    }

    private String smallestGreaterOrEqual(char[] target, int[] original) {
        int m = target.length;

        // Try exact target first
        int[] cnt = original.clone();

        boolean possible = true;

        for (char c : target) {
            if (cnt[c - 'a'] == 0) {
                possible = false;
                break;
            }
            cnt[c - 'a']--;
        }

        if (possible) {
            return new String(target);
        }

        /*
         * Find the rightmost position where we can increase
         * the target character.
         */
        for (int i = m - 1; i >= 0; i--) {
            int[] remaining = original.clone();

            // Use target[0..i-1]
            boolean ok = true;

            for (int j = 0; j < i; j++) {
                int x = target[j] - 'a';

                if (remaining[x] == 0) {
                    ok = false;
                    break;
                }

                remaining[x]--;
            }

            if (!ok) continue;

            int cur = target[i] - 'a';

            // Choose smallest character greater than target[i]
            for (int c = cur + 1; c < 26; c++) {
                if (remaining[c] > 0) {
                    char[] res = new char[m];

                    for (int j = 0; j < i; j++) {
                        res[j] = target[j];
                    }

                    res[i] = (char) ('a' + c);
                    remaining[c]--;

                    int pos = i + 1;

                    for (int x = 0; x < 26; x++) {
                        while (remaining[x] > 0) {
                            res[pos++] = (char) ('a' + x);
                            remaining[x]--;
                        }
                    }

                    return new String(res);
                }
            }
        }

        return null;
    }

    private String nextPermutation(String left, int[] original) {
        char[] a = left.toCharArray();
        int n = a.length;

        // Standard next permutation.
        int i = n - 2;

        while (i >= 0 && a[i] >= a[i + 1]) {
            i--;
        }

        if (i < 0) return null;

        int j = n - 1;

        while (a[j] <= a[i]) {
            j--;
        }

        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;

        // Reverse suffix
        int l = i + 1;
        int r = n - 1;

        while (l < r) {
            tmp = a[l];
            a[l] = a[r];
            a[r] = tmp;
            l++;
            r--;
        }

        return new String(a);
    }

    private String build(String left, int mid) {
        StringBuilder sb = new StringBuilder();

        sb.append(left);

        if (mid != -1) {
            sb.append((char) ('a' + mid));
        }

        sb.append(new StringBuilder(left).reverse());

        return sb.toString();
    }
}