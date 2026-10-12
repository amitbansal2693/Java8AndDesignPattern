package com.algorithm.practice.array.pair_triplet;

import java.util.*;

/**
 * ============================================================================
 * SORTED PAIR PROBLEMS - Two Pointers Approach
 * ============================================================================
 * 
 * PATTERN: When input is SORTED or we can SORT it
 * 
 * KEY INSIGHT:
 * Use two pointers - one at start, one at end
 * Move inward based on sum comparison
 * Time: O(n) if sorted, O(n log n) if need to sort
 * Space: O(1) excluding sort space
 * 
 * TWO POINTER MOVEMENT:
 *   [1, 2, 3, 4, 5, 6, 7]    target = 7
 *    L                    R
 * 
 *    1 + 7 = 8 > 7 → move R left
 *    1 + 6 = 7 = 7 → FOUND! move both
 *    2 + 6 = 8 > 7 → move R left
 *    2 + 5 = 7 = 7 → FOUND! move both
 *    ... continue
 * 
 * ============================================================================
 */
@SuppressWarnings("ALL")
public class SortedPairProblems implements PairTripletInterface {

    // ========================================================================
    // TWO SUM - SORTED APPROACH
    // ========================================================================

    /**
     * PROBLEM 1: Two Sum - Check if Pair Exists
     * 
     * Problem: Return true if any pair sums to target
     * Input: [1, 2, 3, 4, 5], target = 7
     * Output: true (2+5=7 or 3+4=7)
     * 
     * Approach:
     * 1. Assume array is sorted (or sort it first)
     * 2. Use two pointers: left at start, right at end
     * 3. Calculate sum
     * 4. If sum == target: return true
     * 5. If sum < target: move left pointer right (need bigger)
     * 6. If sum > target: move right pointer left (need smaller)
     * 
     * Time: O(n) if sorted, O(n log n) with sort
     * Space: O(1)
     */
    @Override
    public boolean hasPair(int[] arr, int target) {
        //Sort, only 2 elements, store results
        System.out.println("input "+ Arrays.stream(arr).boxed().toList()+ " -target: "+target);
        Arrays.sort(arr);
        Map<String, String> results = new HashMap<>();
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            //if found, store
            if (sum == target)
                results.put((left+1) + ":" + (right+1), arr[left] + ":" + arr[right]);
            if (sum < target) { //Need to add more to reach target
                left++;
            } else {
                right--;
            }
        }
        System.out.println(results);
        return !results.isEmpty();
    }


    /**
     * PROBLEM 2: Find All Unique Pairs
     * 
     * Problem: Return all unique pairs that sum to target
     * Input: [1, 2, 3, 4, 5, 6, 7], target = 7
     * Output: [[1,6], [2,5], [3,4]]
     * 
     * Approach:
     * 1. Sort array
     * 2. Two pointers approach
     * 3. When pair found, add to result
     * 4. Skip duplicates on both sides
     * 5. Continue until pointers cross
     * 
     * Time: O(n log n), Space: O(1)
     */
    @Override
    public List<List<Integer>> findAllPairs(int[] arr, int target) {
        // TODO: Implement using two pointers with duplicate skipping
        System.out.println("input " + Arrays.stream(arr).boxed().toList() + " -target: " + target);
        //step1 - pointers and result map
        int left = 0, right = arr.length - 1;
        List<List<Integer>> results = new ArrayList<>();

        //step 2: while loop
        while (left < right) {
            int sum = arr[left] + arr[right];

            //step 3: conditions for next loop or exist + store
            if (sum == target) {
                results.add(List.of(arr[left], arr[right]));

                //skip duplicated
                while (left<right && arr[left]==arr[left+1])
                    left++;
                while (left<right && arr[right]==arr[right-1])
                    right--;
                left++;
                right--;


            } else if (sum < target) { //try next big number to reach target
                left++;
            } else {
                right--;
            }
        }

        System.out.println(results);
        return results;
    }

    /**
     * Save the pair.
     * Skip duplicate values on the left.
     * Skip duplicate values on the right.
     * Move both pointers once more.
     *
     * when target is match
     *
     * case A: sum==targer
     *     shift both left right
     *     then skip duplicates
     *
     * Case B: sum < target
     *         left++
     * Case C: sum > target
     *         right--
     *
     * @param arr
     * @param target
     * @return
     */
    public static List<List<Integer>> findAllPairs_withduplicatesInput(int[] arr, int target) {
        // TODO: Implement using two pointers with duplicate skipping
        System.out.println("input " + Arrays.stream(arr).boxed().toList() + " -target: " + target);
        //step1 - sort data + pointers and result map
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;
        List<List<Integer>> results = new ArrayList<>();

        //step 2: while loop
        while (left < right) {
            int sum = arr[left] + arr[right];

            //step 3: conditions for next loop or exist + store
            if (sum == target) {
                results.add(List.of(arr[left], arr[right]));

                // Skip duplicate values
                while (left<right && arr[left]==arr[left+1])
                    left++;
                while (left<right && arr[right]==arr[right-1])
                    right--;
                //if while no satisfied, this is minimum to do
                left++;
                right--;
            }
            //step 4: bypass duplicates

            else if (sum < target) { //try next big number to reach target
                left++;
            } else {
                right--;
            }
        }

        System.out.println(results);
        return results;
    }

    /**
     * PROBLEM 3: Count All Pairs (including duplicates)
     * 
     * Problem: Count all index pairs (i,j) where i<j and arr[i]+arr[j]=target
     * Input: [1, 2, 3, 4, 5, 2, 3], target = 7
     * Output: 4 
     * Pairs: (1,4)=[2,5], (1,6)=[2,5], (2,3)=[3,4], (5,3)... wait need i<j
     * 
     * Approach:
     * 1. Sort but keep original indices OR
     * 2. Use HashMap to track frequencies
     * 3. Handle same number case differently (combination formula)
     * 
     * Time: O(n), Space: O(n)
     */
    @Override
    public int countPairs(int[] arr, int target) {
        System.out.println("input " + Arrays.stream(arr).boxed().toList() + " -target: " + target);
        //Step 1- sort+ pointer + map

        System.out.println("After sort: "+ Arrays.stream(arr).boxed().toList());
        List<List<Integer>> list = new ArrayList<>();

        //step 2: For Loop to find all possible pairs including duplicates
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i]+arr[j]==target) {
                    list.add(List.of(arr[i],arr[j]));
                }
            }
        }
        System.out.println("Duplicate pairs inlcuded: " + list);
        return list.size();
    }

    // ========================================================================
    // THREE SUM - SORTED APPROACH
    // ========================================================================

    /**
     * PROBLEM 4: Three Sum - Find All Unique Triplets
     * 
     * Problem: Return all unique triplets that sum to target
     * Input: [-1, 0, 1, 2, -1, -4], target = 0
     * Output: [[-1, -1, 2], [-1, 0, 1]]
     * 
     * Approach:
     * 1. Sort array
     * 2. For each element, use two-pointer for remaining
     * 3. Fix one element, find pair sum for rest
     * 4. Skip duplicates at all levels
     * 5. Optimize: if num > 0 and target > 0, impossible
     * 
     * Complexity:
     * Time: O(n²) - loop N times, two pointer O(n) for each
     * Space: O(1) excluding output
     */
    @Override
    public List<List<Integer>> findAllTriplets(int[] arr, int target) {
        // TODO: Implement three sum using sorted + two pointers
        // Hint: Outer loop fixes one number, inner loop uses two pointers
        System.out.println("input " + Arrays.stream(arr).boxed().toList() + " -target: " + target);
        //step 1: sort+ pointer+ result
        int left = 0, right = arr.length - 1;
        Arrays.sort(arr);
        List<List<Integer>> results = new ArrayList<>();

        //step 2: 3 elements in each array
        for (int i = 0; i < arr.length - 2; i++) {
            left = i + 1;
            right = arr.length - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (sum == target) {
                    results.add(List.of(arr[i], arr[left], arr[right]));

                    while (left < right && arr[left] == arr[left + 1]) {
                        left++;
                    }
                    while (left < right && arr[right] == arr[right - 1]) {
                        right--;
                    }

                    //pointer upgrade
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        System.out.println("Triplets: " + results);
        return results;
    }

    /**
     * PROBLEM 5: Count All Triplets
     * 
     * Problem: Count triplets that sum to target
     * Input: [1, 2, 3, 4, 5], target = 9
     * Output: 2 triplets
     * Possible: (1,3,5), (2,3,4)
     * 
     * Approach: Three nested loops or reduce to two-sum
     * 
     * Time: O(n²), Space: O(1)
     */
    @Override
    public int countTriplets(int[] arr, int target) {
        // TODO: Implement triplet counting
        // Hint: Similar to findAllTriplets but count instead of store
        System.out.println("input " + Arrays.stream(arr).boxed().toList() + " -target: " + target);
        //Step 1: assignment
        int left, right;
        List<List<Integer>> results = new ArrayList<>();

        for (int i = 0; i < arr.length - 2; i++) {
            left = i + 1;
            right = arr.length-1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == target) {
                    results.add(List.of(arr[i], arr[left], arr[right]));
                    while (left < right && arr[left] == arr[left + 1]) {
                        left++;
                    }
                    while (left < right && arr[right] == arr[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        System.out.println("Size of Triples: "+results.size());
        return results.size();
    }

    // ========================================================================
    // COMBINATION SUM (Sorted approach would use iteration)
    // ========================================================================

    /**
     * NOTE: Combination Sum typically uses Backtracking, not Two Pointers
     * But including for interface completeness
     */
    @Override
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        System.out.println("input " + Arrays.stream(candidates).boxed().toList() + " -target: " + target);
        return null;
    }

    @Override
    public List<List<Integer>> combinationSumII(int[] candidates, int target) {
        System.out.println("input " + Arrays.stream(candidates).boxed().toList() + " -target: " + target);
        return null;
    }

    @Override
    public List<List<Integer>> generateCombinations(int n, int k) {
        return null;
     }

    // ========================================================================
    // MAIN - TESTING
    // ========================================================================

    public static void main(String[] args) {
        SortedPairProblems solver = new SortedPairProblems();

        // Test 1: hasPair
        int[] arr1 = {1, 2, 3, 4, 5};
        System.out.println("Test hasPair: " + solver.hasPair(arr1, 7));

        // Test 2: findAllPairs
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Test findAllPairs: " + solver.findAllPairs(arr2, 7));

        // Test 2: findAllPairs
        int[] arrr2 = {1,1,2, 2, 3, 4, 5,5, 6, 7};
        System.out.println("Test findAllPairs: " + findAllPairs_withduplicatesInput(arrr2, 7));

        // Test 3: countPairs
        int[] arr3 = {1, 2,2,2, 3, 4, 5, 2, 3,3};
        System.out.println("Test countPairs: " + solver.countPairs(arr3, 7));

        // Test 4: findAllTriplets
        int[] arr4 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Test findAllTriplets: " + solver.findAllTriplets(arr4, 0));

        // Test 5: countTriplets
        int[] arr5 = {1, 2, 3, 4, 5};
        System.out.println("Test countTriplets: " + solver.countTriplets(arr5, 9));
    }
}

