package com.algorithm.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.*;

/**
 * Given an integer array arr[], find the subarray (containing at least one element)
 * which has the maximum possible sum, and return that sum. A subarray is a continuous part of an array.
 *
 * Examples:
 *
 * Input: arr[] = [2, 3, -8, 7, -1, 2, 3]
 * Output: 11
 * Explanation: The subarray [7, -1, 2, 3] has the largest sum 11.
 *
 * Input: arr[] = [-2, -4]
 * Output: -2
 * Explanation: The subarray [-2] has the largest sum -2.
 *
 * Input: arr[] = [5, 4, 1, 7, 8]
 * Output: 25
 * Explanation: The subarray [5, 4, 1, 7, 8] has the largest sum 25.
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        int[] arr = {2, 3, -8, 7, -1, 2, 3};
        maxSubarraySum_2(arr);
        System.out.println(maxSubarraySum(arr));
    }

    private static boolean maxSubarraySum(int[] arr) {
        int max=0;
        int[] result =new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            max=0;
            for (int j = i; j < arr.length; j++) {
              int currentSum = max + arr[j];
              max=Math.max(max, currentSum);
            }
            result[i]=max;
        }
        System.out.println("result: "+ Arrays.stream(result).boxed().toList());
        return false;
    }

    private static boolean maxSubarraySum_2(int[] arr) {
        int max = 0;
        int[] result = new int[arr.length];
        Map<Integer, List> map = new HashMap<>();
        int i = 0;
        System.out.println("Original list: " + Arrays.stream(arr).boxed().toList());
        for (int num : arr) {
            List<Integer> list = Arrays.stream(arr).boxed().skip(++i).toList(); //fetch subarray exluding current

            map.put(arr[i - 1], list);
            System.out.println("List after skip " + i + " - " + list);
            //result[i - 1] = list.stream().reduce(0, (a, b) -> a + b);
            result[i - 1] = list.stream().mapToInt(Integer::intValue).sum();
        }
        System.out.println(map);
        System.out.println("result: " + Arrays.stream(result).boxed().toList());
        return false;
    }
}
