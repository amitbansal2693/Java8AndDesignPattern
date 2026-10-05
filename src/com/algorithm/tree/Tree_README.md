# TREE — Comprehensive DSA Guide

## Table of Contents
1. [What is a Tree?](#what-is-a-tree)
2. [Tree Terminology](#tree-terminology)
3. [Types of Trees](#types-of-trees)
4. [Tree Traversals](#tree-traversals)
5. [DFS vs BFS](#dfs-vs-bfs)
6. [Common Problems](#common-problems)
7. [Decision Trees](#decision-tree-patterns)
8. [Complexity Analysis](#complexity-analysis)
9. [Interview Tips](#interview-tips)

---

## What is a Tree?

A **Tree** is a hierarchical, acyclic data structure consisting of:
- **Nodes** connected by **edges**
- **One root node** (topmost)
- **Parent-child relationships**
- **No cycles** (unlike graphs)

### Visual Example:
```
         10 (root)
        /  \
       5    15
      / \   / \
     3   7 12  20 (leaves)
```

### Key Properties:
- **Connected**: there's a path between any two nodes
- **Acyclic**: no loops
- **Hierarchical**: parent-child structure
- **n nodes** → **n-1 edges**

---

## Tree Terminology

| Term | Definition | Example |
|------|-----------|---------|
| **Root** | Topmost node (no parent) | 10 |
| **Leaf** | Node with no children | 3, 7, 12, 20 |
| **Parent** | Node that has children | 10, 5, 15 |
| **Child** | Node connected to parent | 5, 15 are children of 10 |
| **Sibling** | Nodes with same parent | 5 and 15 are siblings |
| **Height** | Longest path from node to leaf | Height of 10 = 2 |
| **Depth** | Distance from root to node | Depth of 5 = 1 |
| **Subtree** | Tree rooted at any node | Left subtree rooted at 5 |
| **Level** | Depth + 1 (root is level 1) | 10 is level 1, 5 is level 2 |

---

## Types of Trees

### 1. **Binary Tree**
Each node has **at most 2 children** (left and right)

```
      1
     / \
    2   3
   /
  4
```

### 2. **Binary Search Tree (BST)**
A tree which is sorted.
For every node: **left < node < right**

```
        10
       /  \
      5    15
     / \   / \
    3   7 12  20
```

**Properties:**
- In-order traversal gives sorted sequence
- Fast search: O(log n) average, O(n) worst case

### 3. **Balanced Binary Tree**
Height difference between left and right ≤ 1 for all nodes

```
       10
      /  \
     5    15    ← Balanced
    / \   /
   3   7 12
```

**Types:**
- AVL Trees: strictly balanced
- Red-Black Trees: loosely balanced

### 4. **Complete Binary Tree**
All levels filled except possibly last (filled left-to-right)

```
       1
      / \
     2   3
    / \ /
   4  5 6
```

Used for: Heaps

### 5. **Full/Perfect Binary Tree**
Every node has 0 or 2 children

```
       1
      / \
     2   3
    / \ / \
   4  5 6  7
```

---

## Tree Traversals

### DFS Traversals (Depth-First Search)

#### 1. **INORDER: Left → Root → Right**

**When to use:**
- Get sorted order from BST
- Process nodes in increasing order
- Expression evaluation

**Code:**
```java
void inorder(TreeNode node) {
    if (node == null) return;
    inorder(node.left);
    System.out.print(node.value);
    inorder(node.right);
}
```

**Example:**
```
       10
      /  \
     5    15
    Output: 5, 10, 15 (sorted!)
```

**Time:** O(n), **Space:** O(h)

---

#### 2. **PREORDER: Root → Left → Right**

**When to use:**
- Copy/clone a tree
- Serialize tree
- Build tree from pre-order sequence
- Prefix notation

**Code:**
```java
void preorder(TreeNode node) {
    if (node == null) return;
    System.out.print(node.value);
    preorder(node.left);
    preorder(node.right);
}
```

**Example:**
```
       10
      /  \
     5    15
    Output: 10, 5, 15
```

**Time:** O(n), **Space:** O(h)

---

#### 3. **POSTORDER: Left → Right → Root**

**When to use:**
- Delete a tree (delete children before parent)
- Evaluate expression trees
- Convert tree to another form
- Postfix notation

**Code:**
```java
void postorder(TreeNode node) {
    if (node == null) return;
    postorder(node.left);
    postorder(node.right);
    System.out.print(node.value);
}
```

**Example:**
```
       10
      /  \
     5    15
    Output: 5, 15, 10
```

**Time:** O(n), **Space:** O(h)

---

### BFS Traversal (Breadth-First Search)

#### **LEVEL ORDER: Top-to-Bottom, Left-to-Right**

**When to use:**
- Visit each level separately
- Find minimum depth
- Right side view
- Zigzag traversal
- Connected component counting

**Code:**
```java
void levelOrder(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    
    while (!q.isEmpty()) {
        TreeNode node = q.poll();
        System.out.print(node.value);
        
        if (node.left != null) q.add(node.left);
        if (node.right != null) q.add(node.right);
    }
}
```

**Example:**
```
       10
      /  \
     5    15
    / \
   3   7
   Output: 10, 5, 15, 3, 7 (level-by-level)
```

**Time:** O(n), **Space:** O(w) where w = max width

---

## DFS vs BFS

| Aspect | DFS (Inorder/Preorder/Postorder) | BFS (Level Order) |
|--------|---------|---------|
| **Data Structure** | Stack (Recursion) | Queue |
| **Space** | O(h) - height | O(w) - width |
| **When to Use** | Need ALL traversals, sorted order (inorder), tree building (preorder) | Level-by-level processing |
| **Memory** | Better for deep trees | Better for wide trees |
| **Code** | Recursive = cleaner | Iterative with queue |
| **Example** | Inorder gives BST sorted | Right view of tree |

---

## Common Problems

### 1. **Find Maximum Value**
```java
int maxValue(TreeNode node) {
    if (node == null) return Integer.MIN_VALUE;
    return Math.max(node.value, 
           Math.max(maxValue(node.left), 
                    maxValue(node.right)));
}
```
**Time:** O(n), **Space:** O(h)

---

### 2. **Find Height of Tree**
```java
int height(TreeNode node) {
    if (node == null) return -1;
    return 1 + Math.max(height(node.left), 
                        height(node.right));
}
```
**Time:** O(n), **Space:** O(h)

---

### 3. **Count Total Nodes**
```java
int count(TreeNode node) {
    if (node == null) return 0;
    return 1 + count(node.left) + count(node.right);
}
```
**Time:** O(n), **Space:** O(h)

---

### 4. **Check if Balanced**
```java
boolean isBalanced(TreeNode node) {
    return getHeight(node) != -1;
}

int getHeight(TreeNode node) {
    if (node == null) return 0;
    
    int left = getHeight(node.left);
    if (left == -1) return -1;
    
    int right = getHeight(node.right);
    if (right == -1) return -1;
    
    if (Math.abs(left - right) > 1) return -1;
    return 1 + Math.max(left, right);
}
```
**Time:** O(n), **Space:** O(h)

---

### 5. **Find Path from Root to Target**
```java
List<Integer> findPath(TreeNode node, int target) {
    List<Integer> path = new ArrayList<>();
    helper(node, target, path);
    return path;
}

boolean helper(TreeNode node, int target, List<Integer> path) {
    if (node == null) return false;
    
    path.add(node.value);
    if (node.value == target) return true;
    
    if (helper(node.left, target, path)) return true;
    if (helper(node.right, target, path)) return true;
    
    path.remove(path.size() - 1);  // Backtrack
    return false;
}
```
**Time:** O(n), **Space:** O(h)

---

### 6. **Lowest Common Ancestor (LCA)**
```java
TreeNode findLCA(TreeNode node, int p, int q) {
    if (node == null) return null;
    if (node.value == p || node.value == q) return node;
    
    TreeNode left = findLCA(node.left, p, q);
    TreeNode right = findLCA(node.right, p, q);
    
    if (left != null && right != null) return node;
    return left != null ? left : right;
}
```
**Time:** O(n), **Space:** O(h)

---

### 7. **Serialize and Deserialize**
**Preorder serialization:**
```java
String serialize(TreeNode node) {
    StringBuilder sb = new StringBuilder();
    helper(node, sb);
    return sb.toString();
}

void helper(TreeNode node, StringBuilder sb) {
    if (node == null) {
        sb.append("null,");
        return;
    }
    sb.append(node.value).append(",");
    helper(node.left, sb);
    helper(node.right, sb);
}

TreeNode deserialize(String data) {
    Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
    return deserializeHelper(q);
}

TreeNode deserializeHelper(Queue<String> q) {
    String val = q.poll();
    if (val.equals("null")) return null;
    
    TreeNode node = new TreeNode(Integer.parseInt(val));
    node.left = deserializeHelper(q);
    node.right = deserializeHelper(q);
    return node;
}
```
**Time:** O(n), **Space:** O(n)

---

## Decision Tree Patterns

```
TREE PROBLEM
│
├─ Need SORTED order from BST?
│  └─ INORDER ✓
│
├─ Building/Copying tree?
│  └─ PREORDER ✓
│
├─ Deleting tree?
│  └─ POSTORDER ✓
│
├─ Processing by LEVELS?
│  └─ LEVEL ORDER (BFS) ✓
│
├─ Need HEIGHT or DEPTH?
│  ├─ Recursively visit all → DFS
│  └─ Post-order useful
│
├─ Searching for ELEMENT?
│  └─ Can use any traversal, but consider BST property
│
├─ Path from ROOT to NODE?
│  └─ DFS with backtracking
│
└─ Comparing SUBTREES?
   └─ Process recursively with DFS
```

---

## Complexity Analysis

| Problem | Time | Space | Notes |
|---------|------|-------|-------|
| Traversal (any) | O(n) | O(h) | Visit each node once |
| Max/Min value | O(n) | O(h) | Check all nodes |
| Height | O(n) | O(h) | Process from leaves up |
| Count nodes | O(n) | O(h) | Visit every node |
| Search (BST) | O(log n) avg, O(n) worst | O(h) | Average if balanced |
| Balanced check | O(n) | O(h) | Can terminate early if unbalanced |
| Path finding | O(n) | O(h) | Worst case: DFS entire tree |
| LCA | O(n) | O(h) | May need 2 passes for edge cases |
| Serialize | O(n) | O(n) | Store every node |

**Where h = height, n = number of nodes**

---

## Interview Tips

### ✅ **What to Know**
1. All 4 traversals (inorder, preorder, postorder, level order)
2. When to use DFS vs BFS
3. How to solve recursively
4. Backtracking in path problems
5. Pre/post-processing based on traversal

### ✅ **Common Patterns**
- **Path problems**: Use DFS with backtracking
- **Level problems**: Use BFS
- **Ordered access**: Use inorder for BST
- **Building trees**: Use preorder
- **Memory-efficient**: Use DFS for deep trees

### ❌ **Common Mistakes**
1. Forgetting base case (node == null)
2. Not backtracking in path problems
3. Using wrong traversal for problem
4. Not considering BST special properties
5. Stack overflow on very deep trees (prefer iterative)

### 🎯 **Practice Problems**
1. **Beginner:**
   - Traversals
   - Height/count nodes
   - Max value

2. **Intermediate:**
   - Balanced tree check
   - Path from root to node
   - LCA

3. **Advanced:**
   - Serialize/deserialize
   - Vertical order
   - Boundary traversal
   - Flatten tree

---

## Quick Reference

### Traversal Selection Guide

```
Tree Problem?
│
├─ BST sorted? → INORDER
├─ Copy/serialize? → PREORDER
├─ Delete/cleanup? → POSTORDER
├─ Level-based? → LEVEL ORDER
├─ Path-based? → DFS + backtrack
├─ Width matters? → BFS
└─ Height matters? → Post-order DFS (childrenFirst)
```

### Code Template: DFS with Recursion
```java
void dfs(TreeNode node) {
    // Base case
    if (node == null) return;
    
    // Pre-process (preorder position)
    
    // Recurse left
    dfs(node.left);
    
    // In-process (inorder position)
    
    // Recurse right
    dfs(node.right);
    
    // Post-process (postorder position)
}
```

### Code Template: BFS with Queue
```java
void bfs(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    
    while (!q.isEmpty()) {
        TreeNode node = q.poll();
        // Process node
        
        if (node.left != null) q.add(node.left);
        if (node.right != null) q.add(node.right);
    }
}
```

---

## Resources

- See `Tree.java` for complete implementations
- Run `main()` to see all examples in action
- Study each traversal method separately
- Practice backtracking with path problems

**Good luck with your tree journey!** 🌳

