package com.algorithm.stackqueue;

import java.util.*;

/**
 * ============================================================================
 * PATTERN: Stack & Monotonic Stack
 * ============================================================================
 * 
 * PATTERN IDENTIFICATION:
 * 
 * REGULAR STACK:
 * - Matching/nested structures (parentheses, brackets)
 * - Undo operations
 * - Reverse processing
 * - LIFO (Last In First Out) behavior needed
 * 
 * MONOTONIC STACK:
 * - Find next greater/smaller element
 * - Find previous greater/smaller element
 * - Maintain increasing/decreasing order
 * - Temperature increase/decrease problems
 * 
 * WHEN TO USE:
 * ✓ "Valid parentheses"
 * ✓ "Next greater element"
 * ✓ "Next smaller element"
 * ✓ "Previous greater element"
 * ✓ "Daily temperatures"
 * ✓ "Stock span problem"
 * 
 * KEY INSIGHT FOR MONOTONIC STACK:
 * - Keep stack in monotonic order (increasing or decreasing)
 * - When current element breaks order, pop elements
 * - Popped element has current as "next greater/smaller"
 * - Element below popped element is "previous greater/smaller"
 * 
 * ============================================================================
 */
public class StackAndMonotonicPatterns {

    public static void main(String[] args) {
        System.out.println("=================== REGULAR STACK EXAMPLES ===================\n");

        // Example 1: Valid Parentheses
        System.out.println("EXAMPLE 1: Valid Parentheses");
        testValidParentheses();

        System.out.println("\n=================== MONOTONIC STACK EXAMPLES ===================\n");

        // Example 2: Next Greater Element
        System.out.println("EXAMPLE 2: Next Greater Element");
        int[] arr1 = {1, 3, 2, 4, 5, 2, 1};
        nextGreaterElement(arr1);

        // Example 3: Daily Temperatures
        System.out.println("\nEXAMPLE 3: Days Until Warmer Temperature");
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        dailyTemperatures(temps);

        // Example 4: Stock Span Problem
        System.out.println("\nEXAMPLE 4: Stock Span Problem");
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        stockSpan(prices);
    }

    // ========================================================================
    // REGULAR STACK EXAMPLES
    // ========================================================================

    /**
     * EXAMPLE 1: Valid Parentheses
     * 
     * Problem: Check if string contains valid parentheses
     * Conditions: Every opening bracket has matching closing bracket in correct order
     * Input: "([{}])" → true
     *        "({[}])" → false (mismatched order)
     *        "([)]"   → false (wrong nesting)
     * 
     * APPROACH:
     * 1. Use stack to store opening brackets
     * 2. For each closing bracket, check if it matches top of stack
     * 3. If all matched and stack empty at end, valid
     * 
     * Why stack works:
     * - LIFO nature of stack mirrors nesting structure
     * - Most recently opened bracket should be closed first
     * - Stack naturally tracks this
     * 
     * Time: O(n), Space: O(n) for stack
     */
    public static void testValidParentheses() {
        String[] testCases = {"()", "([{}])", "({[}])", "([)]", "{[}"};

        for (String test : testCases) {
            boolean valid = isValidParentheses(test);
            System.out.println("  \"" + test + "\" → " + (valid ? "VALID ✓" : "INVALID ✗"));
        }
    }

    private static boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put('}', '{');
        pairs.put(']', '[');

        for (char c : s.toCharArray()) {
            if (pairs.containsKey(c)) {
                // It's a closing bracket
                if (stack.isEmpty() || stack.peek() != pairs.get(c)) {
                    return false;
                }
                stack.pop();
            } else {
                // It's an opening bracket
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    // ========================================================================
    // MONOTONIC STACK EXAMPLES
    // ========================================================================

    /**
     * EXAMPLE 2: Next Greater Element
     * 
     * Problem: For each element, find the next greater element to its right
     * Input: [1, 3, 2, 4, 5, 2, 1]
     * Output:
     *   1 → 3 (next greater)
     *   3 → 4 (next greater)
     *   2 → 4 (next greater)
     *   4 → 5 (next greater)
     *   5 → -1 (no greater element to right)
     *   2 → -1 (no greater element to right)
     *   1 → -1 (no greater element to right)
     * 
     * NAIVE APPROACH: O(n²)
     * - For each element, look right until finding greater
     * - Inefficient for large arrays
     * 
     * MONOTONIC STACK APPROACH: O(n)
     * 1. Traverse array from RIGHT to LEFT (why? to build result for each position)
     * 2. Maintain stack of elements in DECREASING order
     * 3. When current > stack.top(), pop (current is their next greater)
     * 4. Result[current] = top of stack (after popping smaller)
     * 5. Push current element
     * 
     * Why it works:
     * - Stack maintains elements that still need a "next greater"
     * - When we find greater element, it's the answer for all smaller ones below it
     * - Decreasing order ensures we can skip checking smaller elements
     * 
     * Visualization for [1, 3, 2, 4, 5, 2, 1]:
     * Processing right to left:
     * 1: stack=[1], result[1] = -1
     * 2: 2>1? pop 1. stack=[2], result[2] = -1
     * 5: 5>2? pop 2,stack=[5], result[5] = -1
     * 4: 4>5? no. stack=[5,4], result[4] = 5
     * 2: 2>4? no. stack=[5,4,2], result[2] = 4
     * 3: 3>2? pop 2. 3>4? no. stack=[5,4,3], result[3] = 4
     * 1: 1>3? no. stack=[5,4,3,1], result[1] = 3
     * 
     * Time: O(n), Space: O(n) for stack
     */
    public static void nextGreaterElement(int[] arr) {
        System.out.println("Array: " + Arrays.toString(arr));
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>(); // Stack of indices

        System.out.println("Processing R→L (right to left):");

        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements smaller than current
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                int popped = stack.pop();
                System.out.println("  arr[" + popped + "]=" + arr[popped] + " <= arr[" + i + "]=" + arr[i] + " → POP");
            }

            // Current element's next greater is top of stack (if exists)
            if (stack.isEmpty()) {
                result[i] = -1;
                System.out.println("  arr[" + i + "]=" + arr[i] + " → no next greater = -1");
            } else {
                result[i] = arr[stack.peek()];
                System.out.println("  arr[" + i + "]=" + arr[i] + " → next greater = " + result[i]);
            }

            stack.push(i);
        }

        System.out.println("\nResult: " + Arrays.toString(result));
    }

    /**
     * EXAMPLE 3: Daily Temperatures (Days Until Warmer)
     * 
     * Problem: For each day, find how many days until a warmer temperature
     * Input: [73, 74, 75, 71, 69, 72, 76, 73]
     * Output: [1, 1, 4, 2, 1, 1, 0, 0]
     * 
     * Explanation:
     * Day 0 (73°): Next warmer is 74° on day 1 → 1 day
     * Day 1 (74°): Next warmer is 75° on day 2 → 1 day
     * Day 2 (75°): Next warmer is 76° on day 6 → 4 days
     * ...
     * 
     * APPROACH (Monotonic Stack):
     * - Similar to "Next Greater Element"
     * - Stack stores indices of temperatures in DECREASING order
     * - When warmer found, calculate difference between indices
     * 
     * Time: O(n), Space: O(n)
     */
    public static void dailyTemperatures(int[] temps) {
        System.out.println("Temperatures: " + Arrays.toString(temps));
        int n = temps.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        System.out.println("Processing L→R (left to right):");

        for (int i = 0; i < n; i++) {
            // Pop smaller temperatures (we found warmer day for them)
            while (!stack.isEmpty() && temps[stack.peek()] < temps[i]) {
                int prev = stack.pop();
                result[prev] = i - prev;
                System.out.println("  Day " + prev + " (" + temps[prev] + "°): Next warmer found on day " + i + 
                                  " (" + temps[i] + "°) → " + result[prev] + " days");
            }

            stack.push(i);
        }

        // Remaining elements in stack have no warmer day
        while (!stack.isEmpty()) {
            int day = stack.pop();
            System.out.println("  Day " + day + " (" + temps[day] + "°): No warmer day found → 0 days");
        }

        System.out.println("\nResult: " + Arrays.toString(result));
    }

    /**
     * EXAMPLE 4: Stock Span Problem
     * 
     * Problem: For each day, calculate the span (consecutive days with price <= current day's price)
     * Input: [100, 80, 60, 70, 60, 75, 85]
     * Output: [1, 1, 1, 2, 1, 4, 6]
     * 
     * Explanation:
     * Day 0 (100): Price is max → span = 1 (just itself)
     * Day 1 (80):  Previous (100) > 80 → span = 1 (just itself)
     * Day 2 (60):  Previous prices > 60 → span = 1 (just itself)
     * Day 3 (70):  70 >= 60 (day 2) → span = 2 (day 2,3)
     * Day 4 (60):  70 (day 3) > 60 → span = 1 (just itself)
     * Day 5 (75):  75 >= 60,70,60 but < 80 → span = 4 (days 2,3,4,5)
     * Day 6 (85):  85 >= all previous → span = 6 (from day 1 to 6)
     * 
     * APPROACH (Monotonic Stack with Index Tracking):
     * - Stack stores indices of days in DECREASING price order
     * - When current price >= stack.top(), it's part of the span
     * - Span = current_index - previous_greater_index
     * - Previous greater index is what remains in stack
     * 
     * Time: O(n), Space: O(n)
     */
    public static void stockSpan(int[] prices) {
        System.out.println("Stock prices: " + Arrays.toString(prices));
        int n = prices.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>();

        System.out.println("Calculating span:");

        for (int i = 0; i < n; i++) {
            // Pop indices with prices <= current price
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                int popped = stack.pop();
                System.out.println("  Day " + popped + " (price=" + prices[popped] + 
                                  ") <= Day " + i + " (price=" + prices[i] + ") → POP");
            }

            // Span is from previous greater to current
            if (stack.isEmpty()) {
                // No previous greater element, span goes from start
                span[i] = i + 1;
                System.out.println("  Day " + i + " (price=" + prices[i] + "): No previous greater → span = " + span[i]);
            } else {
                // Previous greater is at top of stack
                span[i] = i - stack.peek();
                System.out.println("  Day " + i + " (price=" + prices[i] + "): Previous greater at day " + 
                                  stack.peek() + " → span = " + span[i]);
            }

            stack.push(i);
        }

        System.out.println("\nResult (Spans): " + Arrays.toString(span));
    }
}

