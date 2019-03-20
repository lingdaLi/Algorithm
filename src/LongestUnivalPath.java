import Models.TreeNode;

public class LongestUnivalPath {
    // LeetCode 687
    int result = 0;

    public int longestUnivaluePath(TreeNode root) {
        getPathLengthOfNode(root);
        return result;
    }

    public int getPathLengthOfNode(TreeNode cur) {
        if(cur == null) return 0;

        int left = validPath(cur.val, cur.left, getPathLengthOfNode(cur.left));
        int right = validPath(cur.val, cur.right, getPathLengthOfNode(cur.right));

        result = Math.max(result, left + right);
        return Math.max(left, right);
    }

    public int validPath(int val, TreeNode sub, int pathLen) {
        if(sub == null || sub.val != val) return 0;
        return ++pathLen;
    }
}
