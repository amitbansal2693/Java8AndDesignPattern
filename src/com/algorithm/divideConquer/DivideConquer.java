package com.algorithm.divideConquer;

public class DivideConquer {
    public static void main(String[] args) {

    }

    static int binarySearch(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] < target) {
                left = mid + 1; //shift left pointer after mid
            } else {
                right = mid - 1; //shift right pointer
            }
        }

        return -1;
    }
}
