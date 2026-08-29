import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Store {value, originalIndex}
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        // Sort by value
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] ans = new int[n];

        int start = 0;

        while (start < n) {
            int end = start;

            // Find one connected group
            while (end + 1 < n &&
                   (long) arr[end + 1][0] - arr[end][0] <= limit) {
                end++;
            }

            // Collect indices and values of this group
            List<Integer> indices = new ArrayList<>();
            List<Integer> values = new ArrayList<>();

            for (int i = start; i <= end; i++) {
                values.add(arr[i][0]);
                indices.add(arr[i][1]);
            }

            // Smallest values should go to smallest indices
            Collections.sort(indices);

            for (int i = 0; i < values.size(); i++) {
                ans[indices.get(i)] = values.get(i);
            }

            start = end + 1;
        }

        return ans;
    }
}