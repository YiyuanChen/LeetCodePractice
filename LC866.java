/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC866 {
    int depthest = 0;
    
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Map<TreeNode, TreeNode> reverse = new HashMap<>();
        Map<Integer, Set<TreeNode>> map = new HashMap<>();
        dfs(reverse, root, null, map, 0);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        // System.out.println(depthest);
        for (TreeNode node : map.get(depthest)) {
            queue.offer(node);
            visited.add(node);
        }
        // queue.offer(root);
        while (queue.size() != 1) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                // if (visited.contains(cur)) continue;
                // visited.add(cur);
                TreeNode parent = reverse.get(cur);
                if (parent != null && !visited.contains(parent)) {
                    queue.offer(parent);
                    visited.add(parent);
                }
            }
        }
        return queue.poll();
    }
    
    // public int dfs(Map<TreeNode, TreeNode> map, TreeNode root, TreeNode parent, Map<Integer, Set<TreeNode>> depth) {
    //     if (root == null) return 0;
    //     map.put(root, parent);
    //     int l = dfs(map, root.left, root), r = dfs(map, root.right, root);
    //     depthest = Math.max(Math.max(l, r) + 1, depthest);
    //     int height = 
    //     return Math.max(l, r) + 1;
    // }
    
    public void dfs(Map<TreeNode, TreeNode> map, TreeNode root, TreeNode parent, Map<Integer, Set<TreeNode>> depth, int h) {
        if (root == null) return;
        map.put(root, parent);
        if (!depth.containsKey(h)) depth.put(h, new HashSet<>());
        depth.get(h).add(root);
        depthest = Math.max(h, depthest);
        dfs(map, root.left, root, depth, h + 1);
        dfs(map, root.right, root, depth, h + 1);
    }
}