package com.practice;

import java.util.*;
import java.util.stream.Collectors;

public class PartitioningPractice {

    /**
     * 1️⃣ Partition a list of integers into even and odd numbers.
     * Input: [1, 2, 3, 4, 5, 6]
     * Output: {true=[2, 4, 6], false=[1, 3, 5]}
     */
    public static Map<Boolean, List<Integer>> partitionEvenOdd(List<Integer> numbers) {
        Map<Boolean, List<Integer>> list;
        numbers.stream().collect(Collectors.partitioningBy(n->n%2==0));
        list=numbers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println(list);
        numbers.parallelStream();

        //how i can use groupingBy to do partitioning. write here
        Map list2 = numbers.stream().collect(Collectors.groupingBy(n -> n % 2 == 0? "EVEN" : "ODD"));
        System.out.println(list2);
        return numbers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
    }

    /**
     * 2️⃣ Partition a list of strings by length > 3
     * Input: ["hi", "hello", "sun", "world"]
     * Output: {true=[hello, world], false=[hi, sun]}
     */
    public static Map<Boolean, List<String>> partitionByLength(List<String> strings) {
        return strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length() > 3));
    }

    /**
     * 3️⃣ Partition a list of people into adults and minors (age >= 18)
     */
    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public String toString() {
            return name + "(" + age + ")";
        }
    }

    public static Map<Boolean, List<Person>> partitionAdults(List<Person> people) {
        return people.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() >= 18));
    }

    /**
     * 4️⃣ Partition and count elements in each group
     * Input: [1, 2, 3, 4, 5]
     * Output: {true=2, false=3}
     */
    public static Map<Boolean, Long> countEvenOdd(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.partitioningBy(
                        n -> n % 2 == 0,
                        Collectors.counting()
                ));
    }

    public static void main(String[] args) {
        System.out.println("1️⃣ Partition Even/Odd: " + partitionEvenOdd(List.of(1, 2, 3, 4, 5, 6)));
        System.out.println("2️⃣ Partition by Length > 3: " + partitionByLength(List.of("hi", "hello", "sun", "world")));

        List<Person> people = List.of(
                new Person("Alice", 17),
                new Person("Bob", 22),
                new Person("Charlie", 15),
                new Person("David", 30)
        );
        System.out.println("3️⃣ Partition Adults/Minors: " + partitionAdults(people));

        System.out.println("4️⃣ Count Even/Odd: " + countEvenOdd(List.of(1, 2, 3, 4, 5)));
    }
}
