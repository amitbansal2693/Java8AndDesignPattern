package com.algorithm.general;

import java.util.*;

/**
 * ============================================================================
 * PATTERN: Binary Search (Beyond Simple Array Search)
 * ============================================================================
 * 
 * PATTERN IDENTIFICATION:
 * Do NOT think of binary search only as: "Find element in sorted array"
 * 
 * KEY INSIGHT:
 * "Can I divide the search space in half?"
 * "Is there a monotonic condition where one side is always possible/impossible?"
 * 
 * WHEN TO USE BINARY SEARCH:
 * ✓ Find in sorted array
 * ✓ First/last occurrence
 * ✓ Find insertion position
 * ✓ Minimum feasible value (maximize minimum)
 * ✓ Maximum feasible value (minimize maximum)
 * ✓ Search in rotated sorted array
 * ✓ Find peak element
 * ✓ Apply function and search on result
 * 
 * COMMON MISCONCEPTIONS:
 * ✗ Only works on sorted arrays
 * ✗ Only works on arrays
 * ✓ Works on ANY monotonic function where result is yes/no
 * ✓ Works on "answer search space" not just "data search space"
 *
 * Interview definition
 * A useful way to remember it:
 * A monotonic property is a condition where, as the input/search value moves in one direction
 * , the result changes in only one direction.
 *
 * TEMPLATE:
 * ┌─ Problem has monotonic property: the result changes in only one direction.
 * │
 * ├─ Can we narrow down search space in half?
 * │
 * ├─ Set left = start, right = end
 * │
 * ├─ While left <= right:
 * │  ├─ mid = (left + right) / 2
 * │  ├─ Check condition
 * │  └─ Adjust left/right based on condition
 * │
 * └─ Return result
 * 
 * ============================================================================
 */
public class BinarySearchPatterns {

    public static void main(String[] args) {
        System.out.println("=================== BASIC BINARY SEARCH ===================\n");

        // Example 1: Standard Binary Search
        System.out.println("EXAMPLE 1: Find Element in Sorted Array");
        int[] arr1 = {1, 3, 5, 7, 9, 11, 13, 15};
        findElementInSortedArray(arr1, 7);

        System.out.println("\n=================== BOUNDARY SEARCH ===================\n");

        // Example 2: Find First & Last Occurrence
        System.out.println("EXAMPLE 2: Find First and Last Occurrence");
        int[] arr2 = {1, 2, 2, 2, 3, 4, 5, 5, 5, 6};
        findFirstAndLastOccurrence(arr2, 5);

        System.out.println("\n=================== ANSWER SEARCH (Peak/Minimum) ===================\n");

        // Example 3: Find Peak Element
        System.out.println("EXAMPLE 3: Find Peak Element");
        int[] arr3 = {1, 3, 5, 7, 6, 4, 2};
        findPeakElement(arr3);

        System.out.println("\n=================== OPTIMIZE ANSWER SEARCH ===================\n");

        // Example 4: Minimizing Maximum (Binary Search on Answer)
        System.out.println("EXAMPLE 4: Minimum Maximum Load (Capacity Problem)");
        int[] loads = {4, 5, 6, 7, 8};
        minimizeMaximumLoad(loads, 2);
    }

    // ========================================================================
    // EXAMPLE 1: Standard Binary Search
    // ========================================================================

    /**
     * EXAMPLE 1: Find Element in Sorted Array
     * 
     * Problem: Find index of target in sorted array (-1 if not found)
     * Input: [1, 3, 5, 7, 9, 11, 13, 15], target = 7
     * Output: 3
     * 
     * APPROACH (Standard Binary Search):
     * 1. Initialize left=0, right=n-1
     * 2. Loop: left <= right
     * 3. mid = (left + right) / 2
     * 4. If arr[mid] == target, return mid
     * 5. If arr[mid] < target, search right (left = mid + 1)
     * 6. If arr[mid] > target, search left (right = mid - 1)
     * 
     * Why efficient:
     * - Each step eliminates half of remaining elements
     * - Log(n) steps instead of linear n steps
     * 
     * Visualization:
     * [1, 3, 5, 7, 9, 11, 13, 15], target=7
     *  L              M             R
     * arr[M]=7 == 7 → Found at index 3
     * 
     * Time: O(log n), Space: O(1)
     */
    public static void findElementInSortedArray(int[] arr, int target) {
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target: " + target);
        System.out.println("\nProcessing:");

        int left = 0, right = arr.length - 1;
        int step = 0;
 //try to find mid element
        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoid overflow
            step++;

            System.out.println("  Step " + step + ": left=" + left + ", mid=" + mid + ", right=" + right + 
                             " → arr[mid]=" + arr[mid]);

            if (arr[mid] == target) {
                System.out.println("Result: Found at index " + mid);
                return;
            } else if (arr[mid] < target) {
                System.out.println("    " + arr[mid] + " < " + target + " → Search right");
                left = mid + 1;
            } else {
                System.out.println("    " + arr[mid] + " > " + target + " → Search left");
                right = mid - 1;
            }
        }

        System.out.println("Result: Not found (index = -1)");
    }

    // ========================================================================
    // EXAMPLE 2: Find First & Last Occurrence
    // ========================================================================

    /**
     * EXAMPLE 2: Find First and Last Occurrence
     * 
     * Problem: Find first and last index of target in sorted array
     * Input: [1, 2, 2, 2, 3, 4, 5, 5, 5, 6], target = 5
     * Output: [7, 9] (first and last indices)
     * 
     * APPROACH:
     * - Use binary search TWICE
     * - First: Find leftmost occurrence (first index)
     * - Second: Find rightmost occurrence (last index)
     * 
     * For FIRST OCCURRENCE:
     * - When we find target, don't stop
     * - Keep searching LEFT for earlier occurrence
     * - left = mid + 1 to skip this
     * - right = mid - 1 to search earlier
     * 
     * For LAST OCCURRENCE:
     * - When we find target, don't stop
     * - Keep searching RIGHT for later occurrence
     * - right = mid - 1 to skip this
     * - left = mid + 1 to search later
     * 
     * Time: O(log n), Space: O(1)
     */
    public static void findFirstAndLastOccurrence(int[] arr, int target) {
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target: " + target);

        int first = findFirst(arr, target);
        int last = findLast(arr, target);

        if (first == -1) {
            System.out.println("Result: Not found");
        } else {
            System.out.println("Result: First occurrence at index " + first + ", Last at index " + last);
        }
    }

    private static int findFirst(int[] arr, int target) {
        System.out.println("\nFinding FIRST occurrence:");
        int left = 0, right = arr.length - 1;
        int result = -1;
        int step = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            step++;
            System.out.println("  Step " + step + ": arr[" + mid + "]=" + arr[mid]);

            if (arr[mid] == target) {
                result = mid;
                System.out.println("    Found at " + mid + " → Search left for earlier");
                right = mid - 1; // Keep searching left
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    private static int findLast(int[] arr, int target) {
        System.out.println("\nFinding LAST occurrence:");
        int left = 0, right = arr.length - 1;
        int result = -1;
        int step = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            step++;
            System.out.println("  Step " + step + ": arr[" + mid + "]=" + arr[mid]);

            if (arr[mid] == target) {
                result = mid;
                System.out.println("    Found at " + mid + " → Search right for later");
                left = mid + 1; // Keep searching right
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    // ========================================================================
    // EXAMPLE 3: Find Peak Element
    // ========================================================================

    /**
     * EXAMPLE 3: Find Peak Element
     * 
     * Problem: Find index of peak (element greater than neighbors)
     * Input: [1, 3, 5, 7, 6, 4, 2]
     * Output: 3 (value 7 is peak)
     * 
     * APPROACH (Binary Search on Monotonicity):
     * - Key insight: Ascent vs Descent are monotonic
     * - If arr[mid] < arr[mid+1], peak is on RIGHT (ascending)
     * - If arr[mid] > arr[mid+1], peak could be at mid or LEFT (descending)
     * 
     * Visualization:
     * [1, 3, 5, 7, 6, 4, 2]
     *  L        M              R
     * 7 > 6 (descending) → Peak at mid or left
     * Search left
     * 
     * [1, 3, 5, 7]
     *  L     M     R
     * 5 < 7 (ascending) → Peak on right
     * Search right
     * 
     * [5, 7]
     *     L M
     * 7 > (out of bounds) → Peak at 7
     * 
     * Time: O(log n), Space: O(1)
     */
    public static void findPeakElement(int[] arr) {
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("\nProcessing (binary search):");

        int left = 0, right = arr.length - 1;
        int step = 0;

        while (left < right) {
            int mid = left + (right - left) / 2;
            step++;

            System.out.println("  Step " + step + ": left=" + left + ", mid=" + mid + ", right=" + right);
            System.out.println("    arr[" + mid + "]=" + arr[mid] + ", arr[" + (mid + 1) + "]=" + arr[mid + 1]);

            if (arr[mid] < arr[mid + 1]) {
                // Ascending on right, peak is on right side
                System.out.println("    " + arr[mid] + " < " + arr[mid + 1] + " (ascending) → Search right");
                left = mid + 1;
            } else {
                // Descending, peak is at mid or left
                System.out.println("    " + arr[mid] + " >= " + arr[mid + 1] + " (descending) → Search left (or mid)");
                right = mid;
            }
        }

        System.out.println("Result: Peak found at index " + left + " with value " + arr[left]);
    }

    // ========================================================================
    // EXAMPLE 4: Binary Search on Answer (Minimize Maximum)
    // ========================================================================

    /**
     * EXAMPLE 4: Minimize Maximum Load (Capacity Problem)
     * 
     * Problem:
     * Given loads to distribute among k workers,
     * find minimum capacity such that each worker handles loads with sum <= capacity
     * 
     * Input: loads = [4, 5, 6, 7, 8], workers = 2
     * Output: 15
     * Explanation:
     * - Worker 1: [4, 5, 6] = 15
     * - Worker 2: [7, 8] = 15
     * - Maximum is 15 (optimal split)
     * 
     * NAIVE APPROACH: O(sum * n)
     * - Try every possible capacity from min to sum
     * - Check if it's feasible
     * 
     * BINARY SEARCH APPROACH: O(n log sum)
     * - Search space: [max_load, total_sum]
     * - Minimum capacity must be at least max_load (one worker gets it)
     * - Maximum can't exceed total_sum (one worker takes all)
     * - If capacity = mid feasible? Yes → try smaller (right = mid - 1)
     *                       No  → need more (left = mid + 1)
     * 
     * Time: O(n log(sum)), Space: O(1)
     */
    public static void minimizeMaximumLoad(int[] loads, int workers) {
        System.out.println("Loads: " + Arrays.toString(loads) + ", Workers: " + workers);

        int left = 0, right = 0;

        // Set search space
        for (int load : loads) {
            left = Math.max(left, load); // At least max load
            right += load; // At most sum of all loads
        }

        System.out.println("Search space: [" + left + ", " + right + "]");
        System.out.println("\nBinary search:");

        int result = right;
        int step = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            step++;

            boolean feasible = canDistribute(loads, workers, mid);

            System.out.println("  Step " + step + ": Capacity=" + mid + " → Feasible? " + feasible);

            if (feasible) {
                result = mid;
                System.out.println("    Can distribute → Try smaller");
                right = mid - 1;
            } else {
                System.out.println("    Cannot distribute → Need more capacity");
                left = mid + 1;
            }
        }

        System.out.println("\nResult: Minimum capacity needed = " + result);
    }

    private static boolean canDistribute(int[] loads, int workers, int capacity) {
        int currentWorkerLoad = 0;
        int workersUsed = 1;

        for (int load : loads) {
            if (currentWorkerLoad + load <= capacity) {
                currentWorkerLoad += load;
            } else {
                // Give to new worker
                workersUsed++;
                currentWorkerLoad = load;

                if (workersUsed > workers) {
                    return false; // Already used more workers than available
                }
            }
        }

        return true;
    }
}

