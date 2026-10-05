package com.algorithm.array;

import java.util.*;

/**
 * ============================================================================
 * PATTERN: Sliding Window (Fixed & Variable)
 * ============================================================================
 * 
 * PATTERN IDENTIFICATION:
 * - Problem involves contiguous subarrays/substrings
 * - Either fixed window size OR condition-based dynamic window
 * - Can be optimized from O(n²) to O(n)
 * 
 * WHEN TO USE SLIDING WINDOW:
 * ✓ "Find max/min sum of K consecutive elements"
 * ✓ "Find longest substring with property X"
 * ✓ "Find shortest subarray satisfying condition"
 * ✓ "Count windows matching criteria"
 * 
 * TWO VARIANTS:
 * ┌─ FIXED WINDOW
 * │  - Window size is constant (K)
 * │  - Add new element, remove leaving element
 * │  - Process incrementally
 * │
 * └─ VARIABLE WINDOW
 *    - Window expands/contracts based on condition
 *    - Two pointers: left and right
 *    - Shrink when invalid, expand to find max window
 * 
 * COMPLEXITY: Both approaches typically O(n) time
 * 
 * ============================================================================
 */
public class SlidingWindowPatterns {

    public static void main(String[] args) {
        System.out.println("================== FIXED WINDOW EXAMPLES ==================\n");

        // Example 1: Maximum Sum of K Consecutive Elements
        System.out.println("EXAMPLE 1: Maximum Sum Subarray of Size K");
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        System.out.println("Array: " + Arrays.toString(arr1) + ", K=" + k);
        int maxSum = maxSumFixedWindow(arr1, k);
        System.out.println("Maximum Sum: " + maxSum + "\n");

        // Example 2: Average of Every Window
        System.out.println("EXAMPLE 2: Average of Every Window");
        System.out.println("Array: " + Arrays.toString(arr1) + ", K=" + k);
        averageOfEveryWindow(arr1, k);

        System.out.println("\n================== VARIABLE WINDOW EXAMPLES ==================\n");

        // Example 3: Longest Substring Without Repeating Characters
        System.out.println("EXAMPLE 3: Longest Substring Without Repeating Characters");
        String s1 = "abcabcbb";
        System.out.println("String: \"" + s1 + "\"");
        longestSubstringWithoutRepeating(s1);

        // Example 4: Minimum Length Subarray with Sum >= Target
        System.out.println("\nEXAMPLE 4: Minimum Length Subarray with Sum >= Target");
        int[] arr2 = {2, 1, 5, 2, 3, 2};
        int target = 7;
        System.out.println("Array: " + Arrays.toString(arr2) + ", Target Sum: " + target);
        minLengthSubarrayWithSum(arr2, target);
    }

    // ========================================================================
    // FIXED WINDOW EXAMPLES
    // ========================================================================

    /**
     * EXAMPLE 1: Maximum Sum of K Consecutive Elements
     * 
     * Problem: Find the maximum sum among all windows of size K
     * Input: [1,2,3,4,5,6,7], K=3
     * Output: 18 (from window [4,5,6] or [5,6,7])
     * 
     * APPROACH (Fixed Sliding Window):
     * 1. Calculate sum of first K elements
     * 2. Slide window: add new element, remove leftmost element
     * 3. Track maximum sum seen
     * 
     * Why it works:
     * - Instead of recalculating sum for each window from scratch: O(n*k)
     * - We update incrementally: O(n)
     * - Key insight: new_sum = old_sum - removed_element + new_element
     * 
     * Time: O(n), Space: O(1)
     */
    public static int maxSumFixedWindow(int[] arr, int k) {
        // Edge case
        if (arr.length < k) {
            System.out.println("Array size less than window size");
            return 0;
        }

        // Calculate sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        int maxSum = windowSum;

        // Slide the window
        for (int i = k; i < arr.length; i++) {
            // Remove leftmost element of previous window, add new rightmost element
            windowSum = windowSum - arr[i - k] + arr[i];

            // Update maximum
            maxSum = Math.max(maxSum, windowSum);

            System.out.println("  Window [" + (i - k + 1) + "..." + i + "]: " + Arrays.toString(
                    Arrays.copyOfRange(arr, i - k + 1, i + 1)) + " → Sum = " + windowSum);
        }

        return maxSum;
    }

    /**
     * EXAMPLE 2: Average of Every Window of Size K
     * 
     * Problem: Calculate and print average for each window of size K
     * Input: [1,2,3,4,5,6,7], K=3
     * Output:
     *   Window [1,2,3]: Average = 2.0
     *   Window [2,3,4]: Average = 3.0
     *   ...
     * 
     * APPROACH:
     * - Similar to Example 1, but also calculate average
     * - Average = sum / K
     * 
     * Time: O(n), Space: O(1)
     */
    public static void averageOfEveryWindow(int[] arr, int k) {
        if (arr.length < k) {
            System.out.println("Array size less than window size");
            return;
        }

        // Calculate sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        double average = (double) windowSum / k;
        System.out.println("  Window [" + 0 + "..." + (k - 1) + "]: " + 
                          Arrays.toString(Arrays.copyOfRange(arr, 0, k)) + " → Average = " + average);

        // Slide the window
        for (int i = k; i < arr.length; i++) {
            windowSum = windowSum - arr[i - k] + arr[i];
            average = (double) windowSum / k;
            System.out.println("  Window [" + (i - k + 1) + "..." + i + "]: " + 
                              Arrays.toString(Arrays.copyOfRange(arr, i - k + 1, i + 1)) + " → Average = " + average);
        }
    }

    // ========================================================================
    // VARIABLE WINDOW EXAMPLES
    // ========================================================================

    /**
     * EXAMPLE 3: Longest Substring Without Repeating Characters
     * 
     * Problem: Find the length of longest substring with all unique characters
     * Input: "abcabcbb"
     * Output: 3 (from substring "abc")
     * 
     * APPROACH (Variable Sliding Window):
     * 1. Use HashSet to track characters in current window
     * 2. Expand window by moving right pointer
     * 3. If duplicate found: shrink window from left until duplicate removed
     * 4. Track maximum window length
     * 
     * Window states for "abcabcbb":
     * [a] → [ab] → [abc] → (duplicate a) shrink → [bc] → [bca] → (dup a) → [ca] → [cab] → (dup b) → [ab] → [b] → (dup b) → []
     * 
     * Why variable window:
     * - Window size varies based on whether we have duplicates
     * - Need dynamic adjustment, not fixed size
     * 
     * Time: O(n), Space: O(min(n, charset_size))
     */
    public static void longestSubstringWithoutRepeating(String str) {
        Set<Character> window = new HashSet<>();
        int left = 0;
        int maxLength = 0;
        int maxStart = 0;

        for (int right = 0; right < str.length(); right++) {
            char currentChar = str.charAt(right);

            // If character already in window, shrink from left
            while (window.contains(currentChar)) {
                window.remove(str.charAt(left));
                left++;
            }

            // Add current character to window
            window.add(currentChar);

            // Update maximum if current window is larger
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                maxStart = left;
            }

            System.out.println("  Expand to '" + currentChar + "' → Window: \"" + 
                              str.substring(left, right + 1) + "\" (length=" + (right - left + 1) + ")");
        }

        System.out.println("Result: Longest substring = \"" + str.substring(maxStart, maxStart + maxLength) + 
                          "\" with length " + maxLength);
    }

    /**
     * EXAMPLE 4: Minimum Length Subarray with Sum >= Target
     * 
     * Problem: Find minimum length of contiguous subarray with sum >= target
     * Input: [2,1,5,2,3,2], Target=7
     * Output: 2 (from subarray [5,2] or [2,3,2] has length 3, but [5,2] has sum=7, length=2)
     * 
     * APPROACH (Variable Sliding Window):
     * 1. Expand window by moving right pointer, add elements to sum
     * 2. Once sum >= target, try shrinking from left
     * 3. While sum >= target: record window length, shrink by removing left element
     * 4. When sum < target again, expand by moving right pointer
     * 
     * Window progression for [2,1,5,2,3,2], target=7:
     * [2] (sum=2)
     * [2,1] (sum=3)
     * [2,1,5] (sum=8 >= 7) → length=3, shrink
     * [1,5] (sum=6 < 7) → expand
     * [1,5,2] (sum=8 >= 7) → length=3, shrink
     * [5,2] (sum=7 >= 7) → length=2 ✓ (minimum found)
     * [2] (sum=2 < 7)
     * [2,3] (sum=5)
     * [2,3,2] (sum=7 >= 7) → length=3
     * 
     * Time: O(n), Space: O(1)
     */
    public static void minLengthSubarrayWithSum(int[] arr, int target) {
        int left = 0;
        int windowSum = 0;
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;

        for (int right = 0; right < arr.length; right++) {
            // Expand window: add right element
            windowSum += arr[right];

            // Shrink window while sum >= target
            while (windowSum >= target) {
                // Update minimum length
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minStart = left;
                }

                System.out.println("  Valid window [" + left + "..." + right + "]: " + 
                                  Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)) + 
                                  " → Sum = " + windowSum + ", Length = " + (right - left + 1));

                // Shrink window: remove left element
                windowSum -= arr[left];
                left++;
            }
        }

        if (minLength != Integer.MAX_VALUE) {
            System.out.println("Result: Minimum length = " + minLength + 
                              " (subarray: " + Arrays.toString(Arrays.copyOfRange(arr, minStart, minStart + minLength)) + ")");
        } else {
            System.out.println("Result: No subarray found with sum >= " + target);
        }
    }
}

