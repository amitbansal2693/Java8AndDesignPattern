package com.algorithm.practice.general;

import java.util.*;

/**
 * ============================================================================
 * PRACTICE: Binary Search (Beyond Simple Array Search)
 * ============================================================================
 * 
 * This is the PRACTICE version - incomplete methods for you to solve
 * 
 * Complete each method following the hints and approach comments
 * Test your solution against the examples in main()
 * 
 * ============================================================================
 */
public class BinarySearchPatternsPractice {

    public static void main(String[] args) {
        System.out.println("========== BINARY SEARCH PRACTICE PROBLEMS ==========\n");

        System.out.println("=================== BASIC BINARY SEARCH ===================\n");

        // Practice 1: Standard Binary Search
        System.out.println("PRACTICE 1: Find Element in Sorted Array");
        int[] arr1 = {1, 3, 5, 7, 9, 11, 13, 15};
        System.out.println("Array: " + Arrays.toString(arr1));
        try {
            findElementInSortedArray(arr1, 7);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        System.out.println("\n=================== BOUNDARY SEARCH ===================\n");

        // Practice 2: Find First & Last Occurrence
        System.out.println("PRACTICE 2: Find First and Last Occurrence");
        int[] arr2 = {1, 2, 2, 2, 3, 4, 5, 5, 5, 6};
        System.out.println("Array: " + Arrays.toString(arr2));
        try {
            findFirstAndLastOccurrence(arr2, 5);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        System.out.println("\n=================== ANSWER SEARCH =================== \n");

        // Practice 3: Find Peak Element
        System.out.println("PRACTICE 3: Find Peak Element");
        int[] arr3 = {1, 3, 5, 7, 6, 4, 2};
        System.out.println("Array: " + Arrays.toString(arr3));
        try {
            findPeakElement(arr3);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        System.out.println("\n=================== OPTIMIZE ANSWER SEARCH ===================\n");

        // Practice 4: Minimizing Maximum (Binary Search on Answer)
        System.out.println("PRACTICE 4: Minimum Maximum Load (Capacity Problem)");
        int[] loads = {4, 5, 6, 7, 8};
        System.out.println("Loads: " + Arrays.toString(loads) + ", Workers: 2");
        try {
            minimizeMaximumLoad(loads, 2);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }
    }

    /**
     * PRACTICE 1: Find Element in Sorted Array
     * 
     * Problem: Find index of target in sorted array, return -1 if not found
     * Input: [1, 3, 5, 7, 9, 11, 13, 15], target = 7
     * Expected Output: 3
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Standard Binary Search):
     * 1. Initialize: left = 0, right = n-1
     * 2. Loop while left <= right:
     *    - mid = left + (right - left) / 2 (avoid overflow)
     *    - If arr[mid] == target: return mid
     *    - If arr[mid] < target: left = mid + 1 (search right half)
     *    - If arr[mid] > target: right = mid - 1 (search left half)
     * 3. If not found: return -1
     * 
     * WHY EFFICIENT:
     * - Each comparison eliminates half of remaining elements
     * - O(log n) instead of O(n)
     * 
     * TRACE for [1, 3, 5, 7, 9, 11, 13, 15], target=7:
     * left=0, right=7, mid=3 → arr[3]=7 → Found at index 3
     * 
     * HINT: Use (left + right) / 2 or left + (right - left) / 2
     * 
     * Time: O(log n), Space: O(1)
     */
    public static void findElementInSortedArray(int[] arr, int target) {
        throw new UnsupportedOperationException(
            "TODO: Implement standard binary search\n" +
            "Hint: Compare mid with target, adjust left/right pointers"
        );
    }

    /**
     * PRACTICE 2: Find First and Last Occurrence
     * 
     * Problem: Find first and last index of target in sorted array
     * Input: [1, 2, 2, 2, 3, 4, 5, 5, 5, 6], target = 5
     * Expected Output: First = 6, Last = 8
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH:
     * Use binary search TWICE:
     * 
     * 1. FIND FIRST OCCURRENCE:
     *    - When target found, DON'T return immediately
     *    - Continue searching LEFT (right = mid - 1)
     *    - Store result and keep searching
     * 
     * 2. FIND LAST OCCURRENCE:
     *    - When target found, continue searching RIGHT (left = mid + 1)
     *    - Store result and keep searching
     * 
     * TEMPLATE for finding first:
     * result = -1
     * while left <= right:
     *   mid = (left + right) / 2
     *   if arr[mid] == target:
     *     result = mid
     *     right = mid - 1  ← Key: keep searching left
     *   else if arr[mid] < target:
     *     left = mid + 1
     *   else:
     *     right = mid - 1
     * 
     * TRACE for first 5 in [1,2,2,2,3,4,5,5,5,6]:
     * Binary search narrows down to 5
     * finds 5 at index 6, then continues left to see if earlier 5
     * 
     * HINT: Create two separate helper methods for first and last
     * 
     * Time: O(log n), Space: O(1)
     */
    public static void findFirstAndLastOccurrence(int[] arr, int target) {
        throw new UnsupportedOperationException(
            "TODO: Find first and last occurrence using binary search\n" +
            "Hint: Use binary search twice - once searching left, once searching right"
        );
    }

    /**
     * PRACTICE 3: Find Peak Element
     * 
     * Problem: Find index of peak (element greater than both neighbors)
     * Input: [1, 3, 5, 7, 6, 4, 2]
     * Expected Output: 3 (value 7 is peak)
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Binary Search on Monotonicity):
     * KEY INSIGHT: Check if array is ascending or descending at mid
     * 
     * 1. Initialize: left = 0, right = n - 1
     * 2. While left < right:
     *    - mid = (left + right) / 2
     *    - Compare arr[mid] with arr[mid + 1]:
     *      * If arr[mid] < arr[mid + 1]: ascending, peak on right
     *        » left = mid + 1
     *      * If arr[mid] >= arr[mid + 1]: descending/plateau, peak at mid or left
     *        » right = mid
     * 3. When left == right, that's the peak
     * 
     * WHY PEAK MUST EXIST:
     * - First element compares with next
     * - Last element compares with previous
     * - If ascending → peak on right
     * - If descending → peak on left or mid
     * 
     * VISUALIZATION for [1, 3, 5, 7, 6, 4, 2]:
     * left=0, right=6, mid=3
     * 7 >= 6? descending → right = 3
     * 
     * left=0, right=3, mid=1
     * 3 < 5? ascending → left = 2
     * 
     * left=2, right=3, mid=2
     * 5 < 7? ascending → left = 3
     * 
     * left=3, right=3 → peak at 3
     * 
     * HINT: Check mid vs mid+1 to determine direction
     * 
     * Time: O(log n), Space: O(1)
     */
    public static void findPeakElement(int[] arr) {
        throw new UnsupportedOperationException(
            "TODO: Find peak element using binary search\n" +
            "Hint: Check if ascending or descending at mid to decide direction"
        );
    }

    /**
     * PRACTICE 4: Minimize Maximum Load (Binary Search on Answer)
     * 
     * Problem:
     * Distribute loads among k workers
     * Each worker carries consecutive loads
     * Minimize the maximum load any worker carries
     * 
     * Input: loads = [4, 5, 6, 7, 8], workers = 2
     * Expected Output: 15
     * Explanation:
     * Worker 1: [4,5,6] = 15
     * Worker 2: [7,8] = 15
     * Maximum = 15 (optimal split)
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Binary Search on Answer Space):
     * 1. Define search space:
     *    - min = max(loads) - at least one worker gets the heaviest load
     *    - max = sum(loads) - one worker takes everything
     * 2. Binary search on capacity:
     *    - For capacity = mid, can we distribute among k workers?
     *    - If yes: try smaller capacity (right = mid - 1)
     *    - If no: need more capacity (left = mid + 1)
     * 3. Return smallest feasible capacity
     * 
     * HELPER FUNCTION canDistribute(loads, workers, capacity):
     * - Simulate distribution with current capacity
     * - For each load:
     *   * If current_worker_load + load <= capacity:
     *     - Add to current worker
     *   * Else:
     *     - Give to new worker
     * - Return whether workers_used <= workers
     * 
     * TRACE for [4,5,6,7,8], k=2:
     * Search space: [8, 30]
     * mid=19 → can distribute? 4+5+6=15≤19, 7+8=15≤19 → yes → search smaller
     * Search: [8, 18]
     * mid=13 → can distribute? 4+5=9≤13, 6≤13? then 7≤13? (need 2nd worker) 7+8=15>13 → no
     * Search: [14, 18]
     * mid=16 → can distribute? 4+5+6=15≤16, 7+8=15≤16 → yes
     * Continue...
     * Answer: 15
     * 
     * HINT: Write helper function canDistribute first
     * 
     * Time: O(n log(sum)), Space: O(n)
     */
    public static void minimizeMaximumLoad(int[] loads, int workers) {
        throw new UnsupportedOperationException(
            "TODO: Minimize maximum load using binary search on answer\n" +
            "Hint: Binary search capacity, use helper to check if distribution is possible"
        );
    }
}

