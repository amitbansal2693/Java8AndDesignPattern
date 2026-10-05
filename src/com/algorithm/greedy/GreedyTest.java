package com.algorithm.greedy;

import java.util.Arrays;

public class GreedyTest {
    public static void main(String[] args) {

    }

    /**
     Problem Statement
     You have several activities.

     Each activity has:
     - a start time
     - an end time

     You can attend only one activity at a time.
     Two activities are compatible if the second activity starts at or after the first activity finishes.

     Your goal is:
     Attend the maximum possible number of activities.

     Goal
     Find the maximum number of non-overlapping activities.
     Greedy idea
     Ask:
     Which activity should I choose first?

     Choose the activity that finishes earliest.
     Why?
     Because finishing early leaves the most time for the remaining activities.

     * start = [1, 2, 4, 6, 8]
     * end   = [3, 5, 7, 8, 9]

     */
    static int activitySelection(int[] start, int[] end) {

        int count = 0;
        int lastEnd = -1;

        // Assume activities are sorted by end time.
        for (int i = 0; i < start.length; i++) {

            if (start[i] >= lastEnd) { //pick activity, should start after the end of last activity
                count++;
                lastEnd = end[i]; //mark end time of first activity
            }
        }

        return count;
    }

    /**
     * Assign Cookies
     * Problem

     There are n children and m cookies.
     - children[i] = minimum cookie size required by child i. length=number of children. value=demand of cookie by child
     - cookies[j] = size of cookie j
     - Each child can receive at most one cookie.
     - Each cookie can be given to at most one child.
     - A child is satisfied if:


     children = [1, 2, 3, 2, 4]
     cookies  = [1, 1, 3, 2, 5]
     *
     * Greedy idea
     * Give the smallest cookie that can satisfy the smallest child.
     *
     * Sort both arrays.
     * children: 1 2 3
     * cookies:  1 1
     *
     * Important cases covered
     * This single example demonstrates:
     * - 1 >= 1 → exact match
     * - 1 < 2 → cookie too small
     * - 2 >= 2 → exact match
     * - 3 > 2 → larger cookie satisfies child
     * - 5 > 3 → large cookie satisfies child
     * - Duplicate child requirements: 2, 2
     * - Duplicate cookies: 1, 1
     * - One child remains unsatisfied
     * So the answer is:
     *
     * Give first cookie to child 1.
     * Second cookie cannot satisfy child 2.
     * Output
     * 1
     * @param children
     * @param cookies
     * @return
     */
    static int assignCookies(int[] children, int[] cookies) {

        Arrays.sort(children);
        Arrays.sort(cookies);

        int child = 0;

        for (int cookie : cookies) {

            if (child < children.length &&
                    cookie >= children[child]) {

                child++;
            }
        }

        return child;
    }


    /**
     * Jump Game
     * Problem
     * The value at each position tells you the maximum number of positions you can jump forward from there.
     *
     * Each element tells you the maximum number of positions you can jump.
     * Determine whether you can reach the last index.
     *
     * Input
     * nums = [2, 3, 1, 1, 4]
     *
     * Greedy idea
     * Keep track of the farthest position we can reach.
     *
     * index 0 → can reach 2
     * index 1 → can reach 4
     *
     * Now we can reach the end.
     * Output
     * true
     *
     * current position
     *       ↓
     * how far can I reach?
     *       ↓
     * keep maximum
     *
     */

    static boolean canJump(int[] nums) {

        int farthest = 0;

        for (int i = 0; i < nums.length; i++) {

            if (i > farthest) {
                return false;
            }

            farthest = Math.max(
                    farthest,
                    i + nums[i]
            );
        }

        return true;
    }

    /**
     * item price: 5
     * customer start coming and paying some amount, check whether you can return them full money or not.
     * @param bills
     * @return
     */
    static boolean lemonadeChange(int[] bills) {

        int five = 0;
        int ten = 0;

        for (int bill : bills) {

            if (bill == 5) {
                five++;
            }

            else if (bill == 10) {
                if (five == 0) return false;

                five--;
                ten++;
            }

            else {
                // Need $15 change.
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                }
                else if (five >= 3) {
                    five -= 3;
                }
                else {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Fractional Knapsack
     * Problem Statement: Do shopping of maximum value, when bag can hold limited quantity.
     *
     * You have a bag with limited capacity.
     *
     * You have several items.
     *
     * Each item has:
     * value
     * weight
     *
     * Unlike normal 0/1 Knapsack:
     * You are allowed to take a fraction of an item.
     *
     *
     *
     */
    static double fractionalKnapsack(
            int[] value,
            int[] weight,
            int capacity) {

        int n = value.length;

        Integer[] index = new Integer[n];

        for (int i = 0; i < n; i++) {
            index[i] = i;
        }

        Arrays.sort(index, (a, b) ->
                Double.compare(
                        (double) value[b] / weight[b],
                        (double) value[a] / weight[a]
                )
        );

        double total = 0;

        for (int i : index) {

            if (capacity >= weight[i]) {
                total += value[i];
                capacity -= weight[i];
            }
            else {
                total += (double) value[i]
                        * capacity / weight[i];
                break;
            }
        }

        return total;
    }
}
