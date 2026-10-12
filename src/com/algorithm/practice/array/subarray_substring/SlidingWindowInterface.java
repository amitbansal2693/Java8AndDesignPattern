package com.algorithm.practice.array.subarray_substring;

import java.util.List;

/**
 * ============================================================================
 * SLIDING WINDOW PROBLEMS INTERFACE
 * ============================================================================
 * 
 * Core Pattern for Subarray/Substring Problems:
 * 
 * KEY DECISION: Are elements CONTIGUOUS (consecutive)?
 * ├─ YES: Likely Sliding Window
 * │   ├── Fixed window size? → Fixed Sliding Window
 * │   └── Variable condition? → Variable Sliding Window
 * │
 * └─ NO: Different approach (pairs, combinations, etc)
 * 
 * SLIDING WINDOW Basics:
 * - Maintain a window of contiguous elements
 * - Expand/contract based on conditions
 * - Process each element once → O(n) time
 * 
 * FIXED WINDOW:
 * - Window size is constant (K)
 * - Slide one step at a time
 * - Example: max sum of K consecutive elements
 * 
 * VARIABLE WINDOW:
 * - Window size changes based on conditions
 * - Use two pointers: left and right
 * - Example: longest substring without repeating chars
 * 
 * ============================================================================
 */
public interface SlidingWindowInterface {

    /**
     * FIXED WINDOW PROBLEMS
     * Window size is constant
     */

    /**
     * Problem 1: Maximum Sum of K Consecutive Elements
     * Input: [1, 2, 3, 4, 5, 6], K=3
     * Output: 15 (4+5+6)
     */
    int maxSumWindow(int[] arr, int k);

    /**
     * Problem 2: Minimum Sum of K Consecutive Elements
     * Input: [1, 2, 3, 4, 5, 6], K=3
     * Output: 6 (1+2+3)
     */
    int minSumWindow(int[] arr, int k);

    /**
     * Problem 3: Average of K Consecutive Elements
     * Input: [1, 2, 3, 4, 5, 6], K=3
     * Output: [2.0, 3.0, 4.0, 5.0]
     */
    List<Double> averageWindow(int[] arr, int k);

    /**
     * Problem 4: Count Windows with Sum Equals Target
     * Input: [1, 2, 3, 4, 5], K=3, target=9
     * Output: 2 (windows: [1,2,3]=6? no, wait [2,3,4]=9 ✓, [3,4,5]=12? no)
     * Actually just [2,3,4]
     */
    int countWindowsWithSum(int[] arr, int k, int target);

    /**
     * Problem 5: Maximum Element in Each K-sized Window
     * Input: [1, 3, 1, 2, 0, 5], K=3
     * Output: [3, 3, 2, 5]
     * Windows: [1,3,1], [3,1,2], [1,2,0], [2,0,5]
     */
    List<Integer> maxElementInEachWindow(int[] arr, int k);

    /**
     * VARIABLE WINDOW PROBLEMS
     * Window size changes based on conditions
     */

    /**
     * Problem 6: Longest Substring Without Repeating Characters
     * Input: "abcabcbb"
     * Output: 3 ("abc")
     */
    int longestUniqueSubstring(String s);

    /**
     * Problem 7: Longest Substring with At Most K Distinct Characters
     * Input: "eceba", K=2
     * Output: 3 ("ece")
     * At most 2 distinct: 'e' and 'c'
     */
    int longestSubstringKDistinct(String s, int k);

    /**
     * Problem 8: Minimum Window Substring
     * Input: source="ADOBECODEBANC", target="ABC"
     * Output: "BANC"
     * Find smallest window in source containing all chars of target
     */
    String minWindowSubstring(String source, String target);

    /**
     * Problem 9: Smallest Subarray with Sum >= Target
     * Input: [2, 3, 1, 2, 4, 3], target=7
     * Output: 2 (subarray [4, 3])
     */
    int minLengthSubarrayWithSum(int[] arr, int target);

    /**
     * Problem 10: Longest Subarray with All Positive and Sum <= Target
     * Input: [1, 4, 3, 2, 5], target=10
     * Output: 4 (subarray [1, 4, 3, 2] sums to 10)
     */
    int maxLengthSubarrayWithSum(int[] arr, int target);
}

