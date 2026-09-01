class Solution {

    public TreeNode increasingBST(TreeNode root) {

        ArrayList<TreeNode> list = new ArrayList<>();

        inorder(root, list);

        TreeNode dummy = new TreeNode(0);
        TreeNode current = dummy;

        for (TreeNode node : list) {

            node.left = null;
            current.right = node;

            current = node;
        }

        return dummy.right;
    }

    private void inorder(TreeNode root, ArrayList<TreeNode> list) {

        if (root == null) {
            return;
        }

        inorder(root.left, list);

        list.add(root);

        inorder(root.right, list);
    }
}