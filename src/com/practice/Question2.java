package com.practice;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Question2 {

    public static void main(String[] args) {
        int[] arr = {9,3,9,3,9,7,9};
        Optional<Integer> val= Arrays.stream(arr).boxed().collect(Collectors.groupingBy(n->n, Collectors.counting())).entrySet().stream().filter(e-> e.getValue()%2==1).map(Map.Entry::getKey).findFirst();

        System.out.println("odd occurance: " + val);

        System.out.println("findOddOccurrence "+findOddOccurrence(arr));
    }

    /**
     * XOr operation:
     *   0101
     * ^ 0011
     * ------
     *   0110 → 6
     *
     *   When flags are smae: then result is 0, otherwise 1.
     *   Same thing twice = OFF (cancelled)
     * Different things = ON (kept)
     *
     * @param A
     * @return
     */
    public static int findOddOccurrence(int[] A) {
        int result = 0;
        for (int x : A) {
            result ^= x;
        }
        return result;
    }
}
