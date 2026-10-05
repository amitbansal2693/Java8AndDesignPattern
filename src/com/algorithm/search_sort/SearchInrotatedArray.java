package com.algorithm.search_sort;

/**
 * Given an array arr[] of distinct elements, which was initially sorted in ascending order
 * but then rotated at some unknown pivot, the task is to find the index of a target key.
 * If the key is not present in the array, return -1.
 *
 * Examples :
 *
 * Input: arr[] = [5, 6, 7, 8, 9, 10, 1, 2, 3], key = 3
 * Output: 8
 * Explanation: 3 is found at index 8.
 * Input: arr[] = [3, 5, 1, 2], key = 6
 * Output: -1
 * Explanation: There is no element that has value 6.
 * Input: arr[] = [33, 42, 72, 99], key = 42
 * Output: 1
 * Explanation: 42 is found at index 1.
 *
 * The important observation
 * Although the whole array isn't sorted anymore, at every point in the search, one half is guaranteed to be sorted.
 * Pattern recognition
 * When you see:
 * sorted array + rotated + search target
 * Think:
 * Modified Binary Search
 *
 */
public class SearchInrotatedArray {
    public static void main(String[] args) {
        int arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};

        int index=findIndex(arr, 10);
        System.out.println("Key index in Rotated array: "+index);
    }

    public static int findIndex(int arr[], int target){
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    public static int binarySearch(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
