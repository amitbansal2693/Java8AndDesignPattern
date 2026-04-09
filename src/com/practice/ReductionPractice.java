package com.practice;

import java.util.*;
import java.util.stream.*;

public class ReductionPractice {

    /**
     * 1️⃣ Return the sum of all elements
     */
    public static int sum(List<Integer> numbers) {
        // TODO: Use reduce
        return 0;
    }

    /**
     * 2️⃣ Return the product of all elements
     */
    public static int product(List<Integer> numbers) {
        // TODO: Use reduce
        return 0;
    }

    /**
     * 3️⃣ Count how many strings have length > 3
     */
    public static long countLongStrings(List<String> list) {
        // TODO: Use filter + count
        return 0 ;//list.stream().filter(n-> n.length()>3).reduce(0, n-> n+);
    }

    /**
     * 4️⃣ Find the minimum number in the list
     */
    public static Optional<Integer> findMin(List<Integer> numbers) {
        // TODO: Use min()
        return Optional.empty();
    }

    /**
     * 5️⃣ Find the maximum number in the list
     */
    public static Optional<Integer> findMax(List<Integer> numbers) {
        // TODO: Use max()
        return Optional.empty();
    }

    public static void main(String[] args) {
        // Test your solutions here
        List<Integer> emptyList = List.of(1,3,4,6,7);
        double avg= emptyList.stream().
                mapToInt(Integer::intValue).
                average().orElse(0);

        Optional<Integer> result = emptyList.stream()
                .reduce((a, b) -> a + b); // Optional.empty
System.out.println("result "+result);
    }
}
