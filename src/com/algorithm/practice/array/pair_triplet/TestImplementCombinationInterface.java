package com.algorithm.practice.array.pair_triplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestImplementCombinationInterface implements CombinationSumInterface {

    public static void main(String[] args) {
        TestImplementCombinationInterface test = new TestImplementCombinationInterface();
        int[] arr = {2, 3, 6, 7};
        List result = test.combinationSum(arr, 7);
        System.out.println("combinationSum: With duplicates " + result);

        int[] arr2 = {2, 3, 6, 7};
         result = test.combinationSum_NoDuplicates(arr, 7);
        System.out.println("combinationSum_NoDuplicates:  Unqiue pairs " + result);
    }

    @Override
    public List<List<Integer>> combinationSum(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        findDuplicateComboUsingBacktrack(arr, 0, target, new ArrayList<>(), result);
        return result;
    }

    /**
     * @param arr
     * @param startIndex - index of array, this will same in next backtrack
     * @param target
     * @param subArray
     * @param result
     */
    private void findDuplicateComboUsingBacktrack(int[] arr, int startIndex, int target, ArrayList<Integer> subArray, List<List<Integer>> result) {

        // Step 1: target reached
        if (target == 0) {
            List<Integer> current = new ArrayList<>(subArray);
            System.out.println("Found combo with target reached: " + current);
            result.add(current);
            return;
        }

        // Step 2: index passed array length
        if (startIndex >= arr.length) {
            return;
        }

        // Step 3: try each candidate from startIndex
        for (int i = startIndex; i < arr.length; i++) {

            // Array is sorted, so no later value can work
            if (arr[i] > target) {
                break;
            }

            // Add candidate
            subArray.add(arr[i]);

            // Reuse same element -> i
            findDuplicateComboUsingBacktrack(arr, i, target - arr[i], subArray, result);

            // Backtrack: remove last selected element
            subArray.remove(subArray.size() - 1);
        }
    }


    @Override
    public List<List<Integer>> combinationSum_NoDuplicates(int[] arr, int target) {
        List<List<Integer>> results=new ArrayList<>();
        Arrays.sort(arr);
        findUniqueComboUsingBacktrack(arr,0,target,new ArrayList<Integer>(),results);
        return results;
    }

    private void findUniqueComboUsingBacktrack(int[] arr, int index, int target, ArrayList<Integer> current, List<List<Integer>> results) {

        // Step 1: target reached
        if (target == 0) {
            List<Integer> list = new ArrayList<>(current);
            System.out.println("Found combo: " + current);
            results.add(list);
            return;
        }

        // Step 2: index overflow
        if (index >= arr.length) {
            return;
        }

        // Step 3: loop
        for (int i = index; i < arr.length; i++) {

            // Step 3.a: Skip duplicate values at the same recursion level
            if (i > index && arr[i] == arr[i - 1]) {
                continue;
            }

            // Step 3.b: Stop because array is sorted
            if (arr[i] > target) {
                break;
            }

            // Step 3.c: Add current value
            current.add(arr[i]);

            // Step 3.d: i + 1 -> element cannot be reused
            findUniqueComboUsingBacktrack(arr, i + 1, target - arr[i], current, results);

            // Step 3.e: Backtrack
            current.remove(current.size() - 1);
        }
    }
}
