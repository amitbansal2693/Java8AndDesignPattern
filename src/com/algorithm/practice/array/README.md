## 📂 ARRAY / STRING PRACTICE FOLDER STRUCTURE

Successfully created a comprehensive practice folder with **Interfaces** for grouping similar problems!

```
/com/algorithm/practice/array/
│
├── pair_triplet/
│   ├── PairTripletInterface.java (interface with 8 problem signatures)
│   ├── SortedPairProblems.java (Two Pointers approach - 5 TODOs)
│   ├── UnsortedPairProblems.java (HashMap/HashSet approach - 5 TODOs)
│   └── CombinationProblems.java (Backtracking approach - 3 TODOs)
│
└── subarray_substring/
    ├── SlidingWindowInterface.java (interface with 10 problem signatures)
    ├── FixedWindowProblems.java (Fixed window - 5 TODOs)
    └── VariableWindowProblems.java (Variable window - 5 TODOs)
```

---

## ✅ WHAT WAS CREATED

### **1. PAIR/TRIPLET PROBLEMS** (12 problems across 3 approaches)

#### **Interface: `PairTripletInterface`**
Defines contract for all pair/triplet/combination problems

#### **Implementation 1: `SortedPairProblems` (Two Pointers)**
- ✓ Problem 1: hasPair - Check if pair exists
- ✓ Problem 2: findAllPairs - Return all pairs
- ✓ Problem 3: countPairs - Count pairs with duplicates
- ✓ Problem 4: findAllTriplets - Three sum unique
- ✓ Problem 5: countTriplets - Count triplets

## Approach
1. 2 Elemets/Pairs: Sorted 

**CASE A: Unique Pairs -> While loop:
-Sort the array,Assign pointers, create map for store
-if unique pairs needed: use while loop
- if sum==target
     --store value
     --skip duplicates using while loop
    --at the end increase left and right pointer togethor.
- if sum< target
    left++
- else sum>target
   right--

**CASE B: All possible pairs including duplicates: For Loop
- use for loop
- if sum==target, store

2. 3 Elements/Triplets: 
Case A: Unique pairs -> Outer For loop + inner while loop(left,right pointer)
Case B: All pairs -> 3 for loops
 


------------------
------------------
1. Two Elements / Pairs
   Case A: Unique Pairs (Sort → While → Two Pointers → Skip Duplicates)
   Sorted + Two Pointers
- Sort the array
- left = 0, right = n - 1
- Use a while (left < right)
- If sum == target
    - store pair
    - skip duplicate values
    - move left++ and right--
- If sum < target → left++
- If sum > target → right--

Case B: All Pairs / Including Duplicates(outer For Loop → Inner For loop → Check Pair)
Brute Force
- Use two for loops
- Check every (i, j) where i < j
- If arr[i] + arr[j] == target → store/count

2. Three Elements / Triplets
   Case A: Unique Triplets
   Sorted + Outer For + Two Pointers
- Sort the array
- Outer for fixes the first element
- left = i + 1
- right = n - 1
- Use while (left < right)
- If found → store triplet and skip duplicates

Case B: All Triplets / Including Duplicates
Brute Force
- Use three nested for loops
- Check every (i, j, k) where i < j < k
- If sum matches → store/count


------------------------------------


**When to use:** Input is sorted or can be sorted
**Time:** O(n) or O(n log n)
**Space:** O(1)

#### **Implementation 2: `UnsortedPairProblems` (HashMap/HashSet)**
- ✓ Problem 1: hasPair - One-pass detection
- ✓ Problem 2: findAllPairs - No sorting needed
- ✓ Problem 3: countPairs - Frequency-based counting
- ✓ Problem 4: findAllTriplets - Using HashSet
- ✓ Problem 5: countTriplets - Efficient counting

**When to use:** Unsorted input, want O(n) time
**Time:** O(n)
**Space:** O(n)

#### **Implementation 3: `CombinationProblems` (Backtracking)**
- ✓ Problem 6: combinationSum - Reusable elements
- ✓ Problem 7: combinationSumII - Each element once
- ✓ Problem 8: generateCombinations - n choose k

**When to use:** Need ALL combinations/permutations
**Time:** O(2^n) or O(n!)
**Space:** O(n) recursion depth

---

### **2. SUBARRAY/SUBSTRING PROBLEMS** (10 problems across 2 approaches)

#### **Interface: `SlidingWindowInterface`**
Defines contract for all sliding window problems

#### **Implementation 1: `FixedWindowProblems` (K-sized window)**
- ✓ Problem 1: maxSumWindow - Maximum sum of K elements
- ✓ Problem 2: minSumWindow - Minimum sum of K elements
- ✓ Problem 3: averageWindow - Average per window
- ✓ Problem 4: countWindowsWithSum - Windows matching target
- ✓ Problem 5: maxElementInEachWindow - Max per window

**When to use:** Window size is fixed
**Pattern:** Slide = oldSum - leftElement + newElement
**Time:** O(n)
**Space:** O(1) or O(k)

#### **Implementation 2: `VariableWindowProblems` (Dynamic window)**
- ✓ Problem 6: longestUniqueSubstring - No repeating chars
- ✓ Problem 7: longestSubstringKDistinct - At most K distinct
- ✓ Problem 8: minWindowSubstring - Contains target chars
- ✓ Problem 9: minLengthSubarrayWithSum - Sum >= target
- ✓ Problem 10: maxLengthSubarrayWithSum - Sum <= target

**When to use:** Window size changes based on condition
**Pattern:** Expand right, shrink left based on validity
**Time:** O(n)
**Space:** O(charset_size) or O(1)

---

## 🎯 HOW TO USE

### **For Pair/Triplet Problems:**

```java
// Solution 1: If input is sorted or can be sorted
SortedPairProblems solver = new SortedPairProblems();
solver.hasPair(sortedArr, 7);           // O(n)
solver.findAllPairs(sortedArr, 7);      // O(n log n + n)

// Solution 2: If input is unsorted
UnsortedPairProblems solver = new UnsortedPairProblems();
solver.hasPair(unsortedArr, 7);         // O(n)
solver.countPairs(unsortedArr, 7);      // O(n) with duplicates

// Solution 3: Need all combinations with constraints
CombinationProblems solver = new CombinationProblems();
solver.combinationSum(candidates, 7);   // Repetition allowed
solver.combinationSumII(candidates, 8); // Each once
solver.generateCombinations(4, 2);      // 4 choose 2
```

### **For Sliding Window Problems:**

```java
// Fixed window (constant K)
FixedWindowProblems solver = new FixedWindowProblems();
solver.maxSumWindow(arr, 3);            // Max sum of 3 consecutive
solver.minSumWindow(arr, 3);            // Min sum of 3 consecutive

// Variable window (dynamic size)
VariableWindowProblems solver = new VariableWindowProblems();
solver.longestUniqueSubstring("abcabcbb");      // No repeats
solver.longestSubstringKDistinct("eceba", 2);  // At most 2 distinct
```

---

## 📋 QUICK DECISION TREE

### **Problem Type: PAIR/TRIPLET**
```
Do you have all pairs/triplets?
│
├─ NO → Different category
│
└─ YES
   │
   ├─ Can use each element multiple times?
   │  ├─ YES → CombinationProblems (Backtracking)
   │  └─ NO → Continue below
   │
   └─ Input sorted or can sort?
      ├─ YES → SortedPairProblems (Two Pointers)
      └─ NO  → UnsortedPairProblems (HashMap)
```

### **Problem Type: SUBARRAY/SUBSTRING**
```
Are elements CONTIGUOUS (consecutive)?
│
├─ NO → Not a sliding window problem
│
└─ YES
   │
   ├─ Window size constant (K)?
   │  ├─ YES → FixedWindowProblems
   │  │         Pattern: newSum = oldSum - arr[i-k] + arr[i]
   │  │         Time: O(n)
   │  │
   │  └─ NO → VariableWindowProblems
   │          Pattern: Expand right, shrink left
   │          Time: O(n)
   │
   └─ Use two pointers in both cases! (left, right)
```

---

## 🔍 EACH FILE INCLUDES

✅ **Comprehensive Interface**
- Problem definitions
- Input/output examples
- Approach explanation
- Time/space complexity

✅ **Multiple Implementations**
- Each with different approach
- Detailed algorithm steps
- Example traces
- Edge cases explained

✅ **Practice TODOs**
- ~5 problems per file
- Hidden implementations
- Hints provided
- Sample test cases in main()

✅ **Main Method with Tests**
- Sample test cases
- Shows expected behavior
- Easy to run and verify

---

## 📊 STATISTICS

| Category | Files | Problems | Approaches | TODOs |
|----------|-------|----------|-----------|-------|
| Pair/Triplet | 4 | 8 | 3 | 13 |
| Subarray/Substring | 3 | 10 | 2 | 10 |
| **TOTAL** | **7** | **18** | **5** | **23** |

---

## 🚀 NEXT STEPS

1. **Pick a category** (Pair/Triplet or Subarray/Substring)
2. **Study the Interface** to understand problem domains
3. **Read one Implementation** thoroughly
4. **Solve the TODOs** in one file at a time
5. **Run main()** to verify your solutions
6. **Compare approaches** between implementations
7. **Progress** to other variations and approaches

---

## 💡 KEY LEARNING POINTS

### **Pair/Triplet:**
- **Two Pointers**: When sorted, O(n) time, O(1) space
- **HashMap**: When unsorted, O(n) time, O(n) space
- **Backtracking**: When need combinations, complete search space

### **Sliding Window:**
- **Fixed**: Simple increment/decrement pattern
- **Variable**: Two pointer dance of expand/contract
- **Both**: Always O(n) time if done correctly!

---

## 📝 PRACTICE PROGRESSION

**Phase 1 - Understand Patterns:**
1. Read interface and understand all problem definitions
2. Study one implementation thoroughly
3. Solve at least 2-3 TODOs in first implementation

**Phase 2 - Multiple Approaches:**
4. Learn different approach for same problem
5. Understand tradeoffs (time vs space)
6. Solve remaining TODOs across all implementations

**Phase 3 - Mastery:**
7. Solve all TODOs
8. Create variations (different constraints)
9. Teach someone else!

---

**Happy Practicing! 🎉**

Start with whichever you feel less confident about, and master it!

