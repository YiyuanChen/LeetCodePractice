/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC863 {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        if (K == 0) {
            res.add(target.val);
            return res;
        }
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        dfs(parents, root, null);
        BFS(res, target, K);
        // System.out.println(res);
        TreeNode par = parents.get(target);
        while (K-- > 0 && par != null) {
            if (K == 0) {
                res.add(par.val);
                continue;
            }
            if (par.left == target) {
                BFS(res, par.right, K - 1);
            }
            else {
                BFS(res, par.left, K - 1);
            }
            target = par;
            par = parents.get(target);
        }
        return res;
    }
    
    public void dfs(Map<TreeNode, TreeNode> parents, TreeNode root, TreeNode parent) {
        if (root == null) return;
        parents.put(root, parent);
        dfs(parents, root.left, root);
        dfs(parents, root.right, root);
    }
    
    public void BFS(List<Integer> res, TreeNode node, int depth) {
        if (node == null) return;
        if (depth == 0) {
            res.add(node.val);
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty() && depth-- >= 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (depth < 0) res.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
    }
}