class KthSmallestElementInABST {
    // T.C -> O(k), S.C -> O(k)
    private int itr = 0;
    private int ans = -1;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return ans;
    }

    private void inorder(TreeNode node, int k) {
        if(node == null || ans != -1) return;
        inorder(node.left, k);
        itr++;
        if(itr == k) {
            ans = node.val; 
            return;
        }
        inorder(node.right, k);
    }
}
