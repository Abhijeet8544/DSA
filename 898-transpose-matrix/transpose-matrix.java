class Solution {
    public int[][] transpose(int[][] matrix) {

        int m = matrix.length;       // rows
        int n = matrix[0].length;    // columns

        // Transpose will have n rows and m columns
        int[][] ans = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                ans[j][i] = matrix[i][j];
            }
        }

        return ans;
    }
}