class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Store reserved seats for each row
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.put(row, map.getOrDefault(row, 0) | (1 << col));
        }

        // Masks for possible groups
        int left = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        int middle = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
        int right = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);

        // Every completely empty row can fit 2 groups
        int ans = (n - map.size()) * 2;

        // Process rows having reservations
        for (int mask : map.values()) {

            if ((mask & left) == 0 && (mask & right) == 0) {
                // Both left and right blocks are available
                ans += 2;
            }
            else if ((mask & left) == 0 ||
                     (mask & middle) == 0 ||
                     (mask & right) == 0) {
                // At least one block is available
                ans += 1;
            }
        }

        return ans;
    }
}