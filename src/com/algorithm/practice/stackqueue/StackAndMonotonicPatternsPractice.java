package com.algorithm.practice.stackqueue;

import java.util.*;

/**
 * ============================================================================
 * PRACTICE: Stack & Monotonic Stack
 * ============================================================================
 * 
 * This is the PRACTICE version - incomplete methods for you to solve
 * 
 * Complete each method following the hints and approach comments
 * Test your solution against the examples in main()
 * 
 * ============================================================================
 */
public class StackAndMonotonicPatternsPractice {

    public static void main(String[] args) {
        System.out.println("========== STACK & MONOTONIC STACK PRACTICE ==========\n");

        System.out.println("=================== REGULAR STACK ===================\n");

        // Practice 1: Valid Parentheses
        System.out.println("PRACTICE 1: Valid Parentheses");
        testValidParentheses();

        System.out.println("\n=================== MONOTONIC STACK ===================\n");

        // Practice 2: Next Greater Element
        System.out.println("PRACTICE 2: Next Greater Element");
        int[] arr1 = {1, 3, 2, 4, 5, 2, 1};
        System.out.println("Array: " + Arrays.toString(arr1));
        try {
            nextGreaterElement(arr1);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        // Practice 3: Daily Temperatures
        System.out.println("\nPRACTICE 3: Days Until Warmer Temperature");
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("Temperatures: " + Arrays.toString(temps));
        try {
            dailyTemperatures(temps);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }

        // Practice 4: Stock Span Problem
        System.out.println("\nPRACTICE 4: Stock Span Problem");
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        System.out.println("Stock prices: " + Arrays.toString(prices));
        try {
            stockSpan(prices);
        } catch (Exception e) {
            System.out.println("Not implemented yet");
        }
    }

    /**
     * PRACTICE 1: Valid Parentheses
     * 
     * Problem: Check if string contains valid parentheses
     * Valid means: every opening bracket has matching closing in correct order
     * Input: "([{}])" → true
     *        "({[}])" → false (wrong nesting)
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH:
     * 1. Create Stack to store opening brackets
     * 2. Create HashMap: closing_bracket → opening_bracket
     * 3. For each character in string:
     *    - If it's closing bracket (exists in map):
     *      * Check if stack is empty (error - no opening)
     *      * Check if stack.peek() == matching opening bracket
     *      * If yes, pop. If no, return false
     *    - If it's opening bracket:
     *      * Push to stack
     * 4. After loop, stack should be empty (all matched)
     * 
     * WHY STACK WORKS:
     * - LIFO nature mirrors nesting: most recent opening should close first
     * - If we see closing bracket, it must match most recent opening
     * 
     * EXAMPLES:
     * "()" → push (, push ) find ( in map, matches, pop → empty ✓
     * "([)]" → push (, push [, ) closes (, but top is [ → false ✗
     * 
     * HINT: Create bracket pairs in HashMap for easy matching
     * 
     * Time: O(n), Space: O(n)
     */
    public static void testValidParentheses() {
        String[] testCases = {"()", "([{}])", "({[}])", "([)]", "{[]"};
        System.out.println("Test cases:");
        for (String test : testCases) {
            try {
                boolean valid = isValidParentheses(test);
                System.out.println("  \"" + test + "\" → " + (valid ? "VALID ✓" : "INVALID ✗"));
            } catch (Exception e) {
                System.out.println("  \"" + test + "\" → Not implemented yet");
            }
        }
    }

    private static boolean isValidParentheses(String s) {
     Stack<Character> stack= new Stack<>();
     Map<Character,Character> map =new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');
        for(char c: s.toCharArray()){
            System.out.println("character is: "+c);

            if(map.containsKey(c)){
                stack.push(c);
            } else if((c==')' && stack.peek()=='(') ||
                    (c=='}' && stack.peek()=='}') ||
                    (c==']' && stack.peek()=='}')) {
                stack.pop();
            } else {
                return false;
            }
        }
        //

        return stack.isEmpty();
    }

    private static boolean isValidParentheses_2(String s) {
        Stack<Character> stack= new Stack<>();
        Map<Character,Character> map =new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');
        for(char c: s.toCharArray()){
            System.out.println("character is: "+c);

            if(map.containsKey(c)){
                stack.push(c);
            } else if(map.get(stack.peek()) == c) {
                stack.pop();
            } else {
                return false;
            }
        }
        //

        return stack.isEmpty();
    }

    /**
     * PRACTICE 2: Next Greater Element
     * 
     * Problem: For each element, find the next greater element to its right
     * Input: [1, 3, 2, 4, 5, 2, 1]
     * Expected Output:
     *   1 → 3
     *   3 → 4
     *   2 → 4
     *   4 → 5
     *   5 → -1
     *   2 → -1
     *   1 → -1
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Monotonic Stack - RIGHT TO LEFT):
     * 1. Create result array, process from RIGHT to LEFT
     * 2. Use Stack to store indices in DECREASING order of values
     * 3. For each element from right to left:
     *    - While stack not empty AND arr[stack.peek()] <= current:
     *      * Pop (current is their "next greater")
     *    - If stack empty: result = -1
     *    - Else: result = arr[stack.peek()]
     *    - Push current index
     * 
     * WHY MONOTONIC & DECREASING:
     * - Stack contains elements that still need a "next greater"
     * - When we find larger element, it's the answer for all smaller ones below it
     * - Decreasing order ensures O(n) - each element pushed/popped once
     * 
     * VISUALIZATION for [1, 3, 2, 4, 5]:
     * Process R→L:
     * 5: stack=[5], result[5]=-1
     * 4: 4<5? no. stack=[5,4], result[4]=5
     * 2: 2<4? yes pop. 2<5? yes pop. stack=[2], result[2]=-1
     *    Wait, that's wrong. Let me retrace.
     * 
     * Actually:
     * 5: stack=[5_idx], result=-1
     * 4: 4<5? pop. stack=[4_idx], result[4]=5
     * 2: 2<4? no. stack=[4_idx,2_idx], result[2]=4
     * 3: 3<4? pop. 3>2? pop. stack=[3_idx], result[3]=4
     * 1: 1<3? no. stack=[3_idx,1_idx], result[1]=3
     * 
     * HINT: Start from right, maintain decreasing stack
     * 
     * Time: O(n), Space: O(n)
     */
    public static void nextGreaterElement(int[] arr) {
        //idea is to find next greater element to its right
        Map<Integer, String> result =new HashMap<>();
        for(int i=0; i<arr.length ; i++) {
            if(i== arr.length -1){
                result.put(i, arr[i]+ " -> -1)");
            }
            for(int j=i+1; j<arr.length; j++) {
                if(arr[j]> arr[i]){
                    result.put(i, arr[i] +" -> "+arr[j]);
                    break;
                }
            }
            //if nothing in iterator
            if(!result.containsKey(i)){
                result.put(i, arr[i]+ " -> -1");
            }
        }

        System.out.println("nextGreaterElement:: "+result);
        nextGreaterElement_2(arr);
    }

    public static void nextGreaterElement_2(int[] arr) {
        //idea is to find next greater element to its right
        System.out.println("stack approach of nextgreat element");
        Map<Integer, String> result =new HashMap<>();
        Stack<Integer> stack =new Stack<>();


        for(int i=0; i<arr.length ; i++) {
            if(i== arr.length -1){
                result.put(i, arr[i]+ " -> -1)");
            }
            for(int j=i+1; j<arr.length; j++) {
                if(arr[j]> arr[i]){
                    result.put(i, arr[i] +" -> "+arr[j]);
                    break;
                }
            }
            //if nothing in iterator
            if(!result.containsKey(i)){
                result.put(i, arr[i]+ " -> -1");
            }
        }

        System.out.println("nextGreaterElement:: "+result);
    }
    /**
     * PRACTICE 3: Daily Temperatures (Days Until Warmer)
     * 
     * Problem: For each day, find how many days until a warmer temperature
     * Input: [73, 74, 75, 71, 69, 72, 76, 73]
     * Expected Output: [1, 1, 4, 2, 1, 1, 0, 0]
     * 
     * Explanation:
     * Day 0 (73°): Next warmer is 74° on day 1 → 1 day
     * Day 2 (75°): Next warmer is 76° on day 6 → 4 days
     * Day 7 (73°): No warmer temperature → 0 days
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Monotonic Stack - LEFT TO RIGHT):
     * 1. Create result array (default 0)
     * 2. Use Stack to store indices in DECREASING order of temperatures
     * 3. For each day from left to right:
     *    - While stack not empty AND temps[stack.peek()] < current_temp:
     *      * Pop previous index
     *      * result[previous_idx] = current_day - previous_day
     *    - Push current index
     * 4. Return result
     * 
     * WHY L→R:
     * - We're looking for "warmer day" in the future
     * - Process L→R to have future temperatures available
     * 
     * DIFFERENCE FROM PRACTICE 2:
     * - Process L→R instead of R→L
     * - Calculate difference in indices (days)
     * - Need to find actual next warmer (greater, not just next)
     * 
     * TRACE for [73, 74, 75]:
     * Day 0 (73): stack=[0]
     * Day 1 (74): 73 < 74? pop 0. result[0] = 1-0 = 1. stack=[1]
     * Day 2 (75): 74 < 75? pop 1. result[1] = 2-1 = 1. stack=[2]
     * 
     * HINT: Similar to next greater, but L→R and calculate day difference
     * 
     * Time: O(n), Space: O(n)
     */
    public static void dailyTemperatures(int[] temps) {
        Map<Integer, String> result = new HashMap<>();

        for (int i = 0; i < temps.length; i++) {
            if (i == temps.length - 1) {
                result.put(i, temps[i] + " -> 0 days");
            }
            for (int j = i + 1; j < temps.length; j++) {
                if (temps[j] > temps[i]) {
                    result.put(i, temps[i] + " -> "+(j-i) + ((j-i)==1?" day" : " days"));
                    break;
                }
            }
        }
        dailyTemperatures_2(temps);
        System.out.println("dailyTemperatures "+result);
    }

    public static int[] dailyTemperatures_2(int[] temps) {
        //[73, 74, 75, 71, 69, 72, 76, 73]
        int[] result = new int[temps.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < temps.length; i++) {
            //if stack has data
            while (!stack.isEmpty() && temps[i] > temps[stack.peek()]) {
                int prev = stack.pop();//return index
                result[prev] = i - prev;
            }

            stack.push(i);
        }
        System.out.println("dailyTemperatures "+ Arrays.stream(result).boxed().toList());
        return result;
    }
    /**
     * PRACTICE 4: Stock Span Problem
     * 
     * Problem: For each day, calculate span = consecutive days with price <= current
     * Input: [100, 80, 60, 70, 60, 75, 85]
     * Expected Output: [1, 1, 1, 2, 1, 4, 6]
     * 
     * Explanation:
     * Day 0 (100): Just itself → span = 1
     * Day 1 (80):  Previous (100) > 80 → span = 1 (just itself)
     * Day 3 (70):  70 >= 60 (day 2) but < 80 → span = 2 (day 2,3)
     * Day 5 (75):  75 >= 60,70,60 but < 80 → span = 4 (days 2,3,4,5)
     * Day 6 (85):  85 > all before → span includes from day 1 = 6 days (1-6)
     * 
     * TODO: Complete this method
     * ─────────────────────────────
     * 
     * APPROACH (Monotonic Stack with Indices):
     * 1. Create span array
     * 2. Use Stack to store indices in DECREASING price order
     * 3. For each day:
     *    - While stack not empty AND prices[stack.peek()] <= current_price:
     *      * Pop (current spans over this)
     *    - If stack empty: span = i + 1 (from day 0)
     *    - Else: span = i - stack.peek() (from last greater)
     *    - Push current index
     * 
     * KEY INSIGHT:
     * - Span = distance from current to previous greater
     * - If no previous greater, span from start
     * - Stack stores indices with LARGER prices
     * 
     * VISUALIZATION for [100, 80, 60]:
     * Day 0 (100): stack=[0], span[0]=1
     * Day 1 (80):  100 > 80. stack=[0,1], span[1]=1
     * Day 2 (60):  80 > 60. stack=[0,1,2], span[2]=1
     * 
     * For [100, 80, 70, 75]:
     * Day 3 (75):  70 < 75 pop→2. 80 > 75. stack=[0,1,3], span[3] = 3-1 = 2
     * 
     * HINT: When popping, you're finding span
     * 
     * Time: O(n), Space: O(n)
     */
    public static void stockSpan(int[] prices) {
        throw new UnsupportedOperationException(
            "TODO: Calculate stock span using monotonic stack\n" +
            "Hint: Pop elements <= current, span = distance from previous greater"
        );
    }
}

