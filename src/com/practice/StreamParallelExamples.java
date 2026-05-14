package com.practice;

import java.util.List;

public class StreamParallelExamples {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1,2,3,4,5);

        nums.stream()
                .map(n -> n * 2)
                .forEach(System.out::println);

        System.out.println("Paraller Stream:");

        nums.parallelStream()
                .map(n -> n * 2)
                .forEach(System.out::println);
    }
}
