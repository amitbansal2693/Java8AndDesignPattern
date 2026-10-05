package com.practice;

import java.util.*;
import java.util.stream.*;

/**
 * ============================================================================
 * Java Pairing / Combination Problems
 * Streams vs Traditional Loops
 * ============================================================================
 * <p>
 * This guide contains:
 * <p>
 * 1. Two random elements pair sum
 * 2. Three random elements triplet sum
 * 3. Adjacent pair sum
 * 4. Adjacent triplet sum
 * 5. Fixed window adjacent sum
 * 6. N-size adjacent windows
 * <p>
 * Each problem contains:
 * - Problem statement
 * - Input
 * - Expected output
 * - Traditional for-loop solution
 * - Stream solution
 * - Internal explanation
 * <p>
 * ============================================================================
 */
public class PairingAndCombinationProblems {

    public static void main(String[] args) {

        // ================================================================
        // Input Data
        // ================================================================
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);


        // ================================================================
        // 1. Two Random Elements Pair Sum
        // ================================================================
        System.out.println("\n================================================");
        System.out.println("1. TWO ELEMENT PAIR SUM");
        System.out.println("================================================");

        int pairTarget = 7;

        System.out.println("\nFOR LOOP SOLUTION:");
        twoElementPairForLoop(list, pairTarget);

        System.out.println("\nSTREAM SOLUTION:");
        twoElementPairStream(list, pairTarget);


        // ================================================================
        // 2. Three Random Elements Triplet Sum
        // ================================================================
        System.out.println("\n================================================");
        System.out.println("2. THREE ELEMENT TRIPLET SUM");
        System.out.println("================================================");

        int tripletTarget = 10;

        System.out.println("\nFOR LOOP SOLUTION:");
        threeElementTripletForLoop(list, tripletTarget);

        System.out.println("\nSTREAM SOLUTION:");
        threeElementTripletStream(list, tripletTarget);


        // ================================================================
        // 3. Adjacent Pair Sum
        // ================================================================
        System.out.println("\n================================================");
        System.out.println("3. ADJACENT PAIR SUM");
        System.out.println("================================================");

        int adjacentPairTarget = 5;

        System.out.println("\nFOR LOOP SOLUTION:");
        adjacentPairForLoop(list, adjacentPairTarget);

        System.out.println("\nSTREAM SOLUTION:");
        adjacentPairStream(list, adjacentPairTarget);


        // ================================================================
        // 4. Adjacent Triplet Sum
        // ================================================================
        System.out.println("\n================================================");
        System.out.println("4. ADJACENT TRIPLET SUM");
        System.out.println("================================================");

        int adjacentTripletTarget = 9;

        System.out.println("\nFOR LOOP SOLUTION:");
        adjacentTripletForLoop(list, adjacentTripletTarget);

        System.out.println("\nSTREAM SOLUTION:");
        adjacentTripletStream(list, adjacentTripletTarget);


        // ================================================================
        // 5. Fixed Window Adjacent Sum
        // ================================================================
        System.out.println("\n================================================");
        System.out.println("5. FIXED WINDOW ADJACENT SUM");
        System.out.println("================================================");

        int window = 3;
        int windowTarget = 12;

        System.out.println("\nFOR LOOP SOLUTION:");
        fixedWindowForLoop(list, window, windowTarget);

        System.out.println("\nSTREAM SOLUTION:");
        fixedWindowStream(list, window, windowTarget);
    }


    // ========================================================================
    // 1. TWO RANDOM ELEMENTS PAIR SUM
    // ========================================================================
    /*
     * Problem:
     * Find all pairs whose sum equals target.
     *
     * Input:
     * [1,2,3,4,5,6]
     * target = 7
     *
     * Expected Output:
     * [1,6]
     * [2,5]
     * [3,4]
     */

    public static void twoElementPairForLoop(List<Integer> list,
                                             int target) {
//trying to find all pairs of numbers in the list that add up to the target value.
// The outer loop iterates through each element in the list,
// while the inner loop starts from the next element (i + 1) to avoid repeating pairs and to ensure that we only consider unique pairs.
// If the sum of the two elements equals the target, we print the pair as a list.
        //lets collect results and print at the end

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) + list.get(j) == target) {
                    System.out.println(List.of(list.get(i), list.get(j)));
                    result.add(List.of(list.get(i), list.get(j)));
                }
            }
        }
        System.out.println("Result: " + result);
    }


    public static void twoElementPairStream(List<Integer> list,
                                            int target) {
// generate range first from 0 to list size
        //then inner loop is generated by flatMap, which creates a new stream for each element in the outer stream (i).
        // The inner stream generates pairs of indices (i, j) where j starts from i + 1 to avoid repeating pairs and to ensure that we only consider unique pairs.
        // The
        IntStream.range(0, list.size())
                .boxed()
                .flatMap(i ->
                        IntStream.range(i + 1, list.size())
                                .filter(j -> list.get(i) + list.get(j) == target)
                                .mapToObj(j -> List.of(list.get(i), list.get(j))))

                .forEach(System.out::println);
    }


    // ========================================================================
    // 2. THREE RANDOM ELEMENTS TRIPLET SUM
    // ========================================================================
    /*
     * Problem:
     * Find all triplets whose sum equals target.
     *
     * Input:
     * [1,2,3,4,5,6]
     * target = 10
     *
     * Expected Output:
     * [1,3,6]
     * [1,4,5]
     * [2,3,5]
     */

    public static void threeElementTripletForLoop(List<Integer> list,
                                                  int target) {

        for (int i = 0; i < list.size(); i++) {

            for (int j = i + 1; j < list.size(); j++) {

                for (int k = j + 1; k < list.size(); k++) {

                    if (list.get(i)
                            + list.get(j)
                            + list.get(k)
                            == target) {

                        System.out.println(
                                List.of(
                                        list.get(i),
                                        list.get(j),
                                        list.get(k)
                                )
                        );
                    }
                }
            }
        }
    }


    public static void threeElementTripletStream(List<Integer> list,
                                                 int target) {
///mapToObj generates list of list
        IntStream.range(0, list.size()).boxed().flatMap(i ->
//loop 2
                        IntStream.range(i + 1, list.size()).boxed().flatMap(j ->
//loop 3
                                IntStream.range(j + 1, list.size()).filter(k -> list.get(i) + list.get(j) + list.get(k) == target)
                                        .mapToObj(k -> List.of(list.get(i), list.get(j), list.get(k)))))
                .forEach(System.out::println);
    }


    // ========================================================================
    // 3. ADJACENT PAIR SUM
    // ========================================================================
    /*
     * Problem:
     * Find adjacent pairs whose sum equals target.
     *
     * Input:
     * [1,2,3,4,5,6]
     * target = 5
     *
     * Adjacent Pairs:
     * [1,2]
     * [2,3]
     * [3,4]
     * [4,5]
     * [5,6]
     *
     * Expected Output:
     * [2,3]
     */

    public static void adjacentPairForLoop(List<Integer> list,
                                           int target) {

        for (int i = 0; i < list.size() - 1; i++) {

            if (list.get(i) + list.get(i + 1) == target) {

                System.out.println(
                        List.of(list.get(i), list.get(i + 1))
                );
            }
        }
    }


    public static void adjacentPairStream(List<Integer> list,
                                          int target) {

        IntStream.range(0, list.size() - 1)
                .filter(i -> list.get(i) + list.get(i + 1) == target)
                .mapToObj(i -> List.of(list.get(i), list.get(i + 1)))
                .forEach(System.out::println);
    }


    // ========================================================================
    // 4. ADJACENT TRIPLET SUM
    // ========================================================================
    /*
     * Problem:
     * Find adjacent triplets whose sum equals target.
     *
     * Input:
     * [1,2,3,4,5,6]
     * target = 9
     *
     * Adjacent Triplets:
     * [1,2,3]
     * [2,3,4]
     * [3,4,5]
     * [4,5,6]
     *
     * Expected Output:
     * [2,3,4]
     */

    public static void adjacentTripletForLoop(List<Integer> list,
                                              int target) {

        for (int i = 0; i < list.size() - 2; i++) {

            int sum = list.get(i)
                    + list.get(i + 1)
                    + list.get(i + 2);

            if (sum == target) {

                System.out.println(
                        List.of(
                                list.get(i),
                                list.get(i + 1),
                                list.get(i + 2)
                        )
                );
            }
        }
    }


    public static void adjacentTripletStream(List<Integer> list,
                                             int target) {

        IntStream.range(0, list.size() - 2)
                .filter(i -> list.get(i) + list.get(i + 1) + list.get(i + 2) == target)
                .mapToObj(i -> List.of(list.get(i), list.get(i + 1), list.get(i + 2)))
                .forEach(System.out::println);
    }


    // ========================================================================
    // 5. FIXED WINDOW ADJACENT SUM
    // ========================================================================
    /*
     * Problem:
     * Find all adjacent windows of size N whose sum equals target.
     *
     * Input:
     * [1,2,3,4,5,6]
     * window = 3
     * target = 12
     *
     * Windows:
     * [1,2,3] = 6
     * [2,3,4] = 9
     * [3,4,5] = 12
     * [4,5,6] = 15
     *
     * Expected Output:
     * [3,4,5]
     */

    public static void fixedWindowForLoop(List<Integer> list,
                                          int window,
                                          int target) {

        //loop from 0 till size -sliding window size +1 to avoid out of bound error.
        for (int i = 0; i <= list.size() - window; i++) {
            int sum = 0;
            //sliding window sum calculation
            for (int j = i; j < i + window; j++) {
                sum += list.get(j);
            }

            if (sum == target) {
                System.out.println(list.subList(i, i + window));
            }
        }
    }


    public static void fixedWindowStream(List<Integer> list,
                                         int window,
                                         int target) {
        IntStream.range(0, list.size() - window + 1)
                .filter(i -> list.subList(i, i + window).stream().mapToInt(Integer::intValue).sum() == target)
                .mapToObj(i -> list.subList(i, i + window))
                .forEach(System.out::println);
    }
}
