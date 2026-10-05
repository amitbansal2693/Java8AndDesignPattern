# Stack — Java DSA Complete Guide

## Quick Summary
- **Definition**: LIFO (Last In, First Out) data structure
- **Access**: O(1) for push/pop/peek
- **Space**: O(n)
- **Use Cases**: Matching, Undo, Previous/Next Elements, Monotonic Problems

## Stack Concept Tree
```
STACK (LIFO)
│
├── 1. BASIC OPERATIONS (Foundation)
│      ├── Push: O(1) - Add element to top
│      ├── Pop: O(1) - Remove & return top
│      ├── Peek: O(1) - View top without removing
│      ├── isEmpty: O(1) - Check if empty
│      └── Size: O(1) - Get element count
│
├── 2. BASIC PROBLEMS (Foundation)
│      ├── Reverse: Reverse order of elements
│      ├── Valid Parentheses: Match brackets
│      ├── Balanced Brackets: Check all types (){}[]
│      └── Remove Adjacent Duplicates: Delete matched pairs
│
├── 3. TWO STACK PROBLEMS (Intermediate)
│      ├── Queue using Stacks: FIFO from LIFO
│      ├── Min Stack: Track minimum efficiently
│      └── Expression Evaluation: Handle operators & operands
│
└── 4. MONOTONIC STACK (Advanced - Very Important!)
       ├── Next Greater Element: Find first larger to right
       ├── Next Smaller Element: Find first smaller to right
       ├── Previous Greater Element: Find first larger to left
       ├── Previous Smaller Element: Find first smaller to left
       ├── Daily Temperatures: Days until warmer temp
       ├── Stock Span: How many days price ≤ today
       └── Largest Rectangle in Histogram: Max area
```

## Key Mental Models
```
LIFO (Last In, First Out) - Most recent item first
┌─────────────┐
│ Top → Item3 │ ← Pop this first
│     Item2  │
│ Bot → Item1 │ ← Push this first
└─────────────┘

History - Keep useful previous items for later
Example: Undo stack remembers previous states

Matching - Most recent opening needs earliest closing
Example: ( ( ) ) ← inner pair closes first (LIFO)

Monotonic - Maintain order while removing useless elements
Example: For "next greater", only keep increasing elements
```
---

## 1. What is a Stack?

A **Stack** is a linear data structure that follows the **LIFO (Last In, First Out)** principle.

### Real-World Examples
- **Stack of plates**: Add plate on top → remove from top
- **Undo button**: Most recent action undone first
- **Browser back button**: Most recent page visited goes back first
- **Function call stack**: Most recent function called returns first

### Visual Representation
```
Push 1  →  Push 2  →  Push 3  →  Pop
┌───┐     ┌───┐     ┌───┐     ┌───┐
│ 1 │     │ 2 │     │ 3 │     │ 2 │  ← Top (most recent)
└───┘     │ 1 │     │ 2 │     │ 1 │
          └───┘     │ 1 │     └───┘
                    └───┘

Stack grows upward, newest element on top
```

### Key Point
**A Stack gives you access ONLY to the top element** — not to middle or bottom.

---

## 2. Core Stack Operations

| Operation | Description | Time | Space |
|-----------|-------------|------|-------|
| **push(x)** | Add element x to top | O(1) | O(1) |
| **pop()** | Remove & return top element | O(1) | O(1) |
| **peek()** | View top without removing | O(1) | O(1) |
| **isEmpty()** | Check if stack is empty | O(1) | O(1) |
| **size()** | Get number of elements | O(1) | O(1) |

### Java Implementation
```java
// Preferred: Use Deque (not old Stack class)
Deque<Integer> stack = new ArrayDeque<>();

// Basic Operations
stack.push(5);      // Add 5 to top
int top = stack.pop();   // Remove & return top
int peek = stack.peek(); // View top (don't remove)
boolean empty = stack.isEmpty();
int sz = stack.size();
```

---

## 3. Java Stack — Best Practices

### Modern Approach: Use Deque, Not Stack
```java
// ❌ OLD (Don't use for DSA interviews)
Stack<Integer> stack = new Stack<>();

// ✅ NEW (Preferred)
Deque<Integer> stack = new ArrayDeque<>();
```

### Why?
- `ArrayDeque` is faster (array-based, not linked)
- `Stack` is legacy (extends Vector, synchronized)
- Both have same interface: push/pop/peek

### In Interviews
- **Focus on**: Stack pattern/behavior
- **Less focus on**: Which class to use
- **Tip**: Say "I'll use a Deque for better performance"
---

## 4. When to Use a Stack? — Pattern Recognition

### Red Flags (Keywords that suggest Stack)
✅ **"matching"** — Valid parentheses  
✅ **"nested"** — Function calls, nested structures  
✅ **"undo"** — Reverse operations  
✅ **"most recent"** — Last element matters  
✅ **"previous element"** — Need history  
✅ **"next greater/smaller"** — Monotonic stack!  
✅ **"adjacent"** — Remove pairs  
✅ **"reverse"** — Reverse order processing  

---

## 5. Main Stack Problem Categories

### A. MATCHING / VALIDATING (LIFO Order)

**Problem Pattern**: Things must be matched in reverse order

**Classic Examples**:
- **Valid Parentheses**: Match `()`, `{}`, `[]`
- **Balanced Brackets**: Multiple bracket types
- **Remove Matched Pairs**: Delete adjacent duplicates
- **HTML/XML Parsing**: Match opening and closing tags

**Why Stack?**
```
For: ( ( [ ] ) )
     1 2  3  2 1
      
Processing order:
1. See ( → push
2. See ( → push
3. See [ → push
4. See ] → matches most recent [  (LIFO!)
5. See ) → matches most recent (
6. See ) → matches most recent (

LIFO is natural for nested structures!
```

**Time Complexity**: O(n) — one pass through input  
**Space Complexity**: O(n) — worst case stack holds all elements

---

### B. EXPRESSION PROBLEMS (Two Stacks)

**Problem Pattern**: Evaluate mathematical expressions

**Examples**:
- **Postfix Evaluation**: "3 4 +"
- **Prefix Evaluation**: "+ 3 4"
- **Reverse Polish Notation**: "5 1 2 + 4 * + 3 -"
- **Infix to Postfix**: Convert "3 + 4 * 2"
- **Operator Precedence**: Handle *, /, +, -

**Solution Pattern** (Usually 2 stacks):
1. **Value Stack**: Stores operands
2. **Operator Stack**: Stores operators + handles precedence

**Time**: O(n), **Space**: O(n)

---

### C. MONOTONIC STACK (★★★ Most Important!)

**This is THE most important stack pattern for interviews!**

**Core Idea**: Maintain stack in increasing/decreasing order while processing elements

**Common Problems**:
| Problem | Find | Direction |
|---------|------|-----------|
| Next Greater Element | First larger to RIGHT | →  |
| Next Smaller Element | First smaller to RIGHT | →  |
| Previous Greater Element | First larger to LEFT | ← |
| Previous Smaller Element | First smaller to LEFT | ← |
| Daily Temperatures | Days until warmer | → |
| Stock Span | Consecutive days with lower price | ← |
| Largest Rectangle | Max area in histogram | - |

**Key Insight**: Converts O(n²) brute force → O(n) elegant solution!

#### Example: Next Greater Element
```
Input: [2, 1, 5, 3]
Output: [5, 5, -1, -1]

Explanation:
2 → next greater is 5
1 → next greater is 5
5 → no greater element
3 → no greater element

Monotonic Stack Solution (O(n)):
- Keep decreasing stack of indices
- When new element > stack top, pop and record answer
- Push current element
```

#### Example: Daily Temperatures
```
Input: [73, 74, 75, 71, 69, 72, 76, 73]
Output: [1, 1, 4, 2, 1, 1, 0, 0]

Explanation:
Day 0 (73°): Next warmer day is day 1 (74°) → wait 1 day
Day 1 (74°): Next warmer day is day 2 (75°) → wait 1 day
Day 2 (75°): Next warmer day is day 5 (76°) → wait 4 days
...

Monotonic Stack: Keep decreasing temps
When new temp > stack top, calculate days waited
```

**Time**: O(n) — each element pushed/popped once  
**Space**: O(n) — stack storage

---

### D. UNDO / REVERSE PROCESSING

**Problem Pattern**: Process in reverse of addition order

**Examples**:
- **Undo/Redo**: Most recent change undone first
- **Browser History**: Back button shows most recent page
- **Backtracking**: Reverse through decision points
- **Collapse Path**: Process nested operations backwards

**Time**: O(n), **Space**: O(n)

---

### E. NESTED STRUCTURES

**Problem Pattern**: Natural recursion/nesting

**Examples**:
```
Function Calls:
  main()
    → foo()
       → bar()
          
Stack grows as we go deeper
Stack shrinks as we return (LIFO!)

Nested Brackets:
  { [ ( ) ] }
  
Processing inside-out (LIFO)
```

**Usage**: Decoded strings, nested expressions, parsing

**Time**: O(n), **Space**: O(depth of nesting)

---

### F. REMOVE/ELIMINATE ELEMENTS

**Problem Pattern**: Current element affects previous elements

**Examples**:
- **Remove Adjacent Duplicates**: Delete "aa", "bb"
- **Simplify Paths**: Process "../" to go back
- **Collision**: Asteroids colliding (smaller disappears)
- **Remove K Digits**: Delete digits to minimize result

**Approach**:
```
FOR each new element:
   WHILE stack top matches condition:
      POP (remove previous element)
   PUSH current element
```

**Time**: O(n), **Space**: O(n)
---

## 6. One Stack vs Two Stacks Decision Tree

### ONE STACK
Use when there is ONE main LIFO sequence or relationship.

**Typical Examples**:
```
Pattern: Single element stream → process in LIFO order
├── Valid parentheses
├── Next greater element
├── Daily temperatures
├── Stock span
├── Remove adjacent duplicates
├── Reverse processing
└── Largest rectangle
```

**Question**: "Am I tracking one type of pending items?"  
**Answer**: Use ONE stack

---

### TWO STACKS
Use when there are TWO independent types of information OR states.

#### Pattern 1: Queue using Two Stacks (FIFO from LIFO)
```
Incoming Stack  →  Outgoing Stack
Push here          Pop from here
1 2 3              (reverses to 1 2 3)

Stack 1: Temporary storage (LIFO)
Stack 2: Provides FIFO behavior
```

#### Pattern 2: Expression Evaluation
```
Value Stack:     [3, 4, 12]  ← Operands
Operator Stack:  [+, *, -]   ← Operators

Two independent sequences!
```

#### Pattern 3: Min Stack
```
Main Stack:      [5, 3, 7, 1, 4]  ← All values
Min Stack:       [5, 3, 1, 1, 1]  ← Minimum so far

Track both independently
```

**Question**: "Are there TWO independent pieces of information?"  
**Answer**: Might need TWO stacks

---

## 7. Stack vs Other Data Structures

### Stack vs Queue

| Feature | Stack | Queue |
|---------|-------|-------|
| **Order** | LIFO (Last In, First Out) | FIFO (First In, First Out) |
| **Use For** | Undo, brackets, previous element | BFS, level-order, scheduling |
| **Add** | Top (push) | Rear (enqueue) |
| **Remove** | Top (pop) | Front (dequeue) |
| **Example** | Browser back | Printer queue |

### Stack vs Sliding Window

| Aspect | Stack | Sliding Window |
|--------|-------|----------------|
| **Question** | "What previous element is relevant?" | "What's in current window?" |
| **Order Matters** | YES - most recent first | NO - contiguous only |
| **Problem** | Next greater element | Longest substring |
| **Pattern** | History of processed items | Current state snapshot |

### Stack vs HashMap

| When | Use Stack | Use HashMap |
|------|-----------|-------------|
| **Fast lookup needed** | No (O(n)) | YES (O(1)) |
| **Order/history matters** | YES (LIFO) | NO |
| **Valid parentheses** | Bracket matching | Matching pairs |
| **Next greater** | YES (monotonic) | No (can't order) |

---

## 8. Monotonic Stack — Deep Dive (★★★)

### What is Monotonic Stack?

**Regular Stack**: Any element can be added/removed

**Monotonic Stack**: Maintains increasing or decreasing order
- When adding new element, remove elements that violate order
- Seems wasteful, but saves O(n²) iterations!

### Template: Monotonic Decreasing Stack

```java
// Find Next Greater Element for each item
Deque<Integer> stack = new ArrayDeque<>();
int[] result = new int[arr.length];

// Iterate from right to left (to find "next" larger)
for (int i = arr.length - 1; i >= 0; i--) {
    // 1. Pop smaller elements (they won't be useful)
    while (!stack.isEmpty() && stack.peek() <= arr[i]) {
        stack.pop();
    }
    
    // 2. What's on top is the first larger element
    result[i] = stack.isEmpty() ? -1 : stack.peek();
    
    // 3. Push current for future comparisons
    stack.push(arr[i]);
}
```

**Time**: O(n) — each element pushed/popped once  
**Space**: O(n)

### Keywords that Trigger "Monotonic Stack"
```
✅ "next greater" → Monotonic decreasing
✅ "next smaller" → Monotonic increasing
✅ "previous greater" → Adjust direction
✅ "previous smaller" → Adjust direction
✅ "nearest" → Monotonic
✅ "warmer" temperature → Monotonic
✅ "stock span" → Monotonic
```

---

## 9. Common Stack Mistakes

### ❌ Mistake 1: Forgetting to Check isEmpty()
```java
// WRONG:
int top = stack.pop();  // Can throw exception if empty!

// RIGHT:
if (!stack.isEmpty()) {
    int top = stack.pop();
}
```

### ❌ Mistake 2: Using Stack Class Instead of Deque
```java
// Slow for DSA:
Stack<Integer> s = new Stack<>();

// Better:
Deque<Integer> s = new ArrayDeque<>();
```

### ❌ Mistake 3: Checking Conditions After Popping
```java
// WRONG: Already popped, can't check!
if (stack.pop() > 5) { ... }

// RIGHT: Peek, then decide
if (!stack.isEmpty() && stack.peek() > 5) {
    stack.pop();
}
```

### ❌ Mistake 4: Monotonic Stack Wrong Direction
```java
// For "next GREATER": Use DECREASING stack
// For "next SMALLER": Use INCREASING stack
// Easy to mix up!
```

### ❌ Mistake 5: Off-by-One Errors
```java
// For loop vs <= in range checks
for (int i = 0; i < n; i++)      // index 0 to n-1
for (int i = 0; i <= n - 1; i++) // same thing
```

---

## 10. Stack Problem Recognition — Quick Flowchart

```
Read problem
    │
    ├─→ "Match" or "Nested"?  
    │   YES → Matching problems (Valid Parentheses, etc.)
    │   
    ├─→ "Undo" or "Recent"?
    │   YES → Undo/History (Undo button, browser back)
    │   
    ├─→ "Previous/Next" + "Greater/Smaller"?
    │   YES → Monotonic Stack ⭐⭐⭐
    │   
    ├─→ "Adjacent" or "Remove pairs"?
    │   YES → Stack for elimination
    │   
    ├─→ "Expression" or "Evaluate"?
    │   YES → One or Two stacks for operators/values
    │   
    └─→ "Queue-like" + Two stacks?
        YES → Queue using Two Stacks
```

---

## 11. Interview Tips

✅ **Always check isEmpty() before peek()/pop()**  
✅ **Use Deque<T> instead of Stack<T>**  
✅ **Explain your stack pattern out loud:**  
   "I'm using a stack because ___"  
✅ **For monotonic stack, draw the stack state**  
✅ **Test edge cases:** empty, single element, all same  
✅ **Time/Space complexity matters:**  
   Stack solutions usually O(n) time, O(n) space  
✅ **Monotonic stack can turn O(n²) into O(n)**  

---

## 12. Practice Path

### Beginner
1. Valid Parentheses
2. Reverse String
3. Remove Adjacent Duplicates

### Intermediate
4. Next Greater Element
5. Daily Temperatures
6. Min Stack

### Advanced
7. Stock Span
8. Largest Rectangle in Histogram
9. Expression Evaluation
10. Queue using Two Stacks

---

## Quick Reference Table

| Problem | Pattern | Stack Type | Time | Space |
|---------|---------|-----------|------|-------|
| Valid Parentheses | Matching | Single | O(n) | O(n) |
| Next Greater | Monotonic | Monotonic Dec | O(n) | O(n) |
| Daily Temps | Monotonic | Monotonic Dec | O(n) | O(n) |
| Min Stack | Extra Info | With min | O(1) | O(n) |
| Queue by Stacks | Two sequences | Dual | O(1) amort | O(n) |
| Remove Duplicates | Elimination | Single | O(n) | O(n) |
| Stock Span | Monotonic | Monotonic Inc | O(n) | O(n) |
| Expression Eval | Operators | Dual | O(n) | O(n) |
