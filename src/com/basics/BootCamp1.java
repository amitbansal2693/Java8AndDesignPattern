package com.basics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class BootCamp1 {


    public static void main(String[] args) {

        //give task
        ArrayList<Integer> list = new ArrayList();

        list.add(20);
        list.add(10);
        list.add(70);
        System.out.println(list);


        list.add(0, 35);
        System.out.println(list);

        list.remove(1);
        System.out.println(list);//35, 10, 70

        list.stream().sorted().forEach(System.out::println);

        //fix below code to print the list in reverse order after multiplying each element by 2

        list.stream().map(v -> v * 2).sorted(Comparator.reverseOrder())
                .forEach(e -> System.out.println(e));


        //sum of all elements in the list
        Integer a = 10;
        int b = 20;
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum: " + sum);

        System.out.println("Before list: " + list);
        Optional<Integer> first = list.stream().filter(e -> e % 2 == 1).findFirst();


        if (first.isPresent()) {
            System.out.println("First odd number: " + first.get());
        }

        //
        List<Integer> list2 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        list2.stream().skip(2).forEach(e -> System.out.println(e));


    }
}
