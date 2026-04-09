package com.practice;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;

import java.util.*;
import java.util.stream.*;

public class ArrayTest {

    /**
     * 1️⃣ Convert the given int[] array to List<Integer>
     */
    public static List<Integer> arrayToList(int[] numbers) {
        return IntStream.of(numbers).boxed().collect(Collectors.toList());
    }

    /**
     * 2️⃣ Convert array to List<Integer>, include only EVEN numbers
     */
    public static List<Integer> arrayToEvenList(int[] numbers) {
        return IntStream.of(numbers)
                .filter(i -> i % 2 == 0)
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * 3️⃣ Remove duplicates and return List<Integer>
     */
    public static List<Integer> distinctElements(int[] numbers) {
        return IntStream.of(numbers)
                .distinct()
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * 4️⃣ Square each element and return as List<Integer>
     */
    public static List<Integer> squareElements(int[] numbers) {
        return IntStream.of(numbers)
                .map(n -> n * n)
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * 5️⃣ Sort in ASCENDING order
     */
    public static List<Integer> sortAscending(int[] numbers) {
        return IntStream.of(numbers)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * 6️⃣ Convert array to list, then back to int[]
     */
    public static int[] listBackToArray(int[] numbers) {
        List<Integer> list = IntStream.of(numbers)
                .boxed()
                .collect(Collectors.toList());

        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    /**
     * 7️⃣ Frequency Map: number → count
     */
    public static Map<Integer, Long> frequencyMap(int[] numbers) {
        return IntStream.of(numbers)
                .boxed()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
    }

    /**
     * 8️⃣ Numbers > 4 in DESC order
     */
    public static List<Integer> greaterThanFourDesc(int[] numbers) {
        return IntStream.of(numbers)
                .boxed()
                .filter(i -> i > 4)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    // 🔸 Main method to test all methods
    public static void main(String[] args) {
        int[] numbers = {3, 5, 2, 5, 8, 3, 10, 2};
        System.out.println("1. arrayToList: " + arrayToList(numbers));
        System.out.println("2. arrayToEvenList: " + arrayToEvenList(numbers));
        System.out.println("3. distinctElements: " + distinctElements(numbers));
        System.out.println("4. squareElements: " + squareElements(numbers));
        System.out.println("5. sortAscending: " + sortAscending(numbers));
        System.out.println("6. listBackToArray: " + Arrays.toString(listBackToArray(numbers)));
        System.out.println("7. frequencyMap: " + frequencyMap(numbers));
        System.out.println("8. greaterThanFourDesc: " + greaterThanFourDesc(numbers));
        int[] arr = {1, 2, 3};
        List<int[]> list = Arrays.asList(arr); // This creates a List with ONE element
        System.out.println(list.size() + " list: "+list); // Output: 1

        List list1=Arrays.asList(1, 2, 3);
        System.out.println(list1.size() + " list1: "+list1);

    }

}
