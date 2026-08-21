class Solution {

    List<Integer> result = new ArrayList<>();

    int currentCount = 0;
    int maxCount = 0;
    Integer prev = null;

    public int[] findMode(TreeNode root) {

        inorder(root);

        int[] ans = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }

    private void inorder(TreeNode root) {

        if (root == null) {
            return;
        }

        // Left
        inorder(root.left);

        // Current
        if (prev == null || root.val != prev) {
            currentCount = 1;
        } else {
            currentCount++;
        }

        if (currentCount > maxCount) {
            maxCount = currentCount;
            result.clear();
            result.add(root.val);
        } 
        else if (currentCount == maxCount) {
            result.add(root.val);
        }

        prev = root.val;

        // Right
        inorder(root.right);
    }
}
