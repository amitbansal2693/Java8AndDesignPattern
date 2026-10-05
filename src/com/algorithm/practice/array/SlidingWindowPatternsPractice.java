package com.algorithm.practice.array;

import java.util.*;

/**
 * ============================================================================
 * PRACTICE: Sliding Window (Fixed & Variable)
 * ============================================================================
 * 
 * This is the PRACTICE version - incomplete methods for you to solve
 * 
 * Complete each method following the hints and approach comments
 * Test your solution against the examples in main()
 * 
 * ============================================================================
 */
public class SlidingWindowPatternsPractice {

    public static void main(String[] args) {
        System.out.println("========== SLIDING WINDOW PRACTICE PROBLEMS ==========\n");

        // Test data
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        System.out.println("=================== FIXED WINDOW ===================\n");

        // Practice 1: Maximum Sum of K Consecutive Elements
        System.out.println("PRACTICE 1: Maximum Sum Subarray of Size K");
        System.out.println("Array: " + Arrays.toString(arr1) + ", K=" + k);
        try {
            int maxSum = maxSumFixedWindow(arr1, k);
            System.out.println("Maximum Sum: " + maxSum);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        // Practice 2: Average of Every Window
        System.out.println("\nPRACTICE 2: Average of Every Window");
        System.out.println("Array: " + Arrays.toString(arr1) + ", K=" + k);
        try {
            averageOfEveryWindow(arr1, k);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        System.out.println("\n=================== VARIABLE WINDOW ===================\n");

        // Practice 3: Longest Substring Without Repeating Characters
        System.out.println("PRACTICE 3: Longest Substring Without Repeating Characters");
        String s1 = "abcabcbb";
        System.out.println("String: \"" + s1 + "\"");
        try {
            longestSubstringWithoutRepeating(s1);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        // Practice 4: Minimum Length Subarray with Sum >= Target
        System.out.println("\nPRACTICE 4: Minimum Length Subarray with Sum >= Target");
        int[] arr2 = {2, 1, 5, 2, 3, 2};
        int target = 7;
        System.out.println("Array: " + Arrays.toString(arr2) + ", Target Sum: " + target);
        try {
            minLengthSubarrayWithSum(arr2, target);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }
    }

    /**
     * PRACTICE 1: Maximum Sum of K Consecutive Elements
     * 
     * Problem: Find the maximum sum among all windows of size K
     * Input: [1,2,3,4,5,6,7], K=3
     * Expected Output: 18 (from window [4,5,6] or [5,6,7])
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Fixed Sliding Window):
     * 1. Calculate sum of FIRST K elements (initial window)
     * 2. Use a variable to track maximum
     * 3. Slide window:
     *    - Add new element (right side): sum += arr[i]
     *    - Remove leftmost element: sum -= arr[i-k]
     *    - Update maximum
     * 4. Return maximum
     * 
     * WHY EFFICIENT:
     * - Naive: recalculate each window from scratch = O(n*k)
     * - Optimized: update incrementally = O(n)
     * - Magic formula: new_sum = old_sum - removed + new_element
     * 
     * HINT: Print each window sum for debugging
     * 
     * Time: O(n), Space: O(1)
     */
    public static int maxSumFixedWindow(int[] arr, int k) {
        throw new UnsupportedOperationException(
            "TODO: Find maximum sum of K consecutive elements\n" +
            "Hint: Sliding window technique - add new, remove old"
        );
    }

    /**
     * PRACTICE 2: Average of Every Window of Size K
     * 
     * Problem: Calculate and print average for each window of size K
     * Input: [1,2,3,4,5,6,7], K=3
     * Expected Output:
     *   Window [0...2]: Average = 2.0
     *   Window [1...3]: Average = 3.0
     *   Window [2...4]: Average = 4.0
     *   etc.
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH:
     * 1. Similar to Practice 1
     * 2. Calculate sum of first K elements
     * 3. Print average (sum / K)
     * 4. Slide window and update average
     * 
     * HINT: Average = sum / K (integer division or cast to double)
     * 
     * Time: O(n), Space: O(1)
     */
    public static void averageOfEveryWindow(int[] arr, int k) {
        throw new UnsupportedOperationException(
            "TODO: Calculate average for each window of size K\n" +
            "Hint: Similar to max sum, but divide by k to get average"
        );
    }

    /**
     * PRACTICE 3: Longest Substring Without Repeating Characters
     * 
     * Problem: Find the length of longest substring with all unique characters
     * Input: "abcabcbb"
     * Expected Output: 3 (substring "abc")
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Variable Sliding Window):
     * 1. Use HashSet to track characters in current window
     * 2. Use two pointers: left and right (window boundaries)
     * 3. Expand window by moving right pointer:
     *    - Add character to HashSet
     *    - If character already exists:
     *      * Remove characters from left until duplicate is removed
     *      * Then add the character
     * 4. Track maximum window length seen
     * 
     * DETAILED STEPS:
     * For each position right:
     *   - While window.contains(arr[right]):
     *     * window.remove(arr[left])
     *     * left++
     *   - window.add(arr[right])
     *   - maxLength = max(maxLength, right - left + 1)
     * 
     * HINT: For "abcabcbb":
     *   [a] → [ab] → [abc] → (duplicate a) shrink → [bca] → (duplicate b) → [cab] ...
     * 
     * Time: O(n), Space: O(min(n, charset))
     */
    public static void longestSubstringWithoutRepeating(String str) {
        throw new UnsupportedOperationException(
            "TODO: Find longest substring without repeating characters\n" +
            "Hint: Use HashSet with left/right pointers, shrink when duplicate found"
        );
    }

    /**
     * PRACTICE 4: Minimum Length Subarray with Sum >= Target
     * 
     * Problem: Find minimum length of contiguous subarray with sum >= target
     * Input: [2,1,5,2,3,2], Target=7
     * Expected Output: 2 (subarray [5,2] has sum=7)
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Variable Sliding Window):
     * 1. Use two pointers: left and right
     * 2. Expand window (move right):
     *    - Add arr[right] to sum
     *    - While sum >= target:
     *      * Record window length
     *      * Try to shrink (remove arr[left])
     *      * left++
     * 3. Continue until right reaches end
     * 
     * KEY INSIGHT:
     * - Expand by adding elements
     * - Shrink by removing from left while condition still satisfied
     * - This ensures we find MINIMUM length
     * 
     * TRACE for [2,1,5,2,3,2], target=7:
     * [2] (sum=2)
     * [2,1] (sum=3)
     * [2,1,5] (sum=8 >= 7) → length=3, try shrink
     * [1,5] (sum=6 < 7) → expand
     * [1,5,2] (sum=8 >= 7) → length=3, try shrink
     * [5,2] (sum=7 >= 7) → length=2 ✓ minimum found
     * 
     * HINT: Only update minimum when sum >= target
     * 
     * Time: O(n), Space: O(1)
     */
    public static void minLengthSubarrayWithSum(int[] arr, int target) {
        throw new UnsupportedOperationException(
            "TODO: Find minimum length subarray with sum >= target\n" +
            "Hint: Expand right, shrink left while maintaining sum >= target"
        );
    }
}

