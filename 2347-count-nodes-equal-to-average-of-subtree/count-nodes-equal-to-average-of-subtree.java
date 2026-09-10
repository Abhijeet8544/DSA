
class Solution {

    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    // returns {sum, count}
    private int[] dfs(TreeNode node) {

        if (node == null) {
            return new int[]{0, 0};
        }

        // Get information from left and right subtrees
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // Calculate current subtree sum and count
        int sum = left[0] + right[0] + node.val;
        int count = left[1] + right[1] + 1;

        // Integer division gives floor average
        int average = sum / count;

        // Check if current node equals subtree average
        if (node.val == average) {
            ans++;
        }

        return new int[]{sum, count};
    }
}
