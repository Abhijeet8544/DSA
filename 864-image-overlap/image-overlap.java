class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        // Store positions of 1s in both images
        java.util.List<int[]> ones1 = new java.util.ArrayList<>();
        java.util.List<int[]> ones2 = new java.util.ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    ones1.add(new int[]{i, j});
                }

                if (img2[i][j] == 1) {
                    ones2.add(new int[]{i, j});
                }
            }
        }

        int maxOverlap = 0;

        // Map translation -> number of overlaps
        java.util.Map<String, Integer> map = new java.util.HashMap<>();

        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {

                // Translation required to move p1 onto p2
                int dr = p2[0] - p1[0];
                int dc = p2[1] - p1[1];

                String key = dr + "," + dc;

                int count = map.getOrDefault(key, 0) + 1;
                map.put(key, count);

                maxOverlap = Math.max(maxOverlap, count);
            }
        }

        return maxOverlap;
    }
}