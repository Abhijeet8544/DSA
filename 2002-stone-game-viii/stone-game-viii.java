class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        int sum = 0;
        for (int x : stones) {
            sum += x;
        }

        // Start from taking the first n-1 stones
        int ans = sum;

        // Remove stones from the right side conceptually
        for (int i = n - 1; i >= 2; i--) {
            sum -= stones[i];
            ans = Math.max(ans, sum - ans);
        }

        return ans;
    }
}