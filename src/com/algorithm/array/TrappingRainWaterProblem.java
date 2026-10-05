package com.algorithm.array;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array arr[] of size n consisting of non-negative integers,
 * where each element represents the height of a bar in an elevation map
 * and the width of each bar is 1,
 * determine the total amount of water that can be trapped between the bars after it rains.
 *
 * Input: arr[] = [3, 0, 1, 0, 4, 0, 2]
 * Output: 10
 * Explanation: The expected rainwater to be trapped is shown in the above image.
 *
 * Input: arr[] = [3, 0, 2, 0, 4]
 * Output: 7
 * Explanation: We trap 0 + 3 + 1 + 3 + 0 = 7 units.
 *
 * Input: arr[] = [1, 2, 3, 4]
 * Output: 0
 * Explanation: We cannot trap water as there is no height bound on both sides
 */
public class TrappingRainWaterProblem {
    public static void main(String[] args) {
        int[] arr = { 2, 1, 5, 3, 1, 0, 4 };
        System.out.println(maxWater(arr));
    }

    private static int maxWater(int[] arr) {
        System.out.println(Arrays.stream(arr).boxed().toList());
        Map<Integer, String> map =new HashMap<>();

        for (int i = 0; i < arr.length-1 ; i++) {
            int maxIndex = 0; //
            //find maimum
            for (int j = i + 1; j < arr.length; j++) {
                //if next value lower than current, keep moving.

                if (arr[j] < arr[i]) {
                    //if we keep parsing , and found nothing
                    if (j==arr.length-1) {
                        maxIndex=arr.length-1;
                    }
                    continue;
                } else if(arr[j] > arr[i]){
                    maxIndex = j;
                    break;
                }
            }
            int totalWater = 0;
            if (maxIndex > i) {
                for (int j = i + 1; j < maxIndex; j++) {
                    if(arr[i]>arr[maxIndex]) {
                        totalWater += arr[maxIndex] - arr[j];
                    } else
                        totalWater += arr[i] - arr[j];
                }
            }
            map.put(i, "start: "+arr[i]+ " -> "+ arr[maxIndex]+" : "+totalWater);
        }
        System.out.println("Water: "+map);
        map.entrySet().forEach(System.out::println);

        return 0;
    }

    /**
     * Check current bar, its nearby left and right. then how much water it can hold on it.
     * @param arr
     * @return
     */
    static int maxWater_2(int[] arr) {
        int res = 0;

        // For every element of the array
        for (int i = 1; i < arr.length - 1; i++) {

            // Find the maximum element on its left
            int left = arr[i];
            for (int j = 0; j < i; j++)
                left = Math.max(left, arr[j]);

            // Find the maximum element on its right
            int right = arr[i];
            for (int j = i + 1; j < arr.length; j++)
                right = Math.max(right, arr[j]);

            // Update the maximum water
            res += Math.min(left, right) - arr[i];
        }

        return res;
    }

}
