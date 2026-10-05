package com.algorithm.dymanic;

/**
 * Stairs problem:
 * solution: Recursion
 * Note:  current state: we only need answers for previous two states.
 *
 *
 *
 * Given n stairs, and a person standing at the ground wants to climb stairs to reach the top.
 * The person can climb either 1 stair or 2 stairs at a time, count the number of ways the person can reach at the top.
 *
 * Examples:
 *
 * Input: n = 1
 * Output: 1
 * Explanation: There is only one way to climb 1 stair.
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to reach 2th stair: {1, 1} and {2}.
 *
 * Input: n = 4
 * Output: 5
 * Explanation: There are five ways to reach 4th stair: {1, 1, 1, 1}, {1, 1, 2}, {2, 1, 1}, {1, 2, 1} and {2, 2}.
 *
 * Note:
 * current state: we only need answers for previous two states.
 *
 */
public class ClimbStairs {
    public static void main(String[] args) {
        System.out.println(findWays(4));
    }
    public static int findWays(int n){
        if(n==1 || n==0){
            return 1;
        }
        return findWays(n-1) + findWays(n-2);
    }
}
