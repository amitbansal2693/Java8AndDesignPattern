//noinspection ALL
package com.algorithm.practice.array;

import java.util.*;

/**
 * ============================================================================
 * PRACTICE: Pair Sum Problems (Array/String)
 * ============================================================================
 * 
 * This is the PRACTICE version - incomplete methods for you to solve
 * 
 * Same patterns as PairSumPatterns.java but with TODOs
 * Complete each method following the hints and approach comments
 * 
 * Test your solution against the examples in main()
 * 
 * ============================================================================
 */
@SuppressWarnings("ALL")
public class PairSumPatternsPractice {

    public static void main(String[] args) {
        System.out.println("========== PAIR SUM PRACTICE PROBLEMS ==========\n");

        // Test data
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int target = 7;

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target Sum: " + target);

        // Practice 1: Find if pair exists
        System.out.println("\n=== PRACTICE 1: Check if Pair Exists ===");
        try {
            System.out.println("Pair exists: " + hasPairExists(arr, target));
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        // Practice 2: Find all pairs - HashSet approach
        System.out.println("\n=== PRACTICE 2: Find All Pairs (HashSet) ===");
        try {
            findAllPairsHashSet(arr, target);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        // Practice 3: Find all pairs - Two Pointer approach
        System.out.println("\n=== PRACTICE 3: Find All Pairs (Two Pointer) ===");
        try {
            findAllPairsTwoPointer(arr, target);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        // Practice 4: Count total pairs
        System.out.println("\n=== PRACTICE 4: Count Total Pairs ===");
        try {
            int[] dupArr = {1, 2, 3, 4, 5, 2, 3};
            System.out.println("Array: " + Arrays.toString(dupArr));
            System.out.println("Target: " + target);
            System.out.println("Total pairs count: " + countTotalPairs(dupArr, target));
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }
    }

    /**
     * PRACTICE 1: Check if Pair Exists with Target Sum
     * 
     * Problem: Return true if any pair exists that sums to target, else false
     * Input: [1,2,3,4,5,6,7], target = 7
     * Expected Output: true (pairs exist: 1+6, 2+5, 3+4)
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH:
     * 1. Use HashSet to store numbers we've seen
     * 2. For each number in array:
     *    - Calculate complement = target - number
     *    - Check if complement exists in HashSet
     *    - If yes, return true
     *    - Add current number to HashSet
     * 3. If loop completes without finding, return false
     * 
     * HINT: This allows O(n) time complexity instead of O(n²)
     * 
     * Time: O(n), Space: O(n)
     */
    public static boolean hasPairExists(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int num : arr) {
            //find complement
            int complement = target - num;
            //if exist return, otherwise keep adding in stack
            if (seen.contains(complement)) {
                System.out.println("hasPairExists: Pair found (" +num+ " , "+complement+")");
                return true;
            }
            seen.add(num);
        }
        return false;
    }

    /**
     * PRACTICE 2: Find All Pairs (HashSet Approach)
     * 
     * Problem: Print all unique pairs that sum to target
     * Input: [1,2,3,4,5,6,7], target = 7
     * Expected Output:
     *   Pair: 1 + 6 = 7
     *   Pair: 2 + 5 = 7
     *   Pair: 3 + 4 = 7
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH:
     * 1. Use two HashSets:
     *    - seen: store numbers we've already processed
     *    - pairs: store string representation of pairs to avoid duplicates
     * 2. For each number:
     *    - Calculate complement = target - number
     *    - If complement in seen set:
     *      * Create pair string in sorted order (to avoid "1,6" and "6,1" being different)
     *      * If pair not already in pairs set, print and add it
     *    - Add current number to seen set
     * 
     * HINT: Use Math.min/Math.max to create consistent pair representation
     * 
     * Time: O(n), Space: O(n)
     */
    public static void findAllPairsHashSet(int[] arr, int target) {
     Set<String> result=new HashSet<>();
        Set<Integer> seen=new HashSet<>();

        for(int num:arr) {
            int complement=target-num;
            //if seen array has its compliment then pair found
            if(seen.contains(complement)){
                //add pairs
                result.add(Math.min(complement,num)+ ","+ Math.max(num, complement));
            }
            //otherwise
              seen.add(num);
        }
        System.out.println("findAllPairsHashSet: "+result);
    }

    /**
     * PRACTICE 3: Find All Pairs (Two Pointer Approach)
     * 
     * Problem: Print all unique pairs using two-pointer technique
     * Input: [1,2,3,4,5,6,7], target = 7
     * Expected Output:
     *   Pair: 1 + 6 = 7
     *   Pair: 2 + 5 = 7
     *   Pair: 3 + 4 = 7
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH:
     * 1. Create sorted copy of array
     * 2. Initialize: left = 0, right = length - 1
     * 3. While left < right:
     *    - Calculate sum = arr[left] + arr[right]
     *    - If sum == target:
     *      * Print pair/store result
     *      * Skip duplicates on left: while arr[left] == arr[left+1], left++
     *      * Skip duplicates on right: while arr[right] == arr[right-1], right--
     *      * Move both pointers: left++, right--
     *    - Else if sum < target: left++ (need larger sum)
     *    - Else: right-- (need smaller sum)
     * 
     * HINT: This approach is memory-efficient and cleaner for skipping duplicates
     * 
     * Time: O(n log n), Space: O(1) excluding sort space
     */
    public static void findAllPairsTwoPointer(int[] arr, int target) {

        List<Integer> list = Arrays.stream(arr).boxed().sorted().toList();
        List<String> result = new ArrayList<>();
        //use this to compare or scan current elements.
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == target) {
                result.add(list.get(left) + "," + list.get(right));
                //skip duplicates on left
                while (left < right && list.get(left) == list.get(left + 1)) {//skip duplicates
                    left++;
                }
                //skip duplicates on right
                while (left < right && list.get(right) == list.get(right - 1)) {//skip duplicates
                    right--;
                }
                left++;
                right--;

            } else if (sum < target) {//if sum is less<target, then add more but shifting left++
                left++;
            } else {//if sum>target, then reduce sum by shifting right--
                right--;
            }
        }
        System.out.println("findAllPairsTwoPointer:: " + result);
    }

    /**
     * PRACTICE 4: Count Total Pairs (Handles Duplicates)
     * 
     * Problem: Count all unique index pairs (i, j) where i < j that sum to target
     * Input: [1,2,3,4,5,2,3], target = 7
     * Expected Output: 4 pairs
     *   (index 0,5): 1 + 2 = 3 (wait, this doesn't equal 7)
     *   Let me recalculate: target=7
     *   [1,2,3,4,5,2,3] pairs summing to 7:
     *   - (2,5): indices (1,4) = 2+5=7
     *   - (2,5): indices (1,6) = 2+5=7  (second occurrence of 2)
     *   - (3,4): indices (2,3) = 3+4=7
     *   - (2,5): indices (5,4) = No, index should be i < j
     *   Total = multiple pairs based on frequency
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH:
     * 1. Create HashMap to count frequency of each unique number
     * 2. For each unique number in HashMap:
     *    - Calculate complement = target - number
     *    - If complement exists in HashMap:
     *      * Case A: If number == complement (e.g., 0+0=0, 3.5+3.5=7)
     *        - Use combination formula: C(n,2) = n*(n-1)/2
     *        - This gives number of ways to pick 2 from n occurrences
     *      * Case B: If number != complement
     *        - Only count when number < complement (to avoid double counting)
     *        - Pairs = frequency[number] * frequency[complement]
     * 
     * HINT: For same number case, think about how many pairs you can make from 3 occurrences
     *       From [2, 2, 2], you can make pairs: (0,1), (0,2), (1,2) = 3 pairs = C(3,2)
     * 
     * Time: O(n), Space: O(n)
     */
    public static int countTotalPairs(int[] arr, int target) {
        // CORRECTED APPROACH:
        // Store ALL indices for each value using Map<Integer, List<Integer>>
        // Then find pairs by iterating through all occurrences
        
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        Set<String> result = new HashSet<>();
        
        // Step 1: Build map of value → list of ALL indices where it appears
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            // computeIfAbsent: if key doesn't exist, create new ArrayList, then add index
            indexMap.computeIfAbsent(num, k -> new ArrayList<>()).add(i);
        }
        
        System.out.println("indexMap: " + indexMap);
        
        // Step 2: For each unique value, find pairs with its complement
        for (int num : indexMap.keySet()) {
            int complement = target - num;
            
            // If complement exists in the map
            if (indexMap.containsKey(complement)) {
                List<Integer> numIndices = indexMap.get(num);
                List<Integer> complementIndices = indexMap.get(complement);
                
                // For each occurrence of num and complement, create pair
                for (int numIdx : numIndices) {
                    for (int complIdx : complementIndices) {
                        // Only count pairs where i < j to avoid duplicates
                        if (numIdx < complIdx) {
                            String pair = numIdx + "," + complIdx;
                            result.add(pair);
                            System.out.println("  Found pair: [" + numIdx + "," + complIdx + "] = " + 
                                             arr[numIdx] + " + " + arr[complIdx] + " = " + target);
                        }
                    }
                }
            }
        }
        
        System.out.println("countTotalPairs result (index pairs): " + result);
        return result.size();
    }
}

