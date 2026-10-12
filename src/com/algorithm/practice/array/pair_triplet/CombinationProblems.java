package com.algorithm.practice.array.pair_triplet;

import java.util.*;

/**
 * ============================================================================
 * COMBINATION PROBLEMS - Backtracking Approach
 * ============================================================================
 * 
 * WHEN TO USE: When you need ALL possible combinations/permutations
 * 
 * KEY INSIGHT:
 * Build decision tree - at each step, make a choice and explore
 * Backtrack to try other choices
 * 
 * PATTERN:
 * 1. Make a choice (include or skip element)
 * 2. Recursively solve remaining problem
 * 3. Undo choice (backtrack)
 * 4. Try next choice
 * 
 * COMBINATION SUM (can use elements multiple times):
 *   candidates = [2, 3, 6, 7], target = 7
 *   Decision tree:
 *           root
 *          /  |  \
 *      pick 2  pick 3  pick 6...
 *      /
 *   pick 2 again...
 * 
 * COMBINATION SUM II (use each element max once):
 *   candidates = [10, 1, 2, 7, 6, 1, 5], target = 8 (sorted first)
 *   Decision tree with tracking which elements used
 * 
 * Time: O(2^n) for combinations, O(n!) for permutations
 * Space: O(n) recursion depth
 * 
 * ============================================================================
 */
@SuppressWarnings("ALL")
public class CombinationProblems implements PairTripletInterface {

    // ========================================================================
    // COMBINATION SUM - Each element can be used MULTIPLE times
    // ========================================================================

    /**
     * PROBLEM 6: Combination Sum (Reusable Elements)
     * 
     * Problem: Find all combinations that sum to target
     * Can use same element multiple times
     * No duplicates in input
     * 
     * Input: [2, 3, 6, 7], target = 7
     * Output: [[2,2,3], [7]]
     * Explanation:
     *   - 2+2+3 = 7 (used 2 twice)
     *   - 7 = 7 (used 7 once)
     * 
     * Approach (Backtracking with Start Index):
     * 1. Define: backtrack(currentPath, start, remaining)
     * 2. Base case: if remaining == 0, we found a combination!
     * 3. For each candidate from start to end:
     *    - If candidate > remaining, skip (optimization)
     *    - Add candidate to path
     *    - Recurse with same start (allow reuse!)
     *    - Remove candidate (backtrack)
     * 4. Return all paths
     * 
     * Why start index doesn't change?
     *   - To allow same element multiple times
     *   - [2, 3, 6, 7] with target 7
     *   - Pick 2: now available [2, 3, 6, 7] again
     * 
     * Time: O(2^T/M) where T=target, M=minimal value
     * Space: O(T/M) depth of recursion
     */
    @Override
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // TODO: Implement using backtracking with reusable elements
        // Hint: Don't increment start index when recursing
        List<List<Integer>> results =new ArrayList<>();
        findDuplicateComboBacktrack(candidates, 0,target,new ArrayList<Integer>(),results);
        return results;
    }

    private void findDuplicateComboBacktrack(int[] arr, int start, int target, ArrayList<Integer> current, List<List<Integer>> results) {
    //step 1: target
        if(target==0) {
            System.out.println("current: "+current);
            results.add(new ArrayList<>(current));
            return;
        }

        //step2: overflow index
        if(start>=arr.length){
            return;
        }

        for (int i = start; i <arr.length ; i++) {
            //3.a break loop, no need to parse other elements
            if(arr[i]>target){
                break;
            }
            current.add(arr[i]);
            findDuplicateComboBacktrack(arr,i,target-arr[i],current,results);
            current.remove(current.size()-1);
        }
    }

    /**
     * PROBLEM 7: Combination Sum II (Each Element Used Once)
     * 
     * Problem: Find all combinations that sum to target
     * Each element can be used AT MOST once
     * Input may contain duplicates
     * 
     * Input: [10, 1, 2, 7, 6, 1, 5], target = 8
     * Output: [[1,1,6], [1,2,5], [1,7], [2,6]]
     * Explanation:
     *   - [1,1,6]: used two 1's and one 6
     *   - [1,2,5]: used one 1, one 2, one 5
     *   - etc.
     * 
     * Approach (Backtracking with Start Index Increment):
     * 1. Sort array first (to handle duplicates)
     * 2. Define: backtrack(currentPath, start, remaining)
     * 3. Base case: if remaining == 0, found combination!
     * 4. For each candidate from start to end:
     *    - Skip duplicates: if (i > start && candidates[i] == candidates[i-1]) continue
     *    - If candidate > remaining, break (array sorted!)
     *    - Add candidate to path
     *    - Recurse with start = i + 1 (move to next element)
     *    - Remove candidate (backtrack)
     * 5. Return all paths
     * 
     * Why increment start?
     *   - Each element used at most once
     *   - After using element at index i, next recursion starts at i+1
     * 
     * Why skip duplicates?
     *   - [1, 1, 2, 7, 6, 1, 5] sorted: [1, 1, 1, 2, 5, 6, 7]
     *   - Don't generate [1,7] twice from different 1's
     * 
     * Time: O(2^n) worst case
     * Space: O(n) recursion depth
     */
    @Override
    public List<List<Integer>> combinationSumII(int[] candidates, int target) {
        // TODO: Implement using backtracking with single use per element
        // Hint: Sort first, increment start, skip duplicates
        List<List<Integer>> results=new ArrayList<>();
        Arrays.sort(candidates);
        findUniqueComboBacktrack(candidates,0,target,new ArrayList<Integer>(),results);

        return results;
    }

    private void findUniqueComboBacktrack(int[] arr, int start, int target, ArrayList<Integer> current, List<List<Integer>> results) {
        if (target == 0) {
            System.out.println("Print combo:"+current);
            results.add(new ArrayList<>(current));
            return;
        }
        if (start >= arr.length) {
            return;
        }
        for (int i = start; i < arr.length; i++) {
            if (i > start && arr[i - 1] == arr[i]) {
                continue;
            }
            if (arr[i] > target) {
                break;
            }
            current.add(arr[i]);
            findUniqueComboBacktrack(arr, i + 1, target - arr[i], current, results);
            current.remove(current.size() - 1);
        }

    }

    /**
     * PROBLEM 8: Generate All Combinations of n Choose k
     * 
     * Problem: Generate all combinations of k elements from 1 to n
     * Input: n = 4, k = 2
     * Output: [[1,2], [1,3], [1,4], [2,3], [2,4], [3,4]]
     * 
     * Approach (Backtracking):
     * 1. Define: backtrack(currentPath, start, k)
     * 2. Base case: if k == 0, found a combination!
     * 3. For each number from start to n:
     *    - Add number to path
     *    - Recurse with start = number + 1, k = k - 1
     *    - Remove number (backtrack)
     * 4. Optimization: early termination
     *    - Remaining slots = k
     *    - Remaining numbers = n - start + 1
     *    - If remaining numbers < remaining slots, can't complete
     * 
     * Example: n=4, k=2
     * Tree structure:
     *        root
     *       /  |  \
     *      1   2   3   (pick first element)
     *     / \ / \
     *    2  3  3 4     (pick second element)
     *   
     * Result: [1,2], [1,3], [1,4], [2,3], [2,4], [3,4]
     * 
     * Time: O(C(n,k) * k) = O(n! / (k!(n-k)!) * k)
     * Space: O(k) recursion depth
     */
    @Override
    public List<List<Integer>> generateCombinations(int n, int k) {
        // TODO: Implement n choose k combinations
        // Hint: Decrement k each recursion, increment start
        throw new UnsupportedOperationException("TODO: Implement generateCombinations");
    }

    // ========================================================================
    // Override pair/triplet methods (not applicable for combinations)
    // ========================================================================

    @Override
    public boolean hasPair(int[] arr, int target) {
        throw new UnsupportedOperationException("Use SortedPairProblems or UnsortedPairProblems");
    }

    @Override
    public List<List<Integer>> findAllPairs(int[] arr, int target) {
        throw new UnsupportedOperationException("Use SortedPairProblems or UnsortedPairProblems");
    }

    @Override
    public int countPairs(int[] arr, int target) {
        throw new UnsupportedOperationException("Use SortedPairProblems or UnsortedPairProblems");
    }

    @Override
    public List<List<Integer>> findAllTriplets(int[] arr, int target) {
        throw new UnsupportedOperationException("Use SortedPairProblems or UnsortedPairProblems");
    }

    @Override
    public int countTriplets(int[] arr, int target) {
        throw new UnsupportedOperationException("Use SortedPairProblems or UnsortedPairProblems");
    }

    // ========================================================================
    // MAIN - TESTING
    // ========================================================================

    public static void main(String[] args) {
        CombinationProblems solver = new CombinationProblems();

        // Test 1: combinationSum
        int[] candidates1 = {2, 3, 6, 7};
        System.out.println("Test combinationSum([2,3,6,7], 7): " 
            + solver.combinationSum(candidates1, 7));

        // Test 2: combinationSumII
        int[] candidates2 = {10, 1, 2, 7, 6, 1, 5};
        System.out.println("Test combinationSumII([10,1,2,7,6,1,5], 8): " 
            + solver.combinationSumII(candidates2, 8));

        // Test 3: generateCombinations
        System.out.println("Test generateCombinations(4, 2): " 
            + solver.generateCombinations(4, 2));
    }
}

