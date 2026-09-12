import java.util.*;

class Solution {

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    int n;
    int[][] arr;
    int[] next;
    State[][] dp;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        n = intervals.size();
        arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0); // left
            arr[i][1] = intervals.get(i).get(1); // right
            arr[i][2] = intervals.get(i).get(2); // weight
            arr[i][3] = i;                       // original index
        }

        // Sort by starting position
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);
            return Integer.compare(a[3], b[3]);
        });

        // Find next interval whose left > current right
        next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = lowerBound(arr[i][1]);
        }

        dp = new State[n + 1][5];

        State ans = solve(0, 4);

        int[] result = new int[ans.indices.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = ans.indices.get(i);
        }

        return result;
    }

    // First interval with start > right
    int lowerBound(int right) {

        int lo = 0;
        int hi = n;

        while (lo < hi) {

            int mid = lo + (hi - lo) / 2;

            if (arr[mid][0] > right)
                hi = mid;
            else
                lo = mid + 1;
        }

        return lo;
    }

    State solve(int i, int k) {

        if (i == n || k == 0) {
            return new State(0, new ArrayList<>());
        }

        if (dp[i][k] != null)
            return dp[i][k];

        // Option 1: Skip current interval
        State skip = solve(i + 1, k);

        // Option 2: Take current interval
        State nextState = solve(next[i], k - 1);

        List<Integer> takeList = new ArrayList<>();

        takeList.add(arr[i][3]);
        takeList.addAll(nextState.indices);

        // IMPORTANT:
        // Sort by original interval index
        Collections.sort(takeList);

        State take = new State(
            (long) arr[i][2] + nextState.score,
            takeList
        );

        // Choose maximum score
        if (take.score > skip.score) {

            dp[i][k] = take;

        } else if (take.score < skip.score) {

            dp[i][k] = skip;

        } else {

            // Same score -> lexicographically smaller
            if (compare(take.indices, skip.indices) < 0)
                dp[i][k] = take;
            else
                dp[i][k] = skip;
        }

        return dp[i][k];
    }

    // Lexicographical comparison
    int compare(List<Integer> a, List<Integer> b) {

        int len = Math.min(a.size(), b.size());

        for (int i = 0; i < len; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}