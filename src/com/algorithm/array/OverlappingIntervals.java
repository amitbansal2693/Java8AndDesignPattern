package com.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given:
 * A list of non-overlapping intervals.
 * The intervals are already sorted by their start time.
 * You are given one new interval.
 * You need to insert the new interval in the correct position.
 * If the new interval overlaps with existing intervals, you must merge them.
 * An interval [start, end] means an event happens from start to end.
 * Insert and Merge Interval
 * Last Updated :
 * 7 Aug, 2025
 * Given a set of non-overlapping intervals[][] where intervals[i] = [starti , endi] represent the start and the end of the ith event and intervals is sorted in ascending order by starti and a new interval, insert the interval at the correct position such that after insertion, the intervals remain sorted. If the insertion results in overlapping intervals, then merge the overlapping intervals. Assume that the set of non-overlapping intervals is sorted based on start time.
 *
 * Examples:
 *
 * Input: intervals[][] = [[1, 3], [4, 5], [6, 7], [8, 10]], newInterval[] = [5, 6]
 * Output: [[1, 3], [4, 7], [8, 10]]
 * Explanation: The intervals [4, 5] and [6, 7] are overlapping with [5, 6]. So, they are merged into one interval [4, 7].
 *
 * Input: intervals[][] = [[1, 2], [3, 5], [6, 7], [8, 10], [12, 16]], newInterval[]  = [4, 9]
 * Output: [[1, 2], [3, 10], [12, 16]]
 * Explanation: The intervals [ [3, 5], [6, 7], [8, 10] ] are overlapping with [4, 9]. So, they are merged into one interval [3, 10].
 */
public class OverlappingIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {4, 5}, {6, 7}, {8, 10}};
        int[] newInterval = {5, 6};

        int[][] res = insertInterval(intervals, newInterval);
        insertInterval_2(intervals, newInterval);
        for (int[] interval : res) {
            System.out.println(interval[0] + " " + interval[1]);
        }
    }

    private static int[][] insertInterval(int[][] intervals, int[] newInterval) {
        System.out.println(Arrays.toString(newInterval));
        for (int[] res : intervals) {
            System.out.println("intervals " + Arrays.toString(res));
        }
        List<String> list=new ArrayList<>();
        int[][] results= new int[intervals.length][2];
        int start=0;
        int k=0;
        for(int[] arr :intervals) {
            System.out.println("searching  "+newInterval[0] + " to be search: "+Arrays.toString(newInterval) + " in: "+Arrays.toString(arr));

            System.out.println("Tag:" +arr[0] + " : "+arr[1]);
            if(arr[0]<=newInterval[0] && arr[1]>=newInterval[0]){
                System.out.println("Found interval" +k);
                start=k;
                break;
            }
            k++;
        }
        int end=0;
        k=0;
        for(int[] arr :intervals) {
            System.out.println("searching  "+newInterval[1] + " to be search: "+Arrays.toString(newInterval) + " in: "+Arrays.toString(arr));
            if(arr[0]<=newInterval[1] && arr[1]>=newInterval[1]){
                System.out.println("Found interval" +k);
                end=k;
                break;
            }
            k++;
        }

        int first=0;
        int close=0;
        int index=0;
        for (int i = 0; i < intervals.length; i++) {
            if(i < start || i > end){
                results[index++]=intervals[i];
            }
            if (i==start) {
                first =intervals[i][0];

            }
            if(i==end){
                close=intervals[i][1];
                System.out.println();
                results[index++]=new int[]{first,close};
            }
        }

        System.out.println("Input: "+Arrays.toString(intervals));
        System.out.println("first: "+ first + " close "+close);
        for (int[] res : Arrays.copyOf(results, index)) {
            System.out.println("intervals " + Arrays.toString(res));
        }
        return results;
    }

    // Function to insert and merge intervals
    static ArrayList<int[]> insertInterval_2(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> res = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Add all intervals that come before the new interval
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }

        // Merge all overlapping intervals with the new interval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);

        // Add all the remaining intervals
        while (i < n) {
            res.add(intervals[i]);
            i++;
        }

        // Return the result as a List<int[]>
        return res;
    }
}
