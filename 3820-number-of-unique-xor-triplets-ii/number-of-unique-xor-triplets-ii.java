import java.util.*;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if (n == 1) return 1;

        HashSet<Integer> pairXor = new HashSet<>();
        BitSet ans = new BitSet();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pairXor.add(nums[i] ^ nums[j]);
            }
        }

        for (int x : pairXor) {
            for (int num : nums) {
                ans.set(x ^ num);
            }
        }

        return ans.cardinality();
    }
}