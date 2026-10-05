//noinspection ALL
package com.algorithm.practice.tree;

import com.algorithm.tree.Tree;

import java.util.*;

/**
 * ============================================================================
 * PRACTICE: TREE — Java DSA Comprehensive Guide
 * ============================================================================
 *
 * This is the PRACTICE version - incomplete methods for you to solve
 *
 * Same patterns as Tree.java but with TODOs and hints
 * Complete each method following the approach comments
 *
 * Hints:
 * 1. levelOrderTraversal - Use queue, when needed level wise
 * 2. DFS: inorder, preorder - use recursion
 *
 * Test your solution against the examples in main()
 *
 * ============================================================================
 */

@SuppressWarnings("ALL")
public class TreePractice {

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
    // 2. DFS TRAVERSALS (Depth-First Search) - PRACTICE
    // ========================================================================

    /**
     * PRACTICE 1: INORDER TRAVERSAL: Left → Parent → Right
     * For BST, gives sorted order
     *
     * Input Tree:
     *          10
     *         /  \
     *        5    15
     *       / \   / \
     *      3  7  12  20
     *
     * Expected Output: 3 5 7 10 12 15 20
     *
     * TODO: Implement inorder traversal
     * ─────────────────────────────
     * APPROACH:
     * 1. Check if node is null - if yes, return
     * 2. Recursively traverse LEFT subtree
     * 3. Process current node (print value)
     * 4. Recursively traverse RIGHT subtree
     *
     * Time: O(n), Space: O(h) where h = height
     * 
     * WHY THIS ORDER?
     * - For BST: gives values in sorted order
     * - "In" = process node in between left and right
     * - Most common for BST problems
     */
    public void inorderTraversal(TreeNode node) {
        //end of tree, nothing exist.
        if(node==null) return;
        //traverse left, until exhausted,
        inorderTraversal(node.left);
        //print ndoe
        System.out.print(node.value +" ");
        //right node, until exhausted
        inorderTraversal(node.right);
    }

    /**
     * PRACTICE 2: PREORDER TRAVERSAL: Parent → Left → Right
     * Used for copying tree or serialization
     *
     * Expected Output: 10 5 3 1 4 7 6 8 15 12 11 13 20 19 21
     *
     * TODO: Implement preorder traversal
     * ─────────────────────────────
     * APPROACH:
     * 1. Check if node is null - if yes, return
     * 2. Process current node FIRST (print value)
     * 3. Recursively traverse LEFT subtree
     * 4. Recursively traverse RIGHT subtree
     *
     * Time: O(n), Space: O(h)
     * 
     * WHY THIS ORDER?
     * - "Pre" = process node BEFORE children
     * - Used for tree copying/cloning
     * - First node is always root
     * - Can reconstruct tree from preorder
     */
    public void preorderTraversal(TreeNode node) {
        if(node == null) return;
        System.out.print(node.value + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    /**
     * PRACTICE 3: POSTORDER TRAVERSAL: Left → Right → Parent
     * Used for deletion or expression evaluation
     *
     * Expected Output: 1 4 3 6 8 7 5 11 13 12 19 21 20 15 10
     *
     * TODO: Implement postorder traversal
     * ─────────────────────────────
     * APPROACH:
     * 1. Check if node is null - if yes, return
     * 2. Recursively traverse LEFT subtree
     * 3. Recursively traverse RIGHT subtree
     * 4. Process current node LAST (print value)
     *
     * Time: O(n), Space: O(h)
     * 
     * WHY THIS ORDER?
     * - "Post" = process node AFTER children
     * - Used for tree deletion (delete children first)
     * - Used for expression evaluation
     * - Parent processed last
     */
    public void postorderTraversal(TreeNode node) {
        if (node == null) return;
        //reach extreme left, then probably left does not have right, it will print that.
        postorderTraversal(node.left);
        //reach extreme right
        postorderTraversal(node.right);
        System.out.print(node.value + " ");
    }

    // ========================================================================
    // 3. BFS TRAVERSAL (Breadth-First Search) - PRACTICE
    // ========================================================================

    /**
     * PRACTICE 4: LEVEL ORDER TRAVERSAL (BFS)
     * Visits all nodes level by level (top to bottom, left to right)
     *
     * Expected Output: 10 5 15 3 7 12 20 1 4 6 8 11 13 19 21
     *
     * TODO: Implement level order traversal
     * ─────────────────────────────
     * APPROACH:
     * 1. Create empty Queue
     * 2. Add root node to queue
     * 3. While queue is not empty:
     *    a. Remove node from FRONT of queue (poll)
     *    b. Process node (print/store)
     *    c. Add left child to queue (if exists)
     *    d. Add right child to queue (if exists)
     * 4. Continue until queue is empty
     *
     * WHY QUEUE?
     * - Queue = FIFO (First In First Out)
     * - Processes nodes level by level
     * - Unlike DFS (stack), doesn't go deep first
     *
     * KEY OPERATIONS:
     * - queue.add(node) = add to back
     * - queue.poll() = remove from front
     * - queue.isEmpty() = check if empty
     *
     * Time: O(n), Space: O(w) where w = max width
     * 
     * WHEN TO USE LEVEL ORDER:
     * - Process tree level by level
     * - Find values at specific level
     * - Find minimum depth
     * - Right/Left view of tree
     */
    public void levelOrderTraversal(TreeNode root) {
        // If tree is empty, nothing to traverse
        if (root == null) return;

        //queue is needed, add root, pop root then add its left-right childre, then pop both these and add subchildrens
        Queue<TreeNode> queue =new LinkedList<>();

        //add to queue
        queue.add(root);

        //remove from queue logic
        while (!queue.isEmpty()) {

            //returns top element of element, or from head
            //dont use remove, bcoz queue empty->remove-exception
            TreeNode current= queue.poll();
            System.out.print(current.value + " ");

            //add left right of this
            if(current.left!=null) {
                queue.add(current.left);
            }

            if(current.right != null){
                queue.add(current.right);
            }
        }
    }

    /**
     * PRACTICE 5: LEVEL ORDER TRAVERSAL — Return as List of Lists
     * Useful when you need to process each level separately
     *
     * Expected Output: [[10], [5, 15], [3, 7, 12, 20], [1, 4, 6, 8, 11, 13, 19, 21]]
     *
     * TODO: Implement level order as list of lists
     * ─────────────────────────────
     * APPROACH:
     * 1. Create result list to store all levels
     * 2. Create queue and add root
     * 3. While queue not empty:
     *    a. Get current level size = queue.size()
     *    b. For each node in current level:
     *       - Remove node from queue
     *       - Add value to currentLevel list
     *       - Add children to queue
     *    c. Add currentLevel to result
     * 4. Return result
     *
     * KEY INSIGHT:
     * - queue.size() at start of iteration = nodes at current level
     * - Process exactly that many nodes
     * - Their children = next level
     *
     * Time: O(n), Space: O(w)
     */
    public List<List<Integer>> levelOrderList(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        Queue<TreeNode> queue =new LinkedList<>();
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
    // 4. COMMON TREE PROBLEMS - PRACTICE
    // ========================================================================

    /**
     * PRACTICE 6: Find Maximum Value in Tree
     *
     * Problem: Find largest value in tree
     * Input: Tree with values [10, 5, 15, 3, 7, 12, 20, ...]
     * Expected Output: 21
     *
     * TODO: Implement maxValue
     * ─────────────────────────────
     * APPROACH:
     * 1. Base case: if node is null, return Integer.MIN_VALUE
     * 2. Recursively find max in left subtree
     * 3. Recursively find max in right subtree
     * 4. Return max of (current node, leftMax, rightMax)
     *
     * Time: O(n), Space: O(h)
     */
    public int maxValue(TreeNode node) {
        if(node == null)
            return 0;
        int left =maxValue(node.left);
        int right =maxValue(node.right);

        return Math.max(node.value, Math.max(left, right));
    }

    /**
     * PRACTICE 7: Find Height of Tree
     *
     * Problem: Find longest path from node to leaf
     * Definition: Leaf node height = 0, null height = -1
     * Expected Output: 3
     *
     * TODO: Implement height
     * ─────────────────────────────
     * APPROACH:
     * 1. Base case: if node is null, return -1
     * 2. Recursively find height of left subtree
     * 3. Recursively find height of right subtree
     * 4. Return 1 + max(leftHeight, rightHeight)
     *
     * Time: O(n), Space: O(h)
     */
    public int height(TreeNode node) {
        //empty means -1
        if(node ==null) return -1;
        int left =height(node.left);
        int right= height(node.right);
        //1+current level. because it start from 0
        return 1+ Math.max(left,right);
    }

    /**
     * PRACTICE 8: Count Total Nodes
     *
     * Problem: Count how many nodes in tree
     * Expected Output: 15 (4 levels with full binary tree)
     *
     * TODO: Implement countNodes
     * ─────────────────────────────
     * APPROACH:
     * 1. Base case: if node is null, return 0
     * 2. Count current node (1)
     * 3. Add count from left subtree
     * 4. Add count from right subtree
     *
     * Time: O(n), Space: O(h)
     */
    public int countNodes(TreeNode node) {
    if(node==null) return 0;
/*    int left=countNodes(node.left);
    int right=countNodes(node.right);*/
    return 1+ countNodes(node.left)+countNodes(node.right);
    }

    /**
     * PRACTICE 9: Check if Tree is Balanced
     *
     * Problem: Check if tree is balanced
     * Definition: height diff between left and right ≤ 1 for EVERY node
     * Example: Balanced tree has better performance
     * Expected Output: true (our sample is balanced)
     *
     * TODO: Implement isBalanced
     * ─────────────────────────────
     * APPROACH:
     * 1. Use helper function getHeight()
     * 2. If getHeight returns -1, tree is unbalanced
     * 3. Otherwise tree is balanced
     *
     * HELPER getHeight():
     * - Returns -1 if unbalanced
     * - Returns height if balanced
     * - Check at each node if |leftHeight - rightHeight| > 1
     *
     * Time: O(n), Space: O(h)
     */
    public boolean isBalanced(TreeNode node) {
        return getHeight(node)!=0;

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
     * PRACTICE 10: Find Path to Target Value
     *
     * Problem: Find path from root to target node
     * Input: target = 7
     * Expected Output: [10, 5, 7]
     * Expected for target = 1: [10, 5, 3, 1]
     *
     * TODO: Implement findPath
     * ─────────────────────────────
     * APPROACH:
     * 1. Add current node to path
     * 2. If current node is target, return true
     * 3. Recursively search left subtree
     * 4. If found, return true
     * 5. Recursively search right subtree
     * 6. If found, return true
     * 7. If not found in any, remove current node (backtrack) and return false
     *
     * KEY: Backtracking pattern - remove node if path not found
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

        //add in list
        path.add(node.value);

        // if Found target
        if (node.value == target) return true;

        //else traverse left,right
        // Search in left subtree
        if (findPathHelper(node.left, target, path)) return true;

        // Search in right subtree
        if (findPathHelper(node.right, target, path)) return true;

        //left tree parsed, still not found, then time to remove entries.
        // Not found in this subtree, backtrack. bcoz atleast 1 erlement we added
        path.remove(path.size() - 1);
        return false;
    }

    /**
     * PRACTICE 11: Lowest Common Ancestor (LCA)
     *
     * Problem: Find deepest node that is ancestor of both p and q
     * Input: p = 3, q = 7
     * Expected Output: Node with value 5 (their parent)
     * Input: p = 11, q = 13
     * Expected Output: Node with value 12 (their parent)
     *
     * TODO: Implement findLCA
     * ─────────────────────────────
     * APPROACH:
     * 1. Base: if node is null, return null
     * 2. If node equals p or q, return this node (it's ancestor)
     * 3. Search left for p and q (get leftLCA)
     * 4. Search right for p and q (get rightLCA)
     * 5. If both found (leftLCA and rightLCA not null), current is LCA
     * 6. If only leftLCA found, return it (both in left)
     * 7. If only rightLCA found, return it (both in right)
     * 8. If neither found, return null
     *
     * common approach
     * 1. null test, return null
     * 2. decision of found, count, sum, print..
     * 3. now ask left and right children
     *
     * Time: O(n), Space: O(h)
     */

    public TreeNode findLCA(TreeNode node, int p, int q) {

        if(node==null) return  null;
        // If either p or q is current node, it's an ancestor of both
        if (node.value == p || node.value == q) {
            return node;
        }


        TreeNode leftLCA = findLCA(node.left, p, q);
        TreeNode rightLCA = findLCA(node.right, p, q);
        if (leftLCA != null && rightLCA != null)
            return node;
        if (leftLCA != null)
            return leftLCA;
        return rightLCA;
    }

    /**
     * PRACTICE 12: Sum of All Nodes in Path (Root to Leaf)
     *
     * Problem: Sum all paths from root to leaves
     * Example: Path 10→5→3→1 treats as number 1531, sum = 1531
     * Expected Output: Sum of all root-to-leaf paths
     *
     * TODO: Implement sumRootToLeaf
     * ─────────────────────────────
     * APPROACH:
     * 1. Use helper with pathSum parameter
     * 2. For each node: pathSum = pathSum * 10 + node.value
     *    (This treats path as a number)
     * 3. If leaf node, return pathSum
     * 4. If internal node, sum from left and right
     *
     * Time: O(n), Space: O(h)
     */
    public int sumRootToLeaf(TreeNode node) {
        throw new UnsupportedOperationException(
            "TODO: Implement sumRootToLeaf\n" +
            "Hint: Use pathSum * 10 + value to build number"
        );
    }

    private int sumRootToLeafHelper(TreeNode node, int pathSum) {
        throw new UnsupportedOperationException(
            "TODO: Implement helper\n" +
            "Hint: Return pathSum if leaf, else sum from children"
        );
    }

    /**
     * PRACTICE 13: Serialize and Deserialize Tree
     *
     * Problem: Convert tree to string and back
     * Useful: Storage, network transmission, testing
     * Expected: "10,5,3,1,null,null,4,null,null,7,6,null,null,8,null,null,15,12,11,null,null,13,null,null,20,19,null,null,21,null,null,"
     *
     * TODO: Implement serialize
     * ─────────────────────────────
     * APPROACH (Preorder):
     * 1. For null nodes, append "null,"
     * 2. For other nodes, append value + ","
     * 3. Recursively serialize left
     * 4. Recursively serialize right
     *
     * Time: O(n), Space: O(n)
     */
    public String serialize(TreeNode node) {
        throw new UnsupportedOperationException(
            "TODO: Implement serialize\n" +
            "Hint: Use StringBuilder, append values and 'null', preorder"
        );
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        throw new UnsupportedOperationException(
            "TODO: Implement serializeHelper"
        );
    }

    /**
     * PRACTICE 14: Deserialize Tree
     *
     * Problem: Reconstruct tree from serialized string
     * Input: "10,5,3,1,null,null,4,null,null,7,6,null,null,8,null,null,15,12,11,null,null,13,null,null,20,19,null,null,21,null,null,"
     * Expected: Original tree structure
     *
     * TODO: Implement deserialize
     * ─────────────────────────────
     * APPROACH:
     * 1. Split string by ","
     * 2. Create queue from array
     * 3. Call deserializeHelper recursively
     *
     * Time: O(n), Space: O(n)
     */
    public TreeNode deserialize(String data) {
        throw new UnsupportedOperationException(
            "TODO: Implement deserialize\n" +
            "Hint: Split by ',', create queue, call helper"
        );
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        throw new UnsupportedOperationException(
            "TODO: Implement deserializeHelper\n" +
            "Hint: If 'null', return null. Else create node, recurse left and right"
        );
    }

    // ========================================================================
    // 5. MAIN - DEMONSTRATION
    // ========================================================================

    public static void main(String[] args) {
        TreePractice tree = new TreePractice();
        tree.buildSampleTree();

        System.out.println("========== TREE TRAVERSALS ==========\n");

        System.out.println("INORDER (Left-Root-Right) - Expected: 1 3 4 5 6 7 8 10 11 12 13 15 19 20 21");
        try {
            tree.inorderTraversal(tree.root);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Not implemented yet\n");
        }

        System.out.println("PREORDER (Root-Left-Right) - Expected: 10 5 3 1 4 7 6 8 15 12 11 13 20 19 21");
        try {
            tree.preorderTraversal(tree.root);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Not implemented yet\n");
        }

        System.out.println("POSTORDER (Left-Right-Root) - Expected: 1 4 3 6 8 7 5 11 13 12 19 21 20 15 10");
        try {
            tree.postorderTraversal(tree.root);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Not implemented yet\n");
        }

        System.out.println("LEVEL ORDER - Expected: 10 5 15 3 7 12 20 1 4 6 8 11 13 19 21");
        try {
            tree.levelOrderTraversal(tree.root);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Not implemented yet\n");
        }

        System.out.println("LEVEL ORDER (as List) - Expected: [[10], [5, 15], [3, 7, 12, 20], [1, 4, 6, 8, 11, 13, 19, 21]]");
        try {
            System.out.println(tree.levelOrderList(tree.root));
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }
        System.out.println();

        System.out.println("========== COMMON PROBLEMS ==========\n");

        try {
            System.out.println("Maximum Value - Expected: 21");
            System.out.println("Result: " + tree.maxValue(tree.root));
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        try {
            System.out.println("\nHeight - Expected: 3");
            System.out.println("Result: " + tree.height(tree.root));
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        try {
            System.out.println("\nTotal Nodes - Expected: 15");
            System.out.println("Result: " + tree.countNodes(tree.root));
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        try {
            System.out.println("\nIs Balanced - Expected: true");
            System.out.println("Result: " + tree.isBalanced(tree.root));
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        try {
            System.out.println("\nPath to 7 - Expected: [10, 5, 7]");
            System.out.println("Result: " + tree.findPath(tree.root, 7));
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        try {
            System.out.println("\nLCA of 3 and 7 - Expected: 5");
            TreeNode lca = tree.findLCA(tree.root, 3, 7);
            System.out.println("Result: " + (lca != null ? lca.value : "null"));
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        try {
            System.out.println("\nSum Root to Leaf - Expected: Sum of all paths");
            System.out.println("Result: " + tree.sumRootToLeaf(tree.root));
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }
    }
}

