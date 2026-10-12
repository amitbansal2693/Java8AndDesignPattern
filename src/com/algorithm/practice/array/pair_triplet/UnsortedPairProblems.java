package com.algorithm.practice.array.pair_triplet;

import java.util.*;

/**
 * ============================================================================
 * UNSORTED PAIR PROBLEMS - HashMap/HashSet Approach
 * ============================================================================
 * 
 * PATTERN: When input is UNSORTED and we DON'T want to sort
 * 
 * KEY INSIGHT:
 * Use HashMap/HashSet for O(1) lookup
 * For each element, check if complement exists
 * Time: O(n)
 * Space: O(n)
 * 
 * COMPARISON:
 * Sorted (Two Pointers):    O(n log n) time, O(1) space
 * Unsorted (HashMap):       O(n) time, O(n) space ✓ Better!
 * 
 * HASHMAP USAGE:
 *   [1, 2, 3, 4, 5], target = 7
 *   
 *   Process 1: complement=6, not in map, add 1
 *   Process 2: complement=5, not in map, add 2
 *   Process 3: complement=4, not in map, add 3
 *   Process 4: complement=3, found in map! → pair found (3,4)
 *   Process 5: complement=2, found in map! → pair found (2,5)
 * 
 * ============================================================================
 */
@SuppressWarnings("ALL")
public class UnsortedPairProblems implements PairTripletInterface {

    // ========================================================================
    // TWO SUM - HASHSET APPROACH
    // ========================================================================

    /**
     * PROBLEM 1: Two Sum - Check if Pair Exists
     * 
     * Problem: Return true if any pair sums to target (unsorted array)
     * Input: [3, 11, 2, 7, 5], target = 9
     * Output: true (2+7=9)
     * 
     * Approach:
     * 1. Create HashSet to store seen numbers
     * 2. For each number:
     *    - Calculate complement = target - number
     *    - If complement in set, pair exists!
     *    - Add current number to set
     * 3. Return false if no pair found
     * 
     * Why HashSet vs HashMap?
     * - Only need to know if number exists (not frequency)
     * - HashSet: O(1) lookup, simpler
     * 
     * Time: O(n), Space: O(n)
     */
    @Override
    public boolean hasPair(int[] arr, int target) {
        // TODO: Implement using HashSet
        // Hint: One pass, check complement before adding
        System.out.println("Input data: " + Arrays.stream(arr).boxed().toList() + " -target: " + target);
        //Step 1: create seen hasset, plus result List
        Set<Integer> seen = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        //step2: use for loop
        for (int i = 0; i < arr.length; i++) {
            //step3: calculate complement and search in seen set.
            var complement = target - arr[i];
            //Step4: if found, add
            if (seen.contains(complement)) {
                result.add(List.of(arr[i], complement));
            } else
                seen.add(arr[i]);
        }
        System.out.println("result:" + result);
        return !result.isEmpty();
    }

    /**
     * PROBLEM 2: Find All Unique Pairs
     * 
     * Problem: Return all unique pairs (unsorted array)
     * Input: [3, 11, 2, 7, 5, 4], target = 9
     * Output: [[2,7], [4,5]]
     * 
     * Approach:
     * 1. Use HashSet for seen numbers
     * 2. Use HashSet for already found pairs (avoid duplicates)
     * 3. For each number, check if complement exists
     * 4. Store pair in canonical form (smaller, larger)
     * 5. Skip if already found this pair
     * 
     * Duplicate Handling:
     * - [3, 11, 2, 7, 5, 2, 7], target = 9
     * - Find (2,7) first time, skip on second occurrence
     * - Use Set<String> with "2,7" format
     * 
     * Time: O(n), Space: O(n)
     */
    @Override
    public List<List<Integer>> findAllPairs(int[] arr, int target) {
        System.out.println("findAllPairs: Input data: "+Arrays.stream(arr).boxed().toList() + " -target: "+target);
        // TODO: Implement using HashSet for seen and HashSet for pairs
        // Hint: Store pair as "min,max" string to detect duplicates
        //Step 1: declare result, seen set
        Set<Integer> seen=new HashSet<>();
        List<List<Integer>> result=new ArrayList<>();

        //step 2:  2 elements=pairs:  loop + complement
        for (int i = 0; i < arr.length-1; i++) {
            int complement = target- arr[i];
            if(seen.contains(complement))
                result.add(List.of(Math.min(arr[i],complement), Math.max(arr[i],complement)));
            else seen.add(arr[i]);
        }
        System.out.println("findAllPairs Result: "+result);
        return result;
    }

    /**
     * PROBLEM 3: Count All Pairs (with duplicates)
     * 
     * Problem: Count all pairs including duplicates
     * Input: [1, 2, 3, 4, 5, 2, 3], target = 7
     * Output: 4 pairs
     * Pairs: (1,4) from indices (0,3)
     *        (2,5) from indices (1,4) 
     *        (2,5) from indices (5,4)
     *        (3,4) from indices (2,3)
     * 
     * Approach:
     * 1. Use HashMap to count frequency of each number
     * 2. For each unique number:
     *    - Find complement
     *    - If same number (e.g., 3.5 + 3.5): use C(n,2) = n*(n-1)/2
     *    - If different: only count when num < complement (avoid double counting)
     *    - Pairs = freq[num] * freq[complement]
     * 
     * Why track frequency?
     * - Number can appear multiple times
     * - Each occurrence can form new pairs
     * - Example: [2, 2, 5] with target=7
     *   - First 2 can pair with 5
     *   - Second 2 can also pair with 5
     *   - Total: 2 * 1 = 2 pairs
     * 
     * Time: O(n), Space: O(n)
     */
    @Override
    public int countPairs(int[] arr, int target) {
        System.out.println("Input data: "+Arrays.stream(arr).boxed().toList() + " -target: "+target);
        //All pairs including duplicates. Best is to use do while.
        List<List<Integer>> result=new ArrayList<>();
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i]+arr[j]==target)
                    result.add(List.of(arr[i],arr[j]));
            }
        }
        System.out.println("countPairs- with duplicates: "+result);
        return result.size();
    }

    // ========================================================================
    // THREE SUM - HASHMAP APPROACH
    // ========================================================================
    /**
     * PROBLEM 4: Three Sum - Find All Unique Triplets
     * 
     * Problem: Return all unique triplets that sum to target
     * Input: [3, 11, 2, 7, 5, 4, -1], target = 9
     * Output: [[-1,4,6]? no 6, let's use [2,3,4], [2,7,0]? no 0...]
     * 
     * Approach (Method 1 - Reduce to Two Sum):
     * 1. Sort array first (or use HashMap approach)
     * 2. For each element as "first", find two-sum of rest
     * 3. Skip duplicates
     * 
     * Approach (Method 2 - HashSet for validation):
     * 1. For each pair (i,j), calculate third = target - arr[i] - arr[j]
     * 2. Check if third exists in array
     * 3. Use HashSet to avoid revisiting
     * 4. Avoid duplicates with canonical form
     * 
     * Time: O(n²) for checking all pairs, each check O(1)
     * Space: O(n) for HashSet
     */
    @Override
    public List<List<Integer>> findAllTriplets(int[] arr, int target) {
        System.out.println("Input data: "+Arrays.stream(arr).boxed().toList() + " -target: "+target);
        // TODO: Implement three sum using HashSet
        // Hint: Two nested loops for pairs, third element lookup in set
        //step 1 declaration
        List<List<Integer>> result=new ArrayList<>();
        for (int i = 0; i <arr.length-2 ; i++) {
            for (int j = i+1; j <arr.length-1 ; j++) {
                for (int k = j+1; k < arr.length; k++) {
                    if(arr[i]+arr[j]+arr[k]==target)
                        result.add(List.of(arr[i],arr[j],arr[k]));
                }
            }
        }
        //otherwise-sort > for loop > 2 pointers/hashset
        //for loop
        System.out.println("Find all triplets: "+result);
        return result;
    }

    /**
     * PROBLEM 5: Count All Triplets
     * 
     * Problem: Count triplets that sum to target
     * Input: [1, 2, 3, 4, 5], target = 9
     * Output: 2 (triplets: [1,3,5], [2,3,4])
     * 
     * Approach: Similar to findAllTriplets but with counting
     * 
     * Time: O(n²), Space: O(1) excluding input storage
     */
    @Override
    public int countTriplets(int[] arr, int target) {
        System.out.println("Input data: "+Arrays.stream(arr).boxed().toList() + " -target: "+target);
        // TODO: Implement triplet counting with HashMap
        // Hint: Three nested loops or reduce to two-sum counting
        List<List<Integer>> results =new ArrayList<>();
        for (int i = 0; i < arr.length-2; i++) {
            Set<Integer> seen =new HashSet<>();
            for (int j = i+1; j < arr.length; j++) {
                int complement = target-(arr[i] +arr[j]);
                if(seen.contains(complement)){
                    results.add(List.of(arr[i] ,arr[j],complement).stream().sorted().toList());
                } else
                    seen.add(arr[j]);
            }
        }
        System.out.println("countTriplets: seen complemnet- "+results);
        return results.size();
    }

    // ========================================================================
    // Combination problems use backtracking (see CombinationProblems)
    // ========================================================================

    @Override
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        throw new UnsupportedOperationException("Use CombinationProblems");
    }

    @Override
    public List<List<Integer>> combinationSumII(int[] candidates, int target) {
        throw new UnsupportedOperationException("Use CombinationProblems");
    }

    @Override
    public List<List<Integer>> generateCombinations(int n, int k) {
        throw new UnsupportedOperationException("Use CombinationProblems");
    }

    // ========================================================================
    // MAIN - TESTING
    // ========================================================================

    public static void main(String[] args) {
        UnsortedPairProblems solver = new UnsortedPairProblems();

        // Test 1: hasPair (unsorted)
        int[] arr1 = {3, 11, 2, 7, 5};
        System.out.println("Test hasPair (unsorted): " + solver.hasPair(arr1, 9));

        // Test 2: findAllPairs (unsorted)
        int[] arr2 = {3, 11, 2, 7, 5, 4,6};
        System.out.println("Test findAllPairs (unsorted): " + solver.findAllPairs(arr2, 9));

        // Test 3: countPairs (with duplicates)
        int[] arr3 = {1, 2, 3, 4, 5, 2, 3};
        System.out.println("Test countPairs (with duplicates): " + solver.countPairs(arr3, 7));

        // Test 4: findAllTriplets (unsorted)
        int[] arr4 = {3, 11, 2, 7, 5, 4};
        System.out.println("Test findAllTriplets (unsorted): " + solver.findAllTriplets(arr4, 13));

        // Test 5: countTriplets
        int[] arr5 = {1, 2, 3, 4, 5};
        System.out.println("Test countTriplets: " + solver.countTriplets(arr5, 9));
    }
}

