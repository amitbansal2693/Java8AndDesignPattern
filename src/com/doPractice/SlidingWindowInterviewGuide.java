package com.doPractice;

import java.util.*;
import java.util.stream.*;

/**
 * ============================================================================
 * Java Sliding Window Interview Guide - PRACTICE VERSION (UNSOLVED)
 * Note:
 * 1. You must not use sort() or any other method that changes the order of elements in the list.
 * 2. FOR FIXED WINDOW problems, the window size is constant and does not change.
 * 3. FOR VARIABLE WINDOW problems, the window size can change based on conditions.
 *
 * APPROACH:
 * 1. For fixed window problems, use a for loop to iterate through the list and calculate the sum of each window.
 * 2. For variable window problems, use two pointers (left and right) 
 * to define the window and adjust the window size based on conditions.
 
 * ============================================================================
 * Sliding Window Always Means
 * CONSECUTIVE / CONTIGUOUS elements
 * The window moves continuously over neighboring elements.
 *
 * This file contains important Sliding Window interview problems.
 *
 * Each problem includes:
 * - Problem statement
 * - Input / Output
 * - TODO: Implement traditional loop solution
 * - TODO: Implement stream solution (where possible)
 * - Detailed comments and explanations
 *
 * ============================================================================
 * Topics Covered:
 *
 * 1. Fixed Window Sum
 * 2. Maximum Sum Subarray of Size K
 * 3. Minimum Sum Subarray of Size K
 * 4. Find All Windows Matching Target Sum
 * 5. Average of Every Window
 * 6. First Negative Number in Window
 * 7. Variable Sliding Window
 * 8. Longest Substring Without Repeating Characters
 *
 *
 * Note:
 * Sliding Window is a powerful technique for solving problems involving contiguous sequences in arrays or strings.
 * It allows us to efficiently calculate results for each window by reusing previous computations, leading to optimal O(n) solutions in many cases.
 * Remember sliding window means elements are consecutive in that range
 * ============================================================================
 */
public class SlidingWindowInterviewGuide {

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7);


        // ====================================================================
        // 1. FIXED WINDOW SUM
        // ====================================================================
        System.out.println("\n====================================================");
        System.out.println("1. FIXED WINDOW SUM");
        System.out.println("====================================================");

        fixedWindowSumLoop(list, 3);

        fixedWindowSumStream(list, 3);


        // ====================================================================
        // 2. MAXIMUM SUM SUBARRAY OF SIZE K
        // ====================================================================
        System.out.println("\n====================================================");
        System.out.println("2. MAXIMUM SUM SUBARRAY OF SIZE K");
        System.out.println("====================================================");

        maxWindowSumLoop(list, 3);

        maxWindowSumStream(list, 3);


        // ====================================================================
        // 3. MINIMUM SUM SUBARRAY OF SIZE K
        // ====================================================================
        System.out.println("\n====================================================");
        System.out.println("3. MINIMUM SUM SUBARRAY OF SIZE K");
        System.out.println("====================================================");

        minWindowSumLoop(list, 3);

        minWindowSumStream(list, 3);


        // ====================================================================
        // 4. WINDOWS MATCHING TARGET SUM
        // ====================================================================
        System.out.println("\n====================================================");
        System.out.println("4. WINDOWS MATCHING TARGET SUM");
        System.out.println("====================================================");

        matchingTargetLoop(list, 3, 12);

        matchingTargetStream(list, 3, 12);


        // ====================================================================
        // 5. AVERAGE OF EVERY WINDOW
        // ====================================================================
        System.out.println("\n====================================================");
        System.out.println("5. AVERAGE OF EVERY WINDOW");
        System.out.println("====================================================");

        averageWindowLoop(list, 3);

        averageWindowStream(list, 3);


        // ====================================================================
        // 6. FIRST NEGATIVE NUMBER IN WINDOW
        // ====================================================================
        System.out.println("\n====================================================");
        System.out.println("6. FIRST NEGATIVE NUMBER IN WINDOW");
        System.out.println("====================================================");

        List<Integer> negativeList =
                List.of(12, -1, -7, 8, -15, 30, 16, 28);

        firstNegativeLoop(negativeList, 3);


        // ====================================================================
        // 7. VARIABLE SLIDING WINDOW
        // ====================================================================
        System.out.println("\n====================================================");
        System.out.println("7. VARIABLE SLIDING WINDOW");
        System.out.println("====================================================");

        variableWindowExample(List.of(2, 1, 5, 2, 3, 2), 7);


        // ====================================================================
        // 8. LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS
        // ====================================================================
        System.out.println("\n====================================================");
        System.out.println("8. LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS");
        System.out.println("====================================================");

        longestUniqueSubstring("abcabcbb");
    }


    // ========================================================================
    // 1. FIXED WINDOW SUM
    // ========================================================================
    /*
     * Problem:
     * Calculate sum of every window of size K.
     *
     * Input:
     * [1,2,3,4,5,6,7]
     * K = 3
     *
     * Windows:
     * [1,2,3] = 6
     * [2,3,4] = 9
     * [3,4,5] = 12
     *
     * Expected Output:
     * 6
     * 9
     * 12
     * 15
     */

    public static void fixedWindowSumLoop(List<Integer> list, int k) {
        System.out.println("\nFOR LOOP SOLUTION:");
        // TODO: Implement using for loop
        // Hint: Calculate sum of first window, then slide it
        throw new UnsupportedOperationException("TODO: Implement fixedWindowSumLoop");
    }

    /**
     * TODO: Implement stream version of fixed window sum
     * Hint: Use IntStream.range() to iterate over starting indices
     * Then use subList() to get each window
     */
    public static void fixedWindowSumStream(List<Integer> list, int k) {
        System.out.println("\nSTREAM SOLUTION:");
        throw new UnsupportedOperationException("TODO: Implement fixedWindowSumStream");
    }


    // ========================================================================
    // 2. MAXIMUM SUM SUBARRAY OF SIZE K
    // ========================================================================
    /*
     * Problem:
     * Find the maximum sum among all windows of size K.
     *
     * Input:
     * [1,2,3,4,5,6,7]
     * K = 3
     *
     * Windows: [1,2,3]=6, [2,3,4]=9, [3,4,5]=12, [4,5,6]=15, [5,6,7]=18
     *
     * Expected Output:
     * Maximum Window Sum = 18
     */

    public static void maxWindowSumLoop(List<Integer> list, int k) {
        System.out.println("\nFOR LOOP SOLUTION:");
        // TODO: Implement using for loop
        // Hint: Track the maximum sum as you slide the window
        throw new UnsupportedOperationException("TODO: Implement maxWindowSumLoop");
    }

    /**
     * TODO: Implement stream version to find maximum window sum
     * Hint: Use IntStream.range() with map() to calculate all window sums
     * Then use max() to find the maximum
     */
    public static void maxWindowSumStream(List<Integer> list, int k) {
        System.out.println("\nSTREAM SOLUTION:");
        throw new UnsupportedOperationException("TODO: Implement maxWindowSumStream");
    }


    // ========================================================================
    // 3. MINIMUM SUM SUBARRAY OF SIZE K
    // ========================================================================
    /*
     * Problem:
     * Find the minimum sum among all windows of size K.
     *
     * Input:
     * [1,2,3,4,5,6,7]
     * K = 3
     *
     * Windows: [1,2,3]=6, [2,3,4]=9, [3,4,5]=12, [4,5,6]=15, [5,6,7]=18
     *
     * Expected Output:
     * Minimum Window Sum = 6
     */

    public static void minWindowSumLoop(List<Integer> list, int k) {
        System.out.println("\nFOR LOOP SOLUTION:");
        // TODO: Implement using for loop
        // Hint: Track the minimum sum as you slide the window
        throw new UnsupportedOperationException("TODO: Implement minWindowSumLoop");
    }

    /**
     * TODO: Implement stream version to find minimum window sum
     * Hint: Use IntStream.range() with map() to calculate all window sums
     * Then use min() to find the minimum
     */
    public static void minWindowSumStream(List<Integer> list, int k) {
        System.out.println("\nSTREAM SOLUTION:");
        throw new UnsupportedOperationException("TODO: Implement minWindowSumStream");
    }


    // ========================================================================
    // 4. WINDOWS MATCHING TARGET SUM
    // ========================================================================
    /*
     * Problem:
     * Find all windows of size K whose sum equals the target.
     *
     * Input:
     * [1,2,3,4,5,6,7]
     * K = 3, Target = 12
     *
     * Windows: [1,2,3]=6, [2,3,4]=9, [3,4,5]=12 ✓, [4,5,6]=15, [5,6,7]=18
     *
     * Expected Output:
     * [3, 4, 5]
     */

    public static void matchingTargetLoop(List<Integer> list,
                                          int k,
                                          int target) {
        System.out.println("\nFOR LOOP SOLUTION:");
        // TODO: Implement using nested for loops
        // Hint: For each window, calculate sum and check if it equals target
        throw new UnsupportedOperationException("TODO: Implement matchingTargetLoop");
    }

    /**
     * TODO: Implement stream version to find windows matching target sum
     * Hint: Use IntStream.range() with filter() to check if sum equals target
     * Then mapToObj() to convert indices to sublists
     */
    public static void matchingTargetStream(List<Integer> list,
                                            int k,
                                            int target) {
        System.out.println("\nSTREAM SOLUTION:");
        throw new UnsupportedOperationException("TODO: Implement matchingTargetStream");
    }


    // ========================================================================
    // 5. AVERAGE OF EVERY WINDOW
    // ========================================================================
    /*
     * Problem:
     * Calculate the average of every window of size K.
     *
     * Input:
     * [1,2,3,4,5,6,7]
     * K = 3
     *
     * Windows: [1,2,3] avg=2.0, [2,3,4] avg=3.0, [3,4,5] avg=4.0, [4,5,6] avg=5.0, [5,6,7] avg=6.0
     *
     * Expected Output:
     * 2.0
     * 3.0
     * 4.0
     * 5.0
     * 6.0
     */

    public static void averageWindowLoop(List<Integer> list, int k) {
        System.out.println("\nFOR LOOP SOLUTION:");
        // TODO: Implement using for loop
        // Hint: Calculate sum of window, divide by k to get average
        throw new UnsupportedOperationException("TODO: Implement averageWindowLoop");
    }

    /**
     * TODO: Implement stream version to calculate average of each window
     * Hint: Use IntStream.range() with mapToDouble() to calculate averages
     * Or use average() method on IntStream
     */
    public static void averageWindowStream(List<Integer> list, int k) {
        System.out.println("\nSTREAM SOLUTION:");
        throw new UnsupportedOperationException("TODO: Implement averageWindowStream");
    }


    // ========================================================================
    // 6. FIRST NEGATIVE NUMBER IN WINDOW
    // ========================================================================
    /*
     * Problem:
     * For each window of size K, find the first negative number (if it exists).
     *
     * Input:
     * [12, -1, -7, 8, -15, 30, 16, 28]
     * K = 3
     *
     * Windows:
     * [12, -1, -7] -> first negative = -1
     * [-1, -7, 8] -> first negative = -1
     * [-7, 8, -15] -> first negative = -7
     * [8, -15, 30] -> first negative = -15
     * [-15, 30, 16] -> first negative = -15
     * [30, 16, 28] -> first negative = 0 (none)
     *
     * Expected Output:
     * -1
     * -1
     * -7
     * -15
     * -15
     * 0
     */

    public static void firstNegativeLoop(List<Integer> list, int k) {
        System.out.println("\nFOR LOOP SOLUTION:");
        // TODO: Implement using nested for loops
        // Hint: For each window, find the first negative number
        // If no negative found, print 0
        throw new UnsupportedOperationException("TODO: Implement firstNegativeLoop");
    }


    // ========================================================================
    // 7. VARIABLE SLIDING WINDOW
    // ========================================================================
    /*
     * Problem:
     * Find the minimum length of a subarray whose sum >= target.
     * This is a VARIABLE window problem because the window size changes.
     *
     * Input:
     * [2, 1, 5, 2, 3, 2]
     * Target = 7
     *
     * Subarrays:
     * [2] = 2 (< 7)
     * [2, 1] = 3 (< 7)
     * [2, 1, 5] = 8 (>= 7) length = 3
     * [1, 5] = 6 (< 7)
     * [1, 5, 2] = 8 (>= 7) length = 3
     * [5, 2] = 7 (>= 7) length = 2 (MINIMUM)
     * [2, 3] = 5 (< 7)
     * [2, 3, 2] = 7 (>= 7) length = 3
     * [3, 2] = 5 (< 7)
     *
     * Expected Output:
     * Minimum Length = 2
     *
     * APPROACH:
     * Use two pointers: left and right
     * Expand window by moving right pointer until sum >= target
     * Then contract window by moving left pointer to find minimum length
     * Repeat until right reaches end
     */

    public static void variableWindowExample(List<Integer> list,
                                             int target) {
        System.out.println("\nVARIABLE WINDOW SOLUTION:");
        // TODO: Implement using two pointers
        // left pointer starts at 0, right pointer expands the window
        // Track sum and minLength as you go
        throw new UnsupportedOperationException("TODO: Implement variableWindowExample");
    }


    // ========================================================================
    // 8. LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS
    // ========================================================================
    /*
     * Problem:
     * Find the length of the longest substring without repeating characters.
     * This is a classic VARIABLE sliding window problem using HashSet.
     *
     * Input:
     * "abcabcbb"
     *
     * Substrings checked:
     * "a" -> no repeats, length = 1
     * "ab" -> no repeats, length = 2
     * "abc" -> no repeats, length = 3
     * "abca" -> 'a' repeats! shrink from left
     * "bca" -> no repeats, length = 3
     * "bcab" -> no repeats, length = 4
     * "bcabc" -> 'c' repeats! shrink
     * "cabc" -> no repeats, length = 4
     * "cabcb" -> 'b' repeats! shrink
     * "abcb" -> 'b' repeats! shrink
     * "bcb" -> 'b' repeats! shrink
     * "cb" -> no repeats, length = 2
     * "cbb" -> 'b' repeats!
     *
     * Expected Output:
     * Longest Unique Substring Length = 3 (for "abc")
     *
     * STRATEGY:
     * Use a HashSet to track characters in current window
     * When you encounter a duplicate:
     *   - Remove characters from left until the duplicate is removed
     * Track the maximum window size seen
     */

    public static void longestUniqueSubstring(String str) {
        System.out.println("\nLONGEST UNIQUE SUBSTRING SOLUTION:");
        // TODO: Implement using Set and two pointers
        // Use left and right pointers
        // Add characters to set as you expand right
        // When duplicate found, remove from left until no duplicates
        throw new UnsupportedOperationException("TODO: Implement longestUniqueSubstring");
    }
}

