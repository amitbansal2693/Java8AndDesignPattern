package com.practice;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.*;

public class Practice2Java8 {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(10, 15, 8, 49, 25, 98, 98, 32, 15);
        List<String> names = Arrays.asList("AA", "BB", "AA", "CC");
        String input = "Java Articles are Awesome";

        System.out.println("1. Even Numbers:");
        printEvenNumbers(numbers);

        System.out.println("\n2. Numbers starting with 1:");
        printNumbersStartingWithOne(numbers);

        System.out.println("\n3. Duplicate Elements:");
        printDuplicateElements(numbers);

        System.out.println("\n4. First Element:");
        printFirstElement(numbers);

        System.out.println("\n5. Count Elements:");
        printCount(numbers);

        System.out.println("\n6. Max Element:");
        printMax(numbers);

        System.out.println("\n7. First Non-Repeated Character:");
        printFirstNonRepeated(input);

        System.out.println("\n8. First Repeated Character:");
        printFirstRepeated(input);

        System.out.println("\n9. Sort Ascending:");
        printSorted(numbers);

        System.out.println("\n10. Sort Descending:");
        printSortedDesc(numbers);

        System.out.println("\n11. Contains Duplicate:");
        System.out.println(containsDuplicate(numbers));

        System.out.println("\n12. Character Count:");
        printCharCount(input);

        System.out.println("\n13. Names Count:");
        printNamesCount(names);
    }

    // 1. print event numbers.
    public static void printEvenNumbers(List<Integer> list) {
        // TODO print event numbers.
        list.stream().filter(n-> n%2==0).forEach(e-> System.out.println(e));
    }

    // 2
    public static void printNumbersStartingWithOne(List<Integer> list) {
        // TODO
        list.stream().map(e-> e+"").filter(n-> n.startsWith("1")).forEach(System.out::println);

        //or
        list.stream().filter(n-> n.toString().startsWith("1")).forEach(System.out::println);

        //or
        list.stream().filter(n-> String.valueOf(n).startsWith("1")).forEach(System.out::println);

        //or
        list.stream().filter(n-> Character.toChars(n)[0]=='1').forEach(System.out::println);

        //or
        list.stream().map(c-> Character.toChars(c)[0]).
                filter(ch-> ch=='1').forEach(System.out::println);
    //or
        list.stream().filter(c-> c.toString().charAt(0)=='1').forEach(System.out::println);

    }

    // 3
    public static void printDuplicateElements(List<Integer> list) {
        // TODO
        Set<Integer> seen = new HashSet<>();
        list.stream().filter(n -> seen.add(n) == false).forEach(System.out::println);

        //or
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        list.stream().forEach(n -> frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1));
        System.out.println("Duplicate elements:" + frequencyMap);
        System.out.println("Duplicate elements:");
        frequencyMap.entrySet().stream().filter(e -> e.getValue() > 1).forEach(e -> System.out.println(e.getKey()));

        //or
        Map<Integer, Long> map=list.stream().collect(Collectors.groupingBy(n-> n, Collectors.counting()));
        map.entrySet().stream().filter(e-> (Long) e.getValue()>1).forEach(e-> System.out.println(e.getKey()));
        list.stream().
                collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet().stream().
                filter(e -> e.getValue() > 1).
                forEach(e -> System.out.println(e.getKey()));

    }

    // 4
    public static void printFirstElement(List<Integer> list) {
        // TODO
        System.out.println(list.stream().findFirst().get());

        list.stream().limit(1).forEach(System.out::println);

        //what is the difference between findFirst and limit(1) in stream? write here
        //how about skip(-1) what will happen? write here
        list.stream().skip(-1).forEach(System.out::println);

    }

    // 5
    public static void printCount(List<Integer> list) {
        // TODO
        System.out.println(list.stream().count());

        //or
        System.out.println(list.size());

        //or
        System.out.println(list.stream().mapToInt(e->1).sum());

        //or
        System.out.println(list.stream().reduce(0, (a,b)-> a+1));

        //or
        System.out.println(list.stream().collect(Collectors.counting()));

        //or
        System.out.println(list.stream().map(e->1).reduce(0, Integer::sum));

        //or
        System.out.println(list.stream().map(e->1).reduce(0, (a,b)-> a+b));


    }

    // 6
    public static void printMax(List<Integer> list) {
        // TODO
        list.stream().max(Integer::compareTo).ifPresent(System.out::println);

        //or
        list.stream().reduce(Integer::max).ifPresent(System.out::println);

            //or
        list.stream().reduce((a,b)-> a>b? a : b).ifPresent(System.out::println);

        //or
        list.stream().mapToInt(Integer::intValue).max().ifPresent(System.out::println);

        //or
        list.stream().mapToInt(e-> e).max().ifPresent(System.out::println);
    }

    // 7
    public static void printFirstNonRepeated(String input) {
        // TODO
        Character.toChars(input.toString().chars().
                filter(c-> input.indexOf(c)== input.lastIndexOf(c)).findFirst().orElse(-1));
        //or
        input.chars().filter(c-> input.indexOf(c)== input.lastIndexOf(c)).
                findFirst().ifPresent(c-> System.out.println((char) c));



    }

    // 8
    public static void printFirstRepeated(String input) {
        // TODO
    }

    // 9
    public static void printSorted(List<Integer> list) {
        // TODO
    }

    // 10
    public static void printSortedDesc(List<Integer> list) {
        // TODO
    }

    // 11
    public static boolean containsDuplicate(List<Integer> list) {
        // TODO
        return false;
    }

    // 12
    public static void printCharCount(String input) {
        // TODO
    }

    // 13
    public static void printNamesCount(List<String> names) {
        // TODO
    }
}
