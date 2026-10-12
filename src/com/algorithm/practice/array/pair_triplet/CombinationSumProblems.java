package com.algorithm.practice.array.pair_triplet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ============================================================================
 * COMBINATION SUM PROBLEMS
 * ============================================================================
 *
 * PROBLEM:
 * Given an array of positive integers and a target value, find all possible
 * combinations where the selected numbers add up to the target.
 *
 * Example:
 *
 * Input:
 * candidates = [2, 3, 6, 7]
 * target = 7
 *
 * Output:
 * [[2, 2, 3], [7]]
 *
 * IMPORTANT:
 * 1. A number can be used multiple times.
 * 2. Order does not matter.
 *
 * For example:
 * [2, 2, 3]
 * [2, 3, 2]
 * [3, 2, 2]
 *
 * These are considered the SAME combination.
 *
 * ============================================================================
 *
 * MAIN RECURSION PATTERNS:
 *
 * 1. Reuse current element:
 *
 *      recursive call with i
 *
 * 2. Do not reuse current element:
 *
 *      recursive call with i + 1
 *
 * 3. Backtracking:
 *
 *      add
 *      recurse
 *      remove
 *
 * ============================================================================
 */
public class CombinationSumProblems {

    /**
     * ========================================================================
     * APPROACH 1: FOR LOOP + BACKTRACKING
     * ========================================================================
     *
     * Sort the array and use a for loop to select each candidate.
     *
     * Same index 'i' is passed to recursion.
     *
     * Therefore:
     *
     *      i -> repetition allowed
     *
     * Example:
     *
     * [2, 3, 6, 7], target = 7
     *
     * Result:
     * [[2, 2, 3], [7]]
     */
    public static List<List<Integer>> combinationSumBacktracking(int[] arr, int target) {
        List<List<Integer>> results = new ArrayList<>();

        Arrays.sort(arr);

        backtrack(arr, target, 0, new ArrayList<>(), results);

        return results;
    }

    private static void backtrack(int[] arr, int target, int start, List<Integer> current, List<List<Integer>> results) {

        //1.  Target reached, then found 1 full combination
        if (target == 0) {
            results.add(new ArrayList<>(current));
            return;
        }
        //iterate full array
        for (int i = start; i < arr.length; i++) {
            // Remember target comes with new value, so adding new value should be less
            if (arr[i] > target) {
                break;
            }

            // target is no reached yet, then add
            current.add(arr[i]);

            // Same i -> candidate can be reused
            backtrack(arr, target - arr[i], i, current, results);

            // Backtrack- A combination has been reached by now
            current.remove(current.size() - 1);
        }
    }


    /**
     * ========================================================================
     * APPROACH 2: INCLUDE / EXCLUDE RECURSION
     * ========================================================================
     *
     * At every index we have two choices:
     *
     * 1. INCLUDE current candidate
     * 2. EXCLUDE current candidate
     *
     * INCLUDE:
     *      add candidate
     *      reduce target
     *      stay at same index
     *
     * EXCLUDE:
     *      don't add candidate
     *      move to next index
     *
     * Pattern:
     *
     *      INCLUDE -> index
     *      EXCLUDE -> index + 1
     */
    public static List<List<Integer>> combinationSumIncludeExclude(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();

        Arrays.sort(candidates);

        includeExclude(candidates, target, 0, new ArrayList<>(), results);

        return results;
    }

    private static void includeExclude(int[] arr, int target, int index, List<Integer> current, List<List<Integer>> results) {

        // Target reached
        if (target == 0) {
            results.add(new ArrayList<>(current));
            return;
        }

        // No arr left
        if (index >= arr.length) {
            return;
        }

        // Candidate is bigger than target
        if (arr[index] > target) {
            return;
        }

        // ================================================================
        // INCLUDE
        // ================================================================

        current.add(arr[index]);

        // Same index -> repetition allowed
        includeExclude(arr, target - arr[index], index, current, results);

        // Backtrack
        current.remove(current.size() - 1);

        // ================================================================
        // EXCLUDE
        // ================================================================

        includeExclude(arr, target, index + 1, current, results);
    }


    /**
     * ========================================================================
     * APPROACH 3: RECURSIVE CHOICE + BACKTRACKING
     * ========================================================================
     *
     * This approach focuses directly on the recursive decision:
     *
     *      Choose candidate
     *           ↓
     *      Reduce target
     *           ↓
     *      Recursive call
     *           ↓
     *      Remove candidate
     *
     * Same index is passed because repetition is allowed.
     */
    public static List<List<Integer>> combinationSumRecursive(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();

        Arrays.sort(candidates);

        generate(candidates, target, 0, new ArrayList<>(), results);

        return results;
    }

    private static void generate(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> results) {

        // Target reached
        if (target == 0) {
            results.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            if (candidates[i] > target) {
                break;
            }

            // Choose
            current.add(candidates[i]);

            // Same i -> reuse allowed
            generate(candidates, target - candidates[i], i, current, results);

            // Undo choice
            current.remove(current.size() - 1);
        }
    }


    /**
     * ========================================================================
     * COMBINATION SUM II
     * ========================================================================
     *
     * DIFFERENCE:
     *
     * Combination Sum:
     *      Same candidate can be used multiple times.
     *
     * Combination Sum II:
     *      Each array element can be used only once.
     *
     * Therefore:
     *
     *      Combination Sum  -> recursive call with i
     *
     *      Combination Sum II -> recursive call with i + 1
     *
     * Also, duplicate values at the same recursion level are skipped.
     *
     * Example:
     *
     * candidates = [10, 1, 2, 7, 6, 1, 5]
     * target = 8
     *
     * Output:
     *
     * [[1, 1, 6],
     *  [1, 2, 5],
     *  [1, 7],
     *  [2, 6]]
     */
    public static List<List<Integer>> combinationSumII(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();

        Arrays.sort(candidates);

        combinationSumIIBacktrack(candidates, target, 0, new ArrayList<>(), results);

        return results;
    }

    private static void combinationSumIIBacktrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> results) {

        if (target == 0) {
            results.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            // Skip duplicate values at the same recursion level
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // No need to continue
            if (candidates[i] > target) {
                break;
            }

            // Choose
            current.add(candidates[i]);

            // i + 1 -> do not reuse same array element
            combinationSumIIBacktrack(candidates, target - candidates[i], i + 1, current, results);

            // Backtrack
            current.remove(current.size() - 1);
        }
    }


    /**
     * ========================================================================
     * MAIN METHOD
     * ========================================================================
     */
    public static void main(String[] args) {

        // ================================================================
        // TEST 1: Combination Sum
        // ================================================================

        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;

        System.out.println("Input: " + Arrays.toString(candidates1));
        System.out.println("Target: " + target1);

        System.out.println("Approach 1: with duplicates " + combinationSumBacktracking(candidates1, target1));

        System.out.println("Approach 2: with duplicates " + combinationSumIncludeExclude(candidates1, target1));

        System.out.println("Approach 3: with duplicates " + combinationSumRecursive(candidates1, target1));


        // ================================================================
        // TEST 2: Combination Sum II
        // ================================================================

        int[] candidates2 = {10, 1, 2, 7, 6, 1, 5};
        int target2 = 8;

        System.out.println();
        System.out.println("Input: " + Arrays.toString(candidates2));
        System.out.println("Target: " + target2);
        System.out.println("Combination Sum II: same backtracking logic + i + 1 + only extra part is duplicate skipping");
        System.out.println("Combination Sum II — duplicates NOT allowed inside for. skip index logic extra " + combinationSumII(candidates2, target2));
    }
}