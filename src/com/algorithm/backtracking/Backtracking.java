package com.algorithm.backtracking;

import java.util.*;

/**
 * ============================================================================
 * BACKTRACKING — Comprehensive Guide with All Problems
 * ============================================================================
 *
 * WHAT IS BACKTRACKING?
 * A technique to explore all possible solutions by:
 * 1. Making a choice
 * 2. Recursively exploring with that choice
 * 3. Undoing the choice (backtracking)
 * 4. Trying another choice
 *
 * KEY PATTERN: Make → Explore → Undo → Try Next
 *
 * ============================================================================
 */

public class Backtracking {

    // ========================================================================
    // PROBLEM 1: SUBSETS
    // ========================================================================

    /**
     * PROBLEM STATEMENT:
     * ────────────────
     * Given an array of unique integers, return all possible subsets (power set).
     *
     * INPUT: [1, 2, 3]
     *
     * OUTPUT: [
     *   [],
     *   [1], [2], [3],
     *   [1,2], [1,3], [2,3],
     *   [1,2,3]
     * ]
     * Total: 2^3 = 8 subsets
     *
     * APPROACH:
     * ────────
     * At each element, make a CHOICE:
     * - INCLUDE current element
     * - EXCLUDE current element
     *
     * Build a decision tree from index 0 to n-1
     *
     * DECISION TREE:
     *                    []
     *                   /  \
     *            include 1   exclude 1
     *              /            \
     *            [1]             []
     *           /   \           /   \
     *      include 2  exclude 2
     *        /          \        /      \
     *      [1,2]       [1]     [2]      []
     *      / \         / \      / \     / \
     *    [1,2,3] [1,2] [1,3] [1] [2,3] [2] [3] []
     *
     * RECURRENCE:
     * subsets(index i) = subsets(i+1 with [i]) + subsets(i+1 without [i])
     *
     * Time: O(2^n × n) - 2^n subsets, each O(n) to copy
     * Space: O(n) - recursion depth
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrackSubsets(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // BASE CASE: Reached end of array, save current subset
        //if full array list if traversed, then retun list
        if (index == nums.length) {
            result.add(new ArrayList<>(current));  // IMPORTANT: Copy, not reference!
            return;
        }

        // CHOICE 1: INCLUDE current element
        //create subset. subset will have liost of elements
        current.add(nums[index]);
        //after sublist element add, then traverse next element of main array.
        // Then we need to increase the number as well
        backtrackSubsets(nums, index + 1, current, result);

        // CHOICE 2: EXCLUDE current element (Backtrack)
        //This is kind of Level 2. Exclude first elemtn, then make combination with remaining
        current.remove(current.size() - 1);
        backtrackSubsets(nums, index + 1, current, result);
    }

    // ========================================================================
    // PROBLEM 2: SUBSETS II (WITH DUPLICATES)
    // ========================================================================
    /**
     * PROBLEM STATEMENT:
     * ────────────────
     * Given an array with DUPLICATES, return all unique subsets.
     * INPUT: [1, 2, 2]
     *
     * OUTPUT: [
     *   [],
     *   [1], [2], [1,2], [2,2], [1,2,2]
     * ]
     * Note: [2,2] appears once, not twice
     *
     * APPROACH:
     * ────────
     * 1. Sort array first (to group duplicates together)
     * 2. For each index, decide: INCLUDE or EXCLUDE
     * 3. When EXCLUDING, skip all duplicates of current element
     *
     * Why skip duplicates?
     * If we exclude 2 at position i, we already explored all subsets
     * without this 2. Excluding 2 at position i+1 (duplicate) would
     * generate SAME subsets again.
     *
     * Time: O(2^n × n)
     * Space: O(n)
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);  // Group duplicates
        List<List<Integer>> result = new ArrayList<>();
        backtrackSubsetsII(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrackSubsetsII(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // BASE CASE
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // CHOICE 1: INCLUDE current element
        current.add(nums[index]);
        backtrackSubsetsII(nums, index + 1, current, result);

        // CHOICE 2: EXCLUDE current element
        current.remove(current.size() - 1);

        // Skip all duplicates of current element
        while (index + 1 < nums.length && nums[index] == nums[index + 1]) {
            index++;
        }

        backtrackSubsetsII(nums, index + 1, current, result);
    }

    // ========================================================================
    // PROBLEM 3: PERMUTATIONS
    // ========================================================================

    /**
     * PROBLEM STATEMENT:
     * ────────────────
     * Given an array of unique integers, return all permutations.
     *
     * INPUT: [1, 2, 3]
     *
     * OUTPUT: [
     *   [1,2,3], [1,3,2],
     *   [2,1,3], [2,3,1],
     *   [3,1,2], [3,2,1]
     * ]
     * Total: 3! = 6 permutations
     *
     * APPROACH:
     * ────────
     * At each position, pick ANY remaining unused element
     * (Order matters! [1,2,3] ≠ [3,2,1])
     *
     * DECISION TREE:
     *              []
     *            / | \
     *           1  2  3
     *          /   |   \
     *       [1]  [2]  [3]
     *      / |   / |   / |
     *    2   3 1   3 1   2
     *   [1,2] [1,3] [2,1] [2,3] [3,1] [3,2]
     *    |      |      |      |      |      |
     *    3      2      3      1      2      1
     *  [1,2,3] [1,3,2] [2,1,3] [2,3,1] [3,1,2] [3,2,1]
     *
     * TRACK: Use boolean array to mark used elements
     *
     * Time: O(n! × n) - n! permutations, each O(n) to copy
     * Space: O(n) - recursion depth + used array
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackPermute(nums, new ArrayList<>(), new boolean[nums.length], result);
        return result;
    }

    private void backtrackPermute(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
        // BASE CASE: Used all elements
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // EXPLORE: Try each unused element
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;  // Skip used elements

            // CHOOSE
            current.add(nums[i]);
            used[i] = true;

            // RECURSE
            backtrackPermute(nums, current, used, result);

            // UNDO (Backtrack)
            used[i] = false;
            current.remove(current.size() - 1);
        }
    }

    // ========================================================================
    // PROBLEM 4: PERMUTATIONS II (WITH DUPLICATES)
    // ========================================================================

    /**
     * PROBLEM STATEMENT:
     * ────────────────
     * Given an array with DUPLICATES, return all unique permutations.
     *
     * INPUT: [1, 1, 2]
     *
     * OUTPUT: [
     *   [1,1,2], [1,2,1], [2,1,1]
     * ]
     * Note: Only 3 unique permutations (not 3! = 6)
     *
     * APPROACH:
     * ────────
     * 1. Sort array to group duplicates
     * 2. Skip duplicate choices at same recursion level
     *    (But allow duplicates in different branches)
     *
     * Why skip?
     * If we pick 1 at position i, we explore all permutations with this 1.
     * Picking 1 at position i+1 (duplicate) would explore SAME permutations.
     *
     * Time: O(n! × n)
     * Space: O(n)
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrackPermuteII(nums, new ArrayList<>(), new boolean[nums.length], result);
        return result;
    }

    private void backtrackPermuteII(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
        // BASE CASE
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // EXPLORE: Try each unused element
        for (int i = 0; i < nums.length; i++) {
            // Skip used
            if (used[i]) continue;

            // Skip duplicate at same level:
            // If previous element is same and NOT used, skip this
            if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) {
                continue;
            }

            // CHOOSE
            current.add(nums[i]);
            used[i] = true;

            // RECURSE
            backtrackPermuteII(nums, current, used, result);

            // UNDO
            used[i] = false;
            current.remove(current.size() - 1);
        }
    }

    // ========================================================================
    // PROBLEM 5: COMBINATION SUM
    // ========================================================================

    /**
     * PROBLEM STATEMENT:
     * ────────────────
     * Find all combinations of candidates where sum equals target.
     * Each element can be used UNLIMITED times.
     *
     * INPUT:
     * candidates = [2, 3, 6, 7], target = 7
     *
     * OUTPUT: [
     *   [7],
     *   [2, 2, 3]
     * ]
     *
     * APPROACH:
     * ────────
     * Use start index to avoid duplicate combinations.
     * For each choice, recursively explore with SAME index (unlimited use).
     *
     * Decision tree for [2,3] target=7:
     *
     *               []
     *              /
     *           pick 2 (sum=2)
     *            /
     *          [2]
     *         /  \
     *    pick 2  pick 3
     *   (sum=4) (sum=5)
     *     /       \
     *   [2,2]    [2,3]
     *    / \       |
     *  pick 2  pick 3  pick 3 → sum=8 (prune)
     *  sum=6    sum=7 ✓
     *
     * PRUNING:
     * Stop if sum > target (optimization)
     *
     * Time: O(N^(T/M)) where N=candidates, T=target, M=min value
     * Space: O(T/M) - recursion depth
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackCombinationSum(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrackCombinationSum(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> result) {
        // BASE CASE 1: Found solution
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // BASE CASE 2: Exceeded target (Pruning)
        if (target < 0) {
            return;
        }

        // EXPLORE: Start from index to avoid duplicates
        for (int i = start; i < candidates.length; i++) {
            // CHOOSE
            current.add(candidates[i]);

            // RECURSE with SAME index (unlimited use)
            backtrackCombinationSum(candidates, target - candidates[i], i, current, result);

            // UNDO
            current.remove(current.size() - 1);
        }
    }

    // ========================================================================
    // PROBLEM 6: COMBINATIONS (N CHOOSE K)
    // ========================================================================

    /**
     * PROBLEM STATEMENT:
     * ────────────────
     * Find all combinations of k elements from n = [1, 2, ..., n]
     *
     * INPUT: n = 4, k = 2
     *
     * OUTPUT: [
     *   [1,2], [1,3], [1,4],
     *   [2,3], [2,4],
     *   [3,4]
     * ]
     * Total: C(4,2) = 6 combinations
     *
     * APPROACH:
     * ────────
     * Pick k elements from n, without repetition and order-independent.
     * Use start index to ensure combinations (not permutations).
     *
     * Decision tree for n=4, k=2:
     *
     *            []
     *          / | | \
     *         1  2 3  4
     *        /   |  \ \
     *      [1]  [2] [3] [4]
     *      / \   / \ |
     *     2   3 3  4 4
     *   [1,2][1,3][2,3][2,4][3,4]
     *
     * Time: O(C(n,k))
     * Space: O(k)
     */
    public List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackCombinations(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    private void backtrackCombinations(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {
        // BASE CASE: Got k elements
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        // EXPLORE: Pick from start to n
        for (int i = start; i <= n; i++) {
            // CHOOSE
            current.add(i);

            // RECURSE: Move start forward (no repetition)
            backtrackCombinations(n, k, i + 1, current, result);

            // UNDO
            current.remove(current.size() - 1);
        }
    }

    // ========================================================================
    // PROBLEM 7: LETTER COMBINATIONS OF PHONE NUMBER
    // ========================================================================

    /**
     * PROBLEM STATEMENT:
     * ────────────────
     * Map phone number digits to letters and find all combinations.
     *
     * Phone mapping:
     * 2: abc, 3: def, 4: ghi, 5: jkl, 6: mno,
     * 7: pqrs, 8: tuv, 9: wxyz
     *
     * INPUT: digits = "23"
     *
     * OUTPUT: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * (2→[a,b,c], 3→[d,e,f], all combinations)
     *
     * APPROACH:
     * ────────
     * For each digit, pick one letter from its mapping.
     * Build combination by traversing digits sequentially.
     *
     * Decision tree for "23":
     *
     *              ""
     *            / | \
     *           a  b  c   (letters for 2)
     *          /  |  \
     *        [a]  [b] [c]
     *       / | \ / | \ / | \
     *      d  e f d e f d e f  (letters for 3)
     *
     * Result: [ad, ae, af, bd, be, bf, cd, ce, cf]
     *
     * Time: O(4^n) - max 4 letters per digit, n digits
     * Space: O(n) - recursion depth
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        backtrackLetterCombinations(digits, 0, new StringBuilder(), result, map);
        return result;
    }

    private void backtrackLetterCombinations(String digits, int index, StringBuilder current,
                                           List<String> result, Map<Character, String> map) {
        // BASE CASE: Used all digits
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get letters for current digit
        String letters = map.get(digits.charAt(index));

        // EXPLORE: Try each letter
        for (char letter : letters.toCharArray()) {
            // CHOOSE
            current.append(letter);

            // RECURSE
            backtrackLetterCombinations(digits, index + 1, current, result, map);

            // UNDO
            current.deleteCharAt(current.length() - 1);
        }
    }

    // ========================================================================
    // PROBLEM 8: GENERATE PARENTHESES
    // ========================================================================

    /**
     * PROBLEM STATEMENT:
     * ────────────────
     * Generate all combinations of well-formed parentheses with n pairs.
     *
     * INPUT: n = 3
     *
     * OUTPUT: [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     * Total: Catalan number C_n ≈ O(4^n/√n)
     *
     * RULES:
     * 1. At any point, open count ≥ close count
     * 2. At end, open == close == n
     *
     * APPROACH:
     * ────────
     * Track counts of open and close parentheses.
     * Make choices:
     * - Add '(' if open < n
     * - Add ')' if close < open
     *
     * Decision tree for n=2:
     *
     *             ""
     *            /
     *         add '('
     *          /
     *        "("
     *       /   \
     *    '('     ')'
     *   "((    "()"
     *    |       / \
     *   ')'    '('  ')'
     *   "(()   "()(" "()"
     *    |      |     |
     *   ')'    ')'   '('
     *   "(())" "()( )()(
     *          |
     *         ')'
     *        "()("
     *
     * Time: O(4^n/√n) - Catalan number
     * Space: O(n) - recursion depth
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrackParenthesis(n, 0, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrackParenthesis(int n, int open, int close, StringBuilder current, List<String> result) {
        // BASE CASE: Generated n pairs
        if (open == n && close == n) {
            result.add(current.toString());
            return;
        }

        // CHOICE 1: Add '(' if haven't reached limit
        if (open < n) {
            current.append('(');
            backtrackParenthesis(n, open + 1, close, current, result);
            current.deleteCharAt(current.length() - 1);
        }

        // CHOICE 2: Add ')' if haven't used all and valid (close < open)
        if (close < open) {
            current.append(')');
            backtrackParenthesis(n, open, close + 1, current, result);
            current.deleteCharAt(current.length() - 1);
        }
    }

    // ========================================================================
    // MAIN - DEMONSTRATION
    // ========================================================================

    public static void main(String[] args) {
        Backtracking bt = new Backtracking();

        System.out.println("========== PROBLEM 1: SUBSETS ==========");
        System.out.println("Input: [1, 2, 3]");
        System.out.println("Output: " + bt.subsets(new int[]{1, 2, 3}));

        System.out.println("\n========== PROBLEM 2: SUBSETS II ==========");
        System.out.println("Input: [1, 2, 2]");
        System.out.println("Output: " + bt.subsetsWithDup(new int[]{1, 2, 2}));

        System.out.println("\n========== PROBLEM 3: PERMUTATIONS ==========");
        System.out.println("Input: [1, 2, 3]");
        System.out.println("Output: " + bt.permute(new int[]{1, 2, 3}));

        System.out.println("\n========== PROBLEM 4: PERMUTATIONS II ==========");
        System.out.println("Input: [1, 1, 2]");
        System.out.println("Output: " + bt.permuteUnique(new int[]{1, 1, 2}));

        System.out.println("\n========== PROBLEM 5: COMBINATION SUM ==========");
        System.out.println("Input: candidates=[2,3,6,7], target=7");
        System.out.println("Output: " + bt.combinationSum(new int[]{2, 3, 6, 7}, 7));

        System.out.println("\n========== PROBLEM 6: COMBINATIONS ==========");
        System.out.println("Input: n=4, k=2");
        System.out.println("Output: " + bt.combinations(4, 2));

        System.out.println("\n========== PROBLEM 7: LETTER COMBINATIONS ==========");
        System.out.println("Input: digits=\"23\"");
        System.out.println("Output: " + bt.letterCombinations("23"));

        System.out.println("\n========== PROBLEM 8: GENERATE PARENTHESES ==========");
        System.out.println("Input: n=3");
        System.out.println("Output: " + bt.generateParenthesis(3));
    }
}

