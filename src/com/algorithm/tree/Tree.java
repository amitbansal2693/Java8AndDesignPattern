package com.algorithm.tree;

import java.util.*;

/**
 * ============================================================================
 * TREE — Java DSA Comprehensive Guide
 * ============================================================================
 *
 * Hint:
 * 1. levelOrderTraversal- Use queue, when needed level wise
 * 2. Dfs: inorder, preorder, use recursion
 *
 * A Tree is a hierarchical data structure consisting of:
 * - Nodes connected by edges
 * - One root node
 * - Each node has 0 or more children
 * - No cycles (acyclic)
 *
 * KEY TERMINOLOGY:
 * - Root: topmost node (no parent)
 * - Parent: node that has children
 * - Child: node connected to parent
 * - Leaf: node with no children
 * - Height: longest path from node to leaf
 * - Depth: distance from root to node
 * - Subtree: tree rooted at any node
 *
 * TYPES OF TREES:
 * 1. Binary Tree: each node has at most 2 children
 * 2. Binary Search Tree (BST): left < parent < right
 * 3. Balanced Tree: height difference between left and right ≤ 1
 * 4. Complete Tree: all levels filled except possibly last
 * 5. Full Tree: each node has 0 or 2 children
 *
 * ============================================================================
 */

public class Tree {

    /**
     * TreeNode Class
     * Represents a single node in the tree
     */
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode root;

    // ========================================================================
    // 1. TREE CONSTRUCTION
    // ========================================================================

    /**
     * Create a sample binary tree for demonstration
     *
     * Tree structure (4 levels):
     *          10
     *         /  \
     *        5    15
     *       / \   / \
     *      3  7  12  20
     *     / \/ \ / \ / \
     *    1 4 6 8 11 13 19 21
     */
    public void buildSampleTree() {
        root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(20);
        
        // Level 4: Add children to leaf nodes
        root.left.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(8);
        root.right.left.left = new TreeNode(11);
        root.right.left.right = new TreeNode(13);
        root.right.right.left = new TreeNode(19);
        root.right.right.right = new TreeNode(21);
    }

    // ========================================================================
    // 2. DFS TRAVERSALS (Depth-First Search)

    // ========================================================================

    /**
     * DFS TRAVERSALS
     * It is a way to aprse tree. All ways differens when parent will be processed.
     *There are 3 DFS traversals:
     * Preorder(root, left, right)
     * Inorder(left,root,right)
     * Postorder(left,right,root)
     *
     *
     * DFS = Depth First Search
     *
     * INORDER TRAVERSAL: Left → Parent → Right
     * For BST, gives sorted order
     *
     * Tree structure: (left,root,right)
     *          10
     *         /  \
     *        5    15
     *       / \   / \
     *      3  7  12  20
     *
     * Output: 3 5 7 10 12 15 20 
     *
     * Time: O(n), Space: O(h) where h = height
     */
    public void inorderTraversal(TreeNode node) {
        if (node == null) return;

        inorderTraversal(node.left);      // Visit left subtree
        System.out.print(node.value + " "); // Visit current node
        inorderTraversal(node.right);     // Visit right subtree
    }

    /**
     * PREORDER TRAVERSAL: Parent → Left → Right
     * Used for copying tree or serialization
     *
     * Output: 10, 5, 15
     *
     * Tree structure: Node->left->right
     *          10
     *         /  \
     *        5    15
     *       / \   / \
     *      3  7  12  20
     *      
     * Time: O(n), Space: O(h)
     */
    public void preorderTraversal(TreeNode node) {
        if (node == null) return;

        System.out.print(node.value + " "); // Visit current node
        preorderTraversal(node.left);       // Visit left subtree
        preorderTraversal(node.right);      // Visit right subtree
    }

    /**
     * POSTORDER TRAVERSAL: Left → Right → Parent
     * Used for deletion or expression evaluation
     *
     *      * Tree structure: left->right->parent
     *      *          10
     *      *         /  \
     *      *        5    15
     *      *       / \   / \
     *      *      3  7  12  20
     *      
     *
     * Time: O(n), Space: O(h)
     */
    public void postorderTraversal(TreeNode node) {
        if (node == null) return;

        postorderTraversal(node.left);      // Visit left subtree
        postorderTraversal(node.right);     // Visit right subtree
        System.out.print(node.value + " "); // Visit current node
    }

    // ========================================================================
    // 3. BFS TRAVERSAL (Breadth-First Search)
    // ========================================================================

    /**
     * ============================================================================
     * LEVEL ORDER TRAVERSAL (BFS) - COMPLETE EXPLANATION
     * ============================================================================
     * 
     * WHAT IS LEVEL ORDER TRAVERSAL?
     * - Visits all nodes level by level (top to bottom, left to right)
     * - Uses Queue (FIFO - First In First Out)
     * - Also called Breadth-First Search (BFS)
     * 
     * WHY USE QUEUE?
     * - Queue maintains FIFO order
     * - Add nodes to back, remove from front
     * - This ensures we process nodes level by level
     * 
     * ALGORITHM STEPS:
     * 1. Create empty queue
     * 2. Add root node to queue
     * 3. While queue is not empty:
     *    a. Remove node from front of queue
     *    b. Process node (print/store)
     *    c. Add left child to queue (if exists)
     *    d. Add right child to queue (if exists)
     * 4. Continue until queue is empty
     * 
     * EXAMPLE WITH OUR TREE:
     *          10          ← Level 1
     *         /  \
     *        5    15       ← Level 2
     *       / \   / \
     *      3  7  12  20    ← Level 3
     *     / \/ \ / \ / \
     *    1 4 6 8 11 13 19 21 ← Level 4
     * 
     * STEP-BY-STEP EXECUTION:
     * 
     * Initial: Queue = [10]
     * 
     * Step 1: Process 10
     *   - Remove 10 from queue
     *   - Print: 10
     *   - Add children: 5, 15
     *   - Queue = [5, 15]
     * 
     * Step 2: Process 5
     *   - Remove 5 from queue (FIFO)
     *   - Print: 5
     *   - Add children: 3, 7
     *   - Queue = [15, 3, 7]
     * 
     * Step 3: Process 15
     *   - Remove 15 from queue
     *   - Print: 15
     *   - Add children: 12, 20
     *   - Queue = [3, 7, 12, 20]
     * 
     * Step 4: Process 3
     *   - Remove 3 from queue
     *   - Print: 3
     *   - Add children: 1, 4
     *   - Queue = [7, 12, 20, 1, 4]
     * 
     * Step 5: Process 7
     *   - Remove 7 from queue
     *   - Print: 7
     *   - Add children: 6, 8
     *   - Queue = [12, 20, 1, 4, 6, 8]
     * 
     * Step 6: Process 12
     *   - Remove 12 from queue
     *   - Print: 12
     *   - Add children: 11, 13
     *   - Queue = [20, 1, 4, 6, 8, 11, 13]
     * 
     * Step 7: Process 20
     *   - Remove 20 from queue
     *   - Print: 20
     *   - Add children: 19, 21
     *   - Queue = [1, 4, 6, 8, 11, 13, 19, 21]
     * 
     * Step 8-15: Process leaf nodes (1, 4, 6, 8, 11, 13, 19, 21)
     *   - No children, just print
     *   - Queue becomes empty
     * 
     * FINAL OUTPUT: 10 5 15 3 7 12 20 1 4 6 8 11 13 19 21
     * 
     * KEY OBSERVATIONS:
     * ✓ Nodes processed level by level
     * ✓ Left to right within each level
     * ✓ Uses Queue (not Stack like DFS)
     * ✓ More space needed (O(w) max nodes at one level)
     * ✓ Less stack depth issues (unlike recursion)
     * 
     * TIME & SPACE COMPLEXITY:
     * Time: O(n) - Visit every node exactly once
     * Space: O(w) - where w = maximum width of tree
     *        At most, queue holds all nodes at widest level
     *        For complete binary tree at last level: w = n/2
     * 
     * WHEN TO USE LEVEL ORDER:
     * ✓ Process tree level by level
     * ✓ Find minimum height/depth
     * ✓ Find maximum value per level
     * ✓ Right/Left view of tree
     * ✓ Zigzag traversal
     * ✓ Avoid deep recursion (iterative approach)
     * 
     * QUEUE VS STACK:
     * Queue (BFS - Level Order):
     *   - Process: 10, 5, 15, 3, 7, 12, 20, ...
     *   - Level by level
     * 
     * Stack (DFS - Any Order):
     *   - Process: 10, 5, 3, 1, 4, 7, 6, 8, 15, ...
     *   - Depth first, then backtrack
     * 
     * ============================================================================
     */
    public void levelOrderTraversal(TreeNode root) {
        // Step 0: Edge case - empty tree
        // If tree is empty, nothing to traverse
        if (root == null) return;

        // Step 1: Initialize Queue
        // Queue<TreeNode> - stores nodes waiting to be processed
        // LinkedList - efficient queue implementation (add/remove O(1))
        Queue<TreeNode> queue = new LinkedList<>();
        
        // Step 2: Add root node to queue
        // Start with root node
        queue.add(root);

        // Step 3: Process queue while it has nodes
        // Loop continues until all nodes are processed
        while (!queue.isEmpty()) {
            
            // Step 4a: Remove node from FRONT of queue (FIFO)
            // poll() = remove and return first element
            // This ensures we process nodes level by level
            TreeNode current = queue.poll();
            
            // Step 4b: Process current node (print it)
            System.out.print(current.value + " ");

            // Step 4c: Add LEFT child to BACK of queue
            // If left child exists, add to queue for future processing
            // Children will be processed after all nodes at current level
            if (current.left != null) {
                queue.add(current.left);
            }
            
            // Step 4d: Add RIGHT child to BACK of queue
            // Same as left child - add to back of queue
            if (current.right != null) {
                queue.add(current.right);
            }
            
            // After this loop iteration, current node is done
            // Next iteration will process next node in queue
        }
        // Loop ends when queue becomes empty (all nodes processed)
    }

    /**
     * LEVEL ORDER TRAVERSAL — Return as List of Lists
     * Useful when you need to process each level separately
     *
     * levelOrderTraversal- Use queue, when needed level wise
     *
     *      *          10          ← Level 1
     *      *         /  \
     *      *        5    15       ← Level 2
     *      *       / \   / \
     *      *      3  7  12  20    ← Level 3
     *      *     / \/ \ / \ / \
     *      *    1 4 6 8 11 13 19 21 ← Level 4
     * Output: [[10], [5, 15], [3, 7, 12, 20]]
     *
     * Time: O(n), Space: O(w)
     */
    public List<List<Integer>> levelOrderList(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                currentLevel.add(current.value);

                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }

            result.add(currentLevel);
        }

        return result;
    }

    // ========================================================================
    // 4. COMMON TREE PROBLEMS
    // ========================================================================

    /**
     * PROBLEM 1: Find Maximum Value in Tree
     *
     * Approach: Compare current node with max from left and right subtrees
     *
     * Time: O(n), Space: O(h)
     */
    public int maxValue(TreeNode node) {
        if (node == null) return Integer.MIN_VALUE;

        int leftMax = maxValue(node.left);
        int rightMax = maxValue(node.right);

        return Math.max(node.value, Math.max(leftMax, rightMax));
    }

    /**
     * PROBLEM 2: Find Height of Tree
     * Height = longest path from node to leaf
     * Leaf node has height 0
     * Empty tree has height -1
     *
     * Time: O(n), Space: O(h)
     */
    public int height(TreeNode node) {
        if (node == null) return -1;

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * PROBLEM 3: Count Total Nodes
     *
     * Time: O(n), Space: O(h)
     */
    public int countNodes(TreeNode node) {
        if (node == null) return 0;

        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    /**
     * PROBLEM 4: Check if Tree is Balanced
     * Balanced = height difference between left and right ≤ 1 for every node
     *
     * Naive approach: O(n²)
     * Optimized approach (below): O(n)
     *
     * Time: O(n), Space: O(h)
     */
    public boolean isBalanced(TreeNode node) {
        return getHeight(node) != -1;
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = getHeight(node.left); //find height of left treee
        if (leftHeight == -1) return -1;  // Left subtree unbalanced

        //find height of right tree
        int rightHeight = getHeight(node.right);
        if (rightHeight == -1) return -1;  // Right subtree unbalanced

        // If heights differ by more than 1, unbalanced
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        return 1 + Math.max(leftHeight, rightHeight); //tells maximum height
    }

    /**
     * PROBLEM 5: Find Path to Target Value
     * Returns path from root to target node if exists
     *
     * Time: O(n), Space: O(h)
     */
    public List<Integer> findPath(TreeNode node, int target) {
        List<Integer> path = new ArrayList<>();
        findPathHelper(node, target, path);
        return path;
    }

    private boolean findPathHelper(TreeNode node, int target, List<Integer> path) {
        if (node == null) return false;

        path.add(node.value);

        // Found target
        if (node.value == target) return true;

        // Search in left subtree
        if (findPathHelper(node.left, target, path)) return true;

        // Search in right subtree
        if (findPathHelper(node.right, target, path)) return true;

        // Not found in this subtree, backtrack. bcoz atleast 1 erlement we added
        path.remove(path.size() - 1);
        return false;
    }

    /**
     * PROBLEM 6: Lowest Common Ancestor (LCA)
     * Find deepest node that is ancestor of both p and q
     *
     * Approach:
     * - If either p or q found at current node, return this node
     * - If p and q in different subtrees, current node is LCA
     * - Otherwise LCA is in whichever subtree has one of them
     *
     * Assumes p and q exist in tree
     *
     * Time: O(n), Space: O(h)
     */
    public TreeNode findLCA(TreeNode node, int p, int q) {
        if (node == null) return null;

        // If either p or q is current node, it's an ancestor of both
        if (node.value == p || node.value == q) return node;

        // Look for p and q in left and right subtrees
        TreeNode leftLCA = findLCA(node.left, p, q);
        TreeNode rightLCA = findLCA(node.right, p, q);

        // If both found in different subtrees, current is LCA
        if (leftLCA != null && rightLCA != null) return node;

        // If both in left subtree
        if (leftLCA != null) return leftLCA;

        // If both in right subtree
        return rightLCA;
    }

    /**
     * PROBLEM 7: Sum of All Nodes in Path (Root to Leaf)
     * Calculate sum for every path from root to leaf
     *
     * Time: O(n), Space: O(h)
     */
    public int sumRootToLeaf(TreeNode node) {
        return sumRootToLeafHelper(node, 0);
    }

    private int sumRootToLeafHelper(TreeNode node, int pathSum) {
        if (node == null) return 0;

        pathSum = pathSum * 10 + node.value;

        // Leaf node - return this path sum
        if (node.left == null && node.right == null) {
            return pathSum;
        }

        // Internal node - sum from left and right subtrees
        return sumRootToLeafHelper(node.left, pathSum) +
                sumRootToLeafHelper(node.right, pathSum);
    }

    /**
     * PROBLEM 8: Serialize and Deserialize Tree
     * Convert tree to string and back
     *
     * Useful for: storage, network transmission, testing
     *
     * Time: O(n), Space: O(n)
     */
    public String serialize(TreeNode node) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(node, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }

        sb.append(node.value).append(",");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

    public TreeNode deserialize(String data) {
        String[] values = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(values));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        String value = queue.poll();

        if (value.equals("null")) return null;

        TreeNode node = new TreeNode(Integer.parseInt(value));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);

        return node;
    }

    // ========================================================================
    // 5. MAIN - DEMONSTRATION
    // ========================================================================

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.buildSampleTree();

        System.out.println("========== TREE TRAVERSALS ==========\n");

        System.out.println("INORDER (Left-Root-Right):");
        tree.inorderTraversal(tree.root);
        System.out.println("\n");

        System.out.println("PREORDER (Root-Left-Right):");
        tree.preorderTraversal(tree.root);
        System.out.println("\n");

        System.out.println("POSTORDER (Left-Right-Root):");
        tree.postorderTraversal(tree.root);
        System.out.println("\n");

        System.out.println("LEVEL ORDER:");
        tree.levelOrderTraversal(tree.root);
        System.out.println("\n");

        System.out.println("LEVEL ORDER (as List):");
        System.out.println(tree.levelOrderList(tree.root));
        System.out.println();

        System.out.println("========== COMMON PROBLEMS ==========\n");

        System.out.println("Maximum Value: " + tree.maxValue(tree.root));
        System.out.println("Height: " + tree.height(tree.root));
        System.out.println("Total Nodes: " + tree.countNodes(tree.root));
        System.out.println("Is Balanced: " + tree.isBalanced(tree.root));
        System.out.println("Path to 7: " + tree.findPath(tree.root, 7));
        System.out.println("LCA of 3 and 7: " + tree.findLCA(tree.root, 3, 7).value);
        System.out.println("Sum Root to Leaf: " + tree.sumRootToLeaf(tree.root));

        System.out.println("\n========== SERIALIZATION ==========\n");
        String serialized = tree.serialize(tree.root);
        System.out.println("Serialized: " + serialized);
        TreeNode deserialized = tree.deserialize(serialized);
        System.out.println("Deserialized Inorder: ");
        tree.inorderTraversal(deserialized);
        System.out.println();
    }
}
