# BACKTRACKING — Comprehensive DSA Guide

## Table of Contents
1. [What is Backtracking?](#what-is-backtracking)
2. [When to Use Backtracking](#when-to-use-backtracking)
3. [Core Concepts](#core-concepts)
4. [Backtracking Template](#backtracking-template)
5. [Problem Progression](#problem-progression)
6. [Decision Tree Model](#decision-tree-model)
7. [Complexity Analysis](#complexity-analysis)
8. [Common Patterns](#common-patterns)

---

## What is Backtracking?

**Backtracking** is a problem-solving technique that:
1. **Explores** all possible solutions
2. **Makes choices** incrementally (builds solution step by step)
3. **Recognizes** when a path won't lead to solution
4. **Backtracks** and tries another path
5. **Abandons unsuccessful paths** early (pruning)

### Key Insight:
> **Make → Explore → Undo → Try Next**

### Recursion vs Backtracking:
- **Recursion:** Function calls itself (technique)
- **Backtracking:** Make choice → Recursively explore → Undo → Try another (strategy using recursion)

---

## When to Use Backtracking?

🎯 **Keywords that suggest backtracking:**
- "Generate all..."
- "Find all combinations/permutations"
- "All possible arrangements"
- "Find all valid solutions"
- "Generate all paths"
- "All valid placements"
- "All valid outcomes"

📋 **Problems that use backtracking:**
- ✅ Subsets / Power Set
- ✅ Permutations
- ✅ Combinations
- ✅ Combination Sum
- ✅ Palindrome Partitioning
- ✅ Generate Parentheses
- ✅ N-Queens
- ✅ Sudoku Solver
- ✅ Letter Combinations
- ✅ Word Search

---

## Core Concepts

### 1. **Decision Tree**
For each element, you have **choices** (include or exclude, place or skip, etc.)

```
Example: Generate all subsets of [1, 2, 3]

                    []
                   /  \
              include 1   exclude 1
                [1]          []
               /   \        /   \
          include 2 exclude 2
           [1,2]    [1]     [2]    []
            / \      / \    / \    / \
        [1,2,3] [1,2] [1,3] [1] [2,3] [2] [3] []
```

### 2. **Pruning**
Stop exploring paths that can't lead to valid solutions

```java
// Example: Only explore if sum doesn't exceed target
if (currentSum > target) {
    return;  // Prune this branch
}
```

### 3. **State Management**
Maintain current state of solution being built:
- Current combination
- Current sum/count
- Current position
- Used elements

### 4. **Base Case**
When to save a solution:
```java
if (current.size() == k) {
    result.add(new ArrayList<>(current));
    return;
}
```

---

## Backtracking Template

```java
void backtrack(State state, List<...> current) {
    
    // BASE CASE: Solution complete?
    if (isSolutionComplete(state)) {
        result.add(new ArrayList<>(current));
        return;
    }
    
    // EXPLORE: Try each possible choice
    for (int choice : getPossibleChoices(state)) {
        
        // MAKE CHOICE
        makeChoice(current, choice);
        state.update(choice);
        
        // RECURSE: Explore with this choice
        backtrack(state, current);
        
        // UNDO CHOICE (Backtrack)
        undoChoice(current);
        state.revert(choice);
    }
}
```

### Template Variations:

**By Index:**
```java
void backtrack(int index, List<Integer> current) {
    if (index == nums.length) {
        result.add(new ArrayList<>(current));
        return;
    }
    
    // Include current element
    current.add(nums[index]);
    backtrack(index + 1, current);
    current.remove(current.size() - 1);
    
    // Exclude current element
    backtrack(index + 1, current);
}
```

**By Start Index (Combinations):**
```java
void backtrack(int start, List<Integer> current, int target) {
    if (target == 0) {
        result.add(new ArrayList<>(current));
        return;
    }
    
    for (int i = start; i < candidates.length; i++) {
        current.add(candidates[i]);
        backtrack(i, current, target - candidates[i]);
        current.remove(current.size() - 1);
    }
}
```

---

## Problem Progression

### **Level 1 — Core Pattern**
- Subsets
- Subsets II (with duplicates)
- Permutations
- Permutations II (with duplicates)

### **Level 2 — Choice + Constraint**
- Combination Sum
- Combination Sum II
- Letter Combinations

### **Level 3 — Complex Constraints**
- Generate Parentheses
- Palindrome Partitioning
- Word Search

### **Level 4 — Hard Backtracking**
- N-Queens
- Sudoku Solver

---

## Complexity Analysis

| Problem | Time | Space |
|---------|------|-------|
| Subsets | O(2^n × n) | O(n) |
| Permutations | O(n! × n) | O(n) |
| Combinations | O(C(n,k) × k) | O(k) |
| Combination Sum | O(2^(n+m)) | O(m) |
| Generate Parens | O(4^n / √n) | O(n) |
| N-Queens | O(N!) | O(N) |

---

## Common Interview Tips

✅ **Do's:**
- Draw decision tree before coding
- Test with simple examples first
- Always backtrack (undo changes)
- Copy to result list with `new ArrayList<>()`
- Prune impossible branches early

❌ **Don'ts:**
- Forget to undo changes
- Return reference instead of copy
- Miss base case
- Over-complicate condition checks

---

## Resources
See `Backtracking.java` for complete implementations with problem statements, inputs/outputs, and approaches.

