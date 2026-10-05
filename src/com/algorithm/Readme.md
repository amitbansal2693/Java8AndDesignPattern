# Java DSA — Pattern Identification Roadmap

> A practical roadmap for improving algorithmic problem-solving in Java.
>
> **Goal:** Learn to recognize the problem pattern first, then choose the appropriate approach and data structure.

---

## 1. How to Use This Roadmap

When you get a new problem, do **not** immediately think about code.

Use this sequence:

1. **What is the input?** Array, String, Linked List, Tree, Graph, etc.
2. **What is the problem asking?**
3. **Is there a recognizable pattern?**
4. **What condition controls the approach?**
5. **What data structure helps?**
6. **What is the expected time and space complexity?**

The important skill is **pattern identification**.

> Roughly: recognizing the right approach is often more important than writing the code itself.

---

# 2. DSA Pattern Identification Hierarchy

```text
NEW PROBLEM
│
├── ARRAY / STRING
│   │
│   ├── Pair / Triplet / Multiple Elements
│   │   │
│   │   ├── Input already sorted?
│   │   │   ├── YES → Two Pointers
│   │   │   └── NO  → HashMap / HashSet
│   │   │
│   │   └── Need all combinations?
│   │       └── Combination / Backtracking
│   │
│   └── Subarray / Substring
│       │
│       ├── Fixed size?
│       │   └── Fixed Sliding Window
│       │
│       └── Variable size / condition?
│           └── Variable Sliding Window
│
├── LINKED LIST
│   │
│   ├── Need middle / cycle / relative speed?
│   │   └── Fast & Slow Pointers
│   │
│   ├── Need to reverse?
│   │   ├── Entire list → Iterative / Recursive Reversal
│   │   └── Part of list → Reverse Segment
│   │
│   └── Need to merge / reorder?
│       └── Pointer Manipulation
│
├── STACK / QUEUE
│   │
│   ├── Matching / nested structure?
│   │   └── Stack
│   │
│   ├── Next greater / smaller element?
│   │   └── Monotonic Stack
│   │
│   └── First-in-first-out processing?
│       └── Queue
│
├── TREE
│   │
│   ├── Need to visit every node?
│   │   ├── DFS
│   │   └── BFS
│   │
│   ├── Level-by-level?
│   │   └── BFS
│   │
│   └── Path / height / subtree information?
│       └── DFS / Recursion
│
├── GRAPH
│   │
│   ├── Visit / explore nodes?
│   │   ├── DFS
│   │   └── BFS
│   │
│   ├── Shortest path?
│   │   ├── Unweighted → BFS
│   │   ├── Positive weighted → Dijkstra
│   │   └── Other weighted cases → appropriate shortest-path algorithm
│   │
│   └── Connected components / grouping?
│       ├── DFS / BFS
│       └── Union-Find
│
└── GENERAL ALGORITHM
    │
    ├── Search space can be divided?
    │   └── Binary Search
    │
    ├── Need all possible choices?
    │   └── Backtracking
    │
    ├── Repeated overlapping subproblems?
    │   └── Dynamic Programming
    │
    ├── Locally optimal choice leads to global solution?
    │   └── Greedy
    │
    └── Need ordering / ranking?
        ├── Sorting
        ├── Heap / Priority Queue
        └── Binary Search
```

---

# 3. ARRAY / STRING

## A. Pair / Triplet Problems

Typical wording:

- Find two elements with target sum
- Find three elements with target sum
- Find pairs/triplets satisfying a condition
- Find combinations of elements

### First question

**Does the problem require the elements to be contiguous?**

If **no**, this is generally **not a Sliding Window problem**.

For example:

`[1, 2, 3, 4, 5]`

A triplet such as:

`[1, 3, 5]`

is valid even though the elements are not consecutive.

### Common approaches

**Unsorted input:**
- HashMap / HashSet
- Nested loops for simpler brute force
- Sorting + Two Pointers when changing order is allowed

**Sorted input:**
- Two Pointers

**Need every possible combination:**
- Combination / Backtracking

---

# 4. Subarray / Substring

This is where Sliding Window becomes important.

### First question

> Are the elements/characters contiguous?

If yes, ask:

### Fixed size?

Examples:

- Maximum sum of `K` consecutive elements
- Minimum sum of `K` consecutive elements
- Maximum count in every window of size `K`

→ **Fixed Sliding Window**

### Variable size?

Examples:

- Longest substring without repeating characters
- Longest subarray satisfying a condition
- Minimum subarray satisfying a condition
- At most `K` distinct characters

→ **Variable Sliding Window**

---

# 5. Sliding Window

## Fixed Window

The window size is constant.

Approach:

1. Maintain a window of size `K`.
2. Add the new element.
3. Process the window.
4. Remove the element leaving the window.
5. Move forward.

The main optimization is to update the window incrementally instead of recalculating it.

Typical complexity: **O(n)**.

---

## Variable Window

The window size changes according to a condition.

Approach:

1. Expand the window.
2. Maintain the required state.
3. Check whether the window is valid.
4. If invalid, shrink from the left.
5. Update the answer when appropriate.

Typical state to maintain:

- sum
- count
- frequency
- distinct values
- HashMap
- HashSet

---

# 6. Linked List

Linked List problems are mostly about **pointer movement**.

## Fast & Slow Pointers

Use when the problem involves:

- finding the middle
- detecting a cycle
- finding a cycle entry
- finding relative positions

## Reversal

Use when the problem asks to:

- reverse the entire list
- reverse a section
- reverse nodes in groups

Important skill:

> Understand how `next` references change.

---

# 7. Stack / Queue

## Stack

Think Stack when the problem involves:

- matching parentheses
- nested structures
- undo-style processing
- previous/next greater or smaller elements

### Monotonic Stack

Important pattern for:

- Next Greater Element
- Next Smaller Element
- Previous Greater Element
- Previous Smaller Element
- Temperature-style problems

---

## Queue

Think Queue when processing should happen in:

**First In → First Out**

Commonly used in:

- BFS
- level-order traversal
- scheduling-style problems

---

# 8. Trees

First ask:

> Do I need to explore the tree?

Usually:

- **DFS** → recursive/depth-oriented problems
- **BFS** → level-oriented problems

### DFS

Useful for:

- height/depth
- path problems
- subtree calculations
- recursive tree properties

### BFS

Useful for:

- level order
- minimum number of edges in an unweighted tree
- level-by-level processing

---

# 9. Graphs

First identify what the problem is asking.

### Exploration

Use:

- DFS
- BFS

Examples:

- visit all reachable nodes
- connected components
- islands
- graph traversal

### Shortest Path

Common choices:

- Unweighted graph → BFS
- Positive weighted graph → Dijkstra

### Connectivity

Consider:

- DFS/BFS
- Union-Find

---

# 10. Binary Search

Do not think of Binary Search only as:

> "Find a value in a sorted array."

Also recognize:

> **Can I divide the search space in half?**

Common patterns:

- Search in sorted array
- First/last occurrence
- Lower/upper bound
- Minimum feasible value
- Maximum feasible value
- Binary Search on the answer

Key question:

> Is there a monotonic condition where one side is always possible/impossible?

---

# 11. Backtracking

Think Backtracking when the problem asks for:

- all combinations
- all permutations
- all subsets
- possible arrangements
- choices under constraints

Basic idea:

1. Make a choice.
2. Explore.
3. Undo the choice.
4. Try another choice.

---

# 12. Dynamic Programming

Think DP when you see:

- overlapping subproblems
- repeated calculations
- optimization over choices
- "number of ways"
- minimum/maximum result
- choose / skip decisions

Typical process:

1. Define the state.
2. Define the transition.
3. Define the base case.
4. Calculate/store results.

---

# 13. Greedy

Think Greedy when a problem can be solved by repeatedly making the best local choice.

Typical examples:

- interval scheduling
- activity selection
- some minimum/maximum optimization problems
- some resource allocation problems

Important:

> Do not assume a problem is Greedy just because a local choice looks attractive. The choice must be provably safe.

---

# 14. Heap / Priority Queue

Think Heap when you need:

- smallest/largest element repeatedly
- Top K
- Kth largest/smallest
- priority-based processing
- merging sorted structures

Java:

`PriorityQueue`

---

# 15. HashMap / HashSet

Think Hashing when you need:

- fast lookup
- frequency counting
- duplicate detection
- matching complements
- grouping
- remembering previously seen values

Typical average lookup:

**O(1)**

Common examples:

- Two Sum
- character frequency
- duplicate detection
- longest substring problems

---

# 16. Sorting

Sorting is useful when the **relative ordering** of elements helps simplify the problem.

Common combinations:

- Sorting + Two Pointers
- Sorting + Greedy
- Sorting + Binary Search
- Sorting + Intervals

### Important

If the problem says:

> "Do not change the order of the input"

do **not** use `sort()` on the original data.

Consider:

- HashMap / HashSet
- Two pointers only if already sorted
- copy the data first if sorting a copy is acceptable

---

# 17. Pattern Recognition Cheat Sheet

| Problem clue | First pattern to consider |
|---|---|
| Contiguous + fixed `K` | Fixed Sliding Window |
| Contiguous + condition | Variable Sliding Window |
| Pair + unsorted | HashMap / HashSet |
| Pair + sorted | Two Pointers |
| Triplet | 3 loops / Hashing / Two Pointers |
| All combinations | Backtracking |
| All permutations | Backtracking |
| Middle of linked list | Fast & Slow |
| Linked-list cycle | Fast & Slow |
| Reverse linked list | Pointer manipulation |
| Matching brackets | Stack |
| Next greater element | Monotonic Stack |
| Level-by-level tree | BFS |
| Tree path/subtree | DFS |
| Graph exploration | DFS / BFS |
| Shortest unweighted path | BFS |
| Shortest positive-weighted path | Dijkstra |
| Repeated subproblems | DP |
| All possible choices | Backtracking |
| Top K | Heap |
| Fast lookup | HashMap / HashSet |
| Search space can be halved | Binary Search |

---

# 18. Java Data Structures to Know

Before doing large numbers of problems, become comfortable with:

- `ArrayList`
- `HashMap`
- `HashSet`
- `ArrayDeque`
- `PriorityQueue`
- arrays
- `String`
- `StringBuilder`

And understand when to choose each one.

---

# 19. Learning Order

Recommended order:

### Phase 1 — Foundations

1. Big-O
2. Arrays
3. Strings
4. HashMap
5. HashSet

### Phase 2 — Core Patterns

6. Two Pointers
7. Fixed Sliding Window
8. Variable Sliding Window
9. Fast & Slow Pointers
10. Stack
11. Queue
12. Binary Search

### Phase 3 — Core Data Structures

13. Linked List
14. Trees
15. Heap / Priority Queue
16. Graphs

### Phase 4 — Advanced Problem Solving

17. Recursion
18. Backtracking
19. Greedy
20. Dynamic Programming
21. Advanced graph algorithms

---

# 20. Rule for Practicing

For each problem, don't immediately look at the solution.

Write down:

```text
Input:
What is given?

Goal:
What do I need to find?

Contiguous?
Yes / No

Fixed or variable?
Fixed / Variable / Not applicable

Pattern:
What technique might fit?

Data structure:
What do I need to remember?

Complexity:
Target O(?)
```

Then solve.

After solving, ask:

> **"What clue in the question should have made me recognize this pattern?"**

That is the skill this roadmap is designed to build.
