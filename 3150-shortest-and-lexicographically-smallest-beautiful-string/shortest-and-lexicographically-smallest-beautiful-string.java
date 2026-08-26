class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();

        // Store positions of all 1s
        int[] pos = new int[n];
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                pos[cnt++] = i;
            }
        }

        // Not enough 1s
        if (cnt < k) {
            return "";
        }

        int bestLen = Integer.MAX_VALUE;
        String ans = "";

        // Consider every group of k consecutive 1s
        for (int i = 0; i + k - 1 < cnt; i++) {
            int left = pos[i];
            int right = pos[i + k - 1];

            int len = right - left + 1;

            String cur = s.substring(left, right + 1);

            if (len < bestLen ||
                (len == bestLen && cur.compareTo(ans) < 0)) {

                bestLen = len;
                ans = cur;
            }
        }

        return ans;
    }
}