package com.algorithm.array;

import java.util.*;

/**
 * ============================================================================
 * PATTERN: Pair Sum Problems (Array/String)
 * ============================================================================
 * 
 * PROBLEM IDENTIFICATION:(Random triplet/pair sum problems)
 * - Involves finding pairs/triplets of elements
 * - Elements do NOT need to be contiguous
 * - Can be combined with various conditions
 * 
 * KEY QUESTIONS TO ASK:
 * 1. Is the input sorted? → Two Pointers
 * 2. Is the input unsorted? → HashMap / HashSet or Brute Force
 * 3. Do I need all pairs or just one? → Affects approach
 * 4. Can I modify the input? → Can sort if yes
 * 
 * APPROACHES:
 * ┌─ Unsorted Input
 * │  ├─ Brute Force: Nested loops O(n²)
 * │  └─ Optimized: HashMap/HashSet O(n)
 * │
 * └─ Sorted Input
 *    └─ Two Pointers: O(n) after sorting
 * 
 * ============================================================================
 */
public class PairSumPatterns {

    public static void main(String[] args) {
        // Test data
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int target = 7;

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target Sum: " + target);

        // Example 1: Find if pair exists
        System.out.println("\n=== EXAMPLE 1: Check if Pair Exists ===");
        System.out.println("Pair exists: " + hasPairExists(arr, target));

        // Example 2: Find all pairs - HashSet approach
        System.out.println("\n=== EXAMPLE 2: Find All Pairs (HashSet) ===");
        findAllPairsHashSet(arr, target);

        // Example 3: Find all pairs - Two Pointer approach
        System.out.println("\n=== EXAMPLE 3: Find All Pairs (Two Pointer) ===");
        findAllPairsTwoPointer(arr, target);

        // Example 4: Count total pairs
        System.out.println("\n=== EXAMPLE 4: Count Total Pairs ===");
        int[] dupArr = {1, 2, 3, 4, 5, 2, 3};
        System.out.println("Array: " + Arrays.toString(dupArr));
        System.out.println("Target: " + target);
        System.out.println("Total pairs count: " + countTotalPairs(dupArr, target));
    }

    /**
     * EXAMPLE 1: Check if Pair(2 elements) Exists with Target Sum
     * 
     * Problem: Return true if any pair exists that sums to target, else false
     * Input: [1,2,3,4,5,6,7], target = 7
     * Output: true → pairs exist (1,6), (2,5), (3,4)
     * 
     * Approach:
     * - HashSet to store seen numbers
     * - For each number, check if complement (target - num) exists
     * - Time: O(n), Space: O(n)
     * 
     * STREAM SOLUTION (Alternative):
     * ─────────────────────────────
     * Set<Integer> seen = new HashSet<>();
     * return Arrays.stream(arr)
     *     .anyMatch(num -> {
     *         int complement = target - num;
     *         if (seen.contains(complement)) {
     *             System.out.println("  Found pair: " + complement + " + " + num + " = " + target);
     *             return true;
     *         }
     *         seen.add(num);
     *         return false;
     *     });
     * 
     * Note about stream solution:
     * - Uses anyMatch() which short-circuits (stops at first true)
     * - Maintains seen set with side effects (lambda modifies external set)
     * - More functional style but less readable due to side effects
     * - Generally: Traditional loop is preferred for this pattern
     * - Streams shine when operations are pure and no external state is modified
     */
    public static boolean hasPairExists(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();
        
        for (int num : arr) {
            int complement = target - num;
            
            // If we've seen the complement before, pair exists
            if (seen.contains(complement)) {
                System.out.println("  Found pair: " + complement + " + " + num + " = " + target);
                return true;
            }
            
            seen.add(num);
        }
        
        return false;
    }

    /**
     * EXAMPLE 2: Find All Pairs (HashSet Approach)
     * 
     * Problem: Print all unique pairs that sum to target
     * Input: [1,2,3,4,5,6,7], target = 7
     * Output:
     *   Pair: 1 + 6 = 7
     *   Pair: 2 + 5 = 7
     *   Pair: 3 + 4 = 7
     * 
     * Approach:
     * - Use HashSet to avoid counting same pair twice
     * - Check if complement exists before adding
     * - Only add if num < complement (to avoid duplicates)
     * - Time: O(n), Space: O(n)
     */
    public static void findAllPairsHashSet(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();
        Set<String> pairs = new HashSet<>();
        
        for (int num : arr) {
            int complement = target - num;
            
            // If complement exists and we haven't recorded this pair
            if (seen.contains(complement)) {
                // Store pair in sorted order to avoid duplicates
                String pair = Math.min(num, complement) + "," + Math.max(num, complement);
                if (!pairs.contains(pair)) {
                    System.out.println("  Pair: " + Math.min(num, complement) + " + " + 
                                     Math.max(num, complement) + " = " + target);
                    pairs.add(pair);
                }
            }
            
            seen.add(num);
        }
    }

    /**
     * EXAMPLE 3: Find All Pairs (Two Pointer Approach)
     *
     * sort will not impact the result as we are only interested in pairs
     * that sum to target, not their original indices.
     *
     * Problem: Print all unique pairs using two-pointer technique
     * Input: [1,2,3,4,5,6,7], target = 7
     * Output:
     *   Pair: 1 + 6 = 7
     *   Pair: 2 + 5 = 7
     *   Pair: 3 + 4 = 7
     * 
     * Approach:
     * - Sort array first: O(n log n)
     * - Place pointers at start and end
     * - Move inward based on sum comparison
     * - Skip duplicates while moving
     * - Time: O(n log n), Space: O(1) if we ignore sorting space
     * 
     * Advantage: Memory efficient, easier to skip duplicates
     */
    public static void findAllPairsTwoPointer(int[] arr, int target) {
        // Create sorted copy
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        
        int left = 0;
        int right = sorted.length - 1;
        
        while (left < right) {
            int sum = sorted[left] + sorted[right];
            
            if (sum == target) {
                // Found a pair
                System.out.println("  Pair: " + sorted[left] + " + " + sorted[right] + " = " + target);
                
                // Skip duplicates on left
                while (left < right && sorted[left] == sorted[left + 1]) {
                    left++;
                }
                
                // Skip duplicates on right
                while (left < right && sorted[right] == sorted[right - 1]) {
                    right--;
                }
                
                left++;
                right--;
                
            } else if (sum < target) {
                // Sum too small, move left pointer right to increase sum
                left++;
            } else {
                // Sum too large, move right pointer left to decrease sum
                right--;
            }
        }
    }

    /**
     * EXAMPLE 4: Count Total Pairs (Handles Duplicates)
     * 
     * Problem: Count all unique index pairs (i, j) where i < j that sum to target
     * Handle duplicates correctly using combination formula
     * Input: [1,2,3,4,5,2,3], target = 7
     * Output: 4 pairs → (1,6), (2,5), (4,3), (5,2)
     * 
     * Approach:
     * - Use HashMap to count frequency of each number
     * - For each unique number, check if complement exists
     * - If num == complement: use combination C(n,2) = n*(n-1)/2
     * - If num != complement: multiply frequencies
     * - Only count when num < complement to avoid double counting
     * - Time: O(n), Space: O(n)
     */
    public static int countTotalPairs(int[] arr, int target) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        
        // Count frequency of each number
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        int pairCount = 0;
        
        for (int num : frequencyMap.keySet()) {
            int complement = target - num;
            
            if (frequencyMap.containsKey(complement)) {
                // Case 1: num == complement (e.g., 3.5 + 3.5 = 7, but with integers: 0+0=0)
                // Use combination formula: C(n,2) = n*(n-1)/2
                if (num == complement) {
                    int freq = frequencyMap.get(num);
                    int pairs = (freq * (freq - 1)) / 2;
                    pairCount += pairs;
                    System.out.println("  Same number (" + num + "): " + pairs + " pairs from " + freq + " occurrences");
                }
                // Case 2: Different numbers - count only when num < complement to avoid double counting
                else if (num < complement) {
                    int freq1 = frequencyMap.get(num);
                    int freq2 = frequencyMap.get(complement);
                    int pairs = freq1 * freq2;
                    pairCount += pairs;
                    System.out.println("  Pair (" + num + "," + complement + "): " + pairs + " pairs");
                }
            }
        }
        
        return pairCount;
    }
}

