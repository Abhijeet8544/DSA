/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    public boolean isUnivalTree(TreeNode root) {
        return check(root, root.val);
    }

    private boolean check(TreeNode root, int value) {

        // Empty node
        if (root == null) {
            return true;
        }

        // Different value found
        if (root.val != value) {
            return false;
        }

        // Check left and right
        return check(root.left, value) &&
               check(root.right, value);
    }
}