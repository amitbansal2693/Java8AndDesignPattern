package com.practice;
import java.util.*;
import java.util.stream.*;

/**
 * ================================================================
 * Java Stream Pairing Examples - Interview Preparation Guide
 * ================================================================
 *
 * This file contains common pairing/interview problems solved using:
 * - Java Streams
 * - IntStream
 * - flatMap
 * - groupingBy
 * - mapToObj
 *
 * Each method contains:
 * - Problem statement
 * - Input
 * - Expected output
 * - Explanation
 * - Stream logic
 *
 * ================================================================
 */
public class ElementsPairing {
    public static void main(String[] args) {

        // ============================================================
        // 1. Pair Elements from Two Lists
        // ============================================================
        List<String> names = List.of("John", "Sam", "David");
        List<Integer> ages = List.of(25, 30, 35);

        System.out.println("\n1. Pair Elements from Two Lists");
        pairTwoLists(names, ages);


        // ============================================================
        // 2. Find Pairs with Given Sum
        // ============================================================
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int target = 7;

        System.out.println("\n2. Find Pairs with Given Sum");
        System.out.println(findPairsWithSum(numbers, target));


        // ============================================================
        // 3. Pair Adjacent Elements
        // ============================================================
        List<Integer> adjacentList = List.of(1, 2, 3, 4, 5);

        System.out.println("\n3. Pair Adjacent Elements");
        System.out.println(pairAdjacentElements(adjacentList));


        // ============================================================
        // 4. Cartesian Product (All Possible Pairs)
        // ============================================================
        List<String> colors = List.of("Red", "Blue");
        List<String> sizes = List.of("S", "M");

        System.out.println("\n4. Cartesian Product");
        System.out.println(cartesianProduct(colors, sizes));


        // ============================================================
        // 5. All Pair Sums
        // ============================================================
        List<Integer> pairSumList = List.of(1, 5, 2, 9, 7);

        System.out.println("\n5. All Pair Sums");
        System.out.println(allPairSums(pairSumList));


        // ============================================================
        // 6. Find Duplicate Elements
        // ============================================================
        List<Integer> duplicateList = List.of(1, 2, 2, 3, 4, 4, 5);

        System.out.println("\n6. Duplicate Elements");
        System.out.println(findDuplicates(duplicateList));


        // ============================================================
        // 7. Map Pairing (Key-Value)
        // ============================================================
        Map<String, String> employeeDepartment = Map.of(
                "John", "IT",
                "Sam", "HR",
                "David", "Finance"
        );

        System.out.println("\n7. Map Pairing");
        printMapPairs(employeeDepartment);


        // ============================================================
        // 8. Group Strings by Length
        // ============================================================
        List<String> groupNames = List.of("John", "Sam", "David", "Tom");

        System.out.println("\n8. Group By Length");
        System.out.println(groupByLength(groupNames));


        // ============================================================
        // 9. Pair Consecutive Characters
        // ============================================================
        String text = "ABCDE";

        System.out.println("\n9. Pair Consecutive Characters");
        System.out.println(pairConsecutiveCharacters(text));


        // ============================================================
        // 10. Pair Elements with Index
        // ============================================================
        List<String> indexedNames = List.of("John", "Sam", "David");

        System.out.println("\n10. Pair Elements with Index");
        pairWithIndex(indexedNames);
    }


    // ================================================================
    // 1. Pair Elements from Two Lists
    // ================================================================
    /*
     * Problem:
     * Pair elements from two lists using same index.
     *
     * Input:
     * names = [John, Sam, David]
     * ages  = [25, 30, 35]
     *
     * Output:
     * John -> 25
     * Sam -> 30
     * David -> 35
     *
     * Key Concept:
     * IntStream.range() gives indexes.
     */
    public static void pairTwoLists(List<String> names, List<Integer> ages) {

        IntStream.range(0, names.size())
                .mapToObj(i -> names.get(i) + " -> " + ages.get(i))
                .forEach(System.out::println);
    }


    // ================================================================
    // 2. Find Pairs with Given Sum | Sorted array. use pointer
    // ================================================================
    /*
     * Problem:
     * Find all pairs whose sum equals target.
     *
     * Input:
     * [1,2,3,4,5,6]
     * target = 7
     *
     * Output:
     * [1-6, 2-5, 3-4]
     *
     * Key Concepts:
     * - Nested looping using flatMap
     * - Avoid duplicate pairs using j = i + 1
     */
    public static List<String> findPairsWithSum(List<Integer> list, int target) {

        return IntStream.range(0, list.size())

                .boxed()

                .flatMap(i ->
                        IntStream.range(i + 1, list.size())

                                .filter(j ->
                                        list.get(i) + list.get(j) == target
                                )

                                .mapToObj(j ->
                                        list.get(i) + "-" + list.get(j)
                                )
                )

                .collect(Collectors.toList());
    }


    // ================================================================
    // 3. Pair Adjacent Elements
    // ================================================================
    /*
     * Problem:
     * Pair neighboring elements.
     *
     * Input:
     * [1,2,3,4,5]
     *
     * Output:
     * [1-2, 2-3, 3-4, 4-5]
     */
    public static List<String> pairAdjacentElements(List<Integer> list) {

        return IntStream.range(0, list.size() - 1)

                .mapToObj(i ->
                        list.get(i) + "-" + list.get(i + 1)
                )

                .collect(Collectors.toList());
    }


    // ================================================================
    // 4. Cartesian Product
    // ================================================================
    /*
     * Problem:
     * Generate all possible combinations.
     *
     * Input:
     * colors = [Red, Blue]
     * sizes = [S, M]
     *
     * Output:
     * [Red-S, Red-M, Blue-S, Blue-M]
     *
     * Key Concept:
     * flatMap() creates nested iteration.
     */
    public static List<String> cartesianProduct(List<String> colors,
                                                List<String> sizes) {

        return colors.stream()

                .flatMap(color ->
                        sizes.stream()
                                .map(size -> color + "-" + size)
                )

                .collect(Collectors.toList());
    }


    // ================================================================
    // 5. All Pair Sums
    // ================================================================
    /*
     * Problem:
     * Generate sum of all unique pairs.
     *
     * Input:
     * [1,5,2,9,7]
     *
     * Example Output:
     * [6,3,10,8,7,14,12,11,9,16]
     */
    public static List<Integer> allPairSums(List<Integer> list) {

        return IntStream.range(0, list.size())

                .boxed()

                .flatMap(i ->
                        IntStream.range(i + 1, list.size())

                                .mapToObj(j ->
                                        list.get(i) + list.get(j)
                                )
                )

                .collect(Collectors.toList());
    }


    // ================================================================
    // 6. Find Duplicate Elements
    // ================================================================
    /*
     * Problem:
     * Find duplicate values.
     *
     * Input:
     * [1,2,2,3,4,4,5]
     *
     * Output:
     * [2,4]
     *
     * Key Concept:
     * Set.add() returns false for duplicates.
     */
    public static Set<Integer> findDuplicates(List<Integer> list) {

        Set<Integer> seen = new HashSet<>();

        return list.stream()

                .filter(n -> !seen.add(n))

                .collect(Collectors.toSet());
    }


    // ================================================================
    // 7. Map Pairing
    // ================================================================
    /*
     * Problem:
     * Print key-value pairs from map.
     */
    public static void printMapPairs(Map<String, String> map) {

        map.entrySet()
                .stream()
                .map(entry ->
                        entry.getKey() + " -> " + entry.getValue()
                )
                .forEach(System.out::println);
    }


    // ================================================================
    // 8. Group Strings by Length
    // ================================================================
    /*
     * Problem:
     * Group strings according to length.
     *
     * Input:
     * [John, Sam, David, Tom]
     *
     * Output:
     * {
     *   3=[Sam, Tom],
     *   4=[John],
     *   5=[David]
     * }
     */
    public static Map<Integer, List<String>> groupByLength(List<String> list) {

        return list.stream()

                .collect(Collectors.groupingBy(String::length));
    }


    // ================================================================
    // 9. Pair Consecutive Characters
    // ================================================================
    /*
     * Problem:
     * Create adjacent character pairs.
     *
     * Input:
     * ABCDE
     *
     * Output:
     * [AB, BC, CD, DE]
     */
    public static List<String> pairConsecutiveCharacters(String str) {

        return IntStream.range(0, str.length() - 1)

                .mapToObj(i ->
                        "" + str.charAt(i) + str.charAt(i + 1)
                )

                .collect(Collectors.toList());
    }


    // ================================================================
    // 10. Pair Elements with Index
    // ================================================================
    /*
     * Problem:
     * Print index and element together.
     *
     * Output:
     * 0 -> John
     * 1 -> Sam
     * 2 -> David
     */
    public static void pairWithIndex(List<String> list) {

        IntStream.range(0, list.size())

                .mapToObj(i ->
                        i + " -> " + list.get(i)
                )

                .forEach(System.out::println);
    }
}
