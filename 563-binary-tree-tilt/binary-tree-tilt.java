class Solution {

    int totalTilt = 0;

    public int findTilt(TreeNode root) {
        calculateSum(root);
        return totalTilt;
    }

    private int calculateSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSum = calculateSum(root.left);
        int rightSum = calculateSum(root.right);

        // Calculate current node's tilt
        totalTilt += Math.abs(leftSum - rightSum);

        // Return subtree sum
        return leftSum + rightSum + root.val;
    }
}