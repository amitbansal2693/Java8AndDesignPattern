package com.algorithm.general;

import java.util.*;

/**
 * ============================================================================
 * PATTERN: Backtracking & Recursion for Combinations/Permutations
 * ============================================================================
 * 
 * PATTERN IDENTIFICATION:
 * - Need to generate all possible choices
 * - Need to explore all possibilities
 * - Problems want combinations, permutations, or subsets
 * - Constraints need to be satisfied
 * 
 * WHEN TO USE BACKTRACKING:
 * ✓ "Find all combinations"
 * ✓ "Find all permutations"
 * ✓ "Generate all subsets"
 * ✓ "Word search in grid"
 * ✓ "N-Queens problem"
 * ✓ "Sudoku solver"
 * ✓ "All paths in tree"
 * ✓ "Letter combinations of phone number"
 * 
 * KEY CONCEPT:
 * 1. MAKE A CHOICE
 * 2. EXPLORE with that choice
 * 3. UNDO the choice (backtrack)
 * 4. TRY NEXT CHOICE
 * 
 * TEMPLATE:
 * ┌─ Base case: reached valid solution
 * │  └─ Add to result, return
 * │
 * ├─ For each possible choice:
 * │  ├─ Make choice (add to current)
 * │  ├─ Recurse with that choice
 * │  └─ Undo choice (remove from current) ← BACKTRACK
 * │
 * └─ Return result
 * 
 * COMPLEXITY: Exponential (2^n for subsets, n! for permutations)
 * 
 * ============================================================================
 */
public class BacktrackingPatterns {

    public static void main(String[] args) {
        System.out.println("=================== COMBINATIONS & SUBSETS ===================\n");

        // Example 1: All Subsets
        System.out.println("EXAMPLE 1: Generate All Subsets");
        allSubsets(new int[]{1, 2, 3});

        System.out.println("\n=================== COMBINATIONS WITH CONSTRAINT ===================\n");

        // Example 2: Combinations of Numbers (Sum to Target)
        System.out.println("EXAMPLE 2: Combination Sum");
        combinationSum(new int[]{2, 3, 6, 7}, 7);

        System.out.println("\n=================== PERMUTATIONS ===================\n");

        // Example 3: All Permutations
        System.out.println("EXAMPLE 3: Generate All Permutations");
        allPermutations(new int[]{1, 2, 3});

        System.out.println("\n=================== LETTER COMBINATIONS ===================\n");

        // Example 4: Letter Combinations of Phone Number
        System.out.println("EXAMPLE 4: Letter Combinations (Phone Number)");
        letterCombinations("234");
    }

    // ========================================================================
    // EXAMPLE 1: All Subsets
    // ========================================================================

    /**
     * EXAMPLE 1: Generate All Subsets (Power Set)
     * 
     * Problem: Generate all possible subsets of given array
     * Input: [1, 2, 3]
     * Output: [[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]]
     * 
     * APPROACH (Backtracking):
     * For each element: either INCLUDE it or EXCLUDE it
     * 
     * Decision tree for [1, 2, 3]:
     * Start with []
     * ├─ INCLUDE 1 → [1]
     * │  ├─ INCLUDE 2 → [1, 2]
     * │  │  ├─ INCLUDE 3 → [1, 2, 3] ✓ (solution)
     * │  │  └─ EXCLUDE 3 → [1, 2] ✓ (solution)
     * │  └─ EXCLUDE 2 → [1]
     * │     ├─ INCLUDE 3 → [1, 3] ✓ (solution)
     * │     └─ EXCLUDE 3 → [1] ✓ (solution)
     * └─ EXCLUDE 1 → []
     *    ├─ INCLUDE 2 → [2]
     *    │  ├─ INCLUDE 3 → [2, 3] ✓ (solution)
     *    │  └─ EXCLUDE 3 → [2] ✓ (solution)
     *    └─ EXCLUDE 2 → []
     *       ├─ INCLUDE 3 → [3] ✓ (solution)
     *       └─ EXCLUDE 3 → [] ✓ (solution)
     * 
     * Total: 2^n subsets
     * 
     * Time: O(n * 2^n), Space: O(n) for recursion depth
     */
    public static void allSubsets(int[] nums) {
        System.out.println("Array: " + Arrays.toString(nums));
        List<List<Integer>> result = new ArrayList<>();

        System.out.println("\nGenerating subsets:");
        backtrackSubsets(nums, 0, new ArrayList<>(), result);

        System.out.println("\nAll subsets (" + result.size() + " total):");
        for (List<Integer> subset : result) {
            System.out.println("  " + subset);
        }
    }

    private static void backtrackSubsets(int[] nums, int index, List<Integer> current, 
                                        List<List<Integer>> result) {
        // Base case: processed all elements
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            System.out.println("  Added subset: " + current);
            return;
        }

        // CHOICE 1: INCLUDE current element
        current.add(nums[index]);
        System.out.println("  Include " + nums[index] + " → " + current);
        backtrackSubsets(nums, index + 1, current, result);

        // BACKTRACK: EXCLUDE current element
        current.remove(current.size() - 1);
        System.out.println("  Exclude " + nums[index] + " → " + current);
        backtrackSubsets(nums, index + 1, current, result);
    }

    // ========================================================================
    // EXAMPLE 2: Combinations with Sum Constraint
    // ========================================================================

    /**
     * EXAMPLE 2: Combination Sum (Find combinations that sum to target)
     * 
     * Problem: Find all unique combinations where sum = target
     * Can reuse same element multiple times
     * Input: [2, 3, 6, 7], target = 7
     * Output: [[2,2,3], [7]]
     * 
     * APPROACH (Backtracking with Pruning):
     * 1. At each step, try each number
     * 2. Add it to current combination
     * 3. If sum = target, found a solution
     * 4. If sum < target, continue exploring
     * 5. If sum > target, backtrack (pruning)
     * 6. Can reuse elements (start index doesn't increment)
     * 
     * Decision tree for [2,3,6,7], target=7:
     * 
     * []
     * ├─ Add 2 → [2] (sum=2)
     * │  ├─ Add 2 → [2,2] (sum=4)
     * │  │  ├─ Add 2 → [2,2,2] (sum=6)
     * │  │  │  └─ Add 2 → [2,2,2,2] (sum=8 > 7) PRUNE ✗
     * │  │  ├─ Add 3 → [2,2,3] (sum=7) ✓ SOLUTION
     * │  │  └─ Add 6 → [2,2,6] (sum=10 > 7) PRUNE ✗
     * ├─ Add 3 → [3] (sum=3)
     * ├─ Add 6 → [6] (sum=6)
     * │  └─ Add 2 → [6,2] sum=8 > 7, but 2 comes after 6 in order
     * └─ Add 7 → [7] (sum=7) ✓ SOLUTION
     * 
     * Time: O(N^(T/M)) where T=target, M=minimal value
     * Space: O(T/M) for recursion depth
     */
    public static void combinationSum(int[] candidates, int target) {
        System.out.println("Candidates: " + Arrays.toString(candidates) + ", Target: " + target);
        List<List<Integer>> result = new ArrayList<>();

        System.out.println("\nBacktracking:");
        backtrackCombinationSum(candidates, target, 0, 0, new ArrayList<>(), result);

        System.out.println("\nCombinations found (" + result.size() + " total):");
        for (List<Integer> combo : result) {
            int sum = combo.stream().mapToInt(Integer::intValue).sum();
            System.out.println("  " + combo + " → sum=" + sum);
        }
    }

    private static void backtrackCombinationSum(int[] candidates, int target, int index, 
                                               int currentSum, List<Integer> current, 
                                               List<List<Integer>> result) {
        // Base case: found valid combination
        if (currentSum == target) {
            result.add(new ArrayList<>(current));
            System.out.println("  ✓ Found: " + current);
            return;
        }

        // If sum exceeded target, backtrack
        if (currentSum > target) {
            System.out.println("  ✗ Sum " + currentSum + " > target " + target + " → BACKTRACK");
            return;
        }

        // Try each candidate starting from index (allows reuse)
        for (int i = index; i < candidates.length; i++) {
            int num = candidates[i];

            // CHOICE: Add current candidate
            current.add(num);
            System.out.println("  Try adding " + num + " → " + current + " (sum=" + (currentSum + num) + ")");

            // EXPLORE
            backtrackCombinationSum(candidates, target, i, currentSum + num, current, result);

            // BACKTRACK: Remove candidate
            current.remove(current.size() - 1);
        }
    }

    // ========================================================================
    // EXAMPLE 3: All Permutations
    // ========================================================================

    /**
     * EXAMPLE 3: Generate All Permutations
     * 
     * Problem: Generate all possible arrangements of given array
     * Input: [1, 2, 3]
     * Output: [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]
     * 
     * APPROACH (Backtracking):
     * Use boolean array to track used elements
     * At each position, try each unused element
     * 
     * Decision tree for [1, 2, 3]:
     * Position 0:
     *   ├─ Use 1
     *   │  Position 1:
     *   │    ├─ Use 2
     *   │    │  Position 2: Use 3 → [1,2,3] ✓
     *   │    └─ Use 3
     *   │       Position 2: Use 2 → [1,3,2] ✓
     *   ├─ Use 2
     *   │  Position 1:
     *   │    ├─ Use 1
     *   │    │  Position 2: Use 3 → [2,1,3] ✓
     *   │    └─ Use 3
     *   │       Position 2: Use 1 → [2,3,1] ✓
     *   └─ Use 3 ...
     * 
     * Total: n! permutations
     * 
     * Time: O(n * n!), Space: O(n) for recursion
     */
    public static void allPermutations(int[] nums) {
        System.out.println("Array: " + Arrays.toString(nums));
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        System.out.println("\nGenerating permutations:");
        backtrackPermutations(nums, used, new ArrayList<>(), result);

        System.out.println("\nAll permutations (" + result.size() + " total):");
        for (List<Integer> perm : result) {
            System.out.println("  " + perm);
        }
    }

    private static void backtrackPermutations(int[] nums, boolean[] used, List<Integer> current, 
                                             List<List<Integer>> result) {
        // Base case: filled all positions
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            System.out.println("  ✓ Found: " + current);
            return;
        }

        // Try each unused element
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // CHOICE: Use this element
                used[i] = true;
                current.add(nums[i]);

                // EXPLORE
                backtrackPermutations(nums, used, current, result);

                // BACKTRACK: Undo choice
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    // ========================================================================
    // EXAMPLE 4: Letter Combinations of Phone Number
    // ========================================================================

    /**
     * EXAMPLE 4: Letter Combinations of Phone Number
     * 
     * Problem: Find all letter combinations for given digits
     * Input: "234"
     * Output: ["adg","adh","adi","aeg","aeh","aei","afg","afh","afi","bdg","bdh",...]]
     * 
     * Mapping:
     * 2 → "abc", 3 → "def", 4 → "ghi", 5 → "jkl", 6 → "mno", 7 → "pqrs", 8 → "tuv", 9 → "wxyz"
     * 
     * APPROACH (Backtracking):
     * For each digit, try each letter it maps to
     * Move to next digit only after all combinations with current letter
     * 
     * Decision tree for "23":
     * 
     * Digit 2 (maps to "abc"):
     * ├─ Letter a
     * │  Digit 3 (maps to "def"):
     * │  ├─ ad ✓
     * │  ├─ ae ✓
     * │  └─ af ✓
     * ├─ Letter b
     * │  ├─ bd ✓
     * │  ├─ be ✓
     * │  └─ bf ✓
     * └─ Letter c
     *    ├─ cd ✓
     *    ├─ ce ✓
     *    └─ cf ✓
     * 
     * Total: length of letter_combinations[digit0] * length[digit1] * ... 
     * For "234": 3 * 3 * 4 = 36 combinations
     * 
     * Time: O(4^n) worst case, Space: O(n) recursion depth
     */
    public static void letterCombinations(String digits) {
        System.out.println("Digits: " + digits);

        if (digits.isEmpty()) {
            System.out.println("Empty input");
            return;
        }

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        List<String> result = new ArrayList<>();
        System.out.println("\nBacktracking:");
        backtrackLetterCombinations(digits, 0, new StringBuilder(), result, map);

        System.out.println("\nAll combinations (" + result.size() + " total):");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if ((i + 1) % 6 == 0) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    private static void backtrackLetterCombinations(String digits, int index, StringBuilder current, 
                                                   List<String> result, Map<Character, String> map) {
        // Base case: processed all digits
        if (index == digits.length()) {
            result.add(current.toString());
            System.out.println("  ✓ " + current);
            return;
        }

        char digit = digits.charAt(index);
        String letters = map.get(digit);

        // Try each letter for current digit
        for (char letter : letters.toCharArray()) {
            // CHOICE: Add letter
            current.append(letter);

            // EXPLORE next digit
            backtrackLetterCombinations(digits, index + 1, current, result, map);

            // BACKTRACK: Remove letter
            current.deleteCharAt(current.length() - 1);
        }
    }
}

