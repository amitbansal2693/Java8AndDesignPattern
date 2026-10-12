package com.algorithm.practice.array.subarray_substring;

import java.util.*;

/**
 * ============================================================================
 * VARIABLE SLIDING WINDOW PROBLEMS
 * ============================================================================
 * 
 * PATTERN: Window size CHANGES based on CONDITIONS
 * 
 * ALGORITHM (Two Pointers):
 * 1. Expand window by moving right pointer
 * 2. Add element to window
 * 3. Check if window is valid
 * 4. If invalid, shrink from left
 * 5. Update result
 * 6. Move right pointer
 * 
 * STATE TO MAINTAIN:
 * - HashMap/HashSet for character frequencies or tracking
 * - Integer counters for conditions
 * - Variables for min/max length, result substring
 * 
 * TEMPLATE:
 * int left = 0;
 * for (int right = 0; right < n; right++) {
 *     // Expand: add element at right
 *     
 *     while (window_invalid) {
 *         // Shrink: remove element at left
 *         left++;
 *     }
 *     
 *     // Update result
 * }
 * 
 * WHY TWO POINTERS:
 * - Can't just iterate K elements (window size unknown)
 * - Need dynamic adjustment of window
 * - Each element visited at most twice (left/right pointers)
 * - Time: O(n) because each pointer moves at most n times total
 * 
 * ============================================================================
 */
@SuppressWarnings("ALL")
public class VariableWindowProblems implements SlidingWindowInterface {

    // ========================================================================
    // PROBLEM 6: Longest Substring Without Repeating Characters
    // ========================================================================

    /**
     * Problem: Find longest substring with no duplicate characters
     * 
     * Input: "abcabcbb"
     * Output: 3 ("abc" or "bca" or "cab")
     * 
     * Input: "bbbbb"
     * Output: 1 ("b")
     * 
     * Input: "pwwkew"
     * Output: 3 ("wke")
     * 
     * Approach (Variable Window with HashSet):
     * 1. Use HashSet to track characters in current window
     * 2. Use left and right pointers.
     * 3. Expand: add s[right] to window
     * 4. While duplicate exists:
     *    - Remove s[left] from set
     *    - Move left pointer right
     * 5. Update max length = right - left + 1
     * 6. Move right pointer
     * 
     * State:
     * - Set<Character>: current characters in window
     * - int maxLength: track longest so far
     * - int left, right: window boundaries
     * 
     * Time: O(n) - each char visited at most twice
     * Space: O(min(n, charset_size)) ≈ O(26) for lowercase letters
     * 
     * Example trace: "abcabcbb"
     * right=0: add 'a', set={'a'}, maxLen=1
     * right=1: add 'b', set={'a','b'}, maxLen=2
     * right=2: add 'c', set={'a','b','c'}, maxLen=3
     * right=3: add 'a'(dup), remove 'a'(left=1), set={'b','c','a'}, maxLen=3
     * right=4: add 'b'(dup), remove 'b'(left=2), set={'c','a','b'}, maxLen=3
     * right=5: add 'c'(dup), remove 'c'(left=3), set={'a','b','c'}, maxLen=3
     * right=6: add 'b'(dup), remove 'a'(left=4), remove 'b'(left=5), set={'c','b'}, maxLen=3
     * right=7: add 'b'(dup), remove 'c'(left=6), set={'b','b'}? No, set={'b'}
     * 
     * Key insight: Remove duplicates by shrinking left until duplicate cleared
     */
    @Override
    public int longestUniqueSubstring(String s) {
        // TODO: Implement using variable window with HashSet
        // Hint: Shrink window when duplicate found, track max length
        throw new UnsupportedOperationException("TODO: Implement longestUniqueSubstring");
    }

    // ========================================================================
    // PROBLEM 7: Longest Substring with At Most K Distinct Characters
    // ========================================================================

    /**
     * Problem: Find longest substring with at most K distinct characters
     * 
     * Input: "eceba", K=2
     * Output: 3 ("ece")
     * Substrings with ≤2 distinct:
     * "e"(1), "ec"(2), "ece"(2) ✓, "eceb"(3-no), "ce"(2), "ceb"(3-no), "ebe"(2), "eb"(2), "ba"(2)
     * 
     * Input: "aa", K=1
     * Output: 2 ("aa")
     * 
     * Approach (Variable Window with HashMap):
     * 1. Use HashMap to count character frequencies
     * 2. Expand: add s[right], update frequency
     * 3. While distinct_count > k:
     *    - Remove s[left], decrease frequency
     *    - If frequency becomes 0, remove from map
     *    - Move left pointer right
     * 4. Update max length
     * 5. Move right pointer
     * 
     * State:
     * - Map<Character, Integer>: freq of chars in window
     * - int maxLength: longest substring so far
     * - int left, right: window boundaries
     * 
     * Time: O(n) - each char visited at most twice
     * Space: O(k) - at most k+1 chars in map (because we shrink when > k)
     * 
     * Example trace: "eceba", K=2
     * right=0: add 'e', freq={'e':1}, distinct=1, maxLen=1
     * right=1: add 'c', freq={'e':1,'c':1}, distinct=2, maxLen=2
     * right=2: add 'e', freq={'e':2,'c':1}, distinct=2, maxLen=3
     * right=3: add 'b', freq={'e':2,'c':1,'b':1}, distinct=3 > 2
     *          remove 'e'(left=0), freq={'e':1,'c':1,'b':1}, distinct=3
     *          remove 'e'(left=1), freq={'c':1,'b':1}, distinct=2, maxLen=3
     * right=4: add 'a', freq={'c':1,'b':1,'a':1}, distinct=3 > 2
     *          remove 'c'(left=2), freq={'b':1,'a':1}, distinct=2, maxLen=3
     */
    @Override
    public int longestSubstringKDistinct(String s, int k) {
        // TODO: Implement at most K distinct characters
        // Hint: Use HashMap to count frequencies, shrink when count > k
        throw new UnsupportedOperationException("TODO: Implement longestSubstringKDistinct");
    }

    // ========================================================================
    // PROBLEM 8: Minimum Window Substring
    // ========================================================================

    /**
     * Problem: Find smallest window in source containing all chars of target
     * 
     * Input: source="ADOBECODEBANC", target="ABC"
     * Output: "BANC"
     * Explanation: "BANC" is smallest window containing A, B, C
     * 
     * Input: source="a", target="aa"
     * Output: "" (impossible)
     * 
     * Approach (Variable Window with HashMap):
     * 1. Build frequency map of target characters
     * 2. Expand window with right pointer
     * 3. Track how many target chars have been matched
     * 4. When all target chars matched:
     *    - Record window length
     *    - Shrink from left to find smaller window
     * 5. Return smallest window found
     * 
     * State:
     * - Map<Character, Integer>: required freq of each target char
     * - Map<Character, Integer>: freq of chars in current window
     * - int minLen, minStart: track minimum window found
     * - int formed: how many unique chars with required freq
     * - int required: unique chars needed (= target.size() unique chars)
     * 
     * Time: O(n + m) where n=source.length, m=target.length
     * Space: O(charset_size) ≈ O(26) for lowercase
     * 
     * NOTE: This is complex - may skip for first practice
     */
    @Override
    public String minWindowSubstring(String source, String target) {
        // TODO: Implement minimum window substring
        // Hint: Track both required and current character frequencies
        throw new UnsupportedOperationException("TODO: Implement minWindowSubstring");
    }

    // ========================================================================
    // PROBLEM 9: Minimum Length Subarray with Sum >= Target
    // ========================================================================

    /**
     * Problem: Find smallest subarray whose sum >= target
     * 
     * Input: [2, 3, 1, 2, 4, 3], target=7
     * Output: 2 (subarray [4,3])
     * Subarrays: [2,3,1]=6, [2,3,1,2]=8→len=4, [3,1,2]=6, [3,1,2,4]=10→len=4,
     *            [1,2,4]=7 ✓→len=3, [2,4]=6, [4,3]=7 ✓→len=2 minLen=2,
     *            [3]=3, [4]=4, [3]=3... => Answer: 2
     * 
     * Approach (Variable Window with Sum Tracking):
     * 1. Keep running sum of current window
     * 2. Expand: add arr[right], increase sum
     * 3. While sum >= target:
     *    - Update min length
     *    - Shrink: remove arr[left], decrease sum
     *    - Move left
     * 4. Return min length
     * 
     * Key insight:
     * - Sorted/positive array: shrinking always decreases sum
     * - If sum >= target at window[left..right], any window[left..right+k] also >= target
     * - So we always want to shrink left to find minimum
     * 
     * State:
     * - int sum: current window sum
     * - int minLen: smallest length found
     * - int left, right: window boundaries
     * 
     * Time: O(n) - each element visited once by left, once by right
     * Space: O(1)
     * 
     * Example trace: [2,3,1,2,4,3], target=7
     * right=0: sum=2, not >=7
     * right=1: sum=5, not >=7
     * right=2: sum=6, not >=7
     * right=3: sum=8 >=7, minLen=4, shrink: sum=6, left=1
     * right=4: sum=10 >=7, minLen=4, shrink: sum=7, minLen=3, shrink: sum=6, left=3
     * right=5: sum=9 >=7, minLen=3, shrink: sum=7, minLen=2, shrink: sum=3, left=5
     * End: minLen=2
     */
    @Override
    public int minLengthSubarrayWithSum(int[] arr, int target) {
        // TODO: Implement minimum length subarray with sum >= target
        // Hint: Expand right, shrink left when sum >= target
        throw new UnsupportedOperationException("TODO: Implement minLengthSubarrayWithSum");
    }

    // ========================================================================
    // PROBLEM 10: Maximum Length Subarray with Sum <= Target
    // ========================================================================

    /**
     * Problem: Find longest subarray with sum <= target
     * 
     * Input: [1, 4, 3, 2, 5], target=10
     * Output: 4 (subarray [1,4,3,2])
     * Subarrays: [1]=1, [1,4]=5, [1,4,3]=8, [1,4,3,2]=10 ✓→len=4,
     *            [4]=4, [4,3]=7, [4,3,2]=9, [4,3,2,5]=14, [3]=3, [3,2]=5,
     *            [3,2,5]=10 ✓, [2]=2, [2,5]=7, [5]=5
     * maxLen = 4
     * 
     * Approach (Variable Window with Sum Tracking):
     * 1. Keep running sum
     * 2. Expand: add arr[right]
     * 3. While sum > target:
     *    - Shrink: remove arr[left]
     *    - Move left
     * 4. Update max length
     * 5. Move right
     * 
     * Difference from problem 9:
     * - We want MAXIMUM length (not minimum)
     * - Condition is sum <= target (not >=)
     * - Shrink only when exceeds target
     * 
     * Time: O(n), Space: O(1)
     */
    @Override
    public int maxLengthSubarrayWithSum(int[] arr, int target) {
        // TODO: Implement maximum length subarray with sum <= target
        // Hint: Track max length, shrink only when sum > target
        throw new UnsupportedOperationException("TODO: Implement maxLengthSubarrayWithSum");
    }

    // ========================================================================
    // Fixed window methods (not applicable here)
    // ========================================================================

    @Override
    public int maxSumWindow(int[] arr, int k) {
        throw new UnsupportedOperationException("Use FixedWindowProblems");
    }

    @Override
    public int minSumWindow(int[] arr, int k) {
        throw new UnsupportedOperationException("Use FixedWindowProblems");
    }

    @Override
    public List<Double> averageWindow(int[] arr, int k) {
        throw new UnsupportedOperationException("Use FixedWindowProblems");
    }

    @Override
    public int countWindowsWithSum(int[] arr, int k, int target) {
        throw new UnsupportedOperationException("Use FixedWindowProblems");
    }

    @Override
    public List<Integer> maxElementInEachWindow(int[] arr, int k) {
        throw new UnsupportedOperationException("Use FixedWindowProblems");
    }

    // ========================================================================
    // MAIN - TESTING
    // ========================================================================

    public static void main(String[] args) {
        VariableWindowProblems solver = new VariableWindowProblems();

        // Test 1: longestUniqueSubstring
        System.out.println("Test longestUniqueSubstring \"abcabcbb\": " 
            + solver.longestUniqueSubstring("abcabcbb"));

        // Test 2: longestSubstringKDistinct
        System.out.println("Test longestSubstringKDistinct \"eceba\", K=2: " 
            + solver.longestSubstringKDistinct("eceba", 2));

        // Test 3: minWindowSubstring
        System.out.println("Test minWindowSubstring \"ADOBECODEBANC\", \"ABC\": " 
            + solver.minWindowSubstring("ADOBECODEBANC", "ABC"));

        // Test 4: minLengthSubarrayWithSum
        int[] arr1 = {2, 3, 1, 2, 4, 3};
        System.out.println("Test minLengthSubarrayWithSum [2,3,1,2,4,3], target=7: " 
            + solver.minLengthSubarrayWithSum(arr1, 7));

        // Test 5: maxLengthSubarrayWithSum
        int[] arr2 = {1, 4, 3, 2, 5};
        System.out.println("Test maxLengthSubarrayWithSum [1,4,3,2,5], target=10: " 
            + solver.maxLengthSubarrayWithSum(arr2, 10));
    }
}

