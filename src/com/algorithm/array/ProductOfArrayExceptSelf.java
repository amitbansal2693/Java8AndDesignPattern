package com.algorithm.array;

import java.util.Arrays;

/**
 * Given an array arr[] of n integers, construct a product array res[] (of the same size) such that res[i] is equal to the product of all the elements of arr[] except arr[i].
 *
 * Example:
 *
 * Input: arr[] = [10, 3, 5, 6, 2]
 * Output: [180, 600, 360, 300, 900]
 * Explanation:
 * For i=0, res[i] = 3 * 5 * 6 * 2 is 180.
 * For i = 1, res[i] = 10 * 5 * 6 * 2 is 600.
 * For i = 2, res[i] = 10 * 3 * 6 * 2 is 360.
 * For i = 3, res[i] = 10 * 3 * 5 * 2 is 300.
 * For i = 4, res[i] = 10 * 3 * 5 * 6 is 900.
 *
 * Input: arr[] = [12, 0]
 * Output: [0, 12]
 * Explanation:
 * For i = 0, res[i] = 0.
 * For i = 1, res[i] = 12.
 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        int[] arr = {10, 3, 5, 6, 2};
        int[] res = productExceptSelf(arr);
        productExceptSelf_2(arr);
        for (int val : res) {
            System.out.print(val + " ");
        }
    }

    private static int[] productExceptSelf(int[] arr) {
        int[] result = new int[arr.length];
        Arrays.stream(result).map(e -> 1);
        Arrays.fill(result, 1);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i != j) {
                    result[i] = result[i] * arr[j];
                }
            }
        }
        System.out.println("Result: " + Arrays.stream(result).boxed().toList());
        return result;
    }

    private static int[] productExceptSelf_2(int[] arr) {
        int[] result = new int[arr.length];
        int i = 0;
        for (int current : arr) {

            int product = Arrays.stream(arr)
                    .filter(x -> x != current) //filter current element
                    .reduce(1, (a, b) -> a * b); //do reduce
            result[i++] = product;
        }
        System.out.println(Arrays.stream(result).boxed().toList());
        return result;
    }
    }
