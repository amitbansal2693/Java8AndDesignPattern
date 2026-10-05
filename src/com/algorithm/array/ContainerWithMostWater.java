package com.algorithm.array;

import java.util.HashMap;
import java.util.*;
import java.util.Map;

/**
 *Given an array arr[] of non-negative integers, where each element arr[i] represents the height of the vertical lines, find the maximum amount of water that can be contained between any two lines, together with the x-axis.
 *
 * Examples :
 *
 * Input: arr[] = [1, 5, 4, 3]
 * Output: 6
 * Explanation: 5 and 3 are 2 distance apart.
 * So the size of the base = 2. Height of container = min(5, 3) = 3. So total area = 3 * 2 = 6.
 *
 * Input: arr[] = [3, 1, 2, 4, 5]
 * Output: 12
 * Explanation: 5 and 3 are 4 distance apart.
 * So the size of the base = 4. Height of container = min(5, 3) = 3. So total area = 4 * 3 = 12.
 *
 * Input: arr[] = [2, 1, 8, 6, 4, 6, 5, 5]
 * Output: 25
 * Explanation: 8 and 5 are 5 distance apart.
 * So the size of the base = 5. Height of container = min(8, 5) = 5. So, total area = 5 * 5 = 25.
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] arr = {2, 1, 8, 6, 4, 6, 5, 5};
        System.out.println(maxWater(arr));
        System.out.println(maxWater_2(arr));
        System.out.println(maxWater_pointer(arr));
    }

    private static int maxWater(int[] arr) {
        System.out.println("input: "+ Arrays.stream(arr).boxed().toList());
        int area =0;
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < arr.length-1; i++) {
            int distance=0;
            area=0;
            for (int j = i+1; j < arr.length; j++) {
                distance = j-i; //distance
                int height =Math.min(arr[i], arr[j]); //min height
                int currentArea = distance * height; //area
                if(area < currentArea){
                    area=currentArea;
                    map.put(currentArea, i+ " -> "+j);
                }
                System.out.println("Between: "+ arr[i]+" -> "+arr[j]+" distance: "+distance + " height: "+height + " currentArea: "+currentArea + " Area: "+area);

            }
            System.out.println("map "+map);
        }
        return map.keySet().stream().mapToInt(Integer::intValue).max().getAsInt();
    }

    static int maxWater_2(int[] arr) {
        int n = arr.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if(arr[j]>arr[i])
                    break;
                // calculate the amount of water
                int amount =
                        Math.min(arr[i], arr[j]) * (j - i);

                // keep track of maximum amount of water
                res = Math.max(amount, res);
            }
        }
        return res;
    }

    /**
     * Try every possible pair of walls and keep the best one.
     * Brute force = check all combinations.
     * Two pointers = eliminate combinations that cannot improve the answer.
     *
     * @param arr
     * @return
     */
    static int maxWater_pointer(int[] arr) {

        int left = 0;
        int right = arr.length - 1;
        int maxWater = 0;

        while (left < right) {

            int height = Math.min(arr[left], arr[right]);
            int width = right - left;

            int water = height * width;

            maxWater = Math.max(maxWater, water);

            if (arr[left] < arr[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }
}
