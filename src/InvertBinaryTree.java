class InvertBinaryTree {
    // T.C -> O(n), S.C ->  O(log(n))
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(root.left);
        return root;
    }
}
