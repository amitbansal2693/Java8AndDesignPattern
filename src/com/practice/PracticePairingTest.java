package com.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * IntStream.range(0, n) generates a stream of indexes.
 * Then stream operations apply on each index.
 */
public class  PracticePairingTest {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int target = 7;

        // Example solved call: prints all pairs whose sum is target.
        System.out.println("=== findPairs (print all pairs) ===");
        findPairs(arr, target);

        // Assignment stubs (uncomment as you solve each method).
        System.out.println("\n=== hasPair (check if pair exists) ===");
        System.out.println("Has pair: " + hasPair(arr, target));
        
        System.out.println("\n=== countPairs (count total pairs) ===");
        System.out.println("Total pairs: " + countPairs(arr, target));
        
        System.out.println("\n=== findPairIndices (find first pair's indices) ===");
        System.out.println("Pair indices: " + findPairIndices(arr, target));
        
        System.out.println("\n=== findUniquePairsTwoPointer (unique pairs) ===");
        System.out.println("Unique pairs: " + findUniquePairsTwoPointer(arr, target));
        
        System.out.println("\n=== findTriplets (all triplets) ===");
        System.out.println("Triplets (sum=12): " + findTriplets(arr, 12));
        
        System.out.println("\n=== closestPairToTarget (closest sum) ===");
        System.out.println("Closest pair to 10: " + closestPairToTarget(arr, 10));
    }

    /**
     * Input: int array and target sum.
     * Output: prints each valid pair value in format "a + b = target".
     * Solve: basic O(n^2) pair-sum approach using nested loops.
     */
    private static void findPairs(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    System.out.println(arr[i] + " + " + arr[j] + " = " + target);
                }
            }
        }
        IntStream.range(0, arr.length).forEach(i->
        {
            if(arr[i] + arr[i + 1] == target) {
                System.out.println(arr[i] + " + " + arr[i + 1] + " = " + target);
            }
        });
    }

    /**
     * Input: int array and target sum.
     * Output: true if at least one pair exists, else false.
     * Solve: implement optimal O(n) approach using HashSet.
     */
    private static boolean hasPair(int[] arr, int target) {
        // Use HashSet to store numbers we've seen
        Set<Integer> seen = new HashSet<>();
        
        // Iterate through array once
        for (int num : arr) {
            // Calculate what value we need to reach target
            int complement = target - num;
            
            // If complement exists in the set, we found a pair
            if (seen.contains(complement)) {
                return true;
            }
            
            // Add current number to the seen set
            seen.add(num);
        }
        
        // No pair found
        return false;
    }

    /**
     * Task: Count all unique index pairs (i, j) such that arr[i] + arr[j] = target.
     * Input: int array and target sum.
     * Output: number of index pairs (i, j), i < j, whose values sum to target.
     * Solve: handle duplicates correctly and avoid counting same index pair twice.
     *
     * TWO APPROACHES:
     * 1. HashMap Frequency Approach (Current): O(n) time, O(n) space
     *    - Count frequency of each unique value
     *    - For each value, check if complement exists and calculate combinations
     * 
     * 2. Sort + Two-Pointer Approach: O(n log n) time, O(1) space
     *    - Sort the array
     *    - Use two pointers to find pairs
     *    - Skip duplicates while counting
     */
    private static int countPairs(int[] arr, int target) {
        // APPROACH 1: HashMap Frequency (efficient for counting)
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        
        // Count frequency of each number in the array
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        int pairCount = 0;
        
        // Iterate through the frequency map
        for (int num : frequencyMap.keySet()) {
            int complement = target - num;
            
            // Check if complement exists in the map
            if (frequencyMap.containsKey(complement)) {
                
                // Special case: if num == complement (e.g., target=10, num=5)
                // We need to count pairs from duplicates: C(n,2) = n*(n-1)/2
                // 
                // EXPLANATION:
                // When num == complement, it means: target - num = num, so 2*num = target
                // 
                // Example 1: arr = [5, 5, 5], target = 10
                //   - num = 5, complement = 5, freq = 3
                //   - Possible pairs (indices): (0,1), (0,2), (1,2) = 3 pairs
                //   - Formula: C(3,2) = 3 * 2 / 2 = 3 ✓
                // 
                // Example 2: arr = [2, 2, 2, 2], target = 4
                //   - num = 2, complement = 2, freq = 4
                //   - Possible pairs: (0,1), (0,2), (0,3), (1,2), (1,3), (2,3) = 6 pairs
                //   - Formula: C(4,2) = 4 * 3 / 2 = 6 ✓
                // 
                // The formula C(n,2) counts combinations of picking 2 elements from n:
                // - It avoids double counting (pair (i,j) is same as (j,i))
                // - It ensures i < j constraint
                // 
                if (num == complement) {
                    int freq = frequencyMap.get(num);
                    pairCount += (freq * (freq - 1)) / 2;
                } 
                // Normal case: num < complement to avoid double counting
                // (we count each pair only once)
                else if (num < complement) {
                    int freq1 = frequencyMap.get(num);
                    int freq2 = frequencyMap.get(complement);
                    pairCount += freq1 * freq2;
                }
            }
        }
        
        return pairCount;
    }
    
    /**
     * ALTERNATIVE APPROACH: Sort + Two-Pointer (more intuitive for beginners)
     * This method counts pairs using sorted array and two-pointer technique.
     */
    private static int countPairsSortedApproach(int[] arr, int target) {
        // Sort the array
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        
        int pairCount = 0;
        int left = 0;
        int right = sorted.length - 1;
        
        // Two-pointer approach
        while (left < right) {
            int sum = sorted[left] + sorted[right];
            
            if (sum == target) {
                // Found a pair
                // Handle duplicates on left side
                int leftValue = sorted[left];
                int leftCount = 0;
                while (left < right && sorted[left] == leftValue) {
                    leftCount++;
                    left++;
                }
                
                // Handle duplicates on right side
                int rightValue = sorted[right];
                int rightCount = 0;
                while (left <= right && sorted[right] == rightValue) {
                    rightCount++;
                    right--;
                }
                
                // If left and right values are same (e.g., 5+5=10), use combination
                if (leftValue == rightValue) {
                    pairCount += (leftCount * (leftCount - 1)) / 2;
                } else {
                    // Different values: multiply frequencies
                    pairCount += leftCount * rightCount;
                }
                
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return pairCount;
    }

    /**
     * Here we need to find the indices of the first pair that sums to the target.
     * Approach:
     *1. Use a HashMap to store each number and its index as we iterate through the array.
     *2. For each number, calculate its complement (target - current number).
     *3. Check if the complement exists in the HashMap.
     *4. If it does, return the indices of the complement and the current number.
     *
     * Input: int array and target sum.
     * Output: first matching pair indices as list [i, j], or empty list if not found.
     * Solve: return positions instead of values.
     */
    private static List<Integer> findPairIndices(int[] arr, int target) {
        // Use HashMap to store value -> index mapping
        Map<Integer, Integer> indexMap = new HashMap<>();
        
        // Iterate through array to find first pair
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int complement = target - num;
            
            // Check if complement exists in map
            if (indexMap.containsKey(complement)) {
                // Found a pair! Return indices [complementIndex, currentIndex]
                int complementIndex = indexMap.get(complement);
                return Arrays.asList(complementIndex, i);
            }
            
            // Store current number and its index
            indexMap.put(num, i);
        }
        
        // No pair found
        return new ArrayList<>();
    }

    /**
     * Input: int array and target sum.
     * Output: list of unique value pairs like ["1,6", "2,5"].
     * Solve: sort copy of array + two-pointer approach; skip duplicates.
     */
    private static List<String> findUniquePairsTwoPointer(int[] arr, int target) {
        // Create a sorted copy to enable two-pointer technique
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        
        List<String> result = new ArrayList<>();
        
        // Initialize two pointers: one at start, one at end
        int left = 0;
        int right = sorted.length - 1;
        
        // Two-pointer approach: move pointers towards each other
        while (left < right) {
            int sum = sorted[left] + sorted[right];
            
            if (sum == target) {
                // Found a valid pair! Add to result
                result.add(sorted[left] + "," + sorted[right]);
                
                // Skip duplicates on the left side, since that combination is already counted
                // Keep moving left pointer while the next value is the same
                while (left < right && sorted[left] == sorted[left + 1]) {
                    left++;
                }
                
                // Skip duplicates on the right side
                // Keep moving right pointer while the previous value is the same
                while (left < right && sorted[right] == sorted[right - 1]) {
                    right--;
                }
                
                // Move both pointers to continue searching for more pairs
                left++;
                right--;
                
            } else if (sum < target) {
                // Sum is too small, we need a larger number
                // Move left pointer to the right to increase the sum
                left++;
                
            } else {
                // Sum is too large, we need a smaller number
                // Move right pointer to the left to decrease the sum
                right--;
            }
        }
        
        return result;
    }

    /**
     * Input: int array and target sum.
     * Output: all triplets [a, b, c] such that a + b + c = target.
     * Solve: reduce 3-sum complexity using sorting + two-pointer inside loop.
     */
    private static List<List<Integer>> findTriplets(int[] arr, int target) {
        // Create sorted copy for easier duplicate handling
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        
        List<List<Integer>> result = new ArrayList<>();
        
        // Loop through array, fixing one element at a time
        for (int i = 0; i < sorted.length - 2; i++) {
            // Skip duplicate values for the first element
            if (i > 0 && sorted[i] == sorted[i - 1]) {
                continue;
            }
            
            // For remaining elements, use two-pointer approach
            int left = i + 1;
            int right = sorted.length - 1;
            int twoSum = target - sorted[i];
            
            // Two-pointer to find pairs that sum to twoSum
            while (left < right) {
                int sum = sorted[left] + sorted[right];
                
                if (sum == twoSum) {
                    // Found a valid triplet!
                    result.add(Arrays.asList(sorted[i], sorted[left], sorted[right]));
                    
                    // Skip duplicates on left side
                    while (left < right && sorted[left] == sorted[left + 1]) {
                        left++;
                    }
                    
                    // Skip duplicates on right side
                    while (left < right && sorted[right] == sorted[right - 1]) {
                        right--;
                    }
                    
                    left++;
                    right--;
                    
                } else if (sum < twoSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }

    /**
     * Input: int array and target sum.
     * Output: pair [a, b] whose sum is closest to target.
     * Solve: optimize for closest sum, not exact sum.
     */
    private static List<Integer> closestPairToTarget(int[] arr, int target) {
        // Create sorted copy
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        
        List<Integer> result = new ArrayList<>();
        
        // Initialize two pointers and track best pair
        int left = 0;
        int right = sorted.length - 1;
        int minDifference = Integer.MAX_VALUE;
        int bestLeft = -1, bestRight = -1;
        
        // Two-pointer approach to find closest pair
        while (left < right) {
            int sum = sorted[left] + sorted[right];
            int difference = Math.abs(sum - target);
            
            // Update best pair if this sum is closer to target
            if (difference < minDifference) {
                minDifference = difference;
                bestLeft = sorted[left];
                bestRight = sorted[right];
            }
            
            // Move pointers based on sum
            if (sum < target) {
                // Sum too small, move left pointer right to increase sum
                left++;
            } else if (sum > target) {
                // Sum too large, move right pointer left to decrease sum
                right--;
            } else {
                // Exact match found! This is as close as it gets
                return Arrays.asList(sorted[left], sorted[right]);
            }
        }
        
        // Return the pair with closest sum to target
        if (bestLeft != -1) {
            result.add(bestLeft);
            result.add(bestRight);
        }
        
        return result;
    }
}
