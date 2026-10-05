package com.algorithm.practice.general;

import java.util.*;

/**
 * ============================================================================
 * PRACTICE: Backtracking & Recursion for Combinations/Permutations
 * ============================================================================
 * 
 * This is the PRACTICE version - incomplete methods for you to solve
 * 
 * Complete each method following the hints and approach comments
 * Test your solution against the examples in main()
 * 
 * ============================================================================
 */
public class BacktrackingPatternsPractice {

    public static void main(String[] args) {
        System.out.println("========== BACKTRACKING PRACTICE PROBLEMS ==========\n");

        System.out.println("=================== COMBINATIONS & SUBSETS ===================\n");

        // Practice 1: All Subsets
        System.out.println("PRACTICE 1: Generate All Subsets");
        try {
            allSubsets(new int[]{1, 2, 3});
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        System.out.println("\n=================== COMBINATIONS WITH CONSTRAINT ===================\n");

        // Practice 2: Combination Sum
        System.out.println("PRACTICE 2: Combination Sum");
        try {
            combinationSum(new int[]{2, 3, 6, 7}, 7);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        System.out.println("\n=================== PERMUTATIONS ===================\n");

        // Practice 3: All Permutations
        System.out.println("PRACTICE 3: Generate All Permutations");
        try {
            allPermutations(new int[]{1, 2, 3});
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        System.out.println("\n=================== LETTER COMBINATIONS ===================\n");

        // Practice 4: Letter Combinations of Phone Number
        System.out.println("PRACTICE 4: Letter Combinations (Phone Number)");
        try {
            letterCombinations("23");
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }
    }

    /**
     * PRACTICE 1: Generate All Subsets (Power Set)
     * 
     * Problem: Generate all possible subsets of given array
     * Input: [1, 2, 3]
     * Expected Output: [[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]]
     * Total: 2^n subsets
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Backtracking - Include/Exclude):
     * For each element: INCLUDE it or EXCLUDE it
     * 
     * RECURSIVE STRUCTURE:
     * backtrack(index, current_subset):
     *   if index == n:
     *     add current_subset to result
     *     return
     *   
     *   # CHOICE 1: INCLUDE current element
     *   current.add(arr[index])
     *   backtrack(index + 1, current)
     *   
     *   # BACKTRACK: EXCLUDE current element
     *   current.remove(arr[index])
     *   backtrack(index + 1, current)
     * 
     * DECISION TREE for [1, 2]:
     * 
     *            start([])
     *           /           \
     *      include 1      exclude 1
     *        ([1])          ([])
     *       /      \        /     \
     *   inc 2  exc 2   inc 2  exc 2
     *   [1,2]  [1]    [2]    []
     *     ✓     ✓      ✓     ✓
     * 
     * Total 4 solutions for 2 elements = 2^2
     * 
     * HINT: Two recursive calls in each function - include and exclude
     * 
     * Time: O(n * 2^n), Space: O(n) for recursion depth
     */
    public static void allSubsets(int[] nums) {
        throw new UnsupportedOperationException(
            "TODO: Generate all subsets using backtracking\n" +
            "Hint: For each element, make recursive call to include it, then exclude it"
        );
    }

    /**
     * PRACTICE 2: Combination Sum
     * 
     * Problem: Find all combinations where sum = target
     * Can reuse same element multiple times
     * Input: [2, 3, 6, 7], target = 7
     * Expected Output: [[2,2,3], [7]]
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Backtracking with Pruning):
     * RECURSIVE STRUCTURE:
     * backtrack(index, current_combination, current_sum):
     *   if current_sum == target:
     *     add current to result
     *     return  ← found solution, backtrack
     *   
     *   if current_sum > target:
     *     return  ← pruning: no point continuing
     *   
     *   for i from index to n:
     *     # CHOICE: Add arr[i] to combination
     *     current.add(arr[i])
     *     
     *     # EXPLORE: continue with same index (allow reuse)
     *     backtrack(i, current, current_sum + arr[i])
     *     
     *     # BACKTRACK: remove arr[i]
     *     current.remove(arr[i])
     * 
     * KEY DIFFERENCES from Practice 1:
     * - We have constraint: sum == target (pruning)
     * - We can reuse elements: backtrack(i) not backtrack(i+1)
     * - We track a numeric constraint, not just position
     * 
     * EXAMPLE TRACE for [2,3], target=5:
     * 
     * start([])
     *   try 2: [2]
     *     try 2: [2,2]
     *       try 2: [2,2,2] sum=6 > 5 PRUNE
     *       try 3: [2,2,3] sum=7 > 5 PRUNE
     *     try 3: [2,3]
     *       sum=5 → FOUND ✓
     *   try 3: [3]
     *     try 2: [3,2] (duplicate)
     *     try 3: [3,3] sum=6 > 5 PRUNE
     * 
     * HINT: Use pruning (sum > target) to avoid unnecessary exploration
     * 
     * Time: O(N^(T/M)) where T=target, M=min value
     * Space: O(T/M) for recursion depth
     */
    public static void combinationSum(int[] candidates, int target) {
        throw new UnsupportedOperationException(
            "TODO: Find all combinations summing to target\n" +
            "Hint: Recursively add elements, prune when sum > target, allow reuse (use same index)"
        );
    }

    /**
     * PRACTICE 3: Generate All Permutations
     * 
     * Problem: Generate all possible arrangements of given array
     * Input: [1, 2, 3]
     * Expected Output: [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]
     * Total: n! permutations
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Backtracking with Used Array):
     * RECURSIVE STRUCTURE:
     * backtrack(current, used):
     *   if current.size() == n:
     *     add copy of current to result
     *     return
     *   
     *   for i from 0 to n:
     *     if used[i]:
     *       continue  ← skip already used
     *     
     *     # CHOICE: Use element at index i
     *     used[i] = true
     *     current.add(arr[i])
     *     
     *     # EXPLORE
     *     backtrack(current, used)
     *     
     *     # BACKTRACK
     *     current.remove(current.size() - 1)
     *     used[i] = false
     * 
     * KEY INSIGHT:
     * - Use boolean array to track which elements are already in current permutation
     * - At each position, try each unused element
     * - Different from subsets: we care about position/order
     * 
     * DECISION TREE for [1,2]:
     * 
     * Position 0:
     *   try 1: [1]
     *     Position 1:
     *       try 2: [1,2] ✓
     *   try 2: [2]
     *     Position 1:
     *       try 1: [2,1] ✓
     * 
     * Total: 2! = 2 permutations
     * 
     * DIFFERENCE from subsets:
     * - Subsets: choose elements (unordered)
     * - Permutations: arrange elements (ordered)
     * - So [1,2] and [2,1] are DIFFERENT in permutations
     *   but SAME in subsets (after sorting)
     * 
     * HINT: Use boolean array to track used elements
     * 
     * Time: O(n * n!), Space: O(n) for recursion
     */
    public static void allPermutations(int[] nums) {
        throw new UnsupportedOperationException(
            "TODO: Generate all permutations using backtracking\n" +
            "Hint: Use boolean array to track used elements, try each unused at each position"
        );
    }

    /**
     * PRACTICE 4: Letter Combinations of Phone Number
     * 
     * Problem: Find all letter combinations for given digits
     * Mapping:
     * 2→"abc", 3→"def", 4→"ghi", 5→"jkl", 6→"mno",
     * 7→"pqrs", 8→"tuv", 9→"wxyz"
     * 
     * Input: "23"
     * Expected Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * Explanation: 2→abc, 3→def → 3*3=9 combinations
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Backtracking on Digit Positions):
     * RECURSIVE STRUCTURE:
     * backtrack(digit_index, current_string):
     *   if digit_index == n:
     *     add current to result
     *     return
     *   
     *   digit = digits[digit_index]
     *   letters = mapping[digit]  # "abc" for digit 2
     *   
     *   for each letter in letters:
     *     # CHOICE: Add letter to current string
     *     current.append(letter)
     *     
     *     # EXPLORE: Continue to next digit
     *     backtrack(digit_index + 1, current)
     *     
     *     # BACKTRACK: Remove letter
     *     current.remove last
     * 
     * KEY INSIGHT:
     * - Process one digit at a time
     * - For each digit, try all its corresponding letters
     * - Build combinations row by row
     * 
     * TRACE for "23":
     * 
     * Digit 0 (2→abc):
     *   letter a → "a"
     *     Digit 1 (3→def):
     *       letter d → "ad" ✓
     *       letter e → "ae" ✓
     *       letter f → "af" ✓
     *   letter b → "b"
     *     Digit 1 (3→def):
     *       letter d → "bd" ✓
     *       letter e → "be" ✓
     *       letter f → "bf" ✓
     *   letter c → "c"
     *     Digit 1 (3→def):
     *       letter d → "cd" ✓
     *       letter e → "ce" ✓
     *       letter f → "cf" ✓
     * 
     * Total: 3 * 3 = 9 combinations
     * General: |letters[d0]| * |letters[d1]| * ...
     * 
     * DIFFERENCE from others:
     * - Not about permuting existing elements
     * - Building new strings by choosing from different options per digit
     * 
     * HINT: Create digit-to-letters mapping first, then backtrack
     * 
     * Time: O(4^n * n) where 4 is max letters per digit
     * Space: O(n) for recursion depth
     */
    public static void letterCombinations(String digits) {
        throw new UnsupportedOperationException(
            "TODO: Generate letter combinations for phone number\n" +
            "Hint: Create mapping, backtrack digit by digit trying each letter"
        );
    }
}

