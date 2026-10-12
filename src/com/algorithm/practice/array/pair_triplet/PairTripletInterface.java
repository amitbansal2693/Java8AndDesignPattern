package com.algorithm.practice.array.pair_triplet;

import java.util.List;

/**
 * ============================================================================
 * PAIR / TRIPLET PROBLEMS INTERFACE
 * ============================================================================
 * 
 * Common Pattern for Pair/Triplet/Multiple Element Problems:
 * 
 * KEY DECISION POINT:
 * Is input sorted?
 * ├─ YES → Use Two Pointers (O(n))
 * └─ NO  → Use HashMap/HashSet (O(n)) or Sort first (O(n log n))
 * 
 * Need all combinations? → Use Backtracking
 * 
 * ============================================================================
 */
public interface PairTripletInterface {

    /**
     * PAIR SUM PROBLEMS
     * Find if/count/return pairs with target sum
     */
    
    /**
     * Problem 1: Two Sum - Return if pair exists
     * Input: [1, 2, 3, 4, 5], target = 7
     * Output: true (pairs: 2,5 or 3,4)
     * 
     * Approach: Based on whether sorted
     * - Sorted: Two Pointers O(n)
     * - Unsorted: HashMap O(n)
     */
    boolean hasPair(int[] arr, int target);

    /**
     * Problem 2: Find All Pairs
     * Input: [1, 2, 3, 4, 5], target = 7
     * Output: [[2,5], [3,4]]
     * 
     * Approach: Two Pointers or HashSet
     */
    List<List<Integer>> findAllPairs(int[] arr, int target);

    /**
     * Problem 3: Count Pairs
     * Input: [1, 2, 3, 4, 5, 2, 3], target = 7
     * Output: 4 (pairs with indices)
     * 
     * Approach: HashMap with frequency counting
     */
    int countPairs(int[] arr, int target);

    /**
     * TRIPLET SUM PROBLEMS
     */

    /**
     * Problem 4: Three Sum - Return all unique triplets
     * Input: [-1, 0, 1, 2, -1, -4], target = 0
     * Output: [[-1, -1, 2], [-1, 0, 1]]
     * 
     * Approach: Sort + Two Pointer inside loop
     */
    List<List<Integer>> findAllTriplets(int[] arr, int target);

    /**
     * Problem 5: Count Triplets
     * Input: [1, 2, 3, 4, 5], target = 9
     * Output: 2 (triplets: 1,3,5 and 2,3,4)
     * 
     * Approach: Three nested loops or hashing
     */
    int countTriplets(int[] arr, int target);

    /**
     * COMBINATION PROBLEMS
     */

    /**
     * Problem 6: Combination Sum (multiple use)
     * Input: [2, 3, 6, 7], target = 7
     * Output: [[2,2,3], [7]]
     * 
     * Approach: Backtracking with repetition allowed
     */
    List<List<Integer>> combinationSum(int[] candidates, int target);

    /**
     * Problem 7: Combination Sum II (each use once)
     * Input: [10, 1, 2, 7, 6, 1, 5], target = 8
     * Output: [[1,1,6], [1,2,5], [1,7], [2,6]]
     * 
     * Approach: Backtracking with visited tracking
     */
    List<List<Integer>> combinationSumII(int[] candidates, int target);

    /**
     * Problem 8: Generate All Combinations
     * Input: n = 4, k = 2
     * Output: [[1,2], [1,3], [1,4], [2,3], [2,4], [3,4]]
     * 
     * Approach: Backtracking with start index
     */
    List<List<Integer>> generateCombinations(int n, int k);
}

