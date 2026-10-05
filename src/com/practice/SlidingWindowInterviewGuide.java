package com.practice;

import java.util.*;
import java.util.stream.*;

/**
 * ============================================================================
 * Java Sliding Window Interview Guide
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
 *Sliding Window Always Means
 * CONSECUTIVE / CONTIGUOUS elements
 * The window moves continuously over neighboring elements.
 *
 * This file contains important Sliding Window interview problems.
 *
 * Each problem includes:
 * - Problem statement
 * - Input / Output
 * - Traditional loop solution
 * - Stream solution (where possible)
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
 * 
 * ----
 * 7. Variable Sliding Window
 * 8. Longest Substring Without Repeating Characters
 *
 *
 * Note:
 * Sliding Window is a powerful technique for solving problems involving contiguous sequences in arrays or strings.
 * It allows us to efficiently calculate results for each window by reusing previous computations, leading to optimal O(n) solutions in many cases.
 * Remeber sliding window means elements are consecutive in that range
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
     */

    /**
     * ==================== FIXED WINDOW ====================
     * FIXED WINDOW SUM - Traditional Loop Approach
     * 
     * Problem: Calculate sum of every consecutive K elements
     * Input: [1,2,3,4,5,6,7], K=3
     * Output: 6, 9, 12, 15, 18
     * 
     * Approach:
     * 1. Calculate sum of FIRST K elements
     * 2. Use sliding technique: remove leftmost, add new rightmost
     * 3. Key optimization: reuse previous sum instead of recalculating
     * 
     * Magic Formula: newSum = oldSum - leftElement + newRightElement
     * 
     * Why Efficient:
     * - Naive approach: recalculate each window = O(n*k)
     * - Sliding window: update incrementally = O(n)
     * 
     * Trace:
     * Window [1,2,3]: sum = 6
     * Remove 1, Add 4: sum = 6 - 1 + 4 = 9
     * Remove 2, Add 5: sum = 9 - 2 + 5 = 12
     * Remove 3, Add 6: sum = 12 - 3 + 6 = 15
     * 
     * Time: O(n), Space: O(1)
     */
    public static void fixedWindowSumLoop(List<Integer> list, int k) {

        System.out.println("\nFOR LOOP SOLUTION:");
        int windowSum = 0;

        // First window: calculate sum of first k elements
        for (int i = 0; i < k; i++) {
            windowSum += list.get(i);
        }

        System.out.println(windowSum);

        // Sliding window: update by removing left, adding right
        for (int i = k; i < list.size(); i++) {
            windowSum += list.get(i);           // Add new right element
            windowSum -= list.get(i - k);       // Remove old left element
            System.out.println(windowSum);
        }
    }

    /**
     * ==================== FIXED WINDOW ====================
     * FIXED WINDOW SUM - Stream/Functional Approach
     * 
     * Problem: Same as fixedWindowSumLoop - sum every K consecutive elements
     * 
     * Approach:
     * 1. Create stream of window starting indices: 0, 1, 2, ..., (n-k)
     * 2. For each index i, extract sublist from i to i+k
     * 3. Sum each sublist
     * 4. Print results
     * 
     * Why IntStream.range(0, list.size() - k + 1):
     * - Start at 0, end at list.size() - k (inclusive)
     * - Example: list size=7, k=3 → range(0, 5) → indices 0,1,2,3,4
     * - Last window at index 4: [5,6,7] ✓
     * 
     * Comparison with loop approach:
     * - Loop: O(n) with 1 pass, incremental updates
     * - Stream: O(n*k) because each window recalculates sum
     * - Stream is cleaner but LESS EFFICIENT
     * 
     * Time: O(n*k), Space: O(1)
     */
    public static void fixedWindowSumStream(List<Integer> list, int k) {

        System.out.println("\nSTREAM SOLUTION:");
        // IntStream.range(0, end) generates indices: 0, 1, 2, ..., end-1
        // We want indices 0 to (list.size()-k) inclusive
        // So range end should be (list.size() - k + 1)
        IntStream.range(0, (list.size() - k) + 1)
                .map(i ->
                        // For each starting index i, create window [i, i+k)
                        // Extract sublist(i, i+k) and calculate sum
                        list.subList(i, i + k)
                                .stream()
                                .mapToInt(Integer::intValue)
                                .sum()
                )

                .forEach(System.out::println);
    }


    /**
     * ==================== FIXED WINDOW ====================
     * MAXIMUM SUM SUBARRAY OF SIZE K - Traditional Loop
     * 
     * Problem: Find the MAXIMUM sum among all windows of size K
     * Input: [1,2,3,4,5,6,7], K=3
     * Windows: [1,2,3]=6, [2,3,4]=9, [3,4,5]=12, [4,5,6]=15, [5,6,7]=18
     * Output: 18
     * 
     * Approach:
     * 1. Calculate first window sum
     * 2. Slide window and track maximum
     * 3. Use same optimization as fixedWindowSum
     * 
     * Key Insight:
     * - We don't need ALL sums, just the MAXIMUM
     * - Still O(n) time with incremental updates
     * 
     * Trace:
     * Window 0-2: sum=6, max=6
     * Window 1-3: sum=9, max=9
     * Window 2-4: sum=12, max=12
     * Window 3-5: sum=15, max=15
     * Window 4-6: sum=18, max=18 ← Answer
     * 
     * Time: O(n), Space: O(1)
     */
    public static void maxWindowSumLoop(List<Integer> list, int k) {

        System.out.println("\nFOR LOOP SOLUTION:");

        int windowSum = 0;

        // Calculate first window sum
        for (int i = 0; i < k; i++) {
            windowSum += list.get(i);
        }

        int max = windowSum;

        // Slide and track maximum
        for (int i = k; i < list.size(); i++) {
            windowSum += list.get(i);
            windowSum -= list.get(i - k);

            max = Math.max(max, windowSum);
        }

        System.out.println("Maximum Window Sum = " + max);
    }

    /**
     * ==================== FIXED WINDOW ====================
     * MAXIMUM SUM SUBARRAY OF SIZE K - Stream Approach
     * 
     * Problem: Same as maxWindowSumLoop
     * 
     * Approach:
     * 1. Use IntStream to generate window starting indices
     * 2. For each index, calculate window sum
     * 3. Use .max() to find maximum
     * 4. Use .orElse(0) for empty stream handling
     * 
     * Why .max().orElse(0):
     * - IntStream.max() returns OptionalInt
     * - orElse(0) provides default if no elements
     * 
     * Comparison:
     * - Loop: O(n) single pass, efficient
     * - Stream: O(n*k) recalculates each sum, less efficient
     * - Stream is more readable/functional
     * 
     * Time: O(n*k), Space: O(1)
     */
    public static void maxWindowSumStream(List<Integer> list, int k) {

        System.out.println("\nSTREAM SOLUTION:");

        int max = IntStream.rangeClosed(0, list.size() - k)
                .map(i ->
                        list.subList(i, i + k)
                                .stream()
                                .mapToInt(Integer::intValue)
                                .sum()
                )
                .max()
                .orElse(0);

        System.out.println("Maximum Window Sum = " + max);
    }


    // ========================================================================
    // 3. MINIMUM SUM SUBARRAY OF SIZE K
    // ========================================================================

    /**
     * ==================== FIXED WINDOW ====================
     * MINIMUM SUM SUBARRAY OF SIZE K - Traditional Loop
     * 
     * Problem: Find the MINIMUM sum among all windows of size K
     * Input: [1,2,3,4,5,6,7], K=3
     * Windows: [1,2,3]=6, [2,3,4]=9, [3,4,5]=12, [4,5,6]=15, [5,6,7]=18
     * Output: 6
     * 
     * Approach: Identical to maxWindowSum but using Math.min instead of Math.max
     * 
     * Trace:
     * Window 0-2: sum=6, min=6 ← Answer
     * Window 1-3: sum=9, min=6
     * Window 2-4: sum=12, min=6
     * Window 3-5: sum=15, min=6
     * Window 4-6: sum=18, min=6
     * 
     * Time: O(n), Space: O(1)
     */
    public static void minWindowSumLoop(List<Integer> list, int k) {

        System.out.println("\nFOR LOOP SOLUTION:");

        int windowSum = 0;

        for (int i = 0; i < k; i++) {
            windowSum += list.get(i);
        }

        int min = windowSum;

        for (int i = k; i < list.size(); i++) {

            windowSum += list.get(i);
            windowSum -= list.get(i - k);

            min = Math.min(min, windowSum);
        }

        System.out.println("Minimum Window Sum = " + min);
    }

    /**
     * ==================== FIXED WINDOW ====================
     * MINIMUM SUM SUBARRAY OF SIZE K - Stream Approach
     * 
     * Problem: Same as minWindowSumLoop
     * 
     * Approach: Stream version using .min() instead of .max()
     * 
     * Stream Pattern:
     * IntStream.range(start, end)
     *   .map(transform)
     *   .min()
     *   .orElse(defaultValue)
     * 
     * Time: O(n*k), Space: O(1)
     */
    public static void minWindowSumStream(List<Integer> list, int k) {

        System.out.println("\nSTREAM SOLUTION:");
        
        int min = IntStream.range(0, list.size() - k + 1)
                .map(i -> list.subList(i, i + k)
                        .stream()
                        .mapToInt(Integer::intValue)
                        .sum())
                .min()
                .orElse(0);

        System.out.println("Minimum Window Sum = " + min);
    }


    // ========================================================================
    // 4. WINDOWS MATCHING TARGET SUM
    // ========================================================================

    /**
     * ==================== FIXED WINDOW ====================
     * FIND ALL WINDOWS WITH TARGET SUM - Traditional Loop
     * 
     * Problem: Find all windows of size K whose sum equals target
     * Input: [1,2,3,4,5,6,7], K=3, Target=12
     * Windows: [1,2,3]=6, [2,3,4]=9, [3,4,5]=12✓, [4,5,6]=15, [5,6,7]=18
     * Output: [3,4,5]
     * 
     * Approach:
     * 1. Loop through all possible windows
     * 2. For each window, calculate sum
     * 3. If sum equals target, print/store window
     * 
     * Note: This uses nested loops (calculating sum each time)
     * More efficient would use sliding window technique
     * 
     * Trace:
     * i=0: window [1,2,3], sum=6 ✗
     * i=1: window [2,3,4], sum=9 ✗
     * i=2: window [3,4,5], sum=12 ✓ PRINT [3,4,5]
     * i=3: window [4,5,6], sum=15 ✗
     * i=4: window [5,6,7], sum=18 ✗
     * 
     * Time: O(n*k) - inner loop recalculates each sum
     * Space: O(1)
     * 
     * Better: Use sliding window + check if sum==target
     */
    public static void matchingTargetLoop(List<Integer> list,
                                          int k,
                                          int target) {

        System.out.println("\nFOR LOOP SOLUTION:");
        //Lest 3 elements will be processed togethor. thus size-k+1 is the last index to start the window.

        for (int i = 0; i <= list.size() - k; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += list.get(j);
            }
            if (sum == target) {
                System.out.println(
                        list.subList(i, i + k)
                );
            }
        }
    }

    /**
     * ==================== (NOT SLIDING WINDOW - FOR REFERENCE) ====================
     * HELPER: Divisible Sum Pairs (Uses Pairing, not Sliding Window)
     * 
     * Problem: Count pairs (i,j) where i<j and (arr[i]+arr[j])%k==0
     * Input: [1,2,3,4], K=3
     * Pairs: (1,2) sum=3%3=0✓, (1,5) sum=6%3=0✓, etc.
     * 
     * Approach:
     * 1. Use nested flatMap loops to generate pairs
     * 2. Filter pairs where sum divisible by k
     * 3. Count matching pairs
     * 
     * Stream Pattern:
     * OuterStream.flatMap(i → InnerStream.filter().mapToObj())
     * 
     * Note: This is NOT a sliding window - it's for reference/pairing problems
     * 
     * Time: O(n²), Space: O(pairs)
     */
    public static int divisibleSumPairs(int n, int k, List<Integer> ar) {

        return (int)

                IntStream.range(0, ar.size())
                        .boxed()
                        .flatMap(i ->
                                IntStream.range(i + 1, ar.size())
                                        .filter(j -> (ar.get(i) + ar.get(j)) % k == 0)
                                        .mapToObj(j -> List.of(ar.get(i), ar.get(j))))
                        .count();
    }

    /**
     * ==================== FIXED WINDOW ====================
     * FIND ALL WINDOWS WITH TARGET SUM - Stream Approach
     * 
     * Problem: Same as matchingTargetLoop
     * 
     * Approach:
     * 1. Generate stream of window starting indices
     * 2. Filter windows where sum equals target
     * 3. Map to actual sublists
     * 4. Print each matching window
     * 
     * Stream Chain:
     * IntStream.range() → .filter(sum==target) → .mapToObj(sublist) → .forEach(print)
     * 
     * OneLiner: Very clean and readable
     * 
     * Time: O(n*k), Space: O(1)
     */
    public static void matchingTargetStream(List<Integer> list,
                                            int k,
                                            int target) {
        System.out.println("\nSTREAM SOLUTION:");
        IntStream.range(0, list.size() - k + 1)
                .filter(i -> list.subList(i, i + k).stream().mapToInt(Integer::intValue).sum() == target)
                .mapToObj(i -> list.subList(i, i + k))
                .forEach(System.out::println);
    }


    // ========================================================================
    // 5. AVERAGE OF EVERY WINDOW
    // ========================================================================

    /**
     * ==================== FIXED WINDOW ====================
     * AVERAGE OF EVERY WINDOW OF SIZE K - Traditional Loop
     * 
     * Problem: Calculate average (mean) for each window of size K
     * Input: [1,2,3,4,5,6,7], K=3
     * Output: 2.0, 3.0, 4.0, 5.0, 6.0
     * 
     * Approach:
     * 1. For each window, calculate sum
     * 2. Divide by K to get average
     * 3. Cast to double for decimal result
     * 
     * Calculation:
     * Window [1,2,3]: sum=6, avg=6/3=2.0
     * Window [2,3,4]: sum=9, avg=9/3=3.0
     * Window [3,4,5]: sum=12, avg=12/3=4.0
     * 
     * Note: Using nested loops, recalculates sum each time
     * Could optimize using sliding window technique
     * 
     * Time: O(n*k), Space: O(1)
     */
    public static void averageWindowLoop(List<Integer> list, int k) {

        System.out.println("\nFOR LOOP SOLUTION:");

        for (int i = 0; i <= list.size() - k; i++) {

            int sum = 0;

            for (int j = i; j < i + k; j++) {
                sum += list.get(j);
            }

            double average = (double) sum / k;

            System.out.println(average);
        }
    }

    /**
     * ==================== FIXED WINDOW ====================
     * AVERAGE OF EVERY WINDOW OF SIZE K - Stream Approach
     * 
     * Problem: Same as averageWindowLoop
     * 
     * Approach:
     * 1. For each window starting index
     * 2. Extract sublist and calculate AVERAGE directly
     * 3. Stream can compute average with built-in method
     * 
     * Key Stream Method:
     * stream().mapToInt(Integer::intValue).average()
     * - Returns OptionalDouble
     * - .orElse(0) provides default if empty
     * 
     * OneLiner is cleaner than calculating sum then dividing
     * 
     * Time: O(n*k), Space: O(1)
     */
    public static void averageWindowStream(List<Integer> list, int k) {

        System.out.println("\nSTREAM SOLUTION:");

        IntStream.range(0, list.size() - k + 1)
                .mapToDouble(i -> list.subList(i, i + k)
                        .stream()
                        .mapToInt(Integer::intValue)
                        .average()
                        .orElse(0))
                .forEach(System.out::println);
    }


    // ========================================================================
    // 6. FIRST NEGATIVE NUMBER IN WINDOW
    // ========================================================================

    /**
     * ==================== FIXED WINDOW ====================
     * FIRST NEGATIVE NUMBER IN WINDOW OF SIZE K
     * 
     * Problem: For each window, find first negative number (if any)
     * Input: [12, -1, -7, 8, -15, 30, 16, 28], K=3
     * Output: 0, -1, -7, -15, -15, 0
     * 
     * Explanation:
     * Window [12,-1,-7]: first negative = -1
     * Window [-1,-7,8]: first negative = -1
     * Window [-7,8,-15]: first negative = -7
     * Window [8,-15,30]: first negative = -15
     * Window [-15,30,16]: first negative = -15
     * Window [30,16,28]: first negative = 0 (none found)
     * 
     * Approach:
     * 1. For each window, iterate through elements
     * 2. Find first negative number
     * 3. When found, break (we only need FIRST)
     * 4. If none found, result stays 0
     * 
     * Optimization:
     * - Break early when negative found
     * - No need to check remaining elements in window
     * - Still O(n*k) worst case
     * 
     * Time: O(n*k) worst case, O(n) best case if all negatives
     * Space: O(1)
     */
    public static void firstNegativeLoop(List<Integer> list, int k) {

        System.out.println("\nFOR LOOP SOLUTION:");

        for (int i = 0; i <= list.size() - k; i++) {

            int negative = 0;  // Default: no negative found

            // Search for first negative in window
            for (int j = i; j < i + k; j++) {

                if (list.get(j) < 0) {
                    negative = list.get(j);
                    break;  // Stop at first negative
                }
            }

            System.out.println(negative);
        }
    }


    // ========================================================================
    // 7. VARIABLE SLIDING WINDOW
    // ========================================================================

    /**
     * ==================== VARIABLE WINDOW ====================
     * VARIABLE SLIDING WINDOW - Smallest Subarray with Sum >= Target
     * 
     * Problem: Find MINIMUM length contiguous subarray whose sum >= target
     * Input: [2,1,5,2,3,2], Target=7
     * Output: 2
     * 
     * Subarrays:
     * [2] sum=2 < 7
     * [2,1] sum=3 < 7
     * [2,1,5] sum=8 >= 7 ✓ (length 3)
     * [1,5] sum=6 < 7
     * [5,2] sum=7 >= 7 ✓ (length 2) ← ANSWER
     * [2,3] sum=5 < 7
     * [1,5,2] sum=8 >= 7 ✓ (length 3)
     * [5,2,3] sum=10 >= 7 ✓ (length 3)
     * 
     * Approach (Two Pointers - Variable Window):
     * 1. EXPAND window by moving RIGHT pointer (add elements)
     * 2. When sum >= target:
     *    - Record current window length (it's valid)
     *    - SHRINK window from LEFT (remove elements)
     *    - While maintaining sum >= target
     * 3. Find MINIMUM length that works
     * 
     * Why Variable Window:
     * - Window size CHANGES based on condition (sum >= target)
     * - Expands to include more elements
     * - Shrinks to minimize length
     * - Not fixed like previous problems
     * 
     * How It Works:
     * 1. RIGHT pointer always moves right (expanding)
     * 2. LEFT pointer moves right when sum is valid (shrinking)
     * 3. NEVER go backward (efficient O(n))
     * 4. At any point: window = [left, right]
     * 
     * Detailed Trace for [2,1,5,2,3,2], Target=7:
     * ========================================
     * right=0, num=2:
     *   sum=0+2=2 < 7
     *   window=[0,0], size=1
     *   min=∞
     * 
     * right=1, num=1:
     *   sum=2+1=3 < 7
     *   window=[0,1], size=2
     *   min=∞
     * 
     * right=2, num=5:
     *   sum=3+5=8 >= 7 ✓
     *   Shrink phase:
     *     window=[0,2], size=3, record min=3
     *     sum=8-2=6 < 7, stop shrinking
     *     left=1
     *   window=[1,2]
     * 
     * right=3, num=2:
     *   sum=6+2=8 >= 7 ✓
     *   Shrink phase:
     *     window=[1,3], size=3, min stays 3
     *     sum=8-1=7 >= 7 ✓
     *     remove left=1, left=2
     *   window=[2,3], size=2, min=2 ← UPDATE
     *   sum=7-5=2 < 7, stop
     *   left=3
     * 
     * right=4, num=3:
     *   sum=2+3=5 < 7
     *   window=[3,4], size=2
     *   min=2
     * 
     * right=5, num=2:
     *   sum=5+2=7 >= 7 ✓
     *   Shrink phase:
     *     window=[3,5], size=3, min stays 2
     *     sum=7-2=5 < 7, stop
     *     left=4
     *   window=[4,5]
     * 
     * Final Answer: min=2 ✓
     * Window that gave answer: [5,2] at indices [2,3]
     * 
     * Why This is More Efficient Than Fixed Window:
     * - FIXED: Try every K-sized window = O(n*k)
     * - VARIABLE: Each element visited at most 2 times = O(n)
     * - Better when we want to OPTIMIZE for minimum/maximum
     * 
     * Time Complexity: O(n)
     * - RIGHT pointer moves from 0 to n-1 (n steps)
     * - LEFT pointer moves from 0 to n-1 AT MOST (n steps)
     * - Total: 2n operations = O(n)
     * - Despite nested while, AMORTIZED O(n)
     * 
     * Space Complexity: O(1)
     * - Only use a few variables (left, right, sum, minLength)
     * - No extra data structures
     * 
     * Key Insights:
     * ✓ Variable window adapts to the problem condition
     * ✓ Two pointers: one expands, one shrinks
     * ✓ Maintain INVARIANT: window always satisfies some property
     * ✓ When property breaks, shrink until it's restored
     * ✓ Track answer while shrinking (min, max, count, etc)
     * 
     * Applications:
     * - Minimum window substring
     * - Longest substring without repeating characters
     * - Maximum sum with at most K distinct elements
     * - Smallest subarray with sum >= target
     */
    public static void variableWindowExample(List<Integer> list, int target) {

        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        // RIGHT pointer expands window
        for (int right = 0; right < list.size(); right++) {

            // Add new element: expand window
            sum += list.get(right);

            // SHRINK window while condition is satisfied
            while (sum >= target) {

                // Record current window length (valid solution)
                minLength = Math.min(minLength, right - left + 1);

                // Remove left element: shrink window
                sum -= list.get(left);
                left++;
            }
        }

        System.out.println("Minimum Length = " + minLength);
    }


    // ========================================================================
    // 8. LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS
    // ========================================================================

    /**
     * ==================== VARIABLE WINDOW ====================
     * LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS
     * 
     * Problem: Find the LENGTH of longest contiguous substring with NO duplicate characters
     * Input: "abcabcbb"
     * Output: 3 (substring "abc")
     * 
     * All Substrings:
     * "a" (1), "ab" (2), "abc" (3) ← MAX, "abca" (has duplicate 'a')
     * "bca" (3) ← MAX, "bcab" (has duplicate 'b')
     * "cab" (3) ← MAX, "cabb" (has duplicate 'b')
     * "abb" (has duplicate 'b'), "bb" (has duplicate 'b')
     * 
     * Approach (Variable Window with Two Pointers):
     * 1. Expand window by moving RIGHT pointer (add new character)
     * 2. If character already in window (duplicate found):
     *    - SHRINK window from LEFT until duplicate removed
     *    - Keep removing characters until duplicate is gone
     * 3. Add current character to set
     * 4. Track maximum window size seen
     * 
     * Key Difference from Fixed Window:
     * - Window size CHANGES based on duplicates
     * - Use HashSet to track characters in current window O(1) lookup
     * - When duplicate found, SHRINK (not skip)
     * 
     * Why This Works:
     * - We NEVER have duplicates in the current window
     * - We find the LARGEST such window
     * - Two pointer ensures O(n) - each char visited max 2 times
     * 
     * Detailed Trace for "abcabcbb":
     * ========================================
     * right=0, char='a':
     *   set={}, no duplicate
     *   add 'a': set={a}
     *   window="a" (length=1), max=1
     * 
     * right=1, char='b':
     *   set={a}, no duplicate
     *   add 'b': set={a,b}
     *   window="ab" (length=2), max=2
     * 
     * right=2, char='c':
     *   set={a,b}, no duplicate
     *   add 'c': set={a,b,c}
     *   window="abc" (length=3), max=3
     * 
     * right=3, char='a':
     *   set={a,b,c}, DUPLICATE FOUND! ('a' already exists)
     *   Remove from left until duplicate gone:
     *     left=0: remove arr[0]='a', set={b,c}, left=1
     *   add 'a': set={b,c,a}
     *   window="bca" (length=3), max=3 (unchanged)
     * 
     * right=4, char='b':
     *   set={b,c,a}, DUPLICATE FOUND! ('b' already exists)
     *   Remove from left until duplicate gone:
     *     left=1: remove arr[1]='b', set={c,a}, left=2
     *   add 'b': set={c,a,b}
     *   window="cab" (length=3), max=3 (unchanged)
     * 
     * right=5, char='c':
     *   set={c,a,b}, DUPLICATE FOUND! ('c' already exists)
     *   Remove from left until duplicate gone:
     *     left=2: remove arr[2]='c', set={a,b}, left=3
     *   add 'c': set={a,b,c}
     *   window="abc" (length=3), max=3 (unchanged)
     * 
     * right=6, char='b':
     *   set={a,b,c}, DUPLICATE FOUND! ('b' already exists)
     *   Remove from left until duplicate gone:
     *     left=3: remove arr[3]='a', set={b,c}, left=4
     *     left=4: remove arr[4]='b', set={c}, left=5
     *   add 'b': set={c,b}
     *   window="cb" (length=2), max=3 (unchanged)
     * 
     * right=7, char='b':
     *   set={c,b}, DUPLICATE FOUND! ('b' already exists)
     *   Remove from left until duplicate gone:
     *     left=5: remove arr[5]='c', set={b}, left=6
     *     left=6: remove arr[6]='b', set={}, left=7
     *   add 'b': set={b}
     *   window="b" (length=1), max=3 (unchanged)
     * 
     * Final Answer: max=3 ✓
     * 
     * Time Complexity: O(n)
     * - Each character visited AT MOST 2 times
     * - Once by right pointer moving forward
     * - Once by left pointer when shrinking
     * 
     * Space Complexity: O(min(n, charset_size))
     * - HashSet can have at most min(string_length, alphabet_size) characters
     * - For ASCII: max 128, Unicode: max 10000+
     * - For lowercase letters 'a-z': max 26
     * 
     * Key Insights:
     * ✓ Variable window (changes size based on duplicates)
     * ✓ Two pointers effectively track window boundaries
     * ✓ HashSet ensures O(1) duplicate detection
     * ✓ Never SKIP duplicates, SHRINK until removed
     * ✓ O(n) time despite nested while loop (amortized analysis)
     */
    public static void longestUniqueSubstring(String str) {
        //window
        Set<Character> window = new HashSet<>();
        int left = 0;
        int maxLength = 0;

        // RIGHT pointer expands window
        for (int right = 0; right < str.length(); right++) {
           
            // If character is duplicate, shrink window from LEFT
            //if current position character already in window, then idea is to remove first from window and move left pointer
            //this is called window cleansing, then add new char
            while (window.contains(str.charAt(right))) {
                window.remove(str.charAt(left));
                left++;
            }

            // Add current character (now guaranteed no duplicates)
            window.add(str.charAt(right));

            // Update max length (window without duplicates)
            maxLength = Math.max(maxLength, right - left + 1);
        }

        System.out.println(
                "Longest Unique Substring Length = "
                        + maxLength
        );
    }
}
