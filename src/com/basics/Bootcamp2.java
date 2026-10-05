package com.basics;

public class Bootcamp2 {
    public static void main(String[] args) {
        //Hashmap example
        java.util.HashMap<String, Integer> map = new java.util.HashMap<>();
        map.put("Alice", 30);
        map.put("Bob", 25);
        map.put("Charlie", 35);
        System.out.println("HashMap: " + map);

        map.put("Alice", 31); // Update Alice's age
        map.put("David", 28);
        System.out.println("HashMap: " + map);

    }
}
