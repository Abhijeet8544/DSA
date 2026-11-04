import java.util.*;

class Solution {

    TreeMap<Integer, Integer> left = new TreeMap<>();
    TreeMap<Integer, Integer> right = new TreeMap<>();

    long leftSum = 0;
    long rightSum = 0;

    int leftSize = 0;
    int rightSize = 0;

    void add(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    void remove(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);

        if (count == 1) {
            map.remove(val);
        } else {
            map.put(val, count - 1);
        }
    }

    void insert(int val) {

        if (leftSize == 0 || val <= left.lastKey()) {
            add(left, val);
            leftSum += val;
            leftSize++;
        } else {
            add(right, val);
            rightSum += val;
            rightSize++;
        }

        balance();
    }

    void erase(int val) {

        if (left.containsKey(val)) {
            remove(left, val);
            leftSum -= val;
            leftSize--;
        } else {
            remove(right, val);
            rightSum -= val;
            rightSize--;
        }

        balance();
    }

    void balance() {

        while (leftSize > rightSize + 1) {
            int val = left.lastKey();

            remove(left, val);
            leftSum -= val;
            leftSize--;

            add(right, val);
            rightSum += val;
            rightSize++;
        }

        while (leftSize < rightSize) {
            int val = right.firstKey();

            remove(right, val);
            rightSum -= val;
            rightSize--;

            add(left, val);
            leftSum += val;
            leftSize++;
        }
    }

    long getCost() {

        long median = left.lastKey();

        return median * leftSize - leftSum
                + rightSum - median * rightSize;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        for (int i = 0; i < x; i++) {
            insert(nums[i]);
        }

        cost[0] = getCost();

        for (int i = x; i < n; i++) {

            erase(nums[i - x]);
            insert(nums[i]);

            cost[i - x + 1] = getCost();
        }

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (int j = 0; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        Arrays.fill(dp[0], 0);

        for (int j = 1; j <= k; j++) {

            for (int i = 1; i <= n; i++) {

                dp[j][i] = dp[j][i - 1];

                if (i >= x && dp[j - 1][i - x] != INF) {

                    dp[j][i] = Math.min(
                        dp[j][i],
                        dp[j - 1][i - x] + cost[i - x]
                    );
                }
            }
        }

        return dp[k][n];
    }
}