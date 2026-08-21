class Solution {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        // subRoot completely matched
        if (subRoot == null) {
            return true;
        }

        // root finished but subRoot still exists
        if (root == null) {
            return false;
        }

        // Check current tree
        if (isSameTree(root, subRoot)) {
            return true;
        }

        // Search in left and right subtree
        return isSubtree(root.left, subRoot) ||
               isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode root, TreeNode subRoot) {

        // Both are null
        if (root == null && subRoot == null) {
            return true;
        }

        // One is null
        if (root == null || subRoot == null) {
            return false;
        }

        // Values are different
        if (root.val != subRoot.val) {
            return false;
        }

        // Check left and right
        return isSameTree(root.left, subRoot.left) &&
               isSameTree(root.right, subRoot.right);
    }
}